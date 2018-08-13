package com.yang.tomcat.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by yz on 2018/7/25.
 */
public class TomcatServer {
    public static void main(String[] args) throws LifecycleException {
        String catalina_home = "E:\\java\\homework\\homework\\com.yang.spring.demo\\target";
        Tomcat tomcat = new Tomcat();
        tomcat.setHostname("localhost");
        tomcat.setPort(8080);
        tomcat.setBaseDir(catalina_home);

        tomcat.getHost().setAppBase("E:\\java\\homework\\homework\\com.yang.spring.demo\\target");
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener lifecycleListener = new AprLifecycleListener();
        server.addLifecycleListener(lifecycleListener);

        StandardContext standardContext = new StandardContext();
        standardContext.setPath("/");
        standardContext.setDocBase("E:\\java\\homework\\homework\\com.yang.spring.mvc\\target\\com.yang.spring.mvc");
        standardContext.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(standardContext);

        tomcat.start();

        tomcat.getServer().await();
    }
}
