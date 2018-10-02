package com.yang.activemq.productor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;

import javax.jms.*;

/**
 * Created by yz on 2018/8/23.
 */
public class ProductorTest {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("test-queue");
            messageProducer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage("hello, test-queue");
            messageProducer.send(textMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (null != messageProducer) {
                try {
                    messageProducer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (null != session) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
