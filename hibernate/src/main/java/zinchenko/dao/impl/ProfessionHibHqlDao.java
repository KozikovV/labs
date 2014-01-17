package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public class ProfessionHibHqlDao implements ProfessionDao{

    protected SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Profession find(Long multiId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long save(Profession profession) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
