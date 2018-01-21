package com.yang.hashcode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by yz on 2018/1/13.
 */
@Data
@AllArgsConstructor
public class Person implements Cloneable{
    private String name;
    private int age;

    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap<Person, String> map = new HashMap<Person, String>();
        Person person = new Person("yang", 18);
        map.put(person, "name");
        Person person1 = new Person("yang", 18);
        System.out.println(person1.equals(person));
        System.out.println(person1.hashCode() + ":" + person.hashCode());
        System.out.println(map.get(person1));
        System.out.println(map.get(person1.clone()));
        System.out.println(person1.clone() == person1);
        Child child = new Child("xin", 2, "drak");
        System.out.println(child.clone().getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getAge() != person.getAge()) return false;
        return getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        int result = 10;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge();
        return result;
    }
}
