Feature: Personal info update

  @PersonalInfo
  Scenario: Updating personal info and successfully storage
    Given an user from Wappi app
    When I make the login
    And update user personal info
    Then the new information is saved
    And shown personal info shown is updated
    And if I make a new login
    Then personal info is still updated

  @PersonalInfo
  Scenario: Aborting personal info update
    Given an user from Wappi app
    When I make the login
    And fill the form cancelling update
    Then personal info is not updated

  @PersonalInfo
  Scenario Outline: Updating personal info with incomplete form
    Given an user from Wappi app
    When I make the login
    And update all personal info except <missing info>
    Then an alert is displayed for missing info
    And personal info is not updated
    Examples:
    | missing info |
    | avatar       |
    | firstname    |
    | birthdate    |
    | country      |