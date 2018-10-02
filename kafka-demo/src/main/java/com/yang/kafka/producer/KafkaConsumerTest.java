package com.yang.kafka.producer;

import com.yang.kafka.comsumer.KafkaConsumer;
import com.yang.kafka.demo.KafkaProperties;

/**
 * Created by yz on 2018/8/26.
 */
public class KafkaConsumerTest {
    public static void main(String[] args) {
        KafkaProducer producer = new KafkaProducer(KafkaProperties.topic);
        producer.start();

        KafkaConsumer consumer = new KafkaConsumer(KafkaProperties.topic);
        consumer.start();
    }
}
