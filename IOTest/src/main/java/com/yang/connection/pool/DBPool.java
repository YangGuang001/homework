package com.yang.connection.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by yz on 2018/9/1.
 */
public class DBPool {
    private static DBPool instance;

    private ComboPooledDataSource dataSource;

    static {
        instance = new DBPool();
    }

    private DBPool() {

        try {
            dataSource = new ComboPooledDataSource();
            Properties properties = new Properties();
            InputStream inputStream = DBPool.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);

            dataSource.setDriverClass(properties.getProperty("jdbcdriver"));
            dataSource.setJdbcUrl(properties.getProperty("url"));
            dataSource.setUser(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
