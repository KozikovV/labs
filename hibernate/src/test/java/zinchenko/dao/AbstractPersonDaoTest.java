package zinchenko.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.domain.Person;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public abstract class AbstractPersonDaoTest {

    PersonDao personDao;

//    @Autowired
//    SessionFactory sessionFactory;

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
}
