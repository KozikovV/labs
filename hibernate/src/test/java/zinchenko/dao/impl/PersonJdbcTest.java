package zinchenko.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import zinchenko.dao.PersonDao;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"daoTest.xml", "/hibernateTest.xml"})
@DatabaseSetup("daoTestDataset.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class PersonJdbcTest extends PersonDaoAbstractTest {

    @Autowired
    @Qualifier("personJdbcDao")
    PersonDao personDao;

    @Override
    protected PersonDao getPersonDao() {
        return personDao;
    }
}
