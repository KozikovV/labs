package zinchenko.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"personDaoTest.xml", "/hibernateTest.xml"})
@DatabaseSetup("personDaoTestDataset.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class PersonHibernateCritDaoTest extends AbstractPersonDaoTest {

    @Autowired
    @Qualifier("personHibernateCritDao")
    PersonDao personDao;

    @Override
    protected PersonDao getPersonDao() {
        return personDao;
    }
}
