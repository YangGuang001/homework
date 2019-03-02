package com.yang.kafka.producer;


import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by yz on 2018/8/26.
 */
public class KafkaProducerThread extends Thread {
    private final KafkaProducer<String, String> producer;

    private final String topic;

    private final Properties properties = new Properties();

    public KafkaProducerThread(String topic) {
        properties.put("bootstrap.servers", "localhost:9092");//服务器ip:端口号，集群用逗号分隔
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384); //批量提交大小
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432); //内存大小
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 0;
        int partitionNum = 0;
        while (true) {
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send: " + messageStr);

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, partitionNum++ % 3, String.valueOf(messageNo++), messageStr);
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (null != exception) {
                        System.out.println("发送消息失败");
                    }
                    System.out.println("发送消息成功");
                }
            });


            //producer.send(new ProducerRecord<String, String>(topic, partitionNum++ % 3, String.valueOf(messageNo++), messageStr));

//            try {
//                RecordMetadata recordMetadata = metadataFuture.get();
//                System.out.println("sync send message :" + " topic:" + recordMetadata.topic() + "partition" + recordMetadata.partition() + " value:" + recordMetadata.serializedValueSize());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
