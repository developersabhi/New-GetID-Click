package com.automation.director.login;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseUtil;
import utils.CommonMethod;
import utils.TestBase;

import java.time.Duration;

public class Login {
    Logger logger = Logger.getLogger(Login.class);
    TestBase testBase = new TestBase();
    CommonMethod commonMethod = new CommonMethod();
    BaseUtil baseUtil = new BaseUtil();

    private String acturalUsernameMsg;
    private String acturalPasswordMsg;
    private String acturalTosterMessage;
    private String acturalSuccessfulTosterMessage;

    public Login() {
        PageFactory.initElements(TestBase.getWebDriver(), this);
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement usernameInputField;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInputField;
    @FindBy(xpath = "//input[@type ='email']/following::span[contains(text(),'Username is required')]")
    WebElement usernameReqErrorMessage;
    @FindBy(xpath = "//input[@type ='password']/following::span[contains(text(),'Password is required')]")
    WebElement passwordReqErrorMessage;
    @FindBy(xpath = "//div[@id='toast-container']//div[contains(text(),'Logged in Successfully.')]")
    WebElement successfulLoginTosterMesssage;
    @FindBy(xpath = "//div[@class='toast-top-right']//following::div[contains(text(),'Error 400: Invalid User ID or Password')]")
    WebElement errorTosterMessage;
    public void verifyLoginFunctionality(String username, String password, String expectedMsg) {
        try {
            commonMethod.isElementPresent(usernameInputField);
            commonMethod.isElementPresent(passwordInputField);
            commonMethod.enterText(usernameInputField, username);
            commonMethod.explicitWait(1000);
            commonMethod.enterText(passwordInputField, password);
            commonMethod.explicitWait(1000);
            commonMethod.clickOnButton("Login");
            commonMethod.explicitWait(1000);


            switch (expectedMsg) {
                case "Username is required & Password is required":
                    if (username.isEmpty() && password.isEmpty()) {
                        commonMethod.isElementPresent(usernameReqErrorMessage);
                        commonMethod.isElementPresent(passwordReqErrorMessage);
                        acturalUsernameMsg = usernameReqErrorMessage.getText();
                        acturalPasswordMsg = passwordReqErrorMessage.getText();
                        Assert.assertEquals("Error message for empty username is not correct",
                                "Username is required", acturalUsernameMsg);
                        Assert.assertEquals("Error message for empty password is not correct",
                                "Password is required", acturalPasswordMsg);
                    }
                    break;
                case "Username is required":
                    if (username.isEmpty()){
                        commonMethod.explicitWait(4000);
                        commonMethod.isElementPresent(usernameReqErrorMessage);
                        acturalUsernameMsg = usernameReqErrorMessage.getText();
                        logger.info(acturalUsernameMsg);
                        logger.info(expectedMsg);
                        Assert.assertEquals("Error message for empty username is not correct",
                                expectedMsg, acturalUsernameMsg);
                    }
                    break;
                case "Password is required":
                    if (password.isEmpty()){
                        commonMethod.explicitWait(4000);
                        commonMethod.isElementPresent(passwordReqErrorMessage);
                        acturalPasswordMsg =passwordReqErrorMessage.getText();
                        logger.info(acturalPasswordMsg);
                        logger.info(expectedMsg);
                        Assert.assertEquals("Error message for empty password is not correct",
                                expectedMsg, acturalPasswordMsg);
                    }
                    break;
                case "Error 400: Invalid User ID or Password":
                    Assert.assertEquals("Error message for empty password is not correct",
                            expectedMsg, baseUtil.getTosterMsg(errorTosterMessage).getText());
                    break;

                case "Logged in Successfully.":
                    Assert.assertEquals("Login success message is not correct",
                            expectedMsg, baseUtil.getTosterMsg(successfulLoginTosterMesssage).getText());
                    commonMethod.clickOnButton("Logout");
                    break;
                default:
                    Assert.fail("Unexpected message: " + expectedMsg);
            }
        } catch (Exception e) {
            Assert.fail("Exception occurred during login test :: " + e.getMessage());
        }
    }
}
