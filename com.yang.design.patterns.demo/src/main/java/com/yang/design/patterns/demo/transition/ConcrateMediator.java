package com.yang.design.patterns.demo.transition;

/**
 * Created by yz on 2017/9/6.
 */
public class ConcrateMediator extends Mediator {
    ColleagueA colleagueA;
    ColleagueB colleagueB;

    public void setColleagueA(ColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    public void contact(String content, Colleague coll) {
        if (coll == colleagueA) {
            colleagueA.getMessage(content);
        }
        else if (coll == colleagueB) {
            colleagueB.getMessage(content);
        }
    }
}
