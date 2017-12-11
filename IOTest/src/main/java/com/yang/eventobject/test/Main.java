package com.yang.eventobject.test;

public class Main {
    public static void main(String[] args) {
        EventProducer producer = new EventProducer();
        producer.addListener(new EventConsumer());
        producer.setValue(2);
    }
}
