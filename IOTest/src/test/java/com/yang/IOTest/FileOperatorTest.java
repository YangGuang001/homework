package com.yang.IOTest;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;


/**
 * Created by yz on 2017/6/25.
 */
public class FileOperatorTest extends TestCase {

    @Test
    public void testReadFile() throws Exception {
        FileOperator fileOperator = new FileOperator();
        String content = fileOperator.ReadFile("E:\\java\\homework\\homework\\IOTest\\src\\test\\java\\com\\yang\\IOTest\\1.txt");
        System.out.println(content);
    }



}