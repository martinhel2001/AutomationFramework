package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class NewInvoice extends BasePageInteractions {

    public NewInvoice(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    By headerTitle = new By.ById("Header");

    public WebElement getHeaderTitle() {
        return eventDriver.findElement(headerTitle);
    }
}
