@Verify_Director_Login_Panel
Feature: Login with different different username and password and verify the error message.

  @Verify_Director_Login_Panel
  Scenario Outline: Valid and Invalid login attempt.
    Given The director is on the login page.
    Then Verify the login functionality with invalid  "<username>" and invalid "<password>" and error message "<message>"
    Examples:
      | username        | password      | message                                     |
      |                 |               | Username is required & Password is required |
      |                 | Aa123456      | Username is required                        |
      | admin@getid.com |               | Password is required                        |
      | admin@getid.com | wrongpassword | Error 400: Invalid User ID or Password      |
      | wrongusername   | Aa123456      | Error 400: Invalid User ID or Password      |
      | ADMIN@GETID.COM | aa123456      | Error 400: Invalid User ID or Password      |
      | ADMIN@GETID.COM | Aa123456      | Logged in Successfully.                     |
      | admin@getid.com | Aa123456      | Logged in Successfully.                     |
      | AdMiN@getid.com | Aa123456      | Logged in Successfully.                     |