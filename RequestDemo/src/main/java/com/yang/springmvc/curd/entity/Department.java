package com.yang.springmvc.curd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Department {
    private int id;
    private String departmentName;

    public Department() {}
}
