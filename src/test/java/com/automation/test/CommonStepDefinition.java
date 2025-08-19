package com.automation.test;

import com.automation.director.login.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.CommonMethod;
import utils.TestBase;

public class CommonStepDefinition {

    TestBase testBase = new TestBase();
    CommonMethod commonMethod = new CommonMethod();
    Login login = new Login();

    @Given("The director is on the login page.")
    public void the_director_is_on_the_login_page() {
        TestBase.getWebDriver().get("http://getid.mango7222.com/login/dir");
    }

    @Then("click on the {string} button.")
    public void click_on_the_button(String btn) {
       commonMethod.clickOnButton(btn);
    }

    @Then("close the browser.")
    public void close_the_browser() {
        commonMethod.closeBrowser();
    }
}
