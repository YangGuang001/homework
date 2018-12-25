package com.yang.jedis.demo;

import redis.clients.jedis.Jedis;

public class JedisDemo {
    
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");

//        jedis.set("foo", "yang");
//        System.out.printf("value:" + jedis.get("foo"));

//        jedis.set("test".getBytes(), "xin".getBytes());
//        System.out.println(new String(jedis.get("test".getBytes())));

        jedis.hset("demo".getBytes(), "demo".getBytes(), "demo".getBytes());
        System.out.println(jedis.hgetAll("demo".getBytes()).values());


    }
}
