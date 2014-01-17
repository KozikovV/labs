package zinchenko.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import zinchenko.dao.ProfessionDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"daoTest.xml", "/hibernateTest.xml"})
@DatabaseSetup("daoTestDataset.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ProfessionHibCritDaoTest extends ProfessionAbstractTest{



    @Autowired
    @Qualifier("professionHibCritDao")
    private ProfessionDao professionDao;

    @Override
    public ProfessionDao getProfessionDao() {
        return professionDao;
    }

    public void setProfessionDao(ProfessionDao professionDao) {
        this.professionDao = professionDao;
    }

}
