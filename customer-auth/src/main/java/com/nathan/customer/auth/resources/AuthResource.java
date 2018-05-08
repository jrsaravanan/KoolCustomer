package com.nathan.customer.auth.resources;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.customer.auth.service.AuthService;


@RestController
public class AuthResource {

	@RequestMapping (method = RequestMethod.GET, path="/user")
	public Principal principal(Principal principal) {
		return principal;
	}
	
}
