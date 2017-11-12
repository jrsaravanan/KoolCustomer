package com.nathan.customer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nathan.customer.entity.Customer;

public interface CustomerRepository  extends  CrudRepository<Customer, Long> {

	List<Customer> findAll();
	Customer findById(Long id);
}
