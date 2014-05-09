package zinchenko;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.domain.Car;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 06.01.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/hibernateTest.xml"})
@DatabaseSetup("classpath:/daoTestDataset.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class TestTest {

    @Autowired
    SessionFactory sessionFactory;

    @Before
    public void before(){
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @After
    public void after(){
        if(sessionFactory.getCurrentSession().getTransaction().isActive()) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Test
    @Transactional
    public void test() {
        Session session = sessionFactory.getCurrentSession();
//        Car car_1 = (Car) session.get(Car.class, 50L);
        Car car_50 = new Car();
        car_50.setId(50L);
        car_50.setModel("mmm");
        session.merge(car_50);
        session.flush();

        Session session1 = sessionFactory.openSession();
        Car car = (Car) session.get(Car.class, 50L);

        System.out.println("e");

        assertEquals("mmm", car.getModel());

        //assertEquals(0, sessionFactory.getCurrentSession().createCriteria(Person.class).list());
    }
}
