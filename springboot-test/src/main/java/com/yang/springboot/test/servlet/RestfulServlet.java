package com.yang.springboot.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/hello/*")
public class RestfulServlet extends HttpServlet {



    @Override
    public void init() throws ServletException {
        System.out.println("testdemo");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>Hello MyServlet Response return you this</h1>");
        resp.getWriter().flush();
    }
}
