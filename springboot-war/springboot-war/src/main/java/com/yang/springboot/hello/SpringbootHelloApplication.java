package com.yang.springboot.hello;

import com.yang.springboot.hello.enable.demo.EnableLogInfo;
import com.yang.springboot.hello.enable.demo.MyApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Properties;


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
public class SpringbootHelloApplication extends SpringBootServletInitializer {

	@RequestMapping("/hello")
	public String hello() {
		return "call";
	}

	public static void main(String[] args) {
		SpringApplication context = new SpringApplication(SpringbootHelloApplication.class);
		context.addListeners(new MyApplicationStartedEventListener());
		context.run(args);
	}
}
