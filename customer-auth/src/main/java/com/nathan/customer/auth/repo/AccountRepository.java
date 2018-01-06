package com.nathan.customer.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nathan.customer.auth.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByUsername(String name);

}
