package com.nathan.customer.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.customer.dto.CustomerRequest;
import com.nathan.customer.dto.CustomerResponse;
import com.nathan.customer.dto.PingResponse;
import com.nathan.customer.service.CustomerService;

/**
 * Customer Service Resource
 * 
 * @author Saravanan Renganathan
 *
 */
@RestController
@RequestMapping("v1.0/customers")
public class CustomerServiceResource {

	@Autowired
	private CustomerService customerService;
	
	
	private static final String template = "Hello, %s!";
	
	/**
	 * Health Check just an additional method 
	 * Laster can be extended to check integration services health
	 */
	@RequestMapping(method = RequestMethod.GET , path ="/ping" , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	public PingResponse ping(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new PingResponse(String.format(template, name));
	}
	
	
	/**
	 * Get customer details by id 
	 */
	@RequestMapping(method = RequestMethod.GET , path ="" , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	public List<CustomerResponse> getAllCustomers() {
		return customerService.getCustomers();
	}
	
	
	/**
	 * Save
	 */
	@RequestMapping(method = RequestMethod.POST , path ="" , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE  } )
	public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest request) {
		CustomerResponse responseBody = customerService.saveCustomer(request);
		
		HttpHeaders headers = new HttpHeaders();
		//headers.set("Location", responseBody.getId().toString());
		return  new ResponseEntity<>(responseBody , headers , HttpStatus.CREATED);
	}
	
	/**
	 * Get customer details by id 
	 */
	@RequestMapping(method = RequestMethod.GET , path ="/{id}" , produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	public CustomerResponse getCustomer(@PathVariable("id") Long id) {
		return customerService.getCustomer(id);
	}

	
}
