package com.yang.guava.demo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoadingCacheTest {

    @Test
    public void testLoadingCacheTest() throws Exception{
        LoadingCache employeeCache = CacheBuilder
                .newBuilder()
                .maximumSize(100)
                .expireAfterAccess(20, TimeUnit.MILLISECONDS)
                .build(new CacheLoader() {
                    public Object load(Object empId) throws Exception {
                        return getFromDataBase((String) empId);
                    }
                });
        //on first invocation, cache will be populated with corresponding
        //employee record
        System.out.println("Invocation #1");
        System.out.println(employeeCache.get("100"));
        System.out.println(employeeCache.get("103"));
        System.out.println(employeeCache.get("110"));
        TimeUnit.MILLISECONDS.sleep(30);
        //second invocation, data will be returned from cache
        System.out.println("Invocation #2");
        System.out.println(employeeCache.get("100"));
        System.out.println(employeeCache.get("103"));
        System.out.println(employeeCache.get("110"));
    }

    private Object getFromDataBase(String empId) {
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map database = new HashMap();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for" + empId);
        return database.get(empId);
    }
}
