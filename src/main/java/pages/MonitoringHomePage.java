package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MonitoringHomePage  extends  BasePage {

    protected By systemEventsPanel = By.id("p-0");

    public MonitoringHomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getSystemEventsPanel() {
        return driver.findElement(systemEventsPanel);
    }
}
