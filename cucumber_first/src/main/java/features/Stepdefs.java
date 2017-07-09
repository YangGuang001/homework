package features;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;

/**
 * Created by yz on 2017/6/29.
 */
public class Stepdefs {
    String loginResult = null;
    boolean flag = false;
    @Given("^存在用户账号为 (\\S*) ，密码为 (\\S*)$")
    public void hasUser(String no, String password) throws Throwable{
        if (no.equals("13800138000") && password.equals("12345678")){
            flag = true;
        }
        throw new PendingException();
    }

    @When("^用账号 (\\S*) ，密码 (\\S*) 登录$")
    public void login(String phone, String password) throws Throwable{
        if (flag && phone.equals("13800138000")){
            loginResult = "登录成功";
            System.out.println("login successful");
        } else {
            throw  new Throwable();
        }
    }

    @Then("返回结果为 (\\S*)")
    public void checkResult(String expect){
        if (expect.equals(loginResult)){
            System.out.println("返回结果正确!!!");
        } else {
            System.out.println("失败!!!");
        }
    }
}
