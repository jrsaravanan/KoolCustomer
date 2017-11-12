package com.nathan.customer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nathan.customer.entity.Customer;

/**
 * Customer Repository CRUD operations extends from {@link CrudRepository}
 */
public interface CustomerRepository  extends  CrudRepository<Customer, Long> {

	/**
	 * Retrieve all customers
	 */
	List<Customer> findAll();
	
	/**
	 * find customer by id
	 * @param id
	 * @return {@link Customer}
	 */
	Customer findById(Long id);
	
	/**
	 * find by LastName
	 */
	
	Customer findByLastname(String lastname);
}
