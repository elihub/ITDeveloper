package com.aeromexico.tideveloper.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.aeromexico.tideveloper.models.Usuarios;
import com.aeromexico.cotizador.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsuariosDAOImpl implements UsuariosDAO{

	private Session currentSession() {
            return HibernateUtil.getsession();
	}

    @Override
    public String validateUsername(String username) {
        String validUser= null;
        validUser = (String) currentSession().createSQLQuery("SELECT usuario FROM tabUsuarios WHERE usuario = ?")
                   .setParameter(0, username).uniqueResult();
        return validUser;
    }
	
	@Override
	public Usuarios findByID(Long idUsuario) {
            return (Usuarios) currentSession().get(Usuarios.class, idUsuario);
	}
        
    @Override
    public List<Usuarios> findAll() {
        return currentSession().createQuery("FROM Usuarios").list();
    }

    @Override
    public Long saveUsuario(Usuarios u) {
        Long id=null;//=(Long) currentSession().save(u);
    //    return id;
        SessionFactory sf=null;
        Session s=null;
        Transaction tx=null;
        try {
            sf=HibernateUtil.getHQLSessionFactory();
            s=sf.openSession();
            tx=s.getTransaction();
            tx.begin();
            id = (Long) s.save(u);
            tx.commit();
        } catch (Exception e) {
            //e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }        
        }
        finally{
            if(s!=null){
                s.close();
            }                
            if(sf!=null){
                sf.close();
            }
        }
        return id;
    }
	
    @Override
    public void updateUsuarios(Usuarios u) {
        SessionFactory sf=null;
        Session s=null;
        Transaction tx=null;
        try {
            sf=HibernateUtil.getHQLSessionFactory();
            s=sf.openSession();
            tx=s.getTransaction();
            tx.begin();
            s.update(u);
            tx.commit();
        } catch (Exception e) {
            //e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }        
        }
        finally{
            if(s!=null){
                s.close();
            }                
            if(sf!=null){
                sf.close();
            }
        }
    }

    @Override
    public void deleteUsuario(Usuarios u) {
        currentSession().createSQLQuery("DELETE FROM tabAsignacionRoles where idUsuario="+u.getIdUsuario()).executeUpdate();
        currentSession().createSQLQuery("DELETE FROM tabUSuarios where idUsuario="+u.getIdUsuario()).executeUpdate();    
    }

	@Override
	public void savePermisos(Long idUsuario, String idRol) {
            currentSession().createSQLQuery("INSERT INTO usuarios_roles(idUsuario,idRol) values(?,?)")
                    .setParameter(0, idUsuario)
                    .setParameter(1, idRol).executeUpdate();
	}

	@Override
	public void deletePermisos(Long idUsuario, String idRol) {
            currentSession().createSQLQuery("DELETE FROM usuarios_roles WHERE idUsuario = ? AND idRol=?")
            .setParameter(0, idUsuario)
            .setParameter(1, idRol).executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> findPermisos(Long idUsuario) {
            List<String> permi = currentSession().createSQLQuery("SELECT cast(idRol as varchar) idRol FROM usuarios_roles WHERE idUsuario = ? ORDER BY idRol")
                            .setParameter(0, idUsuario).list();
            return permi;
	}

	@Override
	public Usuarios find(Usuarios u) {
            Example examUsuario = Example.create(u);
            return (Usuarios) currentSession().createCriteria(Usuarios.class).add(examUsuario)
                            .add(Restrictions.eq("usuario",u.getUsuario())).add(Restrictions.eq("contra", u.getContra())).uniqueResult();
	}

}
