package com.automation.director.manageId;

import constants.PathConstants;
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

import java.util.UUID;

public class ManageId {

    CommonMethod commonMethod = new CommonMethod();
    private static final Logger logger = Logger.getLogger(TestBase.class);

    ManageId() {
        PageFactory.initElements(TestBase.getWebDriver(), this);
    }
    BaseUtil baseUtil = new BaseUtil();

    static protected String methodNameValue, bankNameValue, copyText;

    @FindBy(xpath = "//div/span[@class='current' and contains(text(),'Select Method Type')]")
    WebElement methodType;
    @FindBy(xpath = "//input[@placeholder='Enter payment method name' ]")
    WebElement methodName;
    @FindBy(xpath = "//input[@type = 'file' and @placeholder='No file chosen']")
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

    public void selectCountryFromDropdown(String btn, String countryName) {
        commonMethod.clickOnButton(btn);

        WebElement countryOption = TestBase.getWebDriver().findElement(By.xpath("//ul//li[@data-value='" + countryName + "']"));
        commonMethod.clickElement(countryOption);
    }

    public void enterValueOnTheField(String value, String field) {
        switch (field.toUpperCase()) {
            case "METHOD NAME":
                commonMethod.isElementPresent(methodName);
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

    public void verifyAddPaymentMethod(String method){
        String expected;
        String actual;
        switch (method.toUpperCase()) {
            case "PAYMENT":
                commonMethod.isElementPresent(searchManageId);
                searchManageId.sendKeys(methodNameValue);
                searchManageId.sendKeys(Keys.ENTER);
                commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                expected = methodNameValue;
                actual = methodNameOnList.getText();
                Assert.assertEquals("Payment Method Added :: ", expected, actual);
                break;
            case "BANK":
                commonMethod.isElementPresent(searchManageId);
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

    public void updateOTP( String value){
        try {
            commonMethod.isElementPresent(otpTextArea);
            copyText = otpTextArea.getText();
            commonMethod.explicitWait(PathConstants.WAIT_MEDIUM);
            commonMethod.clearAndType(otpTextArea,value);
        }catch (Exception e){
            logger.info("Otp not update with new setting :: "+value);
            logger.error("OTP not update with new setting :: " +e.getMessage());
        }

    }

    public void updateOldOTP() {
        try {
            commonMethod.isElementPresent(otpTextArea);
            commonMethod.clearAndType(otpTextArea, TestBase.globPop.get("originalOTP"));
        } catch (Exception e) {
            logger.info("Otp not update with new setting :: " + copyText);
            logger.error("OTP not update with new setting :: " + e.getMessage());
        }
    }
}
