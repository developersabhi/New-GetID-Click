package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonMethod {

    private static final Logger logger = Logger.getLogger(CommonMethod.class);
    WebDriverWait wait = new WebDriverWait(TestBase.getWebDriver(), Duration.ofMillis(1000));
    private static final TestBase testbase = TestBase.getInstance();

    public CommonMethod() {
        PageFactory.initElements(TestBase.getWebDriver(), this);
    }

    public static Map<String, String> readPropertied(){
        Map<String,String> all = new HashMap<>();
        Properties properties = new Properties();
        try {
            logger.info("Read Properties Files ::");
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/global.properties");
            properties.load(inputStream);
            Enumeration<Object> keys = properties.keys();
            while(keys.hasMoreElements()){
                String key =(String) keys.nextElement();
                all.put(key, properties.getProperty(key));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return all;
    }

    public void eplicitWait(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            logger.error("Getting error while doing explicit wait:: " + e.getMessage());
        }
    }

    public static TestBase getTestbase() {
        return new TestBase();
    }

    public void waitForVisibleElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementPresent(WebElement element){
        try {
            return element.isDisplayed();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return  false;
        }catch (Exception e){
            return  false;
        }
    }

    public void clickOnButton(String button){
        switch (button.toUpperCase()){
            case "LOGIN":
                break;
            case "":
                break;
            default:
                logger.error("Button not click or not found :: "+getClass());
        }
    }
}
