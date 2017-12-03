package com.nathan.customer.utils;

public class TestUtils {

	private static String CUSTOMERS_URI = "http://localhost:8090/v1.0/customers";
	
	public static String getCustomerUri() {
		
		String endpoint = System.getenv("CUSTOMERS_URI");
		
		if (null != endpoint) {
			return endpoint;
		} else {
			return CUSTOMERS_URI;
		}
		
		
	}
	
}
