package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MonitoringHomePage  extends KapschBasePage {

    protected By systemEventsPanel = By.id("p-0");

    public MonitoringHomePage(EventFiringWebDriver driver){
        super(driver);
    }

    public WebElement getSystemEventsPanel() {
        return eventDriver.findElement(systemEventsPanel);
    }
}
