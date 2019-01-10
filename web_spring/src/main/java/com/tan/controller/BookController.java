package com.tan.controller;

import com.tan.model.Book;
import com.tan.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book.do")
public class BookController {

    private BookService bookService;


    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String add(Book book, HttpServletRequest httpRequest){
//        System.out.println("bookname:"+book.getName());
//        System.out.println("author:"+book.getAuthor());
//        bookService.add(book);
        return "success";
    }
    @RequestMapping(params = "method=update")
    public String update(Book book) {
        bookService.update(book);
        return "success";
    }
    public BookService getBookService() {
        return bookService;
    }
    @Resource
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}