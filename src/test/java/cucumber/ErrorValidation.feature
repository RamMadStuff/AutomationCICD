
@tag
Feature: Title of your feature
  I want to use this template for my feature file
Background:
    Given I landed on Ecommerce website

  @ErrorValidation
  Scenario Outline: I landed on Ecommerce website
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed 

    Examples: 
      | name                    | password   | 
      | sairampadala@gmail.com  | Sairam123 | 
