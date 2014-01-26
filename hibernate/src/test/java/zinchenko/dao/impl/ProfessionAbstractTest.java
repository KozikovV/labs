package zinchenko.dao.impl;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Profession;
import zinchenko.interceptors.ProfessionInterceptor;

import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class ProfessionAbstractTest {

    public static final int LOOP_SIZE = 10000;

    private static final Log LOG = LogFactory.getLog(ProfessionAbstractTest.class);

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
    public void testFindAllGetCurrentSes() {
        assertEquals(4, professionDao.findAllGetCurrentSes().size());
    }

    @Test
    public void testFindAllGetCurrentSesForTime(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < LOOP_SIZE; i++) {
            assertEquals(4, professionDao.findAllGetCurrentSes().size());
        }
        stopWatch.stop();
        LOG.debug(MessageFormat.format("Time is spent fo invoking professionDao.findAllGetCurrentSes() {0} times = {1} milliseconds", LOOP_SIZE, stopWatch.getTime()));
    }

    @Test
    public void testFindAllFindAllOpenSesForTime(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < LOOP_SIZE; i++) {
            assertEquals(4, professionDao.findAllOpenSes().size());
        }
        stopWatch.stop();
        LOG.debug(MessageFormat.format("Time is spent fo invoking professionDao.findAllOpenSes() {0} times = {1} milliseconds", LOOP_SIZE, stopWatch.getTime()));
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

        assertEquals(3, professionDao.findAllGetCurrentSes().size());
    }

    @Test
    @Ignore
    public void testDeleteWithExtractingBefore() {
        int expectedQuantity = professionDao.findAllGetCurrentSes().size() - 1;
        Profession profession = new Profession();
        profession.setId(10L);

        professionDao.delete(profession);

        int actualQuantity = professionDao.findAllGetCurrentSes().size();
        assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    public void testSaveWithInterceptor(){
        Profession profession = new Profession();
        profession.setName("name_new");

        professionDao.saveWithInterceptor(profession);
        assertEquals(5, professionDao.findAllGetCurrentSes().size());
    }



}
