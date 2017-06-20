package com.yang.jmx;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by yz on 2017/6/18.
 */
public class Agent {

    public static void main(String[] args) throws JMException,Exception{
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        server.registerMBean(new Hello(),helloName);
        Thread.sleep(60 * 60 * 1000);
    }
}
