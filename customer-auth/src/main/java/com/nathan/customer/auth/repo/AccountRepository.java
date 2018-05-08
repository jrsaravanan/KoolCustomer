package com.nathan.customer.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nathan.customer.auth.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUsername(String name);
}
