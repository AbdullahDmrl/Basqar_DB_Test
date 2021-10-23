Feature: DB Testing with JDBC

  Background:
    Given Navigate to basqar
    When Enter username and password and click login button
    Then User should login successfully

  @Regression
  Scenario: Testing Country names
    And Navigate to country page
    Then Send the query the database "select * from Country" and control match

  @Regression
  Scenario: Testing cities names
    When  Navigate to cities page and select country name as "Deutschland"
    Then Send the query the database "select * from Cities" and control match
