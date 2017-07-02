package com.yang.homeworkTest;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yz on 2017/6/28.
 */
public class ManagerPerson {
    private Set<Person> allPerson;

    public ManagerPerson(){
        allPerson = new HashSet<Person>();
    }

    public void addPerson(Person person){
        allPerson.add(person);
    }

    public boolean removePerson(Person person){
        if (allPerson.size() == 0 || !allPerson.contains(person)){
            return false;
        }

        allPerson.remove(person);
        return true;
    }

    public boolean contains(String name){
        for (Person person : allPerson){
            if (person.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Person getPerson(String name){
        for (Person person : allPerson){
            if (person.getName().equals(name)){
                return person;
            }
        }
        return null;
    }

    public int countOfSameYear(int year){
        int count = 0;
        for (Person person : allPerson){
            if (person.getYear() - person.getAge() == year){
                count++;
            }
        }
        return count;
    }
}
