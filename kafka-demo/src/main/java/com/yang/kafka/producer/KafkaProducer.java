package com.yang.kafka.producer;

import com.yang.kafka.demo.KafkaProperties;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2018/8/26.
 */
public class KafkaProducer extends Thread {
    private final Producer<Integer, String> producer;

    private final String topic;

    private final Properties properties = new Properties();

    public KafkaProducer(String topic) {
        properties.put("bootstrap.servers", "localhost:9092");//服务器ip:端口号，集群用逗号分隔
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new Producer<Integer, String>(new ProducerConfig(properties));
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send: " + messageStr);
            producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
