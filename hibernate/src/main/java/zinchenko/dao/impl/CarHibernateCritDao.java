package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.CarDao;
import zinchenko.domain.Car;

import java.util.List;

public class CarHibernateCritDao implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> findAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Car.class).list();
    }

    @Override
    public Car find(Long id) {
        return (Car) sessionFactory.getCurrentSession()
                .get(Car.class, id);
    }

    @Override
    public List<Car> find(List<String> models) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Car.class)
                .add(Restrictions.in("model", models)).list();
    }

    @Override
    public List<Car> find(Integer from, Integer quantity) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Car.class)
                .setFirstResult(from).setMaxResults(quantity).list();
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
