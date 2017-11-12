package com.nathan.customer.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server for Customer Service application 
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class CustomerDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDiscoveryApplication.class, args);
	}
}
