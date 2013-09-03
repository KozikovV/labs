package laba;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * The class for getting jdbc connection.
 */
public abstract class AbstractJdbcDao {

    protected Connection connection;

    private static DataSource dataSource;

    private String uri;
    private String username;
    private String password;
    private String driverName;

    /**
     * Constructor with parameters.
     * 
     * @param url
     *            URL DB
     * @param username
     *            user name
     * @param password
     *            passwird
     * @param driverName
     *            full name of a Driver of a database with name package
     * 
     */
    public AbstractJdbcDao(final String url, final String username,
            final String password, final String driverName) {
        super();
        this.uri = url;
        this.username = username;
        this.password = password;
        this.driverName = driverName;
    }

    /**
     * Creating object of class Connection/
     * 
     * @return object of class Connection
     * 
     */
    public Connection createConnection() {

        try {
            if (dataSource == null) {
                dataSource = getDataSource();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }

    /**
     * Creating object of class DataSource.
     * 
     * @return object of class DataSource
     * 
     */
    private DataSource getDataSource() {

        Properties prop = new Properties();
        prop.setProperty("user", username);
        prop.setProperty("password", password);

        Driver driver = null;
        try {
            driver = (Driver) Class.forName(driverName).newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        PoolableConnectionFactory factory = new PoolableConnectionFactory(
                new DriverConnectionFactory(driver, uri, prop),
                new GenericObjectPool(null), null, "SELECT 1", false, true);
        DataSource src = new PoolingDataSource(factory.getPool());
        return src;
    }

}
