Feature: NewGetIdClick --> Add Payment Method
  Here will test the Add new payment method.
  For this we will login with director credentials and will test the different scenarios including behaviour and functional test of the payment methods List.
  For Testing we will use the test data.

#  @GetID_VerifyValidationMessage_AddPaymentMethod
#  @Test @PaymentMethod
#  Scenario: Scenario_1: Verify the validation message and add new payment method.
#    Given User on the "director" panel login page and login with valid credential.
#    Then click on the "Add Payment Method" button.
#    Then click on the "Submit Payment" button and Verify the error message for following field.
#      | Method Type             | Method Name             | Payment Icon                   |
#      | Method type is required | Method name is required | Payment Icon image is required |
#    Then click on the "Method Type" dropdown and choice the "UPI".
#    Then click on the "Submit Payment" button and Verify the error message for following field.
#      | Method Name             | Payment Icon                   |
#      | Method name is required | Payment Icon image is required |
#    Then enter the value "Pa" for the field "Method Name"
#      | Method Name                                                                                                   | Payment Icon                   |
#      | Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens | Payment Icon image is required |
#    Then enter the value "Auto" for the field "Method Name".
#    Then click on the "Submit Payment" button and Verify the error message for following field.
#      | Payment Icon                   |
#      | Payment Icon image is required |
#    Then click on the Choose File and Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Payment" method on list.
#
#  @GetID_VerifyValidationMessage_EditPaymentMethod
#  @Test @PaymentMethod
#    Scenario: Scenario_2: Verify the validation message and edit new payment method.
#    Given User on the "director" panel login page and login with valid credential.
#    Then click on the "Add Payment Method" button.
#    Then click on the "Method Type" dropdown and choice the "UPI".
#    Then enter the value "Auto" for the field "Method Name".
#    Then click on the Choose File and Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Payment" method on list.
#    Then Verify the "Status" change on manage id list.
#    Then click on the "Edit Payment" button.
#    Then click on the edit "Edit Method Type" dropdown and choice the "Wallet".
#    Then remove the "Edit Method Name" and Verify the error message for following field.
#      | Method Name             |
#      | Method name is required |
#    Then remove the "Payment Icon" and Verify the error message for following field.
#      | Method Name             | Payment Icon                   |
#      | Method name is required | Payment Icon image is required |
#    Then enter the edit value "Pa" for the field "Method Name"
#      | Method Name                                                                                                   |
#      | Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens |
#    Then enter the value "Auto" for the field "Edit Method Name".
#    Then click on the Choose File and edit icon Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Edit Payment" method on list.
#
#  @GetID_VerifyValidationMessage_AddBank @Bank
#  Scenario: Scenario_3: Verify the validation message and add new bank.
#    Given User on the "director" panel login page and login with valid credential.
#    Then click on the "Banks" button.
#    Then click on the "Add Bank" button.
#    Then click on the "Submit Bank" button and Verify the error message for following field.
#      | Bank Name             | Country                 | Bank Icon                   |
#      | Bank Name is required | Please select a Country | Bank Icon image is required |
#    Then enter the value "Pa" for the field "Bank Name"
#      | Bank Name                                                                                                     |
#      | Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens |
#    Then enter the value "AutoBank" for the field "Bank Name"
#      | Country                 | Bank Icon                   |
#      | Please select a Country | Bank Icon image is required |
#    Then enter the value "AutoBank" for the field "Bank Name".
#    Then click on the "Country" dropdown and choice the "Nepal" country.
#    Then click on the "Automation" button.
#    Then click on the "Saving Statement Import" button.
#    Then click on the "Current Statement Import" button.
#    Then click on the Choose File and Upload.
#    Then click on the "Submit Bank" button.
#    Then Verify the "ADD BANK" Payment method validation message on screen.
#    Then Verify the add "Bank" method on list.
#    Then Verify the "Status" change on manage id list.

  @GetID_VerifyValidationMessage_EditPaymentMethod @Test @Bank
  Scenario: Scenario_4: Verify the validation message and edit new bank .
    Given User on the "director" panel login page and login with valid credential.
    Then click on the "Banks" button.
    Then click on the "Add Bank" button.
    Then enter the value "AutoBank" for the field "Bank Name".
    Then click on the "Country" dropdown and choice the "Pakistan" country.
    Then click on the "Automation" button.
    Then click on the "Saving Statement Import" button.
    Then click on the "Current Statement Import" button.
    Then click on the Choose File and Upload.
    Then click on the "Submit Bank" button.
    Then Verify the "ADD BANK" Payment method validation message on screen.
    Then Verify the add "Bank" method on list.
#    Then Verify the "Status" change on manage id list.
    Then click on the "Banks" button.
    Then click on the "Edit Bank" button.
    Then remove the "Edit Bank Name" and Verify the error message for following field.
      | Bank Name             |
      | Bank Name is required |
    Then enter the edit value "Pa" for the field "Bank Name"
      | Bank Name                                                                                                     |
      | Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens |
    Then click on the "Country" dropdown and choice the "Oman" country.
    Then remove the "Edit Bank Icon" and Verify the error message for following field.
      | Bank Name                                                                                                     | Bank Icon                   |
      | Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens | Bank Icon image is required |
    Then enter the value "AutoBank" for the field "Bank Name".
    Then click on the Choose File and edit icon Upload.
    Then click on the "Submit Bank" button.
    Then Verify the "Update BANK" Payment method validation message on screen.
    Then Verify the add "Edit Bank" method on list.
    Then Verify the "Update Status" change on manage id list.

  @GetID_VerifyOTPSettings @Test @OTP_Setting
  Scenario: Scenario_5: Verify OTP Setting
    Given User on the "director" panel login page and login with valid credential.
    Then click on the "OTP Setting" button.
    Then update the OTP Setting with value "new OTP Setting".
    Then click on the "Save Otp" button.
    Then Verify the "OTP Update" toaster message on screen.

#  ========================

#  @ManageId_Add_PaymentMethod @Test
#  Scenario:Scenario_1: Add Payment Method and verify the added Payment Method.
#    Given User log in to provider URL and is already present at the website list page.
#    Then click on the "Add Payment Method" button.
#    Then click on the "Method Type" dropdown and choice the "UPI".
#    Then enter the value "Auto" for the field "Method Name".
#    Then click on the Choose File and Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Payment" method on list.
#    Then Verify the "Status" change on manage id list.

#  @ManageId_Add_BankMethod @Test
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


#  @GetID_VerifyEditPaymentMethod @Test
#  Scenario: Scenario_4: Edit the Payment Method.
##    Given User log in to provider URL and is already present at the website list page.
#    Then click on the "Add Payment Method" button.
#    Then click on the "Method Type" dropdown and choice the "UPI".
#    Then enter the value "Auto" for the field "Method Name".
#    Then click on the Choose File and Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Payment" method on list.
#    Then click on the "Edit Payment" button.
#    Then click on the edit "Edit Method Type" dropdown and choice the "Wallet".
#    Then enter the value "Auto" for the field "Edit Method Name".
#    Then click on the Choose File and edit icon Upload.
#    Then click on the "Submit Payment" button.
#    Then Verify the "ADD PAYMENT" Payment method validation message on screen.
#    Then Verify the add "Edit Payment" method on list.





