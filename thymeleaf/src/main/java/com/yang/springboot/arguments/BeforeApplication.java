package com.yang.springboot.arguments;

import com.yang.springboot.domain.ConfigMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BeforeApplication implements ApplicationRunner {

    @Value("${name}")
    private String name;

    @Autowired
    private ConfigMap map;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.printf("args :" + args.toString() + name + map.getServers().toString());
    }
}
