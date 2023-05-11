Feature: careers
  Scenario: search open positions
    Given I open fandom to check careers
    And I click careers link in the footer of the main page
    And I enter something in search open positions field
    Then I should see no results found