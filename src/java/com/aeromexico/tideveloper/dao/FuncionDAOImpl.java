/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Funcion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Elida Carrillo
 */
public class FuncionDAOImpl implements FuncionDAO{
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public FuncionDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    } 
    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Funcion findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcion> findAll() {
       return currentSession().createQuery("from Funcion").list();
    }

    @Override
    public int save(Funcion s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Funcion s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Funcion s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
