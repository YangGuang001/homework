package com.yang.homeworkTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yz on 2017/6/28.
 */
public class AgeSearch {
    private ManagerPerson managerPerson;
    private String path;
    public AgeSearch(String path){
        this.path = path;
        init();
    }

    private void init(){
        this.managerPerson = new ManagerPerson();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                String[] words = line.split("\\,");
                Person person = new Person(words[0],Integer.valueOf(words[1]),Integer.valueOf(words[2]));
                this.managerPerson.addPerson(person);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public int search(String name, String year){
        if (!this.managerPerson.contains(name)){
            return -1;
        }
        Person person = this.managerPerson.getPerson(name);
        return Integer.valueOf(year) - person.getYear() + person.getAge();
    }

    public int count(String year, int age){
        int borthYear = Integer.valueOf(year) - age;
        return this.managerPerson.countOfSameYear(borthYear);
    }
}
