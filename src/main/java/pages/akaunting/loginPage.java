package pages.akaunting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {

    private WebDriver driver;

    private By EmailFld = By.name("email");
    private By PasswordFld = By.name("password");
    private By LoginBtn = By.className("btn btn-success float-right");
    private By RememberMeChk = By.id("checkbox-remember");

    private By IforgotMyPassLnk = By.linkText("I forgot my password");



    public LoginPage (WebDriver driver){
        this.driver = driver;
    }

    public void SetEmail(String email){
        driver.findElement(EmailFld).sendKeys(email);
    }

    public void setPassword (String password){
        driver.findElement(PasswordFld).sendKeys(password);
    }






}
