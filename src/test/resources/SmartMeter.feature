Feature: Retrieving smart meter readings from back end API

  Scenario: Client requests meter readings with various account inputs
    Given the application has started up correctly
    Then the client can successfully request an account in the database
    And the client gets a bad request exception for bad account input
    And the client gets a not found exception for an account not in the database