package zinchenko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = getConnection();
        prepare(connection);

        ResultSet resultSet = connection.createStatement().executeQuery("select * from test_table;");
        while(resultSet.next()){
            System.out.println(resultSet.getInt("id"));
        }
        System.out.println("end");
    }
    private static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/jdbc-test", "test", "1234");
        return DriverManager.getConnection("jdbc:h2:mem:test", "test", "1234");
    }
    private static void prepare(Connection connection) throws SQLException {
        connection.createStatement().execute("create table test_table(id int);");
        connection.createStatement().execute("insert into test_table values (1);");
        connection.createStatement().execute("insert into test_table values (2);");
    }

}
