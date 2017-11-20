package com.nathan.customer;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/customer")
public class CustomerBddApplicationTests  {

	@Test
	public void contextLoads() {
	}

}
