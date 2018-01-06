package com.nathan.customer.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.customer.auth.entity.Account;
import com.nathan.customer.auth.repo.AccountRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private AccountRepository repository;

	@Autowired
	public AuthServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Account findUserByName(final String name) {
		return repository.findByUsername(name);
	}
}
