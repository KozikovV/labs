package com.nixsolutions.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.nixsolutions.dao.util.XMLPropertiesLoaderImpl;
import com.nixsolutions.dao.util.XMLPropertyLoader;

/**
 * Класс для получения соединения с БД
 */
public abstract class AbstractJdbcDao {

    protected Connection connection;

    private static final XMLPropertyLoader pl = new XMLPropertiesLoaderImpl();

    private DataSource dataSource;

    private String uri;
    private String username;
    private String password;
    private String driverName;

    /**
     * Создание объекта Connection.
     * 
     * @return объект соединения
     * 
     */
    public Connection createConnection() {

        try {
            if (dataSource == null) {
                dataSource = getDataSource();
            }
        } catch (Exception e) {
            throw new IllegalStateException("can not get DataSource ", e);
        }

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("can not get Connection ", e);
        }
        return connection;
    }

    /**
     * Создание объекта DataSource.
     * 
     * @return объект DataSource
     * 
     */
    private DataSource getDataSource() {

        username = pl.getUsername();
        password = pl.getPassword();
        driverName = pl.getDriver();
        uri = pl.getUrl();
        

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
