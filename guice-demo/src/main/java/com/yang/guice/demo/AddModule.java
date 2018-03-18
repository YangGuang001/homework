package com.yang.guice.demo;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Created by yz on 2018/3/18.
 */
public class AddModule implements Module {
    public void configure(Binder binder) {
        binder.bind(Add.class).to(SimpleAdd.class);
    }
}
