//<start id="java_contextualHibernateDao"/> 
package com.habuma.spitter.persistence.hibernate;

import com.habuma.spitter.domain.hibernate.Spitter;
import com.habuma.spitter.domain.hibernate.Spittle;
import com.habuma.spitter.persistence.hibernate.SpitterDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class HibernateSpitterDao implements SpitterDao {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSpitterDao(SessionFactory sessionFactory) {//<co id="co_injectSessionFactory"/>
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();//<co id="co_getCurrentSession"/>
    }

    @Transactional
    public void addSpitter(Spitter spitter) {
        System.out.println("addSpitter(Spitter spitter)");
        currentSession().save(spitter);
    }

//    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
//    @Transactional
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public Spitter getSpitterById(long id) {
        System.out.println("in getSpitterById(long id)");
        return (Spitter) currentSession().get(Spitter.class, id);//<co id="co_useSession"/>
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void saveSpitter(Spitter spitter) {
        currentSession().update(spitter);//<co id="co_useSession"/>
    }
    //<end id="java_contextualHibernateDao"/>

    public List<Spittle> getRecentSpittle() {
//          return currentSession().l(Spittle.class); // this isn't right...just a placeholder for now
        return null;
    }

    public void saveSpittle(Spittle spittle) {
        currentSession().save(spittle);
    }

    public List<Spittle> getSpittlesForSpitter(
            Spitter spitter) {
        // TODO Auto-generated method stub
        return null;
    }

    public Spitter getSpitterByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    public void deleteSpittle(long id) {
        currentSession().delete(getSpittleById(id));
    }

    public Spittle getSpittleById(long id) {
        return (Spittle) currentSession().get(Spittle.class, id);
    }

    public long queryForIdentity() {
        return 0;
    }

    public List<Spitter> findAllSpitters() {
        // TODO Auto-generated method stub
        return null;
    }
}