package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public class ProfessionHibHqlDao implements ProfessionDao{

    protected SessionFactory sessionFactory;

    @Override
    public List<Profession> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Profession as profession")
                .list();
    }

    @Override
    public Profession find(Long id) {
        return (Profession) sessionFactory.getCurrentSession()
                .createQuery("from Profession as prof where prof.id = :id")
                .setLong("id", id)
                .uniqueResult();
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
