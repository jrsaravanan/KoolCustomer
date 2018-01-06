package com.nathan.customer.auth.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.customer.auth.dto.LoginRequest;
import com.nathan.customer.auth.dto.PingResponse;
import com.nathan.customer.auth.entity.Account;
import com.nathan.customer.auth.service.AuthService;


@RestController
@RequestMapping("v1.0/auth")
public class AuthResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthResource.class);
	
	private static final String template = "Hello, %s!";
	
	@Autowired
	private AuthService authService;
	
	/**
	 * Health Check just an additional method 
	 * Laster can be extended to check integration services health
	 */
	@RequestMapping(method = RequestMethod.GET , path ="/ping" , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.OK)
	public PingResponse ping(@RequestParam(value = "name", defaultValue = "World") String name) {
		LOGGER.debug("Ping service .......");
		return new PingResponse(String.format(template, name));
	}
	
	
	/**
	 * Get customer details by id 
	 */
	@RequestMapping(method = RequestMethod.POST  , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE } , 
			consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE } )
	public Account login(@RequestBody LoginRequest user) {
		return authService.findUser(user);
	}
}
