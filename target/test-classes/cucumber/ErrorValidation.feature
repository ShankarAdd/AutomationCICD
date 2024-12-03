
@tag
Feature: Error validation by Cucumber
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Incorrect username and password
    Given I landed on Ecommerce Page
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
     | name													| password 			|
     | shankarnrraibagi@gmail.com   | Shankar007    |
