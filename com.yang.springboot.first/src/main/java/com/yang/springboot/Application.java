package com.yang.springboot;

import com.yang.springboot.database.service.StudentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@MapperScan(basePackages = "com.yang.springboot.mybits.mapper")
public class Application {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void init() {
        studentService.addStudent("yang", 18, 100, 100);
    }
}
