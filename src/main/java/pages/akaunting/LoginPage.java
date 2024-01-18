package pages.akaunting;
import org.apache.poi.ss.formula.functions.Even;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class LoginPage extends BasePageInteractions {

    private By emailFld = By.name("email");
    private By passwordFld = By.name("password");
    private By loginBtn = By.className("btn-success");
    private By rememberMeChk = By.id("checkbox-remember");

    private By iForgotMyPassLnk = By.linkText("I forgot my password");

    public LoginPage(EventFiringWebDriver session){
        super(session);
        session.get("https://automationcampus.com.ar/akaunting/auth/login");
    }
    public void setEmail(String email){
        eventDriver.findElement(emailFld).sendKeys(email);
    }
    public void setPassword(String password){
        eventDriver.findElement(passwordFld).sendKeys(password);
    }
    public void doLogin(String email, String password){
        eventDriver.findElement(emailFld).sendKeys(email);
        eventDriver.findElement(passwordFld).sendKeys(password);
        eventDriver.findElement(loginBtn).click();
    }
}
