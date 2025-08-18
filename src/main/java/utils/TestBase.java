package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {

    private static final Logger logger = Logger.getLogger(TestBase.class);
    static protected WebDriver driver = null;
    private static TestBase instance;

    public  void init(){
        try {
            if(driver == null){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--Start-maximized");
                options.addArguments("--disable-notification");
                options.addArguments("--incognito");
                driver = new ChromeDriver();
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
    }
}
