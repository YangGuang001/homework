package com.yang.springboot.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootHelloApplicationTests {

	@Test
	public void contextLoads() {
		System.setProperty("test.demo", "222222");
		System.out.println(Integer.getInteger("test.demo", 111111));
	}

}
