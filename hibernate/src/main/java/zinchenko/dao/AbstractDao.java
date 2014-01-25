package zinchenko.dao;

import org.hibernate.SessionFactory;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public class AbstractDao {

    protected SessionFactory sessionFactory;


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
