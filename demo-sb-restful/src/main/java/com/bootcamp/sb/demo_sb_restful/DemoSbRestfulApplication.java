package com.bootcamp.sb.demo_sb_restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoSbRestfulApplication {

	public static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		context = SpringApplication.run(DemoSbRestfulApplication.class, args);
	}

}
