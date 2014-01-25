package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.HouseDao;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
public abstract class HouseDaoTest {

    @Autowired
    SessionFactory sessionFactory;

    public abstract HouseDao getHouseDao();

    @Before
    public void setUp() throws Exception {
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}
