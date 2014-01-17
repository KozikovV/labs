package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.CarDao;
import zinchenko.domain.Car;

import java.util.List;

public class CarHibernateCritDao implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Car find(Long multiId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long save(Car person) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Car person) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
