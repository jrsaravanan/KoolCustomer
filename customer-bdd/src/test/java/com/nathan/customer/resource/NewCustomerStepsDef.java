package com.nathan.customer.resource;

import static io.restassured.RestAssured.given;

import com.nathan.customer.utils.TestUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Test;

public class NewCustomerStepsDef {

	class CustomerRequest {

		private Long customerId;
		private String fistName;
		private String lastName;

		public CustomerRequest(Long customerId, String fistName, String lastName) {
			super();
			this.customerId = customerId;
			this.fistName = fistName;
			this.lastName = lastName;
		}
	}

	public Response response;
	public RequestSpecification request;
	public String CUSTOMERS_URI = TestUtils.getCustomerUri();

	@When("^client call /customers with POST method$")
	public void client_call_customers_with_POST_method() throws Throwable {

		CustomerRequest body = new CustomerRequest(null, "john", "doe");
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body);

	}

	@Then("^client should able to add new customer$")
	public void client_should_able_to_add_new_customer() throws Throwable {
		response = request.post(CUSTOMERS_URI);
	}

	@Then("^client should get success code (\\d+)$")
	public void client_should_get_success_code(int statusCode) throws Throwable {
		response.then().statusCode(statusCode);
	}

	@When("^client call /customers with PUT method$")
	public void client_call_customers_with_PUT_method() throws Throwable {
		CustomerRequest body = new CustomerRequest(1L, "john2", "doe");
		request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(body);
	}

	@Then("^client should able to update customer$")
	public void client_should_able_to_update_customer() throws Throwable {
		response = request.put(CUSTOMERS_URI);
	}

	@Then("^client should get ok response (\\d+)$")
	public void client_should_get_ok_response(int statusCode) throws Throwable {
		response.then().statusCode(statusCode);
	}

}
