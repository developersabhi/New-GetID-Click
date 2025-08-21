package com.automation.director.manageId;

import constants.PathConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import utils.CommonMethod;

import java.util.List;
import java.util.Map;

public class ManageIdTest {

    ManageId manageId = new ManageId();
    CommonMethod commonMethod = new CommonMethod();

    @Then("click on the {string} dropdown and choice the {string}.")
    public void click_on_the_dropdown_and_choice_the(String btn, String method) {
        manageId.clickAndSelectMethod(btn, method);
    }
    @Then("click on the edit {string} dropdown and choice the {string}.")
    public void click_on_edit_the_dropdown_and_choice_the(String btn, String method) {
        manageId.clickAndEditMethod(btn, method);
    }

    @Then("enter the value {string} for the field {string}.")
    public void enter_the_value_for_the_field(String value, String field) {
        manageId.enterValueOnTheField(value, field);
    }

    @Then("click on the Choose File and Upload.")
    public void click_on_Choose_File_the_and_upload() {
        manageId.uploadThePaymentIcon();
    }
    @Then("click on the Choose File and edit icon Upload.")
    public void click_on_Choose_File_the_and_edit_icon_upload() {
        manageId.uploadImageForEditIcon();
    }

    @Then("Verify the {string} Payment method validation message on screen.")
    public void verify_the_payment_method_validation_message_on_screen(String action) {
        commonMethod.verifyToasterMsg(action);
    }

    @Then("Verify the add {string} method on list.")
    public void verify_the_add_method_on_list(String method) {
        manageId.verifyAddPaymentMethod(method);
    }

    @Then("Verify the {string} change on manage id list.")
    public void verify_the_change_on_manage_id_list(String status) {
        commonMethod.verifyToasterMsg(status);
    }

    @Then("click on the {string} dropdown and choice the {string} country.")
    public void click_on_the_dropdown_and_choice_the_country(String value, String field) {
        manageId.selectCountryFromDropdown(value, field);
    }

    @Then("update the OTP Setting with value {string}.")
    public void update_the_OTP_Setting_with_value(String value) {
        manageId.updateOTP(value);
    }

    @After("@GetID_VerifyOTPSettings")
    public void updateOriginalOTP(){
        manageId.updateOldOTP();
        commonMethod.clickOnButton("Save Otp");
    }

    @Then("click on the {string} button and Verify the error message for following field.")
    public void click_on_the_button_and_verify_the_error_message_for_following_field(String btn, DataTable dataTable) {
        commonMethod.clickOnButton("Submit Payment");
        commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
        manageId.verifyErrorMsg(data);
    }

    @Then("enter the value {string} for the field {string}")
    public void enter_the_value_for_the_field(String value, String field, DataTable dataTable) {
        manageId.enterMethodName(value,field);
        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
        manageId.verifyErrorMsg(data);
    }

}
