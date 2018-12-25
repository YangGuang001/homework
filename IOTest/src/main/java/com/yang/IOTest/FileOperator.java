package com.yang.IOTest;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testIsAsserFrom() {
        System.out.println(Object.class.isAssignableFrom(ArrayList.class));
    }

    @Test
    public void testArray() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        String[] liststr = list.toArray(new String[list.size()]);
        System.out.println(liststr[0]);
        System.out.println(Arrays.toString(liststr));

        List<String> stringList = Arrays.asList(liststr);
        System.out.println(stringList.toArray());
    }

    @Test
    public void testProxy() {
        final List<String> list = new ArrayList<String>();

        Object object = Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object object  = method.invoke(proxy, args);
                System.out.println("add a object");
                if (method.getName().equals("size")) {
                    return 100;
                }
                return object;
            }
        });

        List list1 = (List) object;
        list1.add("aaa");
        list1.add("bbb");

        list1.forEach(a -> System.out.println(a));
        System.out.println("size:" + list1.size() + "," + list1.size());
    }

}
