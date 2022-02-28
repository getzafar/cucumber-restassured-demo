@post
Feature: Post tests
  This feature tests the Posts service

  Scenario: All posts are queried
    Given all the posts are queried from Posts service
    When the service response is "200"
    Then there exist one or more posts

  Scenario: Post is queried by post id
    When a post is queried by id "37"
    Then the service response is "200"
    And the id of queried post is "37"
    And the userid of queried post is "4"

  @comment
  Scenario: All comments of a post are queried by post id
    When all the comments are queried from Posts service by post id "6"
    Then the service response is "200"
    And there exist one or more comments for the post

  Scenario: A new post is created
    Given a new post is created
      | userId | 3                    |
      | title  | A brand new title    |
      | body   | Body of the new post |
    Then the service response is "201"
    And the id of newly created post is "101"

  Scenario: An existing post is updated
    Given a post is queried by id "37"
    Then the service response is "200"
    When this post is updated with below details
      | title | New title of existing post |
      | body  | New body of existing post  |
    Then the service response is "200"

  Scenario: An existing post is patched
    Given a post is queried by id "37"
    Then the service response is "200"
    When "body" of this post is patched as "Patched body of existing post"
    Then the service response is "200"

  Scenario: An existing post is deleted
    Given a post is queried by id "37"
    Then the service response is "200"
    When this post is deleted
    Then the service response is "200"
