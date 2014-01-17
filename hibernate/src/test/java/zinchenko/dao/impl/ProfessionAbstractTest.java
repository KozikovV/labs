package zinchenko.dao.impl;

import org.junit.Before;
import org.junit.Test;
import zinchenko.dao.ProfessionDao;
import zinchenko.domain.Profession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class ProfessionAbstractTest {

    ProfessionDao professionDao;

    protected abstract ProfessionDao getProfessionDao();

    @Before
    public void before() {
        professionDao = getProfessionDao();
    }

    @Test
    public void testFindAll() {
        assertEquals(3, professionDao.findAll().size());
    }

    @Test
    public void testSave() {
        Profession profession = new Profession();
        profession.setName("prof1");

        Long id = professionDao.save(profession);
        assertNotNull(id);
    }

}
