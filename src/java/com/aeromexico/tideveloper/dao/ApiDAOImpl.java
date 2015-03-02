/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Api;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Elida Carrillo
 */
@Transactional
@Repository
public class ApiDAOImpl implements ApiDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public ApiDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }    
    
    
     private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public Api findById(int id) {
        return (Api) currentSession().get(Api.class,id);
    }

    @Override
    public List<Api> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(Api s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Api s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Api s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}