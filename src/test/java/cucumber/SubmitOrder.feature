@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
  
  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I added product <productName> to Cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name													| password 			| productName  |
      | shankarnrraibagi@gmail.com    | Shankar@007   | ZARA COAT 3	 |
