package com.yang.mongodbtest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDbTest {
    public static void main(String[] args) {
        try{
            MongoClient client = new MongoClient("localhost", 27017);
            MongoDatabase database = client.getDatabase("test");
            System.out.printf("1111");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
