package com.yang.eventobject.test;

public class EventConsumer implements ValueChangeListener {
    public void performed(ValueChangeEvent e) {
        System.out.printf("value changed, new value = " + e.getValue());
    }
}
