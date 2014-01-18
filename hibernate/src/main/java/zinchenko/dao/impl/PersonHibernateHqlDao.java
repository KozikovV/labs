package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

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
        return sessionFactory.getCurrentSession()
                .createQuery("from Person").list();
    }

    @Override
    public Person find(MultiId multiId) {
        return (Person) sessionFactory.getCurrentSession()
                .createQuery("from Person as person where person.id = :id")
                .setParameter("id", multiId).uniqueResult();
    }

    @Override
    public MultiId save(Person person) {
        return (MultiId) sessionFactory.getCurrentSession().save(person);
    }

    @Override
    public void delete(Person person) {
        sessionFactory.getCurrentSession().delete(person);
    }

    @Override
    public List<Person> findByProfession(Profession profession) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as person " +
                        "where person.profession = :profession")
                .setParameter("profession", profession)
                .list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
