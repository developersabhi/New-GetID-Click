package utils;

import constants.PathConstants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static utils.CommonMethod.readPropertied;

public class TestBase {

    private static final Logger logger = Logger.getLogger(TestBase.class);
    static protected WebDriver driver = null;
    private static TestBase instance;
    public static Map<String, String> globPop = null;
    CommonMethod commonMethod = new CommonMethod();

    public void init() {
        try {
            if (driver == null) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--Start-maximized");
                options.addArguments("--disable-notification");
//                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
            }
        } catch (Exception e) {
            logger.error("Driver not init ::" + getClass() + " " + e.getMessage());
        }
    }

    public static TestBase getInstance() {
        if (instance == null) {
            instance = new TestBase();
        }
        return instance;
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public TestBase() {
        init();
        PageFactory.initElements(getWebDriver(), this);
        globPop = readPropertied();
    }

    @Before("@Test")
    public void login() {
        try {
            String currentUrl = getWebDriver().getCurrentUrl();
            logger.info("Current URL :: " + currentUrl);

            if (!"data".equals(currentUrl)) {
                getWebDriver().get(globPop.get("directorUrl"));
                commonMethod.waitForVisibleElement(driver.findElement(By.xpath(globPop.get("Director_username_xpath"))));
                commonMethod.enterText(driver.findElement(By.xpath(globPop.get("Director_username_xpath"))), globPop.get("directorLoginId"));
                commonMethod.waitForVisibleElement(driver.findElement(By.xpath(globPop.get("Director_password_xpath"))));
                commonMethod.enterText(driver.findElement(By.xpath(globPop.get("Director_password_xpath"))), globPop.get("directorPassword"));
                commonMethod.explicitWait(PathConstants.WAIT_LOW);
                commonMethod.clickOnButton("Login");
            } else {
                getWebDriver().navigate().refresh();
                logger.info("User already present on login page.");
            }
        } catch (Exception e) {
            logger.error("User get error :: " + e.getMessage());
        }
    }

    public void login(String panel) {
        try {
            String currentUrl = null;
            switch (panel.toLowerCase()) {
                case "director":
                    currentUrl = getWebDriver().getCurrentUrl();
                    logger.info("Current URL :: " + currentUrl);
                    if (!"data".equals(currentUrl)) {
                        getWebDriver().get(globPop.get("directorUrl"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Director_username_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Director_username_xpath"))), globPop.get("directorLoginId"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Director_password_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Director_password_xpath"))), globPop.get("directorPassword"));

                        commonMethod.explicitWait(PathConstants.WAIT_LOW);
                        commonMethod.clickOnButton("Login");

                        logger.info("Director login :: " + getClass());
                    }
                    break;
                case "whitelabel":
                    currentUrl = getWebDriver().getCurrentUrl();
                    logger.info("Current URL :: " + currentUrl);
                    if (!"data".equals(currentUrl)) {
                        getWebDriver().get(globPop.get("whitelabelUrl"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Whitelabel_username_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Whitelabel_username_xpath"))), globPop.get("whitelabelLoginId"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Whitelabel_password_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Whitelabel_password_xpath"))), globPop.get("whitelabelPassword"));

                        commonMethod.explicitWait(PathConstants.WAIT_LOW);
                        commonMethod.clickOnButton("Login");

                        logger.info("Director login :: " + getClass());
                    }
                    break;
                case "agent":
                    currentUrl = getWebDriver().getCurrentUrl();
                    logger.info("Current URL :: " + currentUrl);
                    if (!"data".equals(currentUrl)) {
                        getWebDriver().get(globPop.get("agentUrl"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Agent_username_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Agent_username_xpath"))), globPop.get("agentLoginId"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Agent_password_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Agent_password_xpath"))), globPop.get("agentPassword"));

                        commonMethod.explicitWait(PathConstants.WAIT_LOW);
                        commonMethod.clickOnButton("Login");

                        logger.info("Agent login :: " + getClass());
                    }
                    break;
                case "employee":
                    currentUrl = getWebDriver().getCurrentUrl();
                    logger.info("Current URL :: " + currentUrl);
                    if (!"data".equals(currentUrl)) {
                        getWebDriver().get(globPop.get("employeeUrl"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Employee_username_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Employee_username_xpath"))), globPop.get("employeeLoginId"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("Employee_password_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("Employee_password_xpath"))), globPop.get("employeePassword"));

                        commonMethod.explicitWait(PathConstants.WAIT_LOW);
                        commonMethod.clickOnButton("Login");

                        logger.info("Employee login :: " + getClass());
                    }
                    break;
                case "user":
                    currentUrl = getWebDriver().getCurrentUrl();
                    logger.info("Current URL :: " + currentUrl);
                    if (!"data".equals(currentUrl)) {
                        getWebDriver().get(globPop.get("userUrl"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("User_username_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("User_username_xpath"))), globPop.get("userLoginId"));

                        commonMethod.isElementPresent(getWebDriver().findElement(By.xpath(globPop.get("User_password_xpath"))));

                        commonMethod.enterText(getWebDriver().findElement(By.xpath(globPop.get("User_password_xpath"))), globPop.get("userPassword"));

                        commonMethod.explicitWait(PathConstants.WAIT_LOW);
                        commonMethod.clickOnButton("Login");

                        logger.info("User login :: " + getClass());
                    } else {
                        getWebDriver().navigate().refresh();
                        logger.info("User already present on login page.");
                    }
                    break;
                default:
                    getWebDriver().navigate().refresh();
                    logger.info("User already present on login page.");
            }
        } catch (Exception e) {
            logger.error("User get error :: " + e.getMessage());
        }
    }

//    public void login(String username) {
//        try {
//            String currentUrl = getWebDriver().getCurrentUrl();
//            logger.info("Current URL :: " + currentUrl);
//
//            if ("data".equals(currentUrl)) {
//                logger.info("Already on data page. Skipping login.");
//                return;
//            }
//
//            String urlKey = "", usernameXPathKey = "", passwordXPathKey = "", loginIdKey = "", passwordKey = "";
//
//            switch (username.toLowerCase()) {
//                case "director":
//                    urlKey = "directorUrl";
//                    usernameXPathKey = "Director_username_xpath";
//                    passwordXPathKey = "Director_password_xpath";
//                    loginIdKey = "directorLoginId";
//                    passwordKey = "directorPassword";
//                    break;
//
//                case "whitelabel":
//                    urlKey = "whitelabelUrl";
//                    usernameXPathKey = "Whitelabel_username_xpath";
//                    passwordXPathKey = "Whitelabel_password_xpath";
//                    loginIdKey = "whitelabelLoginId";
//                    passwordKey = "whitelabelPassword";
//                    break;
//
//                case "agent":
//                    urlKey = "agentUrl";
//                    usernameXPathKey = "Agent_username_xpath";
//                    passwordXPathKey = "Agent_password_xpath";
//                    loginIdKey = "agentLoginId";
//                    passwordKey = "agentPassword";
//                    break;
//
//                case "employee":
//                    urlKey = "employeeUrl";
//                    usernameXPathKey = "Employee_username_xpath";
//                    passwordXPathKey = "Employee_password_xpath";
//                    loginIdKey = "employeeLoginId";
//                    passwordKey = "employeePassword";
//                    break;
//
//                case "user":
//                    urlKey = "userUrl";
//                    usernameXPathKey = "User_username_xpath";
//                    passwordXPathKey = "User_password_xpath";
//                    loginIdKey = "userLoginId";
//                    passwordKey = "userPassword";
//                    break;
//
//                default:
//                    logger.warn("Unrecognized user role: " + username + ". Refreshing page.");
//                    getWebDriver().navigate().refresh();
//                    return;
//            }
//
//            // Navigate and login
//            getWebDriver().get(globPop.get(urlKey));
//
//            WebElement usernameField = getWebDriver().findElement(By.xpath(globPop.get(usernameXPathKey)));
//            WebElement passwordField = getWebDriver().findElement(By.xpath(globPop.get(passwordXPathKey)));
//
//            commonMethod.isElementPresent(usernameField);
//            commonMethod.enterText(usernameField, globPop.get(loginIdKey));
//
//            commonMethod.isElementPresent(passwordField);
//            commonMethod.enterText(passwordField, globPop.get(passwordKey));
//
//            commonMethod.explicitWait(PathConstants.WAIT_LOW);
//            commonMethod.clickOnButton("Login");
//
//            logger.info(username + " login successful :: " + getClass());
//
//        } catch (Exception e) {
//            logger.error("Login error for user '" + username + "' :: " + e.getMessage(), e);
//        }
//    }


    public void logout() {
        try {
            commonMethod.waitForVisibleElement(driver.findElement(By.xpath(globPop.get("loginBtn"))));
            commonMethod.clickOnButton("Logout");
        } catch (Exception e) {
            logger.error("Get error during logout :: " + e.getMessage());
        }
    }

    public void quitBrowser() {
        getWebDriver().quit();
    }
}
