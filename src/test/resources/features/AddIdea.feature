Feature: Create Idea
  Background:
    Given I'm logged in as "adminUser"
    And Idea List page is shown

  Scenario: Create Idea   - positive
    When I open Add Idea dialog
    And I fill summary with "Summary text"
    And I fill details with "Idea detail text"
    And I post the idea
    Then Add idea popup is hidden
    And Idea List page is shown
    And Idea with summary "Summary text" is on the top of the list

  Scenario: Create Idea and check summary and details after save - positive
    Given I open Add Idea dialog
    And I fill summary with "Summary text check"
    And I fill details with "Idea detail text check"
    And I post the idea
    And Add idea popup is hidden
    And Idea List page is shown
    When I open Idea with summary "Summary text check"
    Then Edit idea page is shown
    And Idea summary is "Summary text check"
    And Idea details is "Idea detail text check"









