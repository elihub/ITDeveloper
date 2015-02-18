/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.security;

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

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected UserDetails retrieveUser(String string, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        Collection<GrantedAuthority> authorities2 = new ArrayList<GrantedAuthority>();
        User user;
        if (upat.getName().equals("Aeromexico") && upat.getCredentials().toString().equals("Aeromexico01")) {
            GrantedAuthorityImpl Role = new GrantedAuthorityImpl("ROLE_SUPERVISOR");
            authorities2.add(Role);
            user = new User(upat.getName(), upat.getCredentials().toString(), true, true, true, true, authorities2);
        } else {
            GrantedAuthorityImpl Role = new GrantedAuthorityImpl("ROLE_INVALID");
            authorities2.add(Role);
            user = new User("x", "x", false, false, false, false, authorities2);

        }

        // TODO Auto-generated method stubs
        return user;
    }

}
