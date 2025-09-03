Feature: Login to Transtreme_stage

  Scenario: Successful login to dashboard
    Given the user is on the Transtreme login page
    When the user enters email "matesteg0@gmail.com" and password "12345678"
    And clicks the Sign In button
    And enters OTP "123456"
    And clicks the Submit button
    Then the user should land on the dashboard
    And the user clicks the Logout button