package BaseTest;

import baseMain.WebDriverFactoryStaticThreadLocal;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.Listeners.TestListenerUI;
import utils.Listeners.WebDriverWaitListener;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Listeners(TestListenerUI.class)

public class BaseTest_UI_parallel extends BaseTest{
    //protected static WebDriver driver;
    //public static EventFiringWebDriver eventDriver;
    public static WebDriver driver;

    @BeforeClass
    public void setUpDriver()
    {
        WebDriverFactoryStaticThreadLocal.setDriverThread();
        driver = WebDriverFactoryStaticThreadLocal.getDriverThread();
        System.out.println("Browser setup by Thread "+Thread.currentThread().getId()+" and Driver reference is : "+driver);
    }


    @BeforeMethod(alwaysRun = true)
    public void startWait(){
        wait = new WebDriverWait(WebDriverFactoryStaticThreadLocal.getDriverThread(),20);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
    }

    public void mouseOverOn(WebElement ele){
        Actions action = new Actions(WebDriverFactoryStaticThreadLocal.getDriverThread());
        action.moveToElement(ele).perform();
    }

    public boolean isDisplayed(WebElement element) {
        if (element.getAttribute("display")=="none"||!element.isDisplayed()) {
            return false;
        } else return true;
    }


    protected File takeScreenshot(String methodName){
        File screenshot = null;
        try {
            screenshot = ((TakesScreenshot) WebDriverFactoryStaticThreadLocal.getDriverThread()).getScreenshotAs(OutputType.FILE);
            log.info("Screenshot taken");
        } catch (WebDriverException e) {
            log.info("Exception while taking screenshot");
            e.printStackTrace();
        }

        //Copy the file to a location and use try catch block to handle exception
        String timestamp = ZonedDateTime
                .now( ZoneId.systemDefault() )
                .format( DateTimeFormatter.ofPattern( "uuuu.MM.dd.HH.mm.ss" ) );
        localScreenshotFileName=methodName+"_"+timestamp+".png";
        localScreenshotCompleteFileName ="C:\\Automation\\"+localScreenshotFileName;
        try {
            FileUtils.copyFile(screenshot, new File(localScreenshotCompleteFileName));
            System.out.println("Screenshot saved at: "+ localScreenshotCompleteFileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return screenshot;
    }

    @AfterClass
    public void tearDownDriverClass() {
        if (driver != null) {
            WebDriverFactoryStaticThreadLocal.closeBrowser();
        }
    }

}
