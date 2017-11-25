package com.nathan.customer.resource;

import static io.restassured.RestAssured.given;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomerNotFoundStepsDef {

	public static final Logger LOGGER = LoggerFactory.getLogger(CustomerNotFoundStepsDef.class);

	private Response response;
	private RequestSpecification request;
	private String CUSTOMERS_URI = "http://localhost:8090/v1.0/customers";


	@Given("^client provide  customerId (\\d+)$")
	public void client_provide_customerId(int customerId) throws Throwable {
		request = given()
				.pathParam("customerId", customerId)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON);
	}

	@When("^customer service  /customers/<customerId> resource$")
	public void customer_service_customers_customerId_resource() throws Throwable {
		response = request
				.when()
				.get(CUSTOMERS_URI + "/{customerId}");
		LOGGER.info("response: {}  " , response.prettyPrint());
	}

	@Then("^customer should get response code (\\d+)$")
	public void customer_should_get_response_code(int statusCode) throws Throwable {
		response.then().statusCode(statusCode);
	}
	
}
