Feature: Shopping into Wappi

  @ShoppingWithoutCoupon
  Scenario: Shopping without claiming coupon
    Given an user from Wappi app
    When I make the login
    And I purchase 1 article without using coupon
    Then the order is saved

  @ShoppingWithCoupon
  Scenario: Claiming coupon after shopping
    Given an user from Wappi app
    When I make the login
    And I purchase 1 article without using coupon
    Then the order is saved
    And claim the welcome coupon
    Then coupon uses are the expected
    And I purchase 1 article using the coupon
    Then the order is saved
    And the coupon uses are updated

  @ShoppingWithCoupon
  Scenario: Claiming coupon but shopping without it
    Given an user from Wappi app
    When I make the login
    And claim the welcome coupon
    And I purchase 1 article without using coupon
    Then the order is saved
    And coupon uses are the same as before shopping

  @ShoppingWithCoupon
  Scenario: Shopping with coupon
    Given an user from Wappi app
    When I make the login
    And claim the welcome coupon
    And I purchase 1 article using the coupon
    Then the order is saved
    And the coupon uses are updated
    And I make a second purchase using the coupon
    Then the order is saved
    And the coupon uses are updated
    And I make a third purchase using the coupon
    Then an alert is displayed saying the coupon is invalid
    And the order is not saved

  @ShoppingWithCoupon
  Scenario: Coupon status not changed after new login
    Given an user from Wappi app
    When I make the login
    And claim the welcome coupon
    And I purchase 1 article using the coupon
    And the coupon uses are updated
    Then the order is saved
    And if I make a new login
    Then I can not claim a new welcome coupon
    And the previous coupon is still present with correct info

  @ShoppingWithoutCoupon
  Scenario: Shopping cancelled without coupon
    Given an user from Wappi app
    When I make the login
    And I cancel the order without using coupon
    Then the order is not saved

  @ShoppingWithCoupon
  Scenario: Shopping cancelled with coupon
    Given an user from Wappi app
    When I make the login
    And claim the welcome coupon
    And I cancel the order using coupon
    Then the order is not saved
    And the coupon uses are not updated
