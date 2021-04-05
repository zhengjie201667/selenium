Feature: api test 1
  I want to check the api

  Scenario Outline: mutiple examples

    Given Construct json file with <id> <oid> <format> <PageIndex> 
    |id|oid|format|PageIndex|
    |<id>| <oid>|<format>|<PageIndex> |
    When I put the json into the server
    Then I check the data

    Examples: 
|id| oid | format | PageIndex | server  | value | status  |
|1|fujian | 456   | 12        | mark |     5 | success |
#|2|fuan | 654 | 21       | name2 |     7 | Fail    |  
