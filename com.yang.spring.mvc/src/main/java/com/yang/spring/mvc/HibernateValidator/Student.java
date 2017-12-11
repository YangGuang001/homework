package com.yang.spring.mvc.HibernateValidator;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Data
public class Student {
    @Range(max = 190, min = 1)
    private Integer age;
    @NotEmpty
    private String name;
    private Integer id;

}
