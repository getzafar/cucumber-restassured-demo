@user
Feature: User tests
  This feature tests the Users service

  Scenario: All users are queried
    Given all the users are queried from Users service
    When the service response is "200"
    Then there exist one or more users

  Scenario: User is queried by id
    When a user is queried by id "3"
    Then the service response is "200"
    And the id of queried user is "3"
    And the username of queried user is "Samantha"