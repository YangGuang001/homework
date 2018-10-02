package com.yang.connection.pool;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yz on 2018/9/1.
 */
public class DbUtil {
    public static Connection getConnection() throws SQLException {
        DBPool pool = DBPool.getInstance();
        return pool.getConnection();
    }

    @Test
    public void testDBUtil() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "select * from stduent";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        int i=0;
        while (resultSet.next()) {
            i++;
            System.out.println(resultSet.getString(i));
        }
    }
}
