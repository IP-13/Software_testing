Feature: search
  Scenario: search Avatar
    Given I open fandom to search info about Avatar
    And I enter in search line Avatar
    And I click search button
    Then I should see available information about Avatar