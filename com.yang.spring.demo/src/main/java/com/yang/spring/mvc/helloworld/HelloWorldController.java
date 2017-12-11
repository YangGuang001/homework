package com.yang.spring.mvc.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("greeting", "Hello Spring world");
        return "helloworld";
    }

    //redirect
    @RequestMapping(value = "/redirct", method = RequestMethod.GET)
    public String redirectController(Model model) {
        return "redirect:/hello";
    }
}
