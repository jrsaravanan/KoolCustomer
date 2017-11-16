package com.nathan.customer.test.integration;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nathan.customer.dto.CustomerResponse;
import com.nathan.customer.resource.CustomerServiceResource;
import com.nathan.customer.service.CustomerService;
import com.nathan.customer.test.conf.CustomerApplicationTestConf;


/**
 * Customer Service Resource endpoint testing also generates REST documentation
 *
 */
@RunWith(SpringRunner.class)  
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@ContextConfiguration (classes = CustomerApplicationTestConf.class )
@WebMvcTest(CustomerServiceResource.class)
public class CustomerServiceApplicationIntegrationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * Test /ping
	 * @throws Exception
	 */
	@Test
	public void noParamPingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/v1.0/customers/ping")).andDo(print()).andExpect(status().isOk()
				

		);

	}

	
 
}
