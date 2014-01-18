package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public abstract class PersonDaoAbstractTest {

    @Autowired
    SessionFactory sessionFactory;

    PersonDao personDao;

    protected abstract PersonDao getPersonDao();

    @Before
    public void before() throws Exception {
        personDao = getPersonDao();
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @After
    public void after(){
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void testFindAll(){
        List<Person> users = personDao.findAll();
        assertEquals(4, users.size());
    }

    @Test
    public void testFind(){
        MultiId multiId = new MultiId("firstName0", "lastName0");

        Person result = personDao.find(multiId);
        assertNotNull(result);
        assertEquals(multiId, result.getId());
    }

    @Test
    public void testSave() {
        Person person = new Person();
        person.setId(new MultiId("fn1", "ln1"));
        person.setAboutMe("aboutMe1");

        MultiId id = personDao.save(person);
        assertNotNull(id);
        assertEquals(person.getId(), id);
    }

    @Test
    public void testDelete(){
        Person person = new Person();
        person.setId(new MultiId("firstName0", "lastName0"));

        personDao.delete(person);
        assertEquals(3, personDao.findAll().size());
    }

    @Test
    public void testFindByProfession(){
        Profession profession = new Profession();
        profession.setId(12L);

        List<Person> result = personDao.findByProfession(profession);
        assertEquals(2, result.size());
        assertEquals(profession.getId(), result.get(0).getProfession().getId());
        assertEquals(profession.getId(), result.get(1).getProfession().getId());
    }

}
