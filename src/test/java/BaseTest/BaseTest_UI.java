package BaseTest;

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

public class BaseTest_UI extends BaseTest{
    private static WebDriver driver;
    public static EventFiringWebDriver eventDriver;

    @BeforeSuite(alwaysRun = true)
    public void openDriver() {
        initializeDriver(false);
    }

    @BeforeMethod(alwaysRun = true)
    public void startWait(){
        wait = new WebDriverWait(eventDriver,20);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
    }

    public void mouseOverOn(WebElement ele){
        Actions action = new Actions(eventDriver);
        action.moveToElement(ele).perform();
    }

    public boolean isDisplayed(WebElement element) {
        if (element.getAttribute("display")=="none"||!element.isDisplayed()) {
            return false;
        } else return true;
    }

    public void initializeDriver(boolean isResponsive)
    {
        String[] browsers =
                //{
                //"CHROME",
                //"FIREFOX",
                //"EDGE"
                (String[]) testsConfig.getBrowsers().split(",");
        //};

        int idx = new Random().nextInt(browsers.length);
        String browser = (browsers [idx]);
        System.out.println("Browser used for the test: "+browser);


        // CHROME
        if (browser.equals("CHROME")) {
            WebDriverManager.chromedriver().setup();
            WebDriverManager.chromedriver().clearResolutionCache();
            //WebDriverManager.chromedriver().version("83").setup();
            ChromeOptions options = new ChromeOptions();

            if (isResponsive) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                options.setExperimentalOption("mobileEmulation", mobileEmulation);
            } else {
                options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }

            options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770

            // SI QUEREMOS CORRERLO EN MODO HEADLESS   - BEGIN
//            options.addArguments("--headless");
//            options.addArguments("--disable-gpu");
            // SI QUEREMOS CORRERLO EN MODO HEADLESS   - END

            driver = new ChromeDriver(options);
            eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(new WebDriverWaitListener(driver));
        }

        // FIREFOX
        if (browser.equals("FIREFOX")) {
            WebDriverManager.firefoxdriver().clearResolutionCache();
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (isResponsive) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                //options.setExperimentalOption("mobileEmulation", mobileEmulation);
            } else {
                options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }
            driver = new FirefoxDriver(options);
            eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(new WebDriverWaitListener(driver));
        }

        // EDGE
        if (browser.equals("EDGE")) {
            WebDriverManager.edgedriver().clearResolutionCache();
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();

            if (isResponsive) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                //options.setExperimentalOption("mobileEmulation", mobileEmulation);
            } else {
                //options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                //options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }
            driver = new EdgeDriver(options);
            eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(new WebDriverWaitListener(driver));
        }

//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        if (isResponsive) driver.manage().window().setSize(new Dimension(375, 812));

        eventDriver.manage().deleteAllCookies();
        eventDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (isResponsive) eventDriver.manage().window().setSize(new Dimension(375, 812));

    }

    protected File takeScreenshot(String methodName){
        File screenshot = null;
        try {
            screenshot = ((TakesScreenshot) eventDriver).getScreenshotAs(OutputType.FILE);
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

    @AfterSuite(alwaysRun = true)
    public void tearDownDriverClass() {
        if (eventDriver != null) {
            eventDriver.quit();
        }
    }
}
