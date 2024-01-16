package pages.akaunting;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class CustomerDetailPage extends BasePageInteractions {


    private By customerName = By.cssSelector("h2.d-inline-flex");

    public CustomerDetailPage(EventFiringWebDriver session) {
        super(session);
    }

    public WebElement getCustomerName(){
        return eventDriver.findElement(customerName);
        }

}
