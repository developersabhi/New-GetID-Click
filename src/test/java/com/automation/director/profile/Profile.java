package com.automation.director.profile;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethod;
import utils.TestBase;

public class Profile {
    private static final Logger logger = Logger.getLogger(Profile.class);

    CommonMethod commonMethod = new CommonMethod();

    public Profile() {
        PageFactory.initElements(TestBase.getWebDriver(), this);
    }

    @FindBy(xpath = "//input[@placeholder='New Password']")
    WebElement newPasswordField;
    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@type='email']")
    WebElement directorUsernameField;
    @FindBy(xpath = "//input[@type='password']")
    WebElement directorPasswordField;


    public void changePassword(String field, String value) {
        try {
            Actions actions = new Actions(TestBase.getWebDriver());

            switch (field.toUpperCase()) {
                case "NEW PASSWORD":
                    commonMethod.isElementPresent(newPasswordField);
                    commonMethod.clearAndType(newPasswordField, value);
                    logger.info("Entered new password successfully." +newPasswordField.getText());
                    break;

                case "CONFIRM PASSWORD":
                    commonMethod.isElementPresent(confirmPasswordField);
                    commonMethod.clearAndType(confirmPasswordField, value);
                    logger.info("Entered confirm password successfully." +newPasswordField.getText());
                    break;

                default:
                    logger.warn("Field not found: " + field);
            }
        } catch (Exception e) {
            logger.error("Error while entering value in " + field + " :: " + e.getMessage());
            Assert.fail("Failed to enter value in " + field);
        }
    }

    public void loginWithNewPassword(String username, String password) {
        try {
            commonMethod.isElementPresent(directorUsernameField);
            commonMethod.clearAndType(directorUsernameField, username);
            commonMethod.isElementPresent(directorPasswordField);
            commonMethod.clearAndType(directorPasswordField, password);
            commonMethod.clickOnButton("Login");
        } catch (Exception e) {
            logger.error("Error while entering value in " + username + " :: " + password + " ::" + e.getMessage());
            Assert.fail("Failed to enter value in " + username + " :: " + password);
        }
    }
}


