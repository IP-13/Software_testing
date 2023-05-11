Feature: change language
  Scenario: change language to russian
    Given I open fandom to change language
    And I click help in the footer of the main page
    And I click on toggle to see available languages
    And I choose russian in drop-down list
    Then I should see site in russian
  Scenario:
