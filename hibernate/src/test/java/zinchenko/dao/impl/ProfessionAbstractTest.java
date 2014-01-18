package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Profession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class ProfessionAbstractTest {

    ProfessionDao professionDao;

    @Autowired
    SessionFactory sessionFactory;

    protected abstract ProfessionDao getProfessionDao();

    @Before
    public void before() {
        professionDao = getProfessionDao();
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @After
    public void after(){
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void testFindAll() {
        assertEquals(4, professionDao.findAll().size());
    }

    @Test
    public void testSave() {
        Profession profession = new Profession();
        profession.setName("prof1");

        Long id = professionDao.save(profession);
        assertNotNull(id);
    }

    @Test
    public void testFind() {
        Long id = 10L;
        Profession expectedProfession = new Profession();
        expectedProfession.setId(id);
        expectedProfession.setName("prof1");

        Profession profession = professionDao.find(id);
        assertEquals(expectedProfession, profession);
    }

    @Test
    public void testDelete() {
        Profession profession = new Profession();
        profession.setId(19L);

        professionDao.delete(profession);

        assertEquals(3, professionDao.findAll().size());
    }

    @Test
    @Ignore
    public void testDeleteWithExtractingBefore() {
        int expectedQuantity = professionDao.findAll().size() - 1;
        Profession profession = new Profession();
        profession.setId(10L);

        professionDao.delete(profession);

        int actualQuantity = professionDao.findAll().size();
        assertEquals(expectedQuantity, actualQuantity);
    }


}
