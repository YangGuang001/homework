package com.yang.learn;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yz on 2017/6/23.
 */
public class LogFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext context = this.config.getServletContext();
        long before = System.currentTimeMillis();
        System.out.println("开始过滤....");
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        context.log("Filter 已经截获用户请求地址： " + request.getServletPath());

        filterChain.doFilter(servletRequest,servletResponse);

        long after = System.currentTimeMillis();

        context.log("过滤结束");

        context.log("请求被定为到" + request.getRequestURI() + "所花费的时间为：" + (after - before));
    }

    public void destroy() {
        this.config = null;
    }
}
