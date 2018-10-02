package com.yang.activemq.productor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by yz on 2018/8/24.
 */
public class JmsTemplateTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("producer/spring-mq-active.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsQueueTemplate");
        Destination destination = (Destination) context.getBean("messageQueue");
        jmsTemplate.send(destination,new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello activemq message!!!");
            }
        });
    }
}
