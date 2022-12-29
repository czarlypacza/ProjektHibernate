package com.projekthibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DBAcess {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projekt");
    private static EntityManager em = entityManagerFactory.createEntityManager();

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return em;
    }
    public static void closeconn(){
        entityManagerFactory.close();
    }
}
