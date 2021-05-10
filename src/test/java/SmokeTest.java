import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class SmokeTest extends BaseTest{
    @Test
    public void loginTest(){
        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        objLoginPage.login(username,password);
        wait.until(ExpectedConditions.visibilityOf(objHomePage.welcomeMessage()));
        Assert.assertTrue(objHomePage.welcomeMessage().isDisplayed());

        wait.until(ExpectedConditions.visibilityOf(objHomePage.headerMenu()));
        objHomePage.goToConfiguration();

    }
}
