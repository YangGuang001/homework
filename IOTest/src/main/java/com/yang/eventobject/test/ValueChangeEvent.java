package com.yang.eventobject.test;

import java.util.EventObject;

public class ValueChangeEvent extends EventObject{
    private static final long serialVersionUID = 12345678L;

    private int value;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ValueChangeEvent(Object source) {
        super(source);
    }

    public ValueChangeEvent(Object source, int newVaule) {
        super(source);
        this.value = newVaule;
    }

    public int getValue() {
        return value;
    }
}
