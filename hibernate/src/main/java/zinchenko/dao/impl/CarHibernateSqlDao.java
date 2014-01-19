package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.CarDao;
import zinchenko.domain.Car;

import java.util.List;

/**
 * User: zinchenko
 * Date: 19.01.14
 */
public class CarHibernateSqlDao implements CarDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Car find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Car> find(List<String> models) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Car> find(Integer from, Integer quantity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long save(Car car) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Car car) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
