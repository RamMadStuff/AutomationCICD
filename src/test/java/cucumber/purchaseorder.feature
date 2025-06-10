Feature: Title of your feature

  Background:
    Given I landed on Ecommerce website
@Regression
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples:
      | name                    | password   | productName     |
      | sairampadala@gmail.com | Sairam@123 | ADIDAS ORIGINAL |
