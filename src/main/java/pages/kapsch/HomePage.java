package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class HomePage extends KapschBasePage {
    //WebDriver driver;

    protected By welcomeMessageTxt = By.className("welcome-message");

    public HomePage(EventFiringWebDriver driver){
        super(driver);
    }

    public WebElement getWelcomeMessage(){
        return this.eventDriver.findElement(welcomeMessageTxt);
    }
}
