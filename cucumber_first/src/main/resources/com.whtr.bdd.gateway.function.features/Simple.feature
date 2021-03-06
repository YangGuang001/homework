Feature: Simple

  Scenario: 3 green cukes
    Given I have 3 green cukes
    When I add a table
    Then I should have 3 green cukes
    And I should have 0 yellow cukes

  Scenario: 4 green cukes
    Given I have 4 green cukes
    When I add a table
    Then I should have 4 green cukes

  Scenario: 3 green and 4 yellow cukes
    Given I have 3 green cukes
    And I have 4 yellow cukes
    Then I should have 3 green cukes
    And I should have 4 yellow cukes

  Scenario: 99 green cukes
    Given I have 99 green cukes
    Then I should have 99 green cukes
