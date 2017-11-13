package com.nathan.customer.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.customer.entity.Customer;
import com.nathan.customer.repository.CustomerRepository;
import com.nathan.customer.response.CustomerResponse;

/**
 * Customer Service 
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public CustomerResponse getCustomer(Long id) {
		Customer entity = customerRepository.findById(id);
		return toResponse(entity);
	}
	
	private CustomerResponse toResponse(Customer entity) {
		return modelMapper.map(entity, CustomerResponse.class);
	}

}
