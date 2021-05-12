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
    static WebDriverWait wait;


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

    public static void initializeLocalizationDriver(String proxyCountry, boolean isResponsive)
    {
        String[] browsers = {
                "CHROME"};

        int idx = new Random().nextInt(browsers.length);
        String browser = (browsers [idx]);
        System.out.println("Browser used for the test: "+browser);
        initializeLocalizationDriver(proxyCountry,isResponsive,browser);
    }

    public static void initializeLocalizationDriver(String proxyCountry, boolean isResponsive, String browser)
    {

        // CHROME
        if (browser.equals("CHROME")) {
            WebDriverManager.chromedriver().clearResolutionCache();
            //WebDriverManager.chromedriver().version("83").setup();
            WebDriverManager.chromedriver().setup();
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

            switch (proxyCountry) {
                case "DEFAULT":
                    break;
                case "CO":
                    options.addExtensions(new File("src/main/resources/proxyColombia.zip"));
                    break;
                case "MX":
                    options.addExtensions(new File("src/main/resources/proxyMexico.zip"));
                    break;
                case "CL":
                    options.addExtensions(new File("src/main/resources/proxyChile.zip"));
                    break;
                case "PE":
                    options.addExtensions(new File("src/main/resources/proxyPeru.zip"));
                    break;
                case "EC":
                    options.addExtensions(new File("src/main/resources/proxyEcuador.zip"));
                    break;
                case "AR":
                    options.addExtensions(new File("src/main/resources/proxyArgentina.zip"));
                    break;
                case "US":
                    options.addExtensions(new File("src/main/resources/proxyUSA.zip"));
                    break;
                case "VE":
                    options.addExtensions(new File("src/main/resources/proxyVenezuela.zip"));
                    break;
                case "GT":
                    options.addExtensions(new File("src/main/resources/proxyGuatemala.zip"));
                    break;
                case "ES":
                    options.addExtensions(new File("src/main/resources/proxyEspana.zip"));
                    break;
                case "CR":
                    options.addExtensions(new File("src/main/resources/proxyCostaRica.zip"));
                    break;
                case "PA":
                    options.addExtensions(new File("src/main/resources/proxyPanama.zip"));
                    break;
                case "HN":
                    options.addExtensions(new File("src/main/resources/proxyHonduras.zip"));
                    break;
                case "UY":
                    options.addExtensions(new File("src/main/resources/proxyUruguay.zip"));
                    break;
                case "BR":
                    options.addExtensions(new File("src/main/resources/proxyBrasil.zip"));
                    break;
                case "CA":
                    options.addExtensions(new File("src/main/resources/proxyCanada.zip"));
                    break;
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
                //   options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                //   options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }
            driver = new EdgeDriver(options);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (isResponsive) driver.manage().window().setSize(new Dimension(375, 812));
    }

    @BeforeSuite
    public void setUser(){
        UserPropertiesReader userReader = new UserPropertiesReader("Administrator");
        username = userReader.getUsername();
        password = userReader.getPassword();
    }

    @BeforeSuite(alwaysRun = true)
    public void openDriver() {
        initializeDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownDriverClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
