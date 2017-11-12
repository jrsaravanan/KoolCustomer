package com.nathan.customer.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * SpringBoot admin Server for customer application
 *
 */
@EnableAdminServer
@SpringBootApplication
public class CustomerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAdminApplication.class, args);
	}
}
