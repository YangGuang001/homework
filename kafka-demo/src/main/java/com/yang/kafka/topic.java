package com.yang.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class topic {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");

        AdminClient kafkaAdminClient = KafkaAdminClient.create(properties); //设置kafka地址，创建client
        NewTopic newTopic = new NewTopic("topic1", 3, (short) 3); //创建topic
        kafkaAdminClient.createTopics(Collections.singleton(newTopic));
        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        KafkaFuture<Map<String, TopicListing>> mapKafkaFuture = listTopicsResult.namesToListings();
        Map<String, TopicListing> stringTopicListingMap = mapKafkaFuture.get();
        Set<String> strings = stringTopicListingMap.keySet();
        strings.forEach(string -> {
            System.out.println(string);
        });
    }
}
