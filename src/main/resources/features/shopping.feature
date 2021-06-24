Feature: Shopping into Wappi

  @Shopping
  Scenario: Shopping without claiming coupon
    Given an user from Wappi app
    When I make the login
    And I purchase 1 article without using coupon
    Then the order is saved

  @Shopping
  Scenario: Shopping with coupon
    Given an user from Wappi app
    When I make the login
    And claim the welcome coupon
    And I purchase 1 article using the coupon
    Then the order is saved
    And the coupon uses are updated
    And I purchase 1 article using the coupon
    Then the order is saved
    And the coupon uses are updated
    And I try to purchase 1 article using the coupon
    Then an alert is displayed saying the coupon is invalid
    And the order is not saved
    And I can not claim a new coupon

  @Shopping
  Scenario: Shopping with invalid coupon
    Given an user from Wappi app
    When I make the login
    And I try to purchase 1 article using the coupon
    Then an alert is displayed saying the coupon is invalid
    And the order is not saved
