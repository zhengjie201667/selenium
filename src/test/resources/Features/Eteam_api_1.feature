@tag
Feature: api test 1
  I want to check the api

  Scenario Outline: Construct json file
    Given I want to construct a json <name>
    When I replace the <value> in sequence
    Then I upload the file into server

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
