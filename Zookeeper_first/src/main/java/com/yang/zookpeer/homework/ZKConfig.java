package com.yang.zookpeer.homework;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yz on 2017/8/25.
 */
@Data
@AllArgsConstructor
public class ZKConfig {
    private String zkUrl;
    private String rootDir;
    private int sessionTimeout;
}
