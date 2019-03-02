package com.yang.proxyDymic;


import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

public class MyProxy {

    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {
        //生成代理字节码java文件
        String path = generateJava(interfaces);
        //编译java文件为class文件
        compileJava(path);

        Class proxyClass = null;
        try {
            //动态加载到jvm中
            proxyClass = loader.findClass(path);
            Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
            return constructor.newInstance(h);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void compileJava(String path) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = ToolProvider.getSystemJavaCompiler().getStandardFileManager(null, null, null);
        Iterable iterable = manager.getJavaFileObjects(new File(path));
        JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, manager, null, null, iterable, null);
        compilationTask.call();
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateJava(Class<?>[] interfaces) {


        return "";
    }
}
