package com.nathan.customer.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.nathan.customer.entity.Customer;
import com.nathan.customer.exception.CustomerNotFoundException;
import com.nathan.customer.repository.CustomerRepository;
import com.nathan.customer.resource.CustomerServiceResource;
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
		return Optional.ofNullable(entity)
				.map(p -> toResponse(entity))
				.orElseThrow(() -> new CustomerNotFoundException(id));
	}
	
	private CustomerResponse toResponse(Customer entity) {
		CustomerResponse response = modelMapper.map(entity, CustomerResponse.class);
		Link selfLink = linkTo(CustomerServiceResource.class).slash(entity.getId()).withSelfRel();
        response.add(selfLink);
        return response;
	}

	@Override
	public List<CustomerResponse> getCustomers() {
		
		List<Customer> collection = customerRepository.findAll();
		
		return collection.stream()
			.map(p -> toResponse(p))
			.collect(Collectors.toList());
	}
	

}
