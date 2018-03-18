package com.yang.factorybean.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by yz on 2018/3/15.
 */
@Data
@AllArgsConstructor
@ToString
public class Car {
    private String brand;
    private double price;
}
