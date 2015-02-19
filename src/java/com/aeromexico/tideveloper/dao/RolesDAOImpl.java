package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Roles;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mperal01
 */
@Repository
public class RolesDAOImpl implements RolesDAO {
    private SessionFactory sessionFactory;
    
    @Autowired
    public RolesDAOImpl(SessionFactory sessionFactory){
    this.sessionFactory=sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List<Roles> findAll() {
        return currentSession().createQuery("FROM Roles").list();
    }
    
}
