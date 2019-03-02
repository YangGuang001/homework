package com.yang.kafka.producer;


import com.yang.kafka.demo.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by yz on 2018/8/26.
 */
public class KafkaConsumerThread extends Thread {
    private final KafkaConsumer<String, String> consumer;

    private final String topic;

    public KafkaConsumerThread(String topic) {
        consumer = createConsumerConfig();
        this.topic = topic;
    }

    private KafkaConsumer createConsumerConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", KafkaProperties.groupId); //设置消费消息分组
        properties.put("enable.auto.commit", "true"); //设置自动提交offset
        properties.put("auto.commit.interval.ms", "1000"); //设置提交offset的时间间隔, 时间间隔太大可能出现重复消费
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(properties);
    }

    @Override
    public void run() {
        TopicPartition topicPartition = new TopicPartition(this.topic, 0);
        consumer.assign(Arrays.asList(topicPartition));
        //consumer.subscribe(topicPartition);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            if (!records.isEmpty()) {
                consumer.commitAsync();
            }
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }

    }
}
