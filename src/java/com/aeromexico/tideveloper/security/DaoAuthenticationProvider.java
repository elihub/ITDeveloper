/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.security;

import com.aeromexico.tideveloper.dao.UsuariosDAO;
import com.aeromexico.tideveloper.dao.UsuariosDAOImpl;
import com.aeromexico.tideveloper.models.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Elida Carrillo
 */
public class DaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider implements Serializable {

    private UsuariosDAO userDao;

    public UsuariosDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UsuariosDAO userDao) {
        this.userDao = userDao;
    }
    

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected UserDetails retrieveUser(String string, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        Collection<GrantedAuthority> authorities2 = new ArrayList<GrantedAuthority>();
        User user;        
        
        System.out.println("Obtencion de usuario");
        Usuarios usuario=new Usuarios();
        System.out.println("USER:::" + upat.getName());

        usuario.setUsuario(upat.getName());
        usuario.setContra(upat.getCredentials().toString());

        Usuarios activo = new Usuarios();
        activo = userDao.find(usuario);
        
        /*if (upat.getName().equals("Aeromexico") && upat.getCredentials().toString().equals("Aeromexico01")) {
            GrantedAuthorityImpl Role = new GrantedAuthorityImpl("ROLE_USER");
            authorities2.add(Role);
            user = new User(upat.getName(), upat.getCredentials().toString(), true, true, true, true, authorities2);
        } else {
            GrantedAuthorityImpl Role = new GrantedAuthorityImpl("ROLE_INVALID");
            authorities2.add(Role);
            user = new User("x", "x", false, false, false, false, authorities2);

        }*/
        if (activo != null) {
            GrantedAuthority[] authorities = new GrantedAuthority[activo.getListaRoles().size()];
            for (int i = 0; i < activo.getListaRoles().size(); i++) {

                authorities[i] = new GrantedAuthorityImpl(activo.getListaRoles().get(i).getNombre());
            }
            CustomUser cu = new CustomUser(activo.getIdUsuario().intValue(), activo.getUsuario(), activo.getContra(), activo.getNombre() + " " + activo.getApellidoPaterno(),
                    authorities, 1);
            //sessionUser = activo;
            activo = null;
            return cu;
        } else {
            GrantedAuthorityImpl noRole = new GrantedAuthorityImpl("ROLE_INVALID");
            authorities2.add(noRole);
            user = new User("x", "x", false, false, false, false, authorities2);
            System.out.println("USUARIO INVALIDO.........................");
            
            return user;
        }

        // TODO Auto-generated method stubs
    }

}
