package zinjvi;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: zinchenko
 * Date: 06.01.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/zinjvi/test.xml")
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


    }
}
