package com.yyq.dao;
import com.yyq.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public UserDao() {
        System.out.println("UserDao");
    }
    public int getMatchCount(String userName, String password) {
        String sqlStr = "select count(*) from t_user where user_name = ? and password = ?";
        return jdbcTemplate.queryForInt(sqlStr, new Object[]{userName, password});
    }
    public User findUserByUserName(final String userName) {
        String sqlStr = " SELECT user_id,user_name,credits "
                + " FROM t_user WHERE user_name =? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                });
        return user;
    }
    public void updateLoginInfo(User user) {
        String sqlStr = " UPDATE t_user SET last_visit=?,last_ip=?,credits=? "
                + " WHERE user_id =?";
        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(),
                user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}