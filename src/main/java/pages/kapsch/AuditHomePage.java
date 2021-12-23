package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class AuditHomePage extends KapschBasePage {

    protected By viewCentralizedAuditPanel = By.cssSelector("span[translate='NAVIGATION.MANAGEMENT_MENU']");
    protected By homeTitle = By.cssSelector("span[translate='NAVIGATION.HOME_MENU']");

    public AuditHomePage(EventFiringWebDriver driver){
        super(driver);
    }

    public WebElement getViewCentralizedAuditPanel() {
        return eventDriver.findElement(viewCentralizedAuditPanel);
    }

    //@Override
    public WebElement getHomeTitle() { return eventDriver.findElement(homeTitle);}

}
