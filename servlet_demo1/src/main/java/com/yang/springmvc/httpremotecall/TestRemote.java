package com.yang.springmvc.httpremotecall;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yz on 2017/12/15.
 */
public class TestRemote {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testRemoteHttpCall.xml");

        IRemoteService service = (IRemoteService) applicationContext.getBean("iRemoteTest");

        service.startRemote();
    }
}
