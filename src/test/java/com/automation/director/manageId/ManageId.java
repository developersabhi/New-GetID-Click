package com.automation.director.manageId;

import constants.PathConstants;
import io.cucumber.java.After;
import io.cucumber.java.it.Ma;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUtil;
import utils.CommonMethod;
import utils.TestBase;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ManageId {

    CommonMethod commonMethod = new CommonMethod();
    private static final Logger logger = Logger.getLogger(TestBase.class);

    ManageId() {
        PageFactory.initElements(TestBase.getWebDriver(), this);
    }

    BaseUtil baseUtil = new BaseUtil();

    static protected String methodNameValue, bankNameValue, copyText, editMethodNameValue;

    @FindBy(xpath = "//div/span[@class='current' and contains(text(),'Select Method Type')]")
    WebElement methodType;
    @FindBy(xpath = "//input[@placeholder='Enter payment method name' ]")
    WebElement methodName;
    @FindBy(xpath = "//input[@type = 'file']")
    WebElement paymentIcon;
    @FindBy(xpath = "//input[@placeholder ='Search']")
    WebElement searchManageId;
    @FindBy(xpath = "//table//td[3]")
    WebElement methodNameOnList;
    @FindBy(xpath = "//input[@placeholder='Enter Bank Name']")
    WebElement bankName;
    @FindBy(xpath = "//tbody//td[3]")
    WebElement bankNameOnList;
    @FindBy(xpath = "//textarea[@placeholder='OTP setting url']")
    WebElement otpTextArea;
    @FindBy(xpath = "//span[@class='current']")
    WebElement editMethodType;
    @FindBy(xpath = "//input[@placeholder='Test Bnk' ]")
    WebElement editMethodName;
    @FindBy(xpath = "//span[contains(text(),'Method type is required')]")
    WebElement methodTypeErrorMsg;
//    @FindBy(xpath = "//div[@class='mb-3']/following::span[contains(text(),'Method name is required')]")
    @FindBy(xpath = "//input[@placeholder='Enter payment method name']/following-sibling::span")
    WebElement methodNameErrorMsg;
    @FindBy(xpath = "//div[@class='mb-3']/following::span[contains(text(),'Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens')]")
    WebElement methodeNameMiniTextErrorMsg;
    @FindBy(xpath = "//div[@class='mb-3']/following::span[contains(text(),'Payment Icon image is required')]")
    WebElement paymentIconErrorMsg;


    public void clickAndSelectMethod(String btn, String method) {
        commonMethod.clickOnButton(btn);
        commonMethod.isElementPresent(methodType);
        switch (method.toUpperCase()) {
            case "UPI":
                commonMethod.clickOnButton("UPI");
                break;
            case "WALLET":
                commonMethod.clickOnButton("WALLET");
                break;
            default:
                logger.info("Method not clicked :: " + method);
        }
    }

    public void clickAndEditMethod(String btn, String method) {
        commonMethod.clickOnButton(btn);
        commonMethod.isElementPresent(editMethodType);
        commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
        switch (method.toUpperCase()) {
            case "UPI":
                commonMethod.clickOnButton("UPI");
                break;
            case "WALLET":
                commonMethod.clickOnButton("WALLET");
                break;
            default:
                logger.info("Method not clicked :: " + method);
        }
    }

    public void selectCountryFromDropdown(String btn, String countryName) {
        commonMethod.clickOnButton(btn);

        WebElement countryOption = TestBase.getWebDriver().findElement(By.xpath("//ul//li[@data-value='" + countryName + "']"));
        commonMethod.clickElement(countryOption);
    }

    public void enterValueOnTheField(String value, String field) {
        switch (field.toUpperCase()) {
            case "METHOD NAME":
                commonMethod.isElementPresent(methodName);
                methodName.clear();
                methodNameValue = value + " " + generateRandomString();
                methodName.sendKeys(methodNameValue);
                break;
            case "BANK NAME":
                commonMethod.isElementPresent(bankName);
                bankName.click();
                bankName.clear();
                bankNameValue = value + " " + generateRandomString();
                bankName.sendKeys(bankNameValue);
                break;
            case "EDIT METHOD NAME":
                commonMethod.isElementPresent(editMethodName);
                editMethodName.clear();
                editMethodNameValue = value + " " + generateRandomString();
                editMethodName.sendKeys(editMethodNameValue);
                break;
            default:
                logger.error("Unable to find the input field :: " + field);
        }
    }

    public void enterMethodName(String value, String field){
        switch (field.toUpperCase()) {
            case "METHOD NAME":
                commonMethod.isElementPresent(methodName);
                methodName.clear();
                methodName.sendKeys(value);
                break;
            default:
                logger.error("Unable to find the input field :: " + field);
        }
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString().substring(2).replaceAll("[^A-Za-z]", "");
    }

    public void uploadThePaymentIcon() {
        paymentIcon.sendKeys(System.getProperty("user.dir") + "/src/main/resources/QR.png");
        commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
    }

    public void uploadImageForEditIcon() {
        paymentIcon.sendKeys(System.getProperty("user.dir") + "/src/main/resources/QR_code.png");
        commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
    }

    public void verifyAddPaymentMethod(String method) {
        String expected;
        String actual;
        switch (method.toUpperCase()) {
            case "PAYMENT":
                commonMethod.isElementPresent(searchManageId);
                searchManageId.clear();
                searchManageId.sendKeys(methodNameValue);
                searchManageId.sendKeys(Keys.ENTER);
                commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                expected = methodNameValue;
                actual = methodNameOnList.getText();
                Assert.assertEquals("Payment Method Added :: ", expected, actual);
                break;
            case "EDIT PAYMENT":
                commonMethod.isElementPresent(searchManageId);
                searchManageId.clear();
                searchManageId.sendKeys(editMethodNameValue);
                searchManageId.sendKeys(Keys.ENTER);
                commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                expected = editMethodNameValue;
                actual = methodNameOnList.getText();
                Assert.assertEquals("Payment Method Added :: ", expected, actual);
                break;
            case "BANK":
                commonMethod.isElementPresent(searchManageId);
                searchManageId.clear();
                searchManageId.sendKeys(bankNameValue);
                searchManageId.sendKeys(Keys.ENTER);
                commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                expected = bankNameValue;
                actual = bankNameOnList.getText();
                Assert.assertEquals("Bank Method Added :: ", expected, actual);
                break;
            default:
                logger.info("Method not found :: " + method);
        }

    }

    public void updateOTP(String value) {
        try {
            commonMethod.isElementPresent(otpTextArea);
            copyText = otpTextArea.getText();
            commonMethod.explicitWait(PathConstants.WAIT_MEDIUM);
            commonMethod.clearAndType(otpTextArea, value);
        } catch (Exception e) {
            logger.info("Otp not update with new setting :: " + value);
            logger.error("OTP not update with new setting :: " + e.getMessage());
        }

    }

    @After("@GetID_VerifyOTPSettings")
    public void updateOldOTP() {
        try {
            commonMethod.isElementPresent(otpTextArea);
            commonMethod.clearAndType(otpTextArea, TestBase.globPop.get("originalOTP"));
        } catch (Exception e) {
            logger.info("Otp not update with new setting :: " + copyText);
            logger.error("OTP not update with new setting :: " + e.getMessage());
        }
    }

    public void verifyErrorMsg(List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String fieldName = entry.getKey();
                String errorMsg = entry.getValue();

                switch (fieldName.toUpperCase()) {
                    case "METHOD TYPE":
                        Assert.assertEquals("Error message for method type not as expected. Expected :: "
                                        + errorMsg + " Actual :: " + methodTypeErrorMsg.getText(),
                                errorMsg, methodTypeErrorMsg.getText());
                        break;
                    case "METHOD NAME":
                        Assert.assertEquals("Error message for method name not as expected. Expected :: "
                                        + errorMsg + " Actual :: " + methodNameErrorMsg.getText(),
                                errorMsg, methodNameErrorMsg.getText());
                        break;

                    case "PAYMENT ICON":
                        Assert.assertEquals("Error message for payment icon not as expected. Expected :: "
                                        + errorMsg + " Actual :: " + paymentIconErrorMsg.getText(),
                                errorMsg, paymentIconErrorMsg.getText());
                        break;
                }
            }
        }
    }
}