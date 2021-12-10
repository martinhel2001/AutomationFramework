package BaseTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.Listeners.TestListenerUI;

@Listeners(TestListenerUI.class)
public class BaseTest_UI extends BaseTest{
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
