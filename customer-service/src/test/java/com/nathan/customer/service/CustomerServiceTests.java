package com.nathan.customer.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nathan.customer.config.ApplicationConfig;
import com.nathan.customer.entity.Customer;
import com.nathan.customer.exception.CustomerNotFoundException;
import com.nathan.customer.repository.CustomerRepository;
import com.nathan.customer.response.CustomerResponse;

@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
@RunWith(SpringRunner.class)
@SpringBootTest (classes = { CustomerServiceImpl.class , ApplicationConfig.class} )
public class CustomerServiceTests {


	@MockBean
	private CustomerRepository repository;
	
	@Autowired
	private CustomerService service;
	
	 
	 
	@Test
	public void testGetCustomerById() {
		
		given(this.repository.findById(anyLong())).willReturn(mock(Customer.class));
		CustomerResponse response = service.getCustomer(10L);
		assertThat(response, notNullValue());
		
	}
	
	@Test
	public void testGetCustomerReponse() {
		
		given(this.repository.findById(anyLong())).willReturn(mockCustomerObject());
		CustomerResponse response = service.getCustomer(10L);
		assertThat(response.getFirstName(),  equalTo("TEST_FIRST"));
		assertThat(response.getLastName(),  equalTo("TEST_LAST"));
		
	}
	
	@Test
	public void testGetAllCustomerReponse() {

		List<Customer> collection = Arrays.asList(mockCustomerObject());
		given(this.repository.findAll()).willReturn(collection);
		List<CustomerResponse> response = service.getCustomers();
		assertThat(response.get(0).getFirstName(), equalTo("TEST_FIRST"));
		assertThat(response.get(0).getLastName(), equalTo("TEST_LAST"));

	}
	
	@Test(expected = CustomerNotFoundException.class) 
	public void empty() { 
		given(this.repository.findById(anyLong())).willReturn(null);
		service.getCustomer(10L);
	}

	private Customer mockCustomerObject() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("TEST_FIRST");
		customer.setLastName("TEST_LAST");
		return customer;
	}
	
}