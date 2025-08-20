package com.automation.director.profile;

import io.cucumber.java.en.Then;

public class ProfileTest {
    Profile profile = new Profile();
    @Then("enter the {string} is {string}.")
    public void enter_the_is(String field, String value) {
        profile.changePassword(field, value);
    }

    @Then("login with username {string} and password {string}.")
    public void login_with_username_and_password(String username, String password) {
        profile.loginWithNewPassword(username, password);
    }
}
