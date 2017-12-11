package com.yang.springboot.arguments;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class DeathOfApplication implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        System.out.printf("Application shutdown!!!!");
        return 0;
    }
}
