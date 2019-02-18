Feature: Greet users
  As a friendly rest service I would like to be able to greet users so that they will like me

  Scenario: Can greet a user by their name
    Given the user has a valid name
    When the greeting endpoint is used with this name
    Then a greeting is returned to the user
