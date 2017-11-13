package com.nathan.customer.service;

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
}
