package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Servicios;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mperal01
 */
@Transactional
@Repository
public class ServiciosDAOImpl implements ServiciosDAO {
    private SessionFactory sessionFactory;
    
    @Autowired
    public ServiciosDAOImpl(SessionFactory sessionFactory){
     this.sessionFactory=sessionFactory;
    }
    
    private Session currentSession() {
        return sessionFactory.openSession();
    }
    
    @Override
    public Servicios findById(int id) {
        return (Servicios) currentSession().get(Servicios.class,id);
    }

    @Override
    public List<Servicios> findAll() {
        return currentSession().createQuery("FROM Servicios").list();
    }

    @Override
    public int save(Servicios s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Servicios s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Servicios s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
