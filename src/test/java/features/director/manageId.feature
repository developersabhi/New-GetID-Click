Feature: NewGetIdClick --> Add Payment Method
  Here will test the Add new payment method.
  For this we will login with director credentials and will test the different scenarios including behaviour and functional test of the payment methods List.
  For Testing we will use the test data.

  @ManageId
#  Scenario:Scenario_1: Add Payment Method and verify the added Payment Method.
##    Given User log in to provider URL and is already present at the website list page.
#    Then click on the "Add Payment Method" button.
#    Then click on the "Method Type" dropdown and choice the "UPI".
#    Then enter the value "Auto" for the field "Method Name".
#    Then click on the Choose File and Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Payment" method on list.
#    Then Verify the "Status" change on manage id list.
#
#    Scenario: Scenario_2: Add Banks and verify the added Banks.
##    Given User log in to provider URL and is already present at the website list page.
#      Then click on the "Banks" button.
#      Then click on the "Add Bank" button.
#      Then enter the value "AutoBank" for the field "Bank Name".
#      Then click on the "Country" dropdown and choice the "Pakistan" country.
#      Then click on the "Automation" button.
#      Then click on the "Saving Statement Import" button.
#      Then click on the "Current Statement Import" button.
#      Then click on the Choose File and Upload.
#      Then click on the "Submit Bank" button.
#      Then Verify the "ADD BANK" Payment method validation message on screen.
#      Then Verify the add "Bank" method on list.
#      Then Verify the "Status" change on manage id list.

    @GetID_VerifyOTPSettings
  Scenario: Scenario_3: Verify OTP Setting
#    Given User log in to provider URL and is already present at the website list page.
    Then click on the "OTP Setting" button.
    Then update the OTP Setting with value "new OTP Setting".
    Then click on the "Save Otp" button.
    Then Verify the "OTP Update" toaster message on screen.


