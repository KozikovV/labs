package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.CarDao;
import zinchenko.domain.Car;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class CarDaoAbstractTest {

    @Autowired
    SessionFactory sessionFactory;

    CarDao carDao;

    protected abstract CarDao getCarDao();

    @Before
    public void before(){
        carDao = getCarDao();
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @After
    public void after(){
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void testFindAll() {
        assertEquals(3, carDao.findAll().size());
    }

    @Test
    public void testFind(){
        Long id = 50L;

        Car result = carDao.find(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSave(){
//        Person owner = new Person();
//        owner.setId(new MultiId("firstName0", "lastName0"));
        Car car = new Car();
        car.setModel("modelNew");
//        car.setOwner(owner);

        carDao.save(car);

        assertEquals(4, carDao.findAll().size());
    }

    @Test
    public void testDelete(){
        Car car = new Car();
        car.setId(50L);

        carDao.delete(car);
        assertEquals(2, carDao.findAll().size());
    }

}
