package utils.Listeners;

import BaseTest.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitListener extends AbstractWebDriverEventListener {

    private static final int TIMEOUT_IN_SECONDS=30;
    private final WebDriverWait wait;
    public Logger log = Logger.getLogger(BaseTest.class.getName());


    public WebDriverWaitListener(WebDriver driver) {
        wait = new WebDriverWait(driver,TIMEOUT_IN_SECONDS);
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        long startTime = System.currentTimeMillis();
        log.info("beforeClickOn:: start");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        long endTime = System.currentTimeMillis();
        log.info("beforeClickOn:: end, took "+(endTime-startTime)+" milliseconds");
    }

    public void beforeGetText(WebElement element, WebDriver driver) {
        long startTime = System.currentTimeMillis();
        log.info("beforeGetText:: start");
        wait.until(ExpectedConditions.visibilityOf(element));
        long endTime = System.currentTimeMillis();
        log.info("beforeGetText:: end, took "+(endTime-startTime)+" milliseconds");
    }
}
