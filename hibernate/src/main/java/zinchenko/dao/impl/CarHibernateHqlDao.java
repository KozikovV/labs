package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.CarDao;
import zinchenko.domain.Car;

import java.util.List;

public class CarHibernateHqlDao implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Car").list();
    }

    @Override
    public Car find(Long id) {
        return (Car) sessionFactory.getCurrentSession()
                .createQuery("from Car as car where car.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public List<Car> find(List<String> models) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Car as c where c.model in (:models)")
                .setParameterList("models", models)
                .list();
    }

    @Override
    public List<Car> find(Integer from, Integer quantity) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Car c")
                .setFirstResult(from)
                .setMaxResults(quantity)
                .list();
    }

    @Override
    public Long save(Car car) {
        return (Long) sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public void merge(Car car) {

    }

    @Override
    public void delete(Car car) {
        sessionFactory.getCurrentSession().delete(car);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
