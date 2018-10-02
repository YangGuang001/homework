package com.yang.activemq.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by yz on 2018/8/24.
 */
public class JmsTemplateComsumer {
    public static void main(String[] args) throws JMSException {
        ApplicationContext context = new ClassPathXmlApplicationContext("producer/spring-mq-active.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsQueueTemplate");
        Destination destination = (Destination) context.getBean("messageQueue");
        Message message = jmsTemplate.receive(destination);
        if (message instanceof TextMessage) {
            System.out.println(((TextMessage) message).getText());
        }
    }
}
