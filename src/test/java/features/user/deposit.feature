@GetID_UserDeposit
Feature: User login with correct username and password
  Here we want to login the user panel using correct username and password.

  Scenario: Scenario_1: Verify the deposit request.
    Given User on the "user" panel login page and login with valid credential.
    Then click on the "notification cancel" button.
    Then click on the "click head amount sec" button.
    Then click on the "wallet deposit" button.
    Then enter the value "500" for the field "deposit amount".
    Then click on the "proceed" button.
    Then click on the "popup proceed" button.
    Then click on the choose file and upload.
    Then enter the utr number "1234567890" on the "utr" field.
    Then click on the "submit request" button.
    Then User on the "agent" panel login page and login with valid credential.
    Then click on the "request" button.
    Then click on the "request type" and select the "client deposit".
    Then click on the "status" and select the "pending".
    Then click on the "select by date" and select the "today".
    Then click on the "utr" and enter the "utr number".
    Then click on the "approval" button.
    Then User on the "user" panel login page and login with valid credential.
    Then click on the "click-head-amount-sec" button.
    Then click on the "wallet history" button.
    Then verify the deposit "amount" "status" and "utr".



