package com.nathan.customer.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthenticationException  extends RuntimeException {

	public AuthenticationException() {
		super("Unable to authenticate User id or password might be wrong.");
	}
}
