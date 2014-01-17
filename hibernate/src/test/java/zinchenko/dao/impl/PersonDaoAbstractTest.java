package zinchenko.dao.impl;

import org.junit.Before;
import org.junit.Test;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public abstract class PersonDaoAbstractTest {

    PersonDao personDao;

    protected abstract PersonDao getPersonDao();

    @Before
    public void before() throws Exception {
        personDao = getPersonDao();
    }

    @Test
    public void testFindAll(){
        List<Person> users = personDao.findAll();
        assertEquals(3, users.size());
    }

    @Test
    public void testSave() {
        Person person = new Person();
//        person.setId(new MultiId("fn1", "ln1"));
        person.setAboutMe("aboutMe1");

        MultiId id = personDao.save(person);
    }
}
