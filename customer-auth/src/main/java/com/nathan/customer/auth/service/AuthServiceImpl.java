package com.nathan.customer.auth.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nathan.customer.auth.dto.AuthToken;
import com.nathan.customer.auth.dto.LoginRequest;
import com.nathan.customer.auth.entity.Account;
import com.nathan.customer.auth.exception.AuthenticationException;
import com.nathan.customer.auth.exception.InvalidTokenException;
import com.nathan.customer.auth.repo.AccountRepository;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	private AccountRepository repository;
	private ConcurrentHashMap<String, AuthToken> users = new ConcurrentHashMap<>();

	@Autowired
	public AuthServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public AuthToken login(LoginRequest user) {

		Optional<Account> entity = repository.findByUsername(user.getUsername());
		return entity.map(p -> generateToken(p, user))
				.orElseThrow(() -> new AuthenticationException());

	}

	private AuthToken generateToken(Account entity, LoginRequest user) {

		if (user.getPassword().equals(entity.getPassword())) {
			return getUser(user.getUsername());
		} else {
			throw new AuthenticationException();
		}
	}

	private AuthToken getUser(String username) {

		AuthToken token = users.entrySet()
				.stream()
				.filter(e -> e.getValue().getUsername().equals(username))
				.map(Map.Entry::getValue)
				.findFirst().orElse(null);

		if (token != null) {
			token.setActiveTime(LocalDateTime.now());
		} else {
			token = new AuthToken();
			token.setToken(UUID.randomUUID().toString());
			token.setActiveTime(LocalDateTime.now());
			token.setUsername(username);
			users.put(token.getToken(), token);
		}
		return token;
	}

	@Override
	public AuthToken validate(String token) {

		AuthToken auth = users.get(token);
		if (auth == null) {
			throw new InvalidTokenException();
		}
		auth.setActiveTime(LocalDateTime.now());
		return auth;
	}

}
