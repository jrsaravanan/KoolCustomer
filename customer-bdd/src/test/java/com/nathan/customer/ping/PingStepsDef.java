package com.nathan.customer.ping;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PingStepsDef  {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PingStepsDef.class);
	
	private Response response;
	private RequestSpecification request;
    private String ENDPOINT_GET_PING = "http://localhost:8080/v1.0/customers/ping";
    
	
	/*public PingStepsDef() {
		
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
	}*/

    
    @Given("^the client send (.*) message$")
    public void the_client_send_sai_message(String message) throws Throwable {
    	request = given()
    			.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
    			.queryParam("name", message);
    }

    @When("^the client GET ping$")
    public void the_client_GET_ping() throws Throwable {
    	response = request.when().get(ENDPOINT_GET_PING);
		LOGGER.info("response: {}  " , response.prettyPrint());
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
    	response.then().statusCode(statusCode)
	    .and()
	   .body("message", equalTo("Hello, sai!"));
    }
    
    
}
