package com.automation.director.manageId;

import constants.PathConstants;
import io.cucumber.java.After;
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

    public ManageId() {
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
    WebElement searchTextArea;
    @FindBy(xpath = "//table//td[3]")
    WebElement methodNameOnList;
    @FindBy(xpath = "//input[@placeholder='Enter Bank Name']")
    WebElement bankName;
    @FindBy(xpath = "//tbody//td[3]")
    WebElement bankNameOnList;
    @FindBy(xpath = "//textarea[@placeholder='OTP setting url']")
    WebElement otpTextArea;
//    @FindBy(xpath = "//span[@class='current']")
    @FindBy(xpath = "//div[@class='nice-select me-sm-2 default-select form-control wide']")
    WebElement editMethodType;
    //    @FindBy(xpath = "//input[@placeholder='Enter Bank Name']")
    @FindBy(xpath = "//input[@placeholder='Test Bnk']")
    WebElement editMethodName;
    @FindBy(xpath = "//span[contains(text(),'Method type is required')]")
    WebElement methodTypeErrorMsg;
    @FindBy(xpath = "//input[@placeholder='Enter payment method name']/following-sibling::span")
    WebElement methodNameErrorMsg;
    @FindBy(xpath = "//div[@class='mb-3']/following::span[contains(text(),'Method Name must be 3–50 characters long and contain only alphabets, numbers, spaces, underscores, or hyphens')]")
    WebElement methodeNameMiniTextErrorMsg;
    @FindBy(xpath = "//div[@class='mb-3']/following::span[contains(text(),'Payment Icon image is required')]")
    WebElement paymentIconErrorMsg;
    @FindBy(xpath = "//div[@class='upload-img']//button[@class='btn-close']")
    WebElement paymentIconCloseBtn;
    @FindBy(xpath = "//input[@placeholder='Test Bnk']/following-sibling::span")
    WebElement getEditMethodNameErrorMsg;
    @FindBy(xpath = "//input[@type='file']/following-sibling::span")
    WebElement getEditPaymentIconErrorMsg;
    @FindBy(xpath = "(//div[@id='addbank']//form/div/label/following-sibling::span)[1]")
    WebElement addBankErrorMsg;
    @FindBy(xpath = "//div[@id='addbank']//form/div/label/following-sibling::span[contains(text(),'Please select a Country')]")
    WebElement addCountyErrorMsg;
    @FindBy(xpath = "//div[@id='addbank']//form/div/label/following-sibling::span[contains(text(),'Bank Icon image is required')]")
    WebElement bankIconErrorMsg;

    @FindBy(xpath = "//input[@placeholder='Enter Bank Name']")
    WebElement editBankNameField;
    @FindBy(xpath = "//div[@class='upload-img']/button[@class='btn-close']")
    WebElement editBankIcon;
    @FindBy(xpath = "//div[@id='edit_bank']/div//form/div/span")
    WebElement bankEditErrorMsg;
    @FindBy(xpath = "")
    WebElement countryEditErrorMsg;
    @FindBy(xpath = "//input[@type='file']//following-sibling::span")
    WebElement bankIconEditErrorMsg;

    @FindBy(xpath = "//div[@class='nice-select me-sm-2 default-select form-control wide open']/ul")
    WebElement editMethodDropdownType;

    public void clickAndSelectMethod(String btn, String method) {
        try {
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
        } catch (Exception e) {
            logger.error("Exception occurred while clicking and selecting method: " + method, e);
        }
    }

    public void clickAndEditMethod(String btn, String method) {
        try {
            commonMethod.explicitWait(PathConstants.WAIT_LOW);
            commonMethod.clickOnButton(btn);
            commonMethod.isElementPresent(editMethodDropdownType);
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
        } catch (Exception e) {
            logger.error("Exception occurred while clicking and editing method: " + method, e);
        }
    }

    public void selectCountryFromDropdown(String btn, String countryName) {
        try {
            commonMethod.clickOnButton(btn);
            WebElement countryOption = TestBase.getWebDriver()
                    .findElement(By.xpath("//ul//li[@data-value='" + countryName + "']"));
            commonMethod.clickElement(countryOption);
        } catch (Exception e) {
            logger.error("Exception occurred while selecting country: " + countryName, e);
        }
    }

    public void enterValueOnTheField(String value, String field) {
        try {
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
        } catch (Exception e) {
            logger.error("Exception occurred while entering value in field: " + field, e);
        }
    }

    public void enterMethodName(String value, String field) {
        try {
            switch (field.toUpperCase()) {
                case "METHOD NAME":
                    commonMethod.isElementPresent(methodName);
                    methodName.clear();
                    methodName.sendKeys(value);
                    break;
                case "BANK NAME":
                    commonMethod.isElementPresent(bankName);
                    commonMethod.clearField(bankName);
                    bankName.sendKeys(value);
                    break;
                default:
                    logger.error("Unable to find the input field :: " + field);
            }
        } catch (Exception e) {
            logger.error("Exception occurred while entering value in field: " + field, e);
        }
    }

    public void enterEditMethodName(String value, String field) {
        try {
            switch (field.toUpperCase()) {
                case "METHOD NAME":
                    commonMethod.isElementPresent(editMethodName);
                    commonMethod.clearField(editMethodName);
                    editMethodName.sendKeys(value);
                    break;
                case "BANK NAME":
                    commonMethod.isElementPresent(editBankNameField);
                    commonMethod.clearField(editBankNameField);
                    editBankNameField.sendKeys(value);
                    break;
                default:
                    logger.error("Unable to find the input field :: " + field);
            }
        } catch (Exception e) {
            logger.error("Exception occurred while entering value in field: " + field, e);
        }
    }

    public String generateRandomString() {
        return UUID.randomUUID().toString().substring(2).replaceAll("[^A-Za-z]", "");
    }

    public void uploadThePaymentIcon() {
        try {
            paymentIcon.sendKeys(System.getProperty("user.dir") + "/src/main/resources/QR.png");
            commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
        } catch (Exception e) {
            logger.error("Exception occurred while uploading image for  icon." + e.getMessage());
        }
    }

    public void uploadImageForEditIcon() {
        try {
            paymentIcon.sendKeys(System.getProperty("user.dir") + "/src/main/resources/QR_code.png");
            commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
        } catch (Exception e) {
            logger.error("Exception occurred while uploading image for edit icon." + e.getMessage());
        }
    }

    public void verifyAddPaymentMethod(String method) {
        String expected;
        String actual;

        try {
            switch (method.toUpperCase()) {
                case "PAYMENT":
                    commonMethod.isElementPresent(searchTextArea);
                    searchTextArea.clear();
                    searchTextArea.sendKeys(methodNameValue);
                    searchTextArea.sendKeys(Keys.ENTER);
                    commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                    expected = methodNameValue;
                    actual = methodNameOnList.getText();
                    Assert.assertEquals("Payment Method Added :: ", expected, actual);
                    break;

                case "EDIT PAYMENT":
                    commonMethod.isElementPresent(searchTextArea);
                    searchTextArea.clear();
                    searchTextArea.sendKeys(editMethodNameValue);
                    searchTextArea.sendKeys(Keys.ENTER);
                    commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                    expected = editMethodNameValue;
                    actual = methodNameOnList.getText();
                    Assert.assertEquals("Payment Method Added :: ", expected, actual);
                    break;

                case "BANK":
                    commonMethod.isElementPresent(searchTextArea);
                    searchTextArea.clear();
                    searchTextArea.sendKeys(bankNameValue);
                    searchTextArea.sendKeys(Keys.ENTER);
                    commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                    expected = bankNameValue;
                    actual = bankNameOnList.getText();
                    Assert.assertEquals("Bank Method Added :: ", expected, actual);
                    break;

                case "EDIT BANK":
                    commonMethod.isElementPresent(searchTextArea);
                    searchTextArea.clear();
                    searchTextArea.sendKeys(bankNameValue);
                    searchTextArea.sendKeys(Keys.ENTER);
                    commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                    expected = bankNameValue;
                    actual = bankNameOnList.getText();
                    Assert.assertEquals("Bank Method Added :: ", expected, actual);
                    break;


                default:
                    logger.info("Method not found :: " + method);
            }
        } catch (Exception e) {
            logger.error("Exception occurred while verifying method: " + method, e);

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

                try {
                    switch (fieldName.toUpperCase()) {
                        case "METHOD TYPE":
                            Assert.assertEquals("Error message for method type not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + methodTypeErrorMsg.getText(),
                                    errorMsg, methodTypeErrorMsg.getText());
                            logger.info("Actual error msg :: " + methodTypeErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        case "METHOD NAME":
                            Assert.assertEquals("Error message for method name not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + methodNameErrorMsg.getText(),
                                    errorMsg, methodNameErrorMsg.getText());
                            logger.info("Actual error msg :: " + methodNameErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        case "PAYMENT ICON":
                            Assert.assertEquals("Error message for payment icon not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + paymentIconErrorMsg.getText(),
                                    errorMsg, paymentIconErrorMsg.getText());
                            logger.info("Actual error msg :: " + paymentIconErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        case "BANK NAME":
                            Assert.assertEquals("Error message for add bank  not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + addBankErrorMsg.getText(),
                                    errorMsg, addBankErrorMsg.getText());
                            logger.info("Actual error msg :: " + addBankErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        case "COUNTRY":
                            Assert.assertEquals("Error message for add bank  not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + addCountyErrorMsg.getText(),
                                    errorMsg, addCountyErrorMsg.getText());
                            logger.info("Actual error msg :: " + addCountyErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        case "BANK ICON":
                            Assert.assertEquals("Error message for add bank  not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + bankIconErrorMsg.getText(),
                                    errorMsg, bankIconErrorMsg.getText());
                            logger.info("Actual error msg :: " + bankIconErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        default:
                            System.out.println("Unknown field: " + fieldName);
                    }
                } catch (AssertionError | Exception e) {
                    logger.error("Verification failed for field: " + fieldName);
                    e.printStackTrace();
                }
            }
        }
    }

    public void verifyEditErrorMsg(List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String fieldName = entry.getKey();
                String errorMsg = entry.getValue();

                try {
                    switch (fieldName.toUpperCase()) {
                        case "METHOD NAME":
                            Assert.assertEquals("Error message for method name not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + getEditMethodNameErrorMsg.getText(),
                                    errorMsg, getEditMethodNameErrorMsg.getText());
                            logger.info("Actual error msg :: " + getEditMethodNameErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;

                        case "PAYMENT ICON":
                            Assert.assertEquals("Error message for payment icon not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + getEditPaymentIconErrorMsg.getText(),
                                    errorMsg, getEditPaymentIconErrorMsg.getText());
                            logger.info("Actual error msg :: " + getEditPaymentIconErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        case "BANK NAME":
                            Assert.assertEquals("Error message for bank name icon as expected. Expected :: "
                                            + errorMsg + " Actual :: " + bankEditErrorMsg.getText(),
                                    errorMsg, bankEditErrorMsg.getText());
                            logger.info("Actual error msg :: " + bankEditErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
//                        case "COUNTRY":
//                            Assert.assertEquals("Error message for country not as expected. Expected :: "
//                                            + errorMsg + " Actual :: " + countryEditErrorMsg.getText(),
//                                    errorMsg, countryEditErrorMsg.getText());
//                            logger.info("Actual error msg :: "+countryEditErrorMsg.getText());
//                            logger.info("Expected error msg :: "+errorMsg );
//                            break;
                        case "BANK ICON":
                            Assert.assertEquals("Error message for bank icon not as expected. Expected :: "
                                            + errorMsg + " Actual :: " + bankIconEditErrorMsg.getText(),
                                    errorMsg, bankIconEditErrorMsg.getText());
                            logger.info("Actual error msg :: " + bankIconEditErrorMsg.getText());
                            logger.info("Expected error msg :: " + errorMsg);
                            break;
                        default:
                            System.out.println("Unknown field: " + fieldName);
                    }
                } catch (AssertionError | Exception e) {
                    System.err.println("Verification failed for edit field: " + fieldName);
                    System.err.println("Expected: " + errorMsg);
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeField(String field) {
        try {
            switch (field.toUpperCase()) {
                case "EDIT METHOD NAME":
                    if (commonMethod.isElementPresent(editMethodName)) {
                        commonMethod.clearField(editMethodName);
                        editMethodName.clear();
                        commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                        commonMethod.clickOnButton("Submit Payment");
                    } else {
                        logger.warn("Edit Method Name field not present.");
                    }
                    break;

                case "PAYMENT ICON":
                    if (commonMethod.isElementPresent(paymentIconCloseBtn)) {
                        paymentIconCloseBtn.click();
                        commonMethod.clickOnButton("Submit Payment");
                    } else {
                        logger.warn("Payment icon close button not present.");
                    }
                    break;

                case "EDIT BANK NAME":
                    if (commonMethod.isElementPresent(editBankNameField)) {
                        commonMethod.clearField(editBankNameField);
                        commonMethod.explicitWait(PathConstants.WAIT_VERY_LOW);
                        commonMethod.clickOnButton("Submit Bank");
                    } else {
                        logger.warn("Edit Method Name field not present.");
                    }
                    break;

                case "EDIT BANK ICON":
                    if (commonMethod.isElementPresent(editBankIcon)) {
                        editBankIcon.click();
                        commonMethod.clickOnButton("Submit Bank");
                    } else {
                        logger.warn("Payment icon close button not present.");
                    }
                    break;

                default:
                    logger.info("Unable to find out for remove field :: " + field);
            }
        } catch (Exception e) {
            logger.error("Exception occurred while trying to remove field: " + field, e);

        }
    }

}