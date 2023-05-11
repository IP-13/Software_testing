Feature: sign in
  Scenario: sign in to fandom
    Given I open fandom to sign in
    And I click sign in on the main page
    And I enter username
    And I enter password
    And I click sign in button
    And I go to profile
    Then I should see my name