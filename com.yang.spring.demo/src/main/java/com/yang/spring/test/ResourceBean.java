package com.yang.spring.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yz on 2017/8/21.
 */
public class ResourceBean {
    private FileOutputStream fos;
    private File file;

    public void init(){
        System.out.println("ResourceBean :====init()=====初始化");
        System.out.println("ResourceBean:=====加载资源,执行预操作");
        try {
            this.fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void destroy(){
        System.out.println("ResourceBean:=====销毁");
        System.out.println("ResourceBean:=====释放资源,执行一些清理操作");
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileOutputStream getFos(){
        return this.fos;
    }

    public void setFile(File file){
        this.file = file;
    }
}
