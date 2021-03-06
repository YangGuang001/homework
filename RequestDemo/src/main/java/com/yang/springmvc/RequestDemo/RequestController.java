package com.yang.springmvc.RequestDemo;


import com.yang.springmvc.entity.Address;
import com.yang.springmvc.entity.User;
import com.yang.springmvc.view.ExeclView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
//@SessionAttributes(value = "name")
public class RequestController {

    private static final String SUCCESS = "success";

    @RequestMapping(value = "/testRequestParams", params = {"username", "age"})
    public String testRequestParams(@RequestParam(value = "username") String name
            , @RequestParam(value = "age", required = false, defaultValue = "0") int age) {

        System.out.println("testRequestParams :" + "name :" + name + " age: " + age);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRequestHeads", headers = "Accept-Encoding")
    public String testRequestHeads(@RequestHeader(value = "Accept-Encoding") String modified) {
        System.out.println("testRequestHeads" + " : " + modified);
        return SUCCESS;
    }

    @RequestMapping(value = "/testCookieParams")
    public String testCookieParams(@CookieValue(value = "JSESSIONID") String cookie) {
        System.out.println("testCookieParams " + cookie);
        return SUCCESS;
    }

    @RequestMapping(value = "/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo : " + user.toString());
        return SUCCESS;
    }

    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView(SUCCESS);
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        map.put("name", Arrays.asList("yang", "xin"));
        return SUCCESS;
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        System.out.println("ModelAttribute method");
        if (id != null) {
            User user = new User(1, "yang", 18);
            System.out.println("get data from database" + user);
            Address address = new Address();
            address.setCity("111");
            address.setPrivince("222");
            user.setAddress(address);
            map.put("user", user);
        }
    }
    /**
     * 运行流程:
     * 1. 执行 @ModelAttribute 注解修饰的方法: 从数据库中取出对象, 把对象放入到了 Map 中. 键为: user
     * 2. SpringMVC 从 Map 中取出 User 对象, 并把表单的请求参数赋给该 User 对象的对应属性.
     * 3. SpringMVC 把上述对象传入目标方法的参数.
     *
     * 注意: 在 @ModelAttribute 修饰的方法中, 放入到 Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致!
     *
     * SpringMVC 确定目标方法 POJO 类型入参的过程
     * 1. 确定一个 key:
     * 1). 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
     * 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值.
     * 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
     * 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到.
     * 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰,
     * 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
     * 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常.
     * 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
     * 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
     * 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中.
     *
     * 源代码分析的流程
     * 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
     * 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
     * 1). 创建 WebDataBinder 对象:
     * ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写.
     * *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute
     * 的 value 属性值
     *
     * ②. 确定 target 属性:
     * 	> 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
     * 	> *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
     * 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常.
     * 	> 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
     * 和 attrName 相匹配, 则通过反射创建了 POJO 对象PojoName
     *
     * 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性.
     * 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel.
     * 近而传到 request 域对象中.
     * 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参.
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute(value = "user") User user) {
        System.out.println("modifier : " + user);
        return SUCCESS;
    }

    /**
     * 无论SpringMVC处理器方法返回的是String、ModelAndViw、View，都会转化为ModelAndView
     * 再转化为ViewResolver 再转化为视图对象。
     */
    @RequestMapping("/testJstl")
    public String testJstl() {
        System.out.println("testJstl ");
        return SUCCESS;
    }

    /**
     * InternalResourceView,jstlView,自定义视图
     */
    @RequestMapping(value = "/testCustomerView")
    public String testCustomerView() {
        System.out.println("/testCustomerView");
        return "helloView";
    }

    /**
     * 利用springmvc中提供以及有的接口，可以实现导出world,execl,pdf等
     * @return
     */
    @RequestMapping("/testExeclView")
    public ModelAndView testExeclView() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userList", getStudents());
        return new ModelAndView(new ExeclView(), model);
    }

    private List<User> getStudents() {
        List<User> userList = new ArrayList<User>();
        User user = new User(1, "Tome", 18);
        userList.add(user);
        return userList;
    }

    /**
     * springmvc 使用处理器方法返回前缀forword实现转发请求，redirect实现重定向操作
     */
    @RequestMapping(value = "/testRedirect")
    public String testRedirect() {
        System.out.println("/testRedirect");
        return "redirect:index.html";
    }

    @RequestMapping(value = "/testMyRestTemplate")
    public String testMyRestTemplate() {
        RestTemplate restTemplate = MyRestTemplate.create();
        return SUCCESS;
    }
}
