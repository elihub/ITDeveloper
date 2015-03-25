package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Soap;
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
public class SoapDAOImpl implements SoapDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public SoapDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }    
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
     
    @Override
    public Soap findById(int id) {
        return (Soap) currentSession().get(Soap.class, id);
    }

    @Override
    public List<Soap> findAll() {
        return currentSession().createQuery("FROM Soap").list();
    }

    @Override
    public int save(Soap s) {
        int id=(int)currentSession().save(s);
        return id; 
    }

    @Override
    public void update(Soap s) {
        currentSession().update(s);
    }

    @Override
    public void delete(Soap s) {
        currentSession().delete(s);
    }
    
}
