package com.yang.jetty.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yz on 2018/3/3.
 */
public class TestEurekaServer {
    public static void main(String[] args) throws Exception{
        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setWar("E:\\java\\homework\\homework\\jetty-demo\\src\\main\\resources\\eureka-server-1.7.0.war");
        server.setHandler(context);
        server.start();
        server.join();
    }

}
