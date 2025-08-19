package com.automation.director.manageId;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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

    static protected String methodNameValue, bankNameValue;

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
    @FindBy(xpath = "//div[@class='toast toast-success' ]//div[contains(text(),'Payment method added successfully.')]")
    WebElement successfullyPaymentAddedToasterMsg;
    @FindBy(xpath = "//div[@id='toast-container']//div[contains(text(),'Status updated successfully')]")
    WebElement manageIdStatusChangeToasterMsg;
    @FindBy(xpath = "//input[@placeholder='Enter Bank Name']")
    WebElement bankName;
    @FindBy(xpath = "//tbody//td[3]")
    WebElement bankNameOnList;
    @FindBy(xpath = "//div[@class='toast toast-success' ]//div[contains(text(),'Bank account added successfully.')]")
    WebElement successfullyBankAddedToasterMsg;

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
        // Click on the Country dropdown
        commonMethod.clickOnButton(btn); // Click on the dropdown

        // Dynamically select the country using countryName
        WebElement countryOption = TestBase.getWebDriver().findElement(By.xpath("//ul//li[@data-value='" + countryName + "']"));
        commonMethod.clickElement(countryOption); // Click on the country
    }
//    public void clickAndSelectCountry(String btn, String countryName){
//        commonMethod.clickOnButton(btn);
//        commonMethod.isElementPresent();
//
//    }

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
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
            default:
                logger.error("Unable to find the input field :: " + field);
        }
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString().substring(2).replaceAll("[^A-Za-z]", "");
    }

    public void uploadThePaymentIcon() {
        paymentIcon.sendKeys(System.getProperty("user.dir") + "/src/main/resources/QR.png");
        commonMethod.explicitWait(1000);
    }

    public void verifyToasterMsg(String action) {
        switch (action.toUpperCase()) {
            case "ADD PAYEMENT":
                Assert.assertEquals("Error message for payment not added",
                        "Payment method added successfully.",
                        baseUtil.getToasterMsg(successfullyPaymentAddedToasterMsg).getText());
                logger.info(baseUtil.getToasterMsg(successfullyPaymentAddedToasterMsg));
                logger.info("Payment method added successfully.");
                break;
            case "STATUS":
                commonMethod.clickOnButton("Status");
                Assert.assertEquals("Error message for status not chanage",
                        "Status updated successfully",
                        baseUtil.getToasterMsg(manageIdStatusChangeToasterMsg).getText());
                logger.info("Payment method status change successfully.");
                break;
            case "ADD BANK":
                Assert.assertEquals("Error message for bank not added",
                        "Bank account added successfully.",
                        baseUtil.getToasterMsg(successfullyPaymentAddedToasterMsg).getText());
                logger.info(baseUtil.getToasterMsg(successfullyBankAddedToasterMsg));
                logger.info("Bank added successfully.");
                break;
            default:
                logger.info("Unable to find action " + action);
        }
    }

    public void verifyAddPaymentMethod(String method) {
        String expected;
        String actual;
        switch (method.toUpperCase()) {
            case "PAYMENT":
                commonMethod.isElementPresent(searchManageId);
                searchManageId.sendKeys(methodNameValue);
                searchManageId.sendKeys(Keys.ENTER);
                commonMethod.explicitWait(1000);
                expected = methodNameValue;
                actual = methodNameOnList.getText();
                Assert.assertEquals("Payment Method Added :: ", expected, actual);
                break;
            case "BANK":
                commonMethod.isElementPresent(searchManageId);
                searchManageId.sendKeys(bankNameValue);
                searchManageId.sendKeys(Keys.ENTER);
                commonMethod.explicitWait(1000);
                expected = bankNameValue;
                actual = bankNameOnList.getText();
                Assert.assertEquals("Bank Method Added :: ", expected, actual);
                break;
            default:
                logger.info("Method not found :: " + method);
        }

    }
}
