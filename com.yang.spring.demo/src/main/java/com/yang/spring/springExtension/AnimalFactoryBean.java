package com.yang.spring.springExtension;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Factory;

/**
 * Created by yz on 2017/9/4.
 */
public class AnimalFactoryBean implements FactoryBean<Animal> {
    private String animal;
    public Animal getObject() throws Exception {
        if ("Monkey".equals(animal))
        {
            return new Monkey();
        }
        else if ("Tiger".equals(animal))
        {
            return new Tiger();
        }
        else
        {
            return null;
        }
    }

    public Class<?> getObjectType() {
        return Animal.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setAnimal(String animal){
        this.animal = animal;
    }
}
