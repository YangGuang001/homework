package com.yang.springboot.test.swagger2;

import lombok.Data;

@Data
public class JsonResult {
    private String status = null;

    private Object result = null;
}
