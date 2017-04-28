Feature: Login to IdeaBox
  Background:
    Given I open Ideabox URL
    And Login page is shown

  Scenario Outline: Successful login to Ideabox
    When I fill login "<UserName>"
    And I fill password "<Password>"
    And I submit login form
    Then I am logged in
    And Idea List page is shown

    Examples:
    | UserName | Password|
    | ideabox-admin | w7kbqf%62xm |
    | ideabox-ldap  |xwPgV42Px9Af67LjJtr|
