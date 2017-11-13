package com.nathan.customer;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nathan.customer.resource.CustomerServiceResource;
import com.nathan.customer.response.CustomerResponse;
import com.nathan.customer.service.CustomerService;


/**
 * Customer Service Resource endpoint testing also generates REST documentation
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerServiceResource.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CustomerServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;

	/**
	 * Test /ping
	 * @throws Exception
	 */
	@Test
	public void noParamPingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/v1.0/customers/ping")).andDo(print()).andExpect(status().isOk())
				.andDo(document("ping", responseFields(fieldWithPath("message").description("Response test message")

		)));

	}

	
	@Test
	public void getCustomerById() throws Exception {
		
		CustomerResponse response = new CustomerResponse();
		response.setFirstName("TEST_FIRST_NAME");
		response.setLastName("TEST_LAST_NAME");
		
		given(customerService.getCustomer(anyLong())).willReturn(response);

		mockMvc.perform(get("/v1.0/customers/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.firstName", is(response.getFirstName())))
			      .andDo(
			    	 document("customer-id",  responseFields (
			    			 	fieldWithPath("firstName").description("First Name") ,
			    			 	fieldWithPath("lastName").description("Last Name")
			    			 )));

	}
	
	@Test
	public void getCustomers() throws Exception {
		
		CustomerResponse response = new CustomerResponse();
		response.setFirstName("TEST_FIRST_NAME");
		response.setLastName("TEST_LAST_NAME");
		
		List<CustomerResponse> collection = Arrays.asList(response);
		
		given(customerService.getCustomers()).willReturn(collection);

		mockMvc.perform(get("/v1.0/customers")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$[0].firstName", is(response.getFirstName())))
			      .andDo(
			    	 document("customers",  responseFields (
			    			 	fieldWithPath("[].firstName").description("First Name") ,
			    			 	fieldWithPath("[].lastName").description("Last Name")
			    			 )));

	}
}
