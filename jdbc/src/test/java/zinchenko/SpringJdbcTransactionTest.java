package zinchenko;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:zinchenko/springJdbcTransaction-appContext.xml")
public class SpringJdbcTransactionTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    Dao dao;

    @Before
    public void before() throws SQLException {
        prepare();
    }

    private static Connection getConnection() throws SQLException {
//        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/jdbc-test", "test", "1234");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "test", "1234");
        connection.setAutoCommit(false);
        return connection;
    }

    private static void prepare() throws SQLException {
        Connection connection = getConnection();
        connection.createStatement().execute("create table test_table(id int primary key);");
        connection.createStatement().execute("insert into test_table values (1);");
        connection.createStatement().execute("insert into test_table values (2);");
        connection.commit();
    }

    @Test
    public void test() throws Exception{
        try{
            dao.transactional();
        }catch (Exception e){
            System.out.println("error");
        }
        Connection connection = getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("select count(id) as number from test_table;");
        resultSet.next();
        int number = resultSet.getInt("number");
        assertEquals(2, number);
    }


}
