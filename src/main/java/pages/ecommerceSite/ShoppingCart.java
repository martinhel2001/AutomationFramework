package pages.ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePage;

public class ShoppingCart extends BasePage {

    public ShoppingCart(EventFiringWebDriver driver) {
        super(driver);
    }

    By headingCounter = new By.ByClassName("heading-counter");

    public String getHeadingCounter() {
        return eventDriver.findElement(headingCounter).getText();
    }
}
