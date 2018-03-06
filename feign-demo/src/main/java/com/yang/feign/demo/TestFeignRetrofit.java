package com.yang.feign.demo;


import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.junit.Test;

public class TestFeignRetrofit {
    @Test
    public void testNormalFeign() {
        RemoteService service = Feign.builder()
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://127.0.0.1:8000");

        String result = service.getOwner("yang");
        System.out.println(result);
    }

    @Test
    public void testUserFeign() {
        UserService service = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(UserService.class, "http://127.0.0.1:8000");

        User result = service.getOwer(1);
        System.out.println(result);
    }
}
