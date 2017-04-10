Feature:  Admin takes idea through the flow
  Background:
    Given default idea is created via BE
    And I'm logged in as "ldapUser"

  Scenario Outline: Changes of Idea statuses
    Given Idea status is "NEW"
    When I open idea
    And Edit idea page is shown
    And Idea status available
    |ANALYSIS|
    |NEW     |
    |REJECTED|
    And I change status to "<Status>"
    And I go to idea list page
    Then Idea status is "<Status>"

    Examples:
    |Status |
    |ANALYSIS|
    |NEW     |
    |REJECTED|

  Scenario Outline: Changes of in Analysis Idea statuses
    Given Idea status is set to "ANALYSIS" via BE
    When I open idea
    And Edit idea page is shown
    And Idea status available
      |ANALYSIS|
      |REJECTED|
      |IN_PROGRESS|
      |SUSPENDED|
    And I change status to "<Status>"
    And I go to idea list page
    Then Idea status is "<Status>"

    Examples:
      |Status |
      |ANALYSIS|
      |REJECTED|
      |IN_PROGRESS|
      |SUSPENDED|



