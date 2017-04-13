Feature: Create Idea
  Background:
    Given I'm logged in as "adminUser"
    And Idea List page is shown

  Scenario: Create Idea   - positive
    When I open Add Idea dialog
    And I fill summary with "Summary text"
    And I fill details with "Idea detail text"
    And I post the idea
    And Edit idea page is shown

  Scenario: Create Idea and check summary and details after save - positive
    Given I open Add Idea dialog
    And I fill summary with "Summary text check"
    And I fill details with "Idea detail text check"
    And I post the idea
    Then Edit idea page is shown
    And Idea summary is "Summary text check"
    And Idea details is "Idea detail text check"









