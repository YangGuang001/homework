package com.yang.springboot.test;

import com.yang.springboot.Application;
import com.yang.springboot.mybits.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
    @Autowired
    private CityService cityMapper;

    @Test
    public void contextLoads() {
        cityMapper.getAll();
    }
}
