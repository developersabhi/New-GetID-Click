package utils;

import constants.PathConstants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static utils.CommonMethod.readPropertied;

public class TestBase {

    private static final Logger logger = Logger.getLogger(TestBase.class);
    static protected WebDriver driver = null;
    private static TestBase instance;
    public static Map<String,String> globPop = null;
    CommonMethod commonMethod = new CommonMethod();

    public  void init(){
        try {
            if(driver == null){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--Start-maximized");
                options.addArguments("--disable-notification");
//                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
            }
        }catch (Exception e) {
            logger.error("Driver not init ::"+getClass()+ " "+e.getMessage());
        }
    }

    public static TestBase getInstance(){
        if(instance == null){
            instance = new TestBase();
        }
        return instance;
    }

    public static WebDriver getWebDriver(){
        return driver;
    }

    public TestBase(){
        init();
        PageFactory.initElements(getWebDriver(), this);
        globPop = readPropertied();
    }
    @Before("@Test")
    public void login(){
        try {
                String  currentUrl =getWebDriver().getCurrentUrl();
                logger.info("Current URL :: "+currentUrl);

                if(!"data".equals(currentUrl)){
                    getWebDriver().get(globPop.get("directorUrl"));
                    commonMethod.waitForVisibleElement(driver.findElement(By.xpath(globPop.get("Director_username_xpath"))));
                    commonMethod.enterText(driver.findElement(By.xpath(globPop.get("Director_username_xpath"))),globPop.get("directorLoginId"));
                    commonMethod.waitForVisibleElement(driver.findElement(By.xpath(globPop.get("Director_password_xpath"))));
                    commonMethod.enterText(driver.findElement(By.xpath(globPop.get("Director_password_xpath"))),globPop.get("directorPassword") );
                    commonMethod.explicitWait(PathConstants.WAIT_LOW);
                    commonMethod.clickOnButton("Login");
                }else {
                    getWebDriver().navigate().refresh();
                    logger.info("User already present on login page.");
                }
        }catch (Exception e){
            logger.error("User get error :: "+e.getMessage());
        }
    }

    public void logout(){
        try {
            commonMethod.waitForVisibleElement(driver.findElement(By.xpath(globPop.get("loginBtn"))));
            commonMethod.clickOnButton("Logout");
        }catch (Exception e){
            logger.error("Get error during logout :: "+e.getMessage());
        }
    }

    public void quitBrowser(){
        getWebDriver().quit();
    }
}
