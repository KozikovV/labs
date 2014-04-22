package zinchenko.springJdbcTemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zinchenko.model.Bean;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:zinchenko/springJdbcTemplate/springTemplateJdbcTransaction-appContext.xml")
public class TransactionTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    SpringJdbcTemplateService service;

//    @Before
//    public void before() throws Exception {
//        Connection connection = dataSource.getConnection();
//        connection.createStatement().executeUpdate("drop table if exists bean;");
//        connection.createStatement().executeUpdate("create table bean (id int not null primary key, name varchar(250) not null);");
//        connection.createStatement().executeUpdate("insert into bean (id, name) values (1, 'name1');");
//        connection.commit();
//        connection.close();
//    }

//    @After
//    public void after() throws Exception {
//        Connection connection = dataSource.getConnection();
//        connection.createStatement().executeUpdate("drop table bean;");
//        connection.commit();
//    }

//    private int beansCount() throws Exception {
//        Connection connection = null;
//        try{
//            connection = dataSource.getConnection();
//            ResultSet resultSet = connection.createStatement().executeQuery("select count(id) as count from bean");
//            return resultSet.getInt("count");
//        }catch (Exception e){
//            throw new RuntimeException();
//        }finally {
//            connection.close();
//        }
//    }

    @Test
    public void testSaveCorrectTwoBeans() throws Exception {
        service.insertBeansOnlyJdbc(new Bean(2, "name2"), new Bean(2, "name3"));
//        Assert.assertEquals(3, beansCount());
    }

}
