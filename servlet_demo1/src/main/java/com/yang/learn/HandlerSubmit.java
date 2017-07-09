package com.yang.learn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yz on 2017/7/6.
 */
public class HandlerSubmit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String className = req.getParameter("class");
        PrintWriter out = resp.getWriter();
        try {
            Class<?> clazz = Class.forName(className);

            out.print("successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.print("failure");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
