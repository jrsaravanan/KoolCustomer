Feature: To check customer application health
   Scenario: Customer application client checks health of the application
   Given the client send sai message
   When the client GET ping
   Then the client receives status code of 200
