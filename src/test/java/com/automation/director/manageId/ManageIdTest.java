package com.automation.director.manageId;

import io.cucumber.java.en.Then;

public class ManageIdTest {

    ManageId manageId = new ManageId();

    @Then("click on the {string} dropdown and choice the {string}.")
    public void click_on_the_dropdown_and_choice_the(String btn, String method) {
        manageId.clickAndSelectMethod(btn, method);
    }

    @Then("enter the value {string} for the field {string}.")
    public void enter_the_value_for_the_field(String value, String field) {
        manageId.enterValueOnTheField(value, field);
    }

    @Then("click on the Choose File and Upload.")
    public void click_on_Choose_File_the_and_upload() {
        manageId.uploadThePaymentIcon();
    }

    @Then("Verify the {string} Payment method validation message on screen.")
    public void verify_the_payment_method_validation_message_on_screen(String action) {
        manageId.verifyToasterMsg(action);
    }

    @Then("Verify the add {string} method on list.")
    public void verify_the_add_method_on_list(String method) {
        manageId.verifyAddPaymentMethod(method);
    }

    @Then("Verify the {string} change on manage id list.")
    public void verify_the_change_on_manage_id_list(String status) {
        manageId.verifyToasterMsg(status);
    }

    @Then("click on the {string} dropdown and choice the {string} country.")
    public void click_on_the_dropdown_and_choice_the_country(String value, String field) {
        manageId.selectCountryFromDropdown(value, field);
//        manageId.clickAndSelectCountry(value, field);
    }
}
