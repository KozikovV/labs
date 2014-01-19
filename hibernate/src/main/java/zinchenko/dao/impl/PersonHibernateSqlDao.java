package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

import java.util.Date;
import java.util.List;

/**
 * User: zinchenko
 * Date: 19.01.14
 */
public class PersonHibernateSqlDao implements PersonDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Person> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Person find(MultiId multiId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> find(List<MultiId> multiIds) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MultiId save(Person person) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Person person) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> findByProfession(Profession profession) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> findYoungerThen(Date birthDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> findYoungerOrEqualThen(Date birthDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> findOlderThen(Date birthDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> findOlderOrEqualThen(Date birthDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Person> findByProfessionName(String professionName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
