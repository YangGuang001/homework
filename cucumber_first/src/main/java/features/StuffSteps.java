package features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by yz on 2017/7/2.
 */
public class StuffSteps {
    private final Map<String,Integer> cukes = new HashMap<String, Integer>();

    @Given("I have (\\d+) (.*) cukes")
    public void iHaveNCukes(int n, String color){
        this.cukes.put(color,n);
    }

    @When("I add a table")
    public void iAddTable(){
//        List<Entry> users
//        for (Entry entry : users){
//            cukes.put(entry.name,entry.age);
//            System.out.println("name: " + entry.name + " age: " + entry.age);
//        }
    }

    @Then("I should have (\\d+) (.*) cukes")
    public void iShouldHaveNCukes(int n, String color) {
        int number = cukes.get(color) != null ? cukes.get(color) : 0;
        assertEquals(n, number);
    }

    public class Entry{
        String name;
        Integer age;
    }
}
