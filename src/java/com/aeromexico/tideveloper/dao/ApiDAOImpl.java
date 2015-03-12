package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Api;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        return currentSession().createQuery("FROM Api").list();
    }

    @Override
    public int save(Api s) {        
        int id=(int)currentSession().save(s);
        return id;        
    }

    @Override
    public void update(Api s) {
        //SessionFactory sf=null;
        Session ses = null;
        Transaction tx = null;
        try {
            //sf=HibernateUtil.getHQLSessionFactory();
            ses = currentSession();
            tx = ses.getTransaction();
            tx.begin();
            ses.update(s);
            tx.commit();
        } catch (Exception e) {
            //e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (ses != null) {
                ses.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public void delete(Api s) {
        currentSession().delete(s);
    }
}
