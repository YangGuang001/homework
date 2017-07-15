package com.yang.yaml;

import org.ho.yaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yz on 2017/7/9.
 */
public class TestYaml {
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File(System.getProperty("user.dir") + "/IOTest/test.yaml");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        try {
            line = reader.readLine();
            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        writeYaml();
//        try {
//            readYaml();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static void readYaml() throws FileNotFoundException{
        File file = new File("test.yaml");
        Person father = (Person) Yaml.loadType(file, Person.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(father.getName())
                .append("\t")
                .append(father.getSex())
                .append("\t")
                .append(father.getAge())
                .append("\t")
                .append(father.getChildren());
        System.out.println(stringBuilder.toString());
    }

    public static void writeYaml(){
        Person father = new Person();
        father.setName("yang");
        father.setAge(18);
        father.setSex("男");
        List<Person> children = new ArrayList<Person>();
        father.setChildren(children);
        for (int i = 1; i < 3; i++){
            Person child = new Person();
            if (i % 2 == 0){
                child.setName("xin");
                child.setAge(i);
                child.setSex("男");
            } else {
                child.setName("zhao");
                child.setSex("女");
                child.setAge(18);
            }
            children.add(child);
        }

        File file = new File("test.yaml");
        try {
            Yaml.dump(father, file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
