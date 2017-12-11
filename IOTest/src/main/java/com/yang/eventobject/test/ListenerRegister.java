package com.yang.eventobject.test;

import java.util.Vector;

public class ListenerRegister {
    private Vector<ValueChangeListener> listeners = new Vector<ValueChangeListener>();

    public synchronized void addListener(ValueChangeListener e){
        listeners.addElement(e);
    }

    public synchronized void removeListener(ValueChangeListener e) {
        listeners.removeElement(e);
    }

    @SuppressWarnings("unchecked")
    public void fireEvent(ValueChangeEvent event) {
        Vector<ValueChangeListener> currentListeners = null;
        synchronized (this) {
            currentListeners = (Vector<ValueChangeListener>) listeners.clone();
        }

        for (int i=0; i < currentListeners.size(); i++) {
            ValueChangeListener listener = listeners.elementAt(i);
            listener.performed(event);
        }
    }
}
