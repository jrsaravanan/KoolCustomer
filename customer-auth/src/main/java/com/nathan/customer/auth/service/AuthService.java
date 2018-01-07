package com.nathan.customer.auth.service;

import com.nathan.customer.auth.dto.AuthToken;
import com.nathan.customer.auth.dto.LoginRequest;
import com.nathan.customer.auth.entity.Account;

public interface AuthService {


	AuthToken login(LoginRequest user);

	AuthToken validate(String token);

}
