package com.yang.eventobject.test;

public class EventProducer {
    ListenerRegister register = new ListenerRegister();

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        if (value != newValue) {
            value = newValue;
            ValueChangeEvent event = new ValueChangeEvent(this, value);
            fireEvent(event);
        }
    }

    public void addListener(ValueChangeListener listener) {
        register.addListener(listener);
    }

    public void removeListener(ValueChangeListener listener) {
        register.removeListener(listener);
    }

    public void fireEvent(ValueChangeEvent event) {
        register.fireEvent(event);
    }
}
