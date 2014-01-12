package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.PersonDao;
import zinchenko.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public class PersonHibernateHqlDao implements PersonDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Person> findAll() {
        return new ArrayList<Person>();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
