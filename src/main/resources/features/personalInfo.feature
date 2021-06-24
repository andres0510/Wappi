Feature: Personal info update

  @PersonalInfo
  Scenario: Updating personal info and successfully storage
    Given an user from Wappi app
    When I make the login
    And update user personal info
    Then the new information is saved

  @PersonalInfo
  Scenario Outline: Updating personal info with incomplete form
    Given an user from Wappi app
    When I make the login
    And update all personal info except <missing info>
    Then an alert is displayed for <missing info> input
    Examples:
    | missing info |
    | avatar       |
    | firstname    |
    | lastname     |
    | birthdate    |
    | country      |