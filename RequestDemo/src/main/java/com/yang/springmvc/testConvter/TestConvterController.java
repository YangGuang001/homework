package com.yang.springmvc.testConvter;

import com.yang.springmvc.curd.dao.EmployeeDao;
import com.yang.springmvc.curd.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yz on 2017/11/26.
 */
@Controller
public class TestConvterController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/testConversionServiceConverer")
    public String testConvter(@RequestParam("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
