package com.yang.springboot.mybits.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "city")
@Data
public class City {

    @Id
    private String id;
    private String name;
    private String state;

}
