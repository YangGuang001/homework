package com.yang.kafka.producer;

import com.yang.kafka.demo.KafkaProperties;

/**
 * Created by yz on 2018/8/26.
 */
public class KafkaConsumerTest {
    public static void main(String[] args) {
        KafkaProducerThread producer = new KafkaProducerThread(KafkaProperties.topic);
        producer.start();

        KafkaConsumerThread consumer = new KafkaConsumerThread(KafkaProperties.topic);
        consumer.start();
    }
}
