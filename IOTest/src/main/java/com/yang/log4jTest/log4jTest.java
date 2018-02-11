package com.yang.log4jTest;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import java.net.URL;


/**
 * Created by yz on 2018/2/10.
 */
public class log4jTest {
    private static final Logger LOGGER = Logger.getLogger(log4jTest.class);

    @Test
    public void testLog4jTest() throws Exception {
        //System.setProperty("log4j.debug", "true");
//        Logger logger = Logger.getLogger("com.DOMConfigurator");
//        logger.info("Logger before DOMConfigurator."); //#1

        //System.setProperty("log4j.defaultInitOverride", "true");
//        URL log4jXml = log4jTest.class.getResource("/log4j.xml");
//        DOMConfigurator.configure(log4jXml);
//        Logger logger1 = Logger.getLogger("com.DOMConfigurator");
//        logger1.info("Logger loggerDOMConfigurator.");//#2

        LOGGER.info("Logger loggerPropertyConfigurator."); //#3

        Logger logger2 = Logger.getLogger("com.text");
        logger2.debug("com.test.1 test !!!!");

        Logger logger3 = Logger.getLogger("com.test.2");
        logger3.debug("com.test.2 test !!!!");
    }
}
