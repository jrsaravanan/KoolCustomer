Feature: Retrieve customers 
	As a customer service client should have provision to retrieve customer details , has to provide correct response HTTP codes when it is NOT available
	Retrieve all available customers
	Retrieve customer by id
	Retrieve customer by invalid id 
   
   Scenario: Client should receive all available customer
	   When customer service client call /customers resource
	   Then customer should receive List of customers
	   And client should receive HTTP status code 200

  Scenario Outline: Customer client should retrieve  customer details by id <customerId>
	   Given client provide  <customerId>
	   When customer service  /customers/<customerId> resource
	   Then customer should retrieve customers
	   And client should receive HTTP status code 200	  
	   
	   Examples:
	    | customerId | 
	    |   1		 |
	    |   2		 |
	    
  Scenario: Customer client get HTTP NOT_FOUND when invalid customerId provided
	   Given client provide  customerId 898390
	   When customer service  /customers/<customerId> resource
	   Then customer should get response code 404
	   	    