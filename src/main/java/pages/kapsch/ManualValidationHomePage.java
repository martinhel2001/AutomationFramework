package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManualValidationHomePage extends KapschBasePage {

    protected By manualValidationPanel = By.cssSelector("span[translate='NAVIGATION.MANUAL_VALIDATION_WORKSPACE_MENU'");

    public ManualValidationHomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getManualValidationPanel() {
        return driver.findElement(manualValidationPanel);
    }
}
