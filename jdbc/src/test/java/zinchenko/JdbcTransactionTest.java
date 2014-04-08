package zinchenko;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class JdbcTransactionTest {

    @Before
    public void before() throws SQLException {
        prepare();
    }

    @Test
    public void main() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        connection.createStatement().execute("insert into test_table values (3);");
        connection.commit();
        ResultSet resultSet1 = connection.createStatement().executeQuery("select count(id) as number from test_table;");
        resultSet1.next();
        int number = resultSet1.getInt("number");

        assertEquals(3, number);

        ResultSet resultSet = connection.createStatement().executeQuery("select * from test_table;");
        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getInt("id"));
        }
    }

    @Test
    public void test() throws SQLException {
        try {
            Connection connection = getConnection();
            connection.createStatement().executeQuery("insert into test_table values (3)");
            connection.createStatement().executeQuery("insert into test_table values (1)");
            connection.commit();
        } catch (Exception e) {
            System.out.println("error");
        }
        Connection connection = getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("select count(id) as number from test_table;");
        resultSet.next();
        int number = resultSet.getInt("number");
        assertEquals(2, number);
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
}
