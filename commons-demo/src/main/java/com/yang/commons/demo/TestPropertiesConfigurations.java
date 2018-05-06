package com.yang.commons.demo;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

/**
 * Created by yz on 2018/5/3.
 */
public class TestPropertiesConfigurations {

    @Test
    public void testPropertiesConfiguration() {
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration("application.properties");
            String str = configuration.getString("demo", "yang");
            System.out.println("string result: " + str);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
