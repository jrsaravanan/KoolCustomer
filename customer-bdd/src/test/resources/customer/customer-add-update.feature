Feature: Add  and update Customers 
	As a customer service client (client) , Client should have provision to add a new customer  
	
   
   Scenario: Add new customer 
	   When client call /customers with POST method
	   Then client should able to add new customer
	   And client should get success code 201
	   
	 Scenario: Update new customer 
	   When client call /customers with PUT method
	   Then client should able to update customer
	   And client should get ok response 200   