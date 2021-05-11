package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    //WebDriver driver;
    protected By loginHeader = By.className("single-box-header");
    protected By usernameLabel = By.xpath("/html/body/div[4]/div/div/div/form/fieldset/div[1]/div/p");
    protected By userNameInput = By.id("username");
    protected By passwordLabel = By.xpath("/html/body/div[4]/div/div/div/form/fieldset/div[2]/div/p");
    protected By passwordInput = By.id("password");
    protected By submitBtn = By.name("submit");

    public HomePage objHomePage = new HomePage(driver);

    public LoginPage (WebDriver driver){
        super(driver);
        driver.manage().deleteAllCookies();
        driver.get("http://intra.obo.test3.fraprr.phxcicd.ktc-int.net/");
    }

    public void login(String username, String password){

        this.driver.findElement(userNameInput).sendKeys(username);
        this.driver.findElement(passwordInput).sendKeys(password);
        this.driver.findElement(submitBtn).click();
        wait.until(ExpectedConditions.visibilityOf( objHomePage.getWelcomeMessage()));
    }
}
