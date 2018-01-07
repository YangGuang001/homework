package com.yang.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by yz on 2017/12/21.
 */
public class DemoEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DemoEvent(Object source, String msg) {

        super(source);
        this.msg = msg;
    }
}
