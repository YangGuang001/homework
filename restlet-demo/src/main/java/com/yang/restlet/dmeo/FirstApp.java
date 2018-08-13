package com.yang.restlet.dmeo;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * Created by yz on 2018/6/16.
 */
public class FirstApp extends Application {
    @Override
    public synchronized Restlet createInboundRoot() {
        System.out.println("starter Application");
        Router router = new Router(getContext());
        router.attach("/hello", HelloWorld.class);
        return router;
    }

    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);
        component.getDefaultHost().attach("/hello", new FirstApp());
        component.start();
    }
}
