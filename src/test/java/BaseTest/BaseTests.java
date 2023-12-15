package BaseTest;

import com.slack.api.model.Login;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.akaunting.LoginPage;

public class BaseTests {

    public static WebDriver driver;
    protected LoginPage loginPage;

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goHome();

        loginPage = new LoginPage(driver);
        //cmfLoginPage01 = new CMFLoginPage01(driver);

        //webFormPage = new WebFormPage(driver);
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://automationcampus.com.ar/akaunting/auth/login");

    }

    //@AfterClass
    public void tearDown(){
        driver.quit();

    }



}
