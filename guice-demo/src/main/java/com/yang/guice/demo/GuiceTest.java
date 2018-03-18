package com.yang.guice.demo;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import org.junit.Test;

import java.util.Map;


/**
 * Created by yz on 2018/3/18.
 */
public class GuiceTest {
    @Test
    public void testAdd() {
        Injector injector = Guice.createInjector(new AddModule());
        Map<Key<?>, Binding<?>> bindings = injector.getBindings();
        Add add = injector.getInstance(Add.class);
        System.out.println(add.add(10, 54));

    }

    @Test
    public void testSimpleAdd() {
        Injector injector = Guice.createInjector();
        SimpleAdd simpleAdd = injector.getInstance(SimpleAdd.class);
        System.out.println(simpleAdd.add(10, 10));
    }

    @Test
    public void testInject() {
        Injector injector = Guice.createInjector();
        Person person = injector.getInstance(Person.class);
        person.diplayInfo();
    }
}
