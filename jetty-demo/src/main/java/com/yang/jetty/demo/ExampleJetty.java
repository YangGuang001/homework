package com.yang.jetty.demo;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;

/**
 * Created by yz on 2018/3/3.
 */
public class ExampleJetty {
    @Test
    public void TestHandlerConllection() throws Exception{
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setWar("test");
        connector.setPort(8000);
        server.setConnectors(new Connector[]{connector});
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        contextHandler.addServlet(HelloServlet.class, "/hello");
        HandlerCollection handlerCollection = new HandlerCollection(new Handler[] {contextHandler
                , new DefaultHandler()});
        server.setHandler(handlerCollection);
        server.start();
        server.join();
    }

    @Test
    public void testOneHandler() throws Exception {
        Server server = new Server(8080);
        server.setHandler(new HelloHandler());

        server.start();
        server.join();
    }
}
