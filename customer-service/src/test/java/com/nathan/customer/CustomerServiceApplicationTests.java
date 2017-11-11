package com.nathan.customer;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nathan.customer.resource.CustomerServiceResource;


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

	/**
	 * Test /ping
	 * @throws Exception
	 */
	@Test
	public void noParamPingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/v1/ping")).andDo(print()).andExpect(status().isOk())
				.andDo(document("ping", responseFields(fieldWithPath("message").description("Response test message")

		)));

	}

}
