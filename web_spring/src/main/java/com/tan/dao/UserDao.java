package com.tan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yz on 2018/9/2.
 */
@Component
public class UserDao {

    @Autowired
    private DataSource dataSource;

    public void getUser() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test.user");
            ResultSet resultSet = statement.executeQuery();
//            while ()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
