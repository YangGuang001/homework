package com.yang.easymock.test;

import java.io.IOException;

/**
 * Created by yz on 2017/7/26.
 */
public class SystemPropertyMockDemo
{
    public String getSystemProperty() throws IOException{
        return System.getProperty("property");

    }
}
