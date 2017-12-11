package com.yang.spring.definescope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yz on 2017/9/13.
 */
public class ThreadScope implements Scope {
    private final ThreadLocal<Map<String, Object>> THREAD_SCOPE = new ThreadLocal<Map<String, Object>>() {
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public Object get(String s, ObjectFactory<?> objectFactory) {
        if (THREAD_SCOPE.get().containsKey(s)) {
            return THREAD_SCOPE.get().get(s);
        }
        THREAD_SCOPE.get().put(s, objectFactory.getObject());
        return THREAD_SCOPE.get().get(s);
    }

    public Object remove(String s) {
        return THREAD_SCOPE.get().remove(s);
    }

    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    public Object resolveContextualObject(String s) {
        return null;
    }

    public String getConversationId() {
        return null;
    }
}
