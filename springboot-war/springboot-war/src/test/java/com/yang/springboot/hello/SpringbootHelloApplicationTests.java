package com.yang.springboot.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringbootHelloApplicationTests {

	@Test
	public void contextLoads() {
		System.setProperty("test.demo", "222222");
		System.out.println(Integer.getInteger("test.demo", 111111));
	}

	@Test
	public void testSpringbootWar() {
		Server server = new Server(8080);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setWar("E:\\java\\homework\\homework\\springboot-war\\springboot-war\\target\\springboot-war-0.0.1-SNAPSHOT.war");
		webAppContext.setContextPath("/");
		server.setHandler(webAppContext);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
