package com.yang.jetty.jersey.demo;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by yz on 2018/4/8.
 */
public class JettyServer {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);
        ServletHolder servletHandler = new ServletHolder(ServletContainer.class);
        servletHandler.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        servletHandler.setInitParameter("com.sun.jersey.config.property.packages", "com.yang.jetty.jersey.demo");
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.addServlet(servletHandler, "/rest/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
