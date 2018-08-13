package com.yang.restlet.dmeo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by yz on 2018/6/16.
 */
public class JettyServer {

    private final int port;

    private Server server;

    public JettyServer() {
        this(8080);
    }

    public JettyServer(int port) {
        this.port = port;
    }

    public boolean startServer() {
        server = new Server(this.port);
        try {
            WebAppContext webAppContext = new WebAppContext("E:\\java\\homework\\homework\\restlet-demo\\target\\classes", "/");
            webAppContext.setDefaultsDescriptor("/WEB-INF/web.xml");
            server.setHandler(webAppContext);
            server.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        JettyServer jettyServer = new JettyServer();
        if (jettyServer.startServer()) {
            System.out.println("starter successful");
        } else {
            System.out.println("failed to starter");
        }
    }
}
