package zinchenko.springJdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zinchenko.model.Bean;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcDao {

    @Autowired
    private DataSource dataSource;

    public void insertBean(Bean bean) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("INSERT INTO BEAN (ID, NAME) VALUES (?, ?)");
            preparedStatement.setInt(1, bean.getId());
            preparedStatement.setString(2, bean.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Fail inserting bean.", e);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
