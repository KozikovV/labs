package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public class ProfessionHibCritDao implements ProfessionDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Car> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Profession.class).list();
    }

    @Override
    public Profession find(Long multiId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional
    public Long save(Profession profession) {
        return (Long) sessionFactory.getCurrentSession().save(profession);
    }

    @Override
    public void delete(Profession profession) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
