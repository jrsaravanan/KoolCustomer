package com.nathan.customer.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.customer.response.PingResponse;

/**
 * Customer Service Resource
 * 
 * @author Saravanan Renganathan
 *
 */
@RestController
@RequestMapping("v1")
public class CustomerServiceResource {

	
	private static final String template = "Hello, %s!";
	
	@RequestMapping(method = RequestMethod.GET , path ="/ping" , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	public PingResponse ping(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new PingResponse(String.format(template, name));
	}

}
