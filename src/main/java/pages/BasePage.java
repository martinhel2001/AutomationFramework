package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }



    public void scrollIntoMiddle (WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void scrollToTop() {
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1000)");
    }

    public  boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            if(!driver.findElement(by).getCssValue("display").equals("none"))
            {return true;} else {return false;}
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isClickable(WebElement webe, int timeOutInSeconds)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(webe));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
