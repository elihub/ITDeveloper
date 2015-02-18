package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Roles;
import com.aeromexico.tideveloper.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author mperal01
 */
public class RolesDAOImpl implements RolesDAO {
    
    private Session currentSession() {
        return HibernateUtil.getsession();
    }
    
    @Override
    public List<Roles> findAll() {
        return currentSession().createQuery("FROM Roles").list();
    }
    
}
