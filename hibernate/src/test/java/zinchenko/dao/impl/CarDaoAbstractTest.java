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

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(7, carDao.findAll().size());
    }

    @Test
    public void testFind(){
        Long id = 50L;

        Car result = carDao.find(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testFindWhenMultipleModels(){
        List<String> models = new ArrayList<String>();
        models.add("model_50");
        models.add("model_52");

        List<Car> result = carDao.find(models);
        assertEquals(2, result.size());
        assertEquals((Object) 50L, result.get(0).getId());
        assertEquals((Object) 52L, result.get(1).getId());
    }

    @Test
    public void testSave(){
        Car car = new Car();
        car.setModel("modelNew");

        carDao.save(car);

        assertEquals(8, carDao.findAll().size());
    }

    @Test
    public void testDelete(){
        Car car = new Car();
        car.setId(50L);

        carDao.delete(car);
        assertEquals(6, carDao.findAll().size());
    }

    @Test
    public void testFindWithPagination(){
        Integer from = 2;
        Integer quantity = 3;

        List<Car> result = carDao.find(from, quantity);
        assertEquals((Object) quantity, result.size());
        assertEquals((Object) 52L, result.get(0).getId());
        assertEquals((Object) 55L, result.get(1).getId());
        assertEquals((Object) 56L, result.get(2).getId());
    }

}
