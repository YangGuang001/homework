package com.yang.startup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by yz on 2017/7/4.
 */
public class MyLister implements ServletContextListener {
    private Log log = LogFactory.getLog(MyLister.class);

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        log.info("即将启动的路径为:" + servletContext.getContextPath());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        log.info("即将关闭:" + servletContext.getContextPath());
    }
}
