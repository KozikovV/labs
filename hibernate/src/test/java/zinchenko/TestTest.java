package zinchenko;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.domain.Person;
import zinchenko.domain.Role;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 06.01.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/zinchenko/test.xml")
public class TestTest {

    @Autowired
    Bean bean;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    @Transactional
    public void test() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Role.class);
        List<Role> roles = criteria.list();
        assertEquals(2, roles.size());

        assertEquals(0, sessionFactory.getCurrentSession().createCriteria(Person.class).list());
    }
}
