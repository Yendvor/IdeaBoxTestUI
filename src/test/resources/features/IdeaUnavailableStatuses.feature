Feature: Admin is not allowed to change statuses to unavailable due to the workflow
  Background:
    Given default idea is created via BE
    And I'm logged in as "ldapUser"

  Scenario Outline: Check unavailable statuses for idea flow - negative
    Given Idea is taken through the "<StatusWorkflow>" via BE
    When I open idea
    And Edit idea page is shown
    And Idea statuses "<StatusList>" are unavailable

    Examples:
      |StatusWorkflow | StatusList|
      || IN_PROGRESS,SUSPENDED,DONE|
      |ANALYSIS| NEW,DONE|
      |ANALYSIS,SUSPENDED| NEW,ANALYSIS,DONE|
      |ANALYSIS,IN_PROGRESS| NEW,ANALYSIS|
      |ANALYSIS,IN_PROGRESS,DONE| NEW,ANALYSIS,REJECTED,IN_PROGRESS,SUSPENDED|


