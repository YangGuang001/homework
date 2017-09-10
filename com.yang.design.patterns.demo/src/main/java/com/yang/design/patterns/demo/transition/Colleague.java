package com.yang.design.patterns.demo.transition;

/**
 * Created by yz on 2017/9/6.
 */
public class Colleague {
    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator){
        this.name = name;
        this.mediator = mediator;
    }
}
