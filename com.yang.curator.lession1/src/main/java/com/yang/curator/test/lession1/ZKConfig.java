package com.yang.curator.test.lession1;

import lombok.Data;

@Data
public class ZKConfig {
    private String zkUrl;
    private String baseDir;
    private int sessionTimout;

}
