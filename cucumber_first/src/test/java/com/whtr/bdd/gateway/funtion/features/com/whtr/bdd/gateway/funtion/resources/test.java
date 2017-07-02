package com.whtr.bdd.gateway.funtion.features.com.whtr.bdd.gateway.funtion.resources;

import cucumber.api.cli.Main;

import java.io.IOException;

/**
 * Created by yz on 2017/7/2.
 */
public class test {
    public static void main(String[] args) throws IOException{
        String[] myArgs = {
//                            "--plugin",
//                           "org.jetbrains.plugins.cucumber.java.run.CucumberJvmSMFormatter",
                           "--monochrome",
                           "--name",
                           "^作为一个已注册的用户,输入正确的账号和密码能正常登录$",
                           "--glue",
                           "com.whtr.bdd.gateway.funtion.features",
                           "E:/java/homework/homework/cucumber_first/src/test/resources/com.whtr.bdd.gateway.function.features/RegisterTest.feature"};
        Main.run(myArgs,Thread.currentThread().getContextClassLoader());
    }
}
