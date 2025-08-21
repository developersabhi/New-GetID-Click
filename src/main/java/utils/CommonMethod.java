package utils;

import constants.PathConstants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonMethod {

    private static final Logger logger = Logger.getLogger(CommonMethod.class);
    WebDriverWait wait = new WebDriverWait(TestBase.getWebDriver(), Duration.ofMillis(PathConstants.defaultTimeout));
    private static final TestBase testbase = TestBase.getInstance();
    protected static String toasterMsgForOTP = "";
    BaseUtil baseUtil = new BaseUtil();

    public CommonMethod() {
        PageFactory.initElements(TestBase.getWebDriver(), this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;
    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt']/following::span[ contains(text(),'Logout')]")
    WebElement logoutBtn;
    @FindBy(xpath = "//button[@type='button' and contains(text(),'Add Payment Method')]")
    WebElement addPaymentMethodBtn;
    @FindBy(xpath = "//div/span[@class='current' and contains(text(),'Select Method Type')]")
    WebElement methodType;
    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    WebElement addPaymentSubmitBtn;
    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement addPaymentCancelBtn;
    @FindBy(xpath = "//ul[@class='list']//li[@data-value='upi']")
    WebElement methodTypeUPI;
    @FindBy(xpath = "//ul[@class='list']//li[@data-value='wallet']")
    WebElement methodTypeWallet;
    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement statusToggleBtn;
    @FindBy(xpath = "//ul[@id='pills-tab']/li/a[contains(text(),'Banks')]")
    WebElement banksBtn;
    @FindBy(xpath = "//button[contains(text(),'Add Bank')]")
    WebElement addBankBtn;
    @FindBy(xpath = "//span[@class='current']")
    WebElement countryDropDownBtn;
    @FindBy(xpath = "//label[contains(text(),'Automation ')]//input[@id='isAutomationAllow']")
    WebElement isAutomationAllowBankCheckBtn;
    @FindBy(xpath = "//label[contains(text(),'Saving Statement Import ')]//input[@id='isAutomationAllow']")
    WebElement isSavingStatementImportBankCheckBtn;
    @FindBy(xpath = "//label[contains(text(),'Current Statement Import ')]//input[@id='isAutomationAllow']")
    WebElement isCurrentStatementImportBankCheckBtn;
    @FindBy(xpath = "//div[@class='modal-footer']/button[contains(text(),'Submit')]")
    WebElement submitBankBtn;
    @FindBy(xpath = "//div[@class='modal-footer']/button[contains(text(),'Cancel')]")
    WebElement cancelBankBtn;
    @FindBy(xpath = "//a[@href='/profile']")
    WebElement profileBtn;
    @FindBy(xpath = "//a[contains(text(),'OTP Setting')]")
    WebElement otpSettingBtn;
    @FindBy(xpath = "//button[contains(text(),' Save ')]")
    WebElement saveOtpBtn;
    @FindBy(xpath = "//div[@class='wallet-submit-btn']//button[contains(text(),'Submit')]")
    WebElement submitForChangePasswordBtn;
    @FindBy(xpath = "/div[@class='toast toast-success' ]//div[contains(text(),'/div[@class='toast toast-success' ]//div[contains(text(),'Bank account added successfully.')]')]")
    WebElement successfullyPasswordChangeMsg;
    @FindBy(xpath = "//div[@class='toast toast-success' ]//div[contains(text(),'Bank account added successfully.')]")
    WebElement successfullyBankAddedToasterMsg;
    @FindBy(xpath = "//div[@class='toast toast-success' ]//div[contains(text(),'Payment method added successfully.')]")
    WebElement successfullyPaymentAddedToasterMsg;
    @FindBy(xpath = "//div[@id='toast-container']//div[contains(text(),'Status updated successfully')]")
    WebElement manageIdStatusChangeToasterMsg;
    @FindBy(xpath = "//textarea[@placeholder='OTP setting url']")
    WebElement otpTextArea;
    @FindBy(xpath = "//div[@id='toast-container']//div[contains(text(),'OTP Url updated successfully.')]")
    WebElement successfullyOtpSettingUpdateMsg;
    @FindBy(xpath = "//td/div[@class='dropdown']/div[@data-bs-toggle='dropdown']")
    WebElement editDropdownPaymentMethodBtn;
    @FindBy(xpath = "//span[@class='current']")
    WebElement editMethodType;
    @FindBy(xpath = "//td/div[@class='dropdown']//a[contains(text(),'Edit')]")
    WebElement editPaymentMethodBtn;
    @FindBy(xpath = "//input[@placeholder='Test Bnk']")
    WebElement editPaymentNameField;
    @FindBy(xpath = "//div[@class='upload-img']//button[@class='btn-close']")
    WebElement paymentIconCloseBtn;
    @FindBy(xpath = "//div[@class='dropdown']/div[@data-bs-toggle='dropdown']")
    WebElement editDropdownBankBtn;
    @FindBy(xpath = "//div[@class='dropdown']//a[contains(text(),'Edit')]")
    WebElement editBankBtn;
    @FindBy(xpath = "//div[@class='toast toast-success' ]//div[contains(text(),'Bank account updated successfully')]")
    WebElement successfullyUpdateBankToasterMsg;


    public void clearAndType(WebElement element, String value) {
        try {
            Actions actions = new Actions(TestBase.getWebDriver());
            actions.click(element)
                    .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                    .sendKeys(Keys.DELETE)
                    .perform();
            element.sendKeys(value);
        } catch (Exception e) {
            logger.error("Exception occurred while clearing and typing in the field: " + element, e);
        }
    }

    public void clearField(WebElement element) {
        try {
            Actions actions = new Actions(TestBase.getWebDriver());
            actions.click(element)
                    .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                    .sendKeys(Keys.DELETE)
                    .perform();
        } catch (Exception e) {
            logger.error("Exception occurred while clearing the field: " + element, e);
        }
    }

    public static Map<String, String> readPropertied() {
        Map<String, String> all = new HashMap<>();
        Properties properties = new Properties();
        try {
            logger.info("Read Properties Files ::");
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
            properties.load(inputStream);
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                all.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            logger.error(" unable read properties file not read :: " + e.getMessage());
        }
        return all;
    }

    public void explicitWait(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            logger.error("Getting error while doing explicit wait:: " + e.getMessage());
        }
    }

    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            logger.error("Exception occurred while clicking the element: " + element, e);
        }
    }

    public void clickElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            logger.error("Exception occurred while clicking element with locator: " + locator, e);
        }
    }

    public static TestBase getTestbase() {
        try {
            return new TestBase();
        } catch (Exception e) {
            logger.error("Exception occurred while creating TestBase instance.", e);
            return null;
        }
    }

    public void waitForVisibleElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Exception while waiting for element visibility: " + element, e);
        }
    }

    public boolean isElementPresent(WebElement element) {
        boolean flag = false;
        waitForVisibleElement(element);
        try {
            if (element.isDisplayed()) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("Element not display :: " + e.getMessage());
        }
        return flag;
    }

    public void clickOnButton(String button) {
        try {
            switch (button.toUpperCase()) {
                case "LOGIN":
                    isElementPresent(loginBtn);
                    loginBtn.click();
                    break;
                case "ADD PAYMENT METHOD":
                    explicitWait(PathConstants.WAIT_LOW);
                    isElementPresent(addPaymentMethodBtn);
                    addPaymentMethodBtn.click();
                    break;
                case "LOGOUT":
                    isElementPresent(logoutBtn);
                    logoutBtn.click();
                    break;
                case "SUBMIT PAYMENT":
                    isElementPresent(addPaymentSubmitBtn);
                    addPaymentSubmitBtn.click();
                    break;
                case "CANCEL PAYMENT":
                    isElementPresent(addPaymentCancelBtn);
                    addPaymentCancelBtn.click();
                    break;
                case "METHOD TYPE":
                    isElementPresent(methodType);
                    methodType.click();
                    break;
                case "UPI":
                    isElementPresent(methodTypeUPI);
                    methodTypeUPI.click();
                    break;
                case "WALLET":
                    isElementPresent(methodTypeWallet);
                    methodTypeWallet.click();
                    break;
                case "STATUS":
                    isElementPresent(statusToggleBtn);
                    statusToggleBtn.click();
                    break;
                case "BANKS":
                    explicitWait(PathConstants.WAIT_HIGH);
                    isElementPresent(banksBtn);
                    banksBtn.click();
                    break;
                case "ADD BANK":
                    isElementPresent(addBankBtn);
                    addBankBtn.click();
                    break;
                case "COUNTRY":
                    isElementPresent(countryDropDownBtn);
                    countryDropDownBtn.click();
                    break;
                case "AUTOMATION":
                    isElementPresent(isAutomationAllowBankCheckBtn);
                    isAutomationAllowBankCheckBtn.click();
                    break;
                case "SAVING STATEMENT IMPORT":
                    isElementPresent(isSavingStatementImportBankCheckBtn);
                    isSavingStatementImportBankCheckBtn.click();
                    break;
                case "CURRENT STATEMENT IMPORT":
                    isElementPresent(isCurrentStatementImportBankCheckBtn);
                    isCurrentStatementImportBankCheckBtn.click();
                    break;
//                case "SUBMIT BANK":
//                    isElementPresent(submitBankBtn);
//                    submitBankBtn.click();
//                    break;
                case "SUBMIT BANK":
                    isElementPresent(submitBankBtn);
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) TestBase.getWebDriver();
                    jsExecutor.executeScript("arguments[0].click();", submitBankBtn);
                     break;
                case "PROFILE":
                    isElementPresent(profileBtn);
                    profileBtn.click();
                    break;
                case "SUBMIT PASSWORD":
                    explicitWait(PathConstants.WAIT_HIGH);
                    isElementPresent(submitForChangePasswordBtn);
                    submitForChangePasswordBtn.click();
                    break;
                case "OTP SETTING":
                    isElementPresent(otpSettingBtn);
                    otpSettingBtn.click();
                    break;
                case "SAVE OTP":
                    isElementPresent(saveOtpBtn);
                    saveOtpBtn.click();
                    toasterMsgForOTP = baseUtil.getToasterMsg(successfullyPasswordChangeMsg).getText();
                    break;
                case "EDIT METHOD TYPE":
                    isElementPresent(editMethodType);
                    editMethodType.click();
                    break;
                case "EDIT PAYMENT":
                    isElementPresent(editDropdownPaymentMethodBtn);
                    editDropdownPaymentMethodBtn.click();
                    isElementPresent(editPaymentMethodBtn);
                    editPaymentMethodBtn.click();
                    break;
                case "PAYMENT ICON":
                    isElementPresent(paymentIconCloseBtn);
                    paymentIconCloseBtn.click();
                    break;
                case "EDIT BANK":
                    explicitWait(PathConstants.WAIT_LOW);
                    isElementPresent(editDropdownBankBtn);
                    editDropdownBankBtn.click();
                    isElementPresent(editBankBtn);
                    editBankBtn.click();
                    break;
                default:
                    logger.error("Button not found or not implemented: " + button);
            }
        } catch (Exception e) {
            logger.error("Error while clicking on button [" + button + "] :: " + e.getMessage());
        }
    }

    public void copyText(WebElement element) {
        try {
            Actions actions = new Actions(TestBase.getWebDriver());
            actions.click(element)
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .sendKeys("c")
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();
        } catch (Exception e) {
            logger.error("Exception occurred while copying text from element.", e);
        }
    }

    public void enterText(WebElement element, String text) {
        try {
            if (isElementPresent(element)) {
                Actions actions = new Actions(TestBase.getWebDriver());
                actions.click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).
                        sendKeys(Keys.DELETE).perform();
                element.sendKeys(text);
            }
        } catch (Exception e) {
            logger.error("Error doing enter the text :: " + e.getMessage());
        }
    }

    @After("@Test")
    public void closeBrowser() {
        try {
            TestBase.getWebDriver().quit();
        } catch (Exception e) {
            logger.error("Unable to close the browser");
        }
    }

    public void verifyToasterMsg(String action) {
        try {
            switch (action.toUpperCase()) {
                case "ADD PAYMENT":
                    Assert.assertEquals("Error message for payment not added",
                            "Payment method added successfully.",
                            baseUtil.getToasterMsg(successfullyPaymentAddedToasterMsg).getText());
                    logger.info(baseUtil.getToasterMsg(successfullyPaymentAddedToasterMsg));
                    logger.info("Payment method added successfully.");
                    break;
                case "STATUS":
                    explicitWait(PathConstants.WAIT_VERY_LOW);
                    clickOnButton("Status");
                    Assert.assertEquals("Error message for status not chanage",
                            "Status updated successfully",
                            baseUtil.getToasterMsg(manageIdStatusChangeToasterMsg).getText());
                    logger.info("Payment method status change successfully.");
                    break;
                case "ADD BANK":
                    Assert.assertEquals("Error message for bank not added",
                            "Bank account added successfully.",
                            baseUtil.getToasterMsg(successfullyBankAddedToasterMsg).getText());
                    logger.info(baseUtil.getToasterMsg(successfullyBankAddedToasterMsg));
                    logger.info("Bank added successfully.");
                    break;
                case "UPDATE BANK":
                    Assert.assertEquals("Error message for bank not added",
                            "Bank account updated successfully",
                            baseUtil.getToasterMsg(successfullyUpdateBankToasterMsg).getText());
                    logger.info(baseUtil.getToasterMsg(successfullyUpdateBankToasterMsg));
                    logger.info("Bank account updated successfully");
                    break;
                case "CHANGE PASSWORD":
                    Assert.assertEquals("Error message for password not chnage", "Password changed successfully", baseUtil.getToasterMsg(successfullyPasswordChangeMsg).getText());
                    logger.info(baseUtil.getToasterMsg(successfullyPasswordChangeMsg));
                    break;
                case "OTP UPDATE":
                    Assert.assertEquals("Error message for OTP setting not change",
                            "OTP Url updated successfully.", toasterMsgForOTP);
                    logger.info(baseUtil.getToasterMsg(successfullyOtpSettingUpdateMsg));
                    break;
                default:
                    logger.info("Unable to find action " + action);
            }
        } catch (AssertionError ae) {
            logger.error("Assertion failed for action: " + action, ae);
            throw ae;  // rethrow to fail the test if assertion fails
        } catch (Exception e) {
            logger.error("Exception occurred while verifying toaster message for action: " + action, e);
        }
    }

}
