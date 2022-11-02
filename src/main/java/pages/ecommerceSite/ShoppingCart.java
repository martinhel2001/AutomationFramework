package pages.ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class ShoppingCart extends BasePageInteractions {

    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    By headingCounter = new By.ByClassName("heading-counter");

    public String getHeadingCounter() {
        return eventDriver.findElement(headingCounter).getText();
    }
}
