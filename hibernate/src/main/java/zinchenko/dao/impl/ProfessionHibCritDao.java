package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public class ProfessionHibCritDao implements ProfessionDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Profession.class).list();
    }

    @Override
    public Profession find(Long id) {
        return (Profession) sessionFactory.getCurrentSession().get(Profession.class, id);
    }

    @Override
    public Long save(Profession profession) {
        return (Long) sessionFactory.getCurrentSession().save(profession);
    }

    @Override
    public void delete(Profession profession) {
        sessionFactory.getCurrentSession().delete(profession);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
