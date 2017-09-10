package com.yang.spring.autoWire;

import com.yang.spring.test.HelloApi;

/**
 * Created by yz on 2017/9/3.
 */
public abstract class AbstractPrinter implements HelloApi {
    private Printer printer;
    public void say() {
        printer.print("setter");
        createPrototypePrinter().print("prototype");
        createSingletonPrinter().print("singleton");
    }

    public abstract Printer createPrototypePrinter();

    public Printer createSingletonPrinter() {
        System.out.println("this method is exec, this is wrong.");
        return new Printer();
    }

    public void setPrinter(Printer printer){
        this.printer = printer;
    }
}
