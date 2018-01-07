package com.nathan.customer.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidTokenException  extends RuntimeException {

	public InvalidTokenException() {
		super("Invalid token provided");
	}
}
