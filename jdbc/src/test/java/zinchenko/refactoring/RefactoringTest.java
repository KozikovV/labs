package zinchenko.refactoring;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class RefactoringTest {

    @Test
    public void fillData() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ref","root", "root");
        connection.setAutoCommit(false);

//        connection.createStatement().execute("create table table_name (id int, name varchar(200));");
        PreparedStatement ps = connection.prepareStatement("insert into table_name (id, name) values (?, ?);");
        for (int i = 0; i < 2000000; i++) {
            ps.setInt(1, i);
            ps.setString(2, "name_" + i);
            ps.execute();
            if(i%10000 == 0){
                connection.commit();
                System.out.println("commited id="+i);
            }
        }
        System.out.println("commited");
        connection.commit();
        connection.close();

        System.out.println("end");

    }

    private Connection getConection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ref","root", "root");
        connection.setAutoCommit(false);
        return connection;
    }

    @Test
    public void getById() throws Exception {
        for (int i = 0; i < 10; i++) {
            StopWatch sw = new StopWatch();
            Connection c = getConection();
            PreparedStatement ps = c.prepareStatement("select * from table_name where id=?");
            int id = Math.abs(new Random().nextInt()) % 2000000;
            ps.setInt(1, id);

            sw.start();
            ps.execute();
            sw.stop();

            System.out.println("select value by id=" + id + ", time=" + sw.getTotalTimeMillis());
        }
    }

    @Test
    public void getByName() throws Exception{
        for (int i = 0; i < 10; i++) {
            StopWatch sw = new StopWatch();
            Connection c = getConection();
            PreparedStatement ps = c.prepareStatement("select * from table_name where name=?");
            String name = "name_" + Math.abs(new Random().nextInt()) % 2000000;
            ps.setString(1, name);

            sw.start();
            ps.execute();
            sw.stop();

            System.out.println("select value by name=" + name + ", time=" + sw.getTotalTimeMillis());
        }
    }

}
