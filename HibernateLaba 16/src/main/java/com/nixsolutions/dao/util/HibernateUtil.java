package com.nixsolutions.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for getting object of SessionFactory.
 * 
 * @author zinchenko
 * 
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Method for getting object of SessionFactory. The method return exist
     * object of class SessionFactory, otherwise it creates object of class
     * SessionFactory and returns it.
     * 
     * @return object of class SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
