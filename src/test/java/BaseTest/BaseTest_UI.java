package BaseTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.Listeners.TestListenerUI;

@Listeners(TestListenerUI.class)

public class BaseTest_UI extends BaseTest{

    @BeforeSuite(alwaysRun = true)
    public void openDriver() {
        initializeDriver(false);
    }

    @BeforeMethod(alwaysRun = true)
    public void startWait(){
        wait = new WebDriverWait(driver,20);
    }

    public void mouseOverOn(WebElement ele){
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
    }

    public boolean isDisplayed(WebElement element) {
        if (element.getAttribute("display")=="none"||!element.isDisplayed()) {
            return false;
        } else return true;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownDriverClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
