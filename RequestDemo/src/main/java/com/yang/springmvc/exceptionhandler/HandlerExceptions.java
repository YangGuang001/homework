package com.yang.springmvc.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yz on 2017/12/3.
 */
@ControllerAdvice
public class HandlerExceptions {
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerException(Exception ex) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex.getMessage());
        return mv;
    }
}
