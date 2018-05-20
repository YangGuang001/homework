package com.yang.springboot.hello;

import com.springboot.aotuconfig.hello.Hello;
import com.yang.springboot.hello.enable.demo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Configuration
//@Import({
//		DispatcherServletAutoConfiguration.class,
//		EmbeddedServletContainerAutoConfiguration.class,
//		ErrorMvcAutoConfiguration.class,
//		HttpEncodingAutoConfiguration.class,
//		HttpMessageConvertersAutoConfiguration.class,
//		JacksonAutoConfiguration.class,
//		MultipartAutoConfiguration.class,
//		ServerPropertiesAutoConfiguration.class,
//		WebMvcAutoConfiguration.class,
//		HelloAutoConfiguration.class
//})
//@ComponentScan
@RestController
@SpringBootApplication
@EnableLogInfo(name = "onlySale")
public class SpringbootHelloApplication  extends SpringBootServletInitializer {

	@Autowired
	private Hello hello;

	@RequestMapping("/hello")
	public String hello() {
		return hello.sayHello();
	}

	public static void main(String[] args) {
		SpringApplication context = new SpringApplication(SpringbootHelloApplication.class);
		context.addListeners(new MyApplicationStartedEventListener());
		context.run(args);


//		System.out.println(context.getBean(MyImportSelector.class));
//		System.out.println(context.getBean(Market.class));
//		System.out.println(context.getBean(Sales.class));
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootHelloApplication.class);
	}
}
