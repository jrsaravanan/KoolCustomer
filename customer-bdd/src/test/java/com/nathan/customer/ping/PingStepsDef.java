package com.nathan.customer.ping;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java8.En;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PingStepsDef implements En {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PingStepsDef.class);
	
	private Response response;
	private RequestSpecification request;
    private String ENDPOINT_GET_PING = "http://localhost:8080/v1.0/customers/ping";
    
	
	public PingStepsDef() {
		
		Given("^the client send (.*) message$", (String message) -> {
			request = given().queryParam("name", message);
		});

		When("^the client GET ping$", () -> {
			response = request.when().get(ENDPOINT_GET_PING);
			LOGGER.info("response: {}  " , response.prettyPrint());
			
		});

		Then("^the client receives status code of (\\d+)$", (Integer statusCode) -> {
		    response.then().statusCode(statusCode)
		    .and()
		   .body("message", equalTo("Hello, sai!"));
		     
		});
	}

}
