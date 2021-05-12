import baseMain.UserPropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    static WebDriver driver;
    static String username;
    static String password;
    static WebDriverWait wait ;

    public void initializeDriver(){
        WebDriverManager.chromedriver().clearResolutionCache();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    public void initializeDriver(boolean isResponsive)
    {
        String[] browsers = {
                "CHROME",
                //"FIREFOX",  TODO: Instalar Firefox en la PC para poder usar su webdriver.
                "EDGE"
        };

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
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (isResponsive) driver.manage().window().setSize(new Dimension(375, 812));
        wait = new WebDriverWait(driver,20);

    }

    @BeforeSuite
    public void setUser(){
        UserPropertiesReader userReader = new UserPropertiesReader("Administrator");
        username = userReader.getUsername();
        password = userReader.getPassword();
    }

    @BeforeSuite(alwaysRun = true)
    public void openDriver() {
        initializeDriver(false);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownDriverClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
