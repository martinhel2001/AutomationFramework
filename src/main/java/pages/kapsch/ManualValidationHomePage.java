package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManualValidationHomePage extends KapschBasePage {

    protected By manualValidationPanel = By.cssSelector("span[translate='NAVIGATION.MANUAL_VALIDATION_WORKSPACE_MENU'");
    protected By manualValidationContent = By.id("pfix-01-content");
    ManualValidationPage obj_MVpage = new ManualValidationPage(eventDriver);

    public ManualValidationHomePage(EventFiringWebDriver driver){
        super(driver);
    }

    public WebElement getManualValidationPanel() {
        return eventDriver.findElement(manualValidationPanel);
    }

    public void goToManualValidationContent(){
        eventDriver.findElement(manualValidationPanel).click();
        wait.until(ExpectedConditions.elementToBeClickable(obj_MVpage.manualValidationHeader));
    }
}
