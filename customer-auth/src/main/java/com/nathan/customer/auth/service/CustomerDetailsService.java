package com.nathan.customer.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nathan.customer.auth.repo.AccountRepository;

@Service
public class CustomerDetailsService  implements UserDetailsService {

	private final AccountRepository accoutRepository;
	
	@Autowired
	public CustomerDetailsService(AccountRepository accountRepository) {
		this.accoutRepository = accountRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		return accoutRepository.findByUsername(name)
			.map(account -> new User(account.getUsername(), account.getPassword(), 
					AuthorityUtils.createAuthorityList("ROLE_ADMIN")))
			.orElseThrow( () -> new UsernameNotFoundException("Invalid user"));
	}

}
