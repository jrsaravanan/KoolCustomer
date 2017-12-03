package com.nathan.customer.resource;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class CustomerBaseDef {
	
	public Response response;
	public RequestSpecification request;
	public String CUSTOMERS_URI = "http://localhost:8090/v1.0/customers";
	
	@Then("^customer should get response code (\\d+)$")
	public void customer_should_get_response_code(int statusCode) throws Throwable {
		response.then().statusCode(statusCode);
	}

}
