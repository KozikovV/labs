package zinchenko;

import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Dao {

    private DataSource dataSource;

    @Transactional
    public void transactional() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.createStatement().execute("insert into test_table values (3);");
        connection.createStatement().execute("insert into test_table values (2);");
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
