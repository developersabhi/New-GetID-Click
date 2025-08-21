package utils;

import constants.PathConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseUtil {
 public WebElement getToasterMsg(WebElement element){
     WebDriverWait wait = new WebDriverWait(TestBase.getWebDriver(), Duration.ofSeconds(PathConstants.WAIT_FOR_TOASTER));
    return  wait.until(ExpectedConditions.visibilityOfElementLocated(
             By.cssSelector(".toast-message")));
 }
}
