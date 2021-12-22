package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ConfigurationHomePage extends KapschBasePage {

    protected By validationPanel = By.cssSelector("span[translate='NAVIGATION.GROUP_NAME_VALIDATION']");

    public ConfigurationHomePage(EventFiringWebDriver driver){
        super(driver);
    }

    public WebElement getValidationPanel() {
        return eventDriver.findElement(validationPanel);
    }

}
