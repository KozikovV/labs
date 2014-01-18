package zinchenko.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

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
       return sessionFactory.getCurrentSession()
               .createCriteria(Person.class).list();
    }

    @Override
    public Person find(MultiId multiId) {
        return (Person) sessionFactory.getCurrentSession()
                .load(Person.class, multiId);
    }

    @Override
    @Transactional
    public MultiId save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        return (MultiId) session.save(person);
    }

    @Override
    public void delete(Person person) {
        sessionFactory.getCurrentSession().delete(person);
    }

    @Override
    public List<Person> findByProfession(Profession profession) {
        return sessionFactory.getCurrentSession().createCriteria(Person.class)
                .add(Restrictions.eq("profession", profession)).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
