package com.yang.springmvc.service;

import com.yang.springmvc.dao.StudentJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by yz on 2017/12/10.
 */
@Controller
public class QueryService {

    @Autowired
    public StudentJDBCTemplate studentJDBCTemplate;

    @RequestMapping("/students")
    public String getAllStudent(Map<String, Object> map) {
        map.put("students", studentJDBCTemplate.listStudents());
        return "list";
    }

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
        personService.savePerson();
        return "success!";
    }
}
