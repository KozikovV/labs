package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;

import java.util.List;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public class PersonHibernateCritDao implements PersonDao {

    public PersonHibernateCritDao() {

    }

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Person> findAll() {
       return sessionFactory.getCurrentSession().createCriteria(Person.class).list();
    }

    @Override
    public Person find(MultiId multiId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(Person person) {
        //To change body of implemented methods use File | Settings | File Templates.
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
