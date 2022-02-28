@comment
Feature: Comment tests
  This feature tests the Comments service

  Scenario: All comments are queried
    Given all the comments are queried from Comments service
    When the service response is "200"
    Then there exist one or more comments

  Scenario: Comment is queried by comment id
    When a comment is queried by id "14"
    Then the service response is "200"
    And the id of queried comment is "14"
    And the postId of queried comment is "3"
    And the email of queried comment is "Nathan@solon.io"

  Scenario: Comments are queried by post id
    When all the comments are queried from Comments service for postId "3"
    Then the service response is "200"
    And there exist "5" comments
    