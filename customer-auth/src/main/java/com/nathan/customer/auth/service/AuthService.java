package com.nathan.customer.auth.service;

import com.nathan.customer.auth.entity.Account;

public interface AuthService {


	Account findUserByName(String name);

}
