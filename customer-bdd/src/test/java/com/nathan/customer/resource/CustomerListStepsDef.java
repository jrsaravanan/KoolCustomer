package com.nathan.customer.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomerListStepsDef {

	public static final Logger LOGGER = LoggerFactory.getLogger(CustomerListStepsDef.class);

	private Response response;
	private RequestSpecification request;
	private String CUSTOMERS_URI = "http://localhost:8080/v1.0/customers";

	

	@When("^customer service client call /customers resource$")
	public void customer_service_client_call_customers_resource() throws Throwable {
		response = given().when().get(CUSTOMERS_URI);
		LOGGER.info("response: {}  " , response.prettyPrint());
	}
	
	@Then("^customer should receive List of customers$")
	public void customer_should_receive_List_of_customers() throws Throwable {

		response
		.then()
		.statusCode(200);
		
		//TODO : verify body content here
	}

	@Then("^client should receive HTTP status code (\\d+)$")
	public void client_should_receive_HTTP_status_code(int statusCode) throws Throwable {
		response.then().statusCode(statusCode);
	}

	
	@Given("^client provide  (\\d+)$")
	public void client_provide(int customerId) throws Throwable {
		request = given()
				.pathParam("customerId", customerId)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON);
				
	}

	
	@When("^customer service  /customers/(\\d+) resource$")
	public void customer_service_customers_resource(int customerId) throws Throwable {
		response = request
				.when()
				.get(CUSTOMERS_URI + "/{customerId}");
		LOGGER.info("response: {}  " , response.prettyPrint());
	}
	

	@Then("^customer should retrieve customers$")
	public void customer_should_retrieve_customers() throws Throwable {
		
		response
		.then()
		.statusCode(200)
	    .and()
	    .body("customerId", notNullValue());
		
	}
	
	
	// 
	
}
