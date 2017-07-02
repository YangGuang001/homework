package com.yang.IOTest;

import java.io.*;
import java.net.URLConnection;

/**
 * Created by yz on 2017/6/25.
 */

public class FileOperator {

    public String ReadFile(String path){
        File file = new File(path);
        if (!file.exists()){
            return new String("");
        }

        StringBuffer stringBuffer = new StringBuffer();

        try {
            FileReader fileReader = new FileReader(file);
            int len = -1;
            char[] chars = new char[1024];
            while ((len = fileReader.read(chars)) != -1) {
                if (len == 1024){
                    stringBuffer.append(chars);
                } else {
                    stringBuffer.append(new String(chars,0,len));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
