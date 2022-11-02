package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageInteractions {
    //protected WebDriver eventDriver;
    protected WebDriverWait wait;
    protected WebDriver eventDriver;



    public BasePageInteractions(WebDriver eventDriver) {
        this.eventDriver = eventDriver;
        wait = new WebDriverWait(eventDriver,20);
    }

    public void scrollIntoMiddle (WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) eventDriver).executeScript(scrollElementIntoMiddle, element);
    }

    public void scrollToTop() {
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        ((JavascriptExecutor) eventDriver).executeScript("window.scrollBy(0,-1000)");
    }

    public  boolean isElementPresent(By by) {
        try {
            eventDriver.findElement(by);
            if(!eventDriver.findElement(by).getCssValue("display").equals("none"))
            {return true;} else {return false;}
        } catch (NoSuchElementException e) {
            return false;
        }
    }

//    public boolean isClickable(WebElement webe, int timeOutInSeconds)
//    {
//        try
//        {
//            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
//            wait.until(ExpectedConditions.elementToBeClickable(webe));
//            return true;
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//    }


}
