package pages.akaunting.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class NewInvoicePage extends BasePageInteractions {

    public NewInvoicePage(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    By headerTitle = new By.ById("Header");
    By addCustomerCard = new By.ByClassName("text-add-contact");
    By customerInput = new By.ByCssSelector("input[placeholder='Type a Customer name']");
    By invoiceDatePicker = new By.ByCssSelector("input.form-control.datepicker.input");

    public WebElement getHeaderTitle() {
        return eventDriver.findElement(headerTitle);
    }
}
