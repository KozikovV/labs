package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
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

    @Override
    public Person find(MultiId multiId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MultiId save(Person person) {
        return new MultiId();
    }

    @Override
    public void delete(Person person) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}