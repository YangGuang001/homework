package com.yang.Observable;

import java.util.Observable;

/**
 * Created by yz on 2018/1/30.
 */
public class Hanfeizi extends Observable implements IHanfeizi {
    public void haveBreakfast() {
        System.out.println("have breakfast");
        super.setChanged();
        super.notifyObservers("have breakfast!!!!!");
    }

    public void havefun() {
        System.out.println("have fun");
        super.setChanged();
        super.notifyObservers("have fun!!!!");
    }
}
