package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    //WebDriver driver;

    protected By welcomeMessageTxt = By.className("welcome-message");

    public HomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getWelcomeMessage(){
        return this.driver.findElement(welcomeMessageTxt);
    }
}
