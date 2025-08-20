Feature: New GetID --> Profile module have Password change feature.
  Here we are verify the password change feature

  Scenario: Scenario_1: Verify the Profile module.
#    Given User log in to provider URL and is already present at the website list page.
    Then click on the "Profile" button.
    Then enter the "new password" is "Aa1234567".
    Then enter the "confirm password" is "Aa1234567".
    Then click on the "Submit Password" button.
    Then Verify the "Change Password" toaster message on screen.
    Then click on the "Logout" button.
    Then login with username "admin@getid.com" and password "Aa1234567".
    Then click on the "Profile" button.
    Then enter the "new password" is "Aa123456".
    Then enter the "confirm password" is "Aa123456".
    Then click on the "Submit Password" button.
    Then click on the "Logout" button.
    Then login with username "admin@getid.com" and password "Aa123456".