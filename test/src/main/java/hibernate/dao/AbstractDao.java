package hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: zinchenko
 * Date: 9/30/13
 */
public class AbstractDao {

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDao() {
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
