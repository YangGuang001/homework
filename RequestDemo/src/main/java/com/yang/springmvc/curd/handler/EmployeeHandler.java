package com.yang.springmvc.curd.handler;

import com.yang.springmvc.curd.dao.DepartmentDao;
import com.yang.springmvc.curd.dao.EmployeeDao;
import com.yang.springmvc.curd.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        return "list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

    /**
     * 使用BindingResult 可以查看绑定出错时的错误结果
     * @param employee
     * @param result
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result) {
        employeeDao.save(employee);
        if (result.getErrorCount() > 0) {
            for (FieldError error : result.getFieldErrors() ) {
                System.out.printf("error Field :" + error.getField()
                + "error message " + error.getDefaultMessage());
            }
        }
        System.out.printf("employee :" + employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") int id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id,
                            Map<String, Object> map) {
        if (id != null) {
            map.put("employee", employeeDao.get(id));
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 主要进行数据的绑定操作，从外部数据到处理器接口的参数转换
     * @param webDataBinder
     */
//    @InitBinder
//    public void testInitDataBinder(WebDataBinder webDataBinder) {
//        webDataBinder.setDisallowedFields("lastName");
//    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson() {
        return employeeDao.getAll();
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity testResponseEntity(HttpSession httpSession) throws IOException {
        ServletContext servletContext = httpSession.getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream("/files/1.txt");
        byte[] body = new byte[inputStream.available()];
        inputStream.read(body);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=1.txt");

        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity responseEntity = new ResponseEntity(body, httpHeaders, httpStatus);
        return responseEntity;
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("name") String name
            , @RequestParam("file") MultipartFile file) {
        System.out.printf("fileName :" + name);
        System.out.printf("fileRealName : " + file.getOriginalFilename());

        return "success";
    }

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerException(Exception ex) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex.getMessage());
        return mv;
    }

    /**
     * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
     * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
     * 3. @ExceptionHandler 方法标记的异常有优先级的问题.
     * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常,
     * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常.
     */
    @RequestMapping("/testHandlerException")
    public String testHandlerException(@RequestParam("id") int i) {
        int result = 10 / i;
        return "success";
    }
}
