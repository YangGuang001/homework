package com.yang.Observable;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yz on 2018/1/30.
 */
public class Lisi implements Observer {
    public void update(Observable o, Object arg) {
        System.out.println("have observer");
        System.out.println(arg);
    }

    public static void main(String[] args) {
        Observer observer = new Lisi();
        Hanfeizi observable = new Hanfeizi();
        observable.addObserver(observer);
        observable.haveBreakfast();
        observable.havefun();
    }
}
