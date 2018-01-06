package com.nathan.customer.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class CustomerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAuthApplication.class, args);
	}
}
