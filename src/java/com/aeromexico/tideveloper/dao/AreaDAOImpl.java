/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Area;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Elida Carrillo
 */
public class AreaDAOImpl implements AreaDAO{
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public AreaDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public Area findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Area> findAll() {
        return currentSession().createQuery("from Area").list();
    }

    @Override
    public int save(Area s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Area s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Area s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
