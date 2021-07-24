package pages.ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ShoppingCart extends BasePage {

    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    By headingCounter = new By.ByClassName("heading-counter");

    public String getHeadingCounter() {
        return driver.findElement(headingCounter).getText();
    }
}
