package com.automation.director.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.TestBase;

public class LoginTest {
    TestBase testBase = new TestBase();
    Login login = new Login();

    @Then("Verify the login functionality with invalid  {string} and invalid {string} and error message {string}")
    public void verify_the_login_functionality_with_invalid_and_invalid_and_error_message(String username, String password, String message) {
        login.verifyLoginFunctionality(username, password, message);
    }
}
