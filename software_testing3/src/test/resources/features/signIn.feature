Feature: sign in
  Scenario: sign in to fandom
    Given I open fandom
    And I click sign in on the main page
    And I enter username
    And I enter password
    And I click sign in button
    Then I should enter the system