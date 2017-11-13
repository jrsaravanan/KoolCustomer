package com.nathan.customer.service;

import java.util.List;

import com.nathan.customer.response.CustomerResponse;

/**
 *  Customer service 
 **/
public interface CustomerService {

	/**
	 * get customer by id , typical usecase
	 * @param id
	 * @return {@link CustomerResponse}
	 */
	public CustomerResponse getCustomer(Long id);
	
	/**
	 * get all customers
	 */
	public List<CustomerResponse> getCustomers();
	
}
