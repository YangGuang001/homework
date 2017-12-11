package com.yang.springboot.database.controller;

import com.yang.springboot.database.entity.Student;
import com.yang.springboot.database.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public List<Student> getStudents() {
        return studentService.getList();
    }
}
