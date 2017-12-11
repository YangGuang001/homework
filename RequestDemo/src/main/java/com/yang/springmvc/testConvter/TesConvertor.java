package com.yang.springmvc.testConvter;

import com.yang.springmvc.curd.entity.Department;
import com.yang.springmvc.curd.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by yz on 2017/11/26.
 */
@Component
public class TesConvertor implements Converter<String, Employee> {
    /**
     * 对应的源码分析 ： org.springframework.web.method.support.InvocableHandlerMethod#getMethodArgumentValues(org.springframework.web.context.request.NativeWebRequest, org.springframework.web.method.support.ModelAndViewContainer, java.lang.Object...)
     * 请求的参数支持原来的请求分析
     * @param source
     * @return
     */
    public Employee convert(String source) {
        if (source == null) {
            return null;
        }
        System.out.printf("get input :" + source);
        String[] retVal = source.split("-");
        if (retVal != null && retVal.length == 4) {
            Employee employee = new Employee();
            employee.setLastName(retVal[0]);
            employee.setEmail(retVal[1]);
            employee.setGender(Integer.valueOf(retVal[2]));
            Department department = new Department();
            department.setId(Integer.valueOf(retVal[3]));
            employee.setDepartment(department);
            return employee;
        }
        return null;
    }
}
