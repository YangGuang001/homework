package com.yang.springboot.database.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable{
    private static final long serialVersionUID = 111111L;

    private int id;
    private String name;
    private String sumScore;
    private String avgScore;
    private int age;

}
