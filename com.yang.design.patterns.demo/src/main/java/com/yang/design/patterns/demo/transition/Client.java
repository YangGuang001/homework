package com.yang.design.patterns.demo.transition;

/**
 * Created by yz on 2017/9/6.
 */
public class Client {

    public static void main(String[] args) {
        ConcrateMediator mediator = new ConcrateMediator();

        ColleagueA colleagueA = new ColleagueA("A", mediator);
        ColleagueB colleagueB = new ColleagueB("B", mediator);

        mediator.setColleagueA(colleagueA);
        mediator.setColleagueB(colleagueB);

        colleagueA.contact("我是A，我要和同事B说说工作的事情");

        colleagueB.contact("我是B,我下午有时间,下午商量吧");
    }
}
