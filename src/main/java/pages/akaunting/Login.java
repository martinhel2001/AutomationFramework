package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class Login extends BasePageInteractions {

    public Login(EventFiringWebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    By emailInput = new By.ByName("email");
    By passwordInput = new By.ByName("password");
    By rememberMeCheckbox = new By.ById("checkbox-remember");
    By forgotPasswordLink = new By.ByPartialLinkText("forgot");
    By loginBtn = new By.ByClassName("btn-success");

    public void tryLogin(String email, String pass, boolean rememberMe){
        eventDriver.findElement(emailInput).sendKeys(email);
        eventDriver.findElement(passwordInput).sendKeys(pass);
        //if (eventDriver.findElement(rememberMeCheckbox).isSelected()){eventDriver.findElement(rememberMeCheckbox).click();};
        //if (rememberMe){eventDriver.findElement(rememberMeCheckbox).click();}
        eventDriver.findElement(loginBtn).click();
    }

}

