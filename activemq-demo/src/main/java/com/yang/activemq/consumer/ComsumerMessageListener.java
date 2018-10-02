package com.yang.activemq.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by yz on 2018/8/24.
 */
public class ComsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("接收到消息：   " + message);
    }
}
