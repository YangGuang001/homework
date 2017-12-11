package com.yang.springboot.database.service;

import com.yang.springboot.database.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addStudent(String name, int age, int sumScore, int avgScore) {

        return true;
    }


    public List<Student> getList() {
        String sql = "SELECT * FROM score";
        return (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setAge(resultSet.getInt("age"));
                student.setName(resultSet.getString("name"));
                student.setSumScore(resultSet.getString("sum_score"));
                student.setAvgScore(resultSet.getString("avg_score"));
                return student;
            }
        });
    }
}
