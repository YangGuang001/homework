package features;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by yz on 2017/6/29.
 */
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
            features = "classpath:com.whtr.bdd.gateway.function.features/")
//            glue = "com.whtr.bdd.gateway.funtion.features")
public class RunAllTest {

//    @BeforeClass
//    public static void beforeClass(){
//        System.out.println("beforeclass!!!");
//    }
//
//    @AfterClass
//    public static void afterClass(){
//        System.out.println("afterclass!!!");
//    }
//
//    @Before
//    public void before(){
//        System.out.println("before!!!!");
//    }
//
//    @After
//    public void after(){
//        System.out.println("after");
//    }
}
