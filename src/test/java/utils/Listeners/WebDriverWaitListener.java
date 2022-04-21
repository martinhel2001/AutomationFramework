package utils.Listeners;

import BaseTest.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitListener extends AbstractWebDriverEventListener {

    private static final int TIMEOUT_IN_SECONDS=30;
    private final WebDriverWait wait;
    public Logger log = Logger.getLogger(BaseTest.class.getName());
    private JavascriptExecutor js;


    public WebDriverWaitListener(WebDriver driver) {
        wait = new WebDriverWait(driver,TIMEOUT_IN_SECONDS);
        js = (JavascriptExecutor) driver;
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        long startTime = System.currentTimeMillis();
        log.info("beforeClickOn '"+element.getText()+"':: start");
        //wait.until(ExpectedConditions.elementToBeClickable(element));
        long endTime = System.currentTimeMillis();
        log.info("beforeClickOn '"+element.getText()+"':: end, took "+(endTime-startTime)+" milliseconds");
    }

    public void beforeGetText(WebElement element, WebDriver driver) {
        long startTime = System.currentTimeMillis();
        log.info("beforeGetText '"+element.getText()+"':: start");
        wait.until(ExpectedConditions.visibilityOf(element));
        long endTime = System.currentTimeMillis();
        log.info("beforeGetText '"+element.getText()+"':: end, took "+(endTime-startTime)+" milliseconds");
    }
}
