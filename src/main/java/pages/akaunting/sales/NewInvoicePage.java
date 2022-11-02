package pages.akaunting.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class NewInvoicePage extends BasePageInteractions {

    public NewInvoicePage(WebDriver eventDriver) {
        super(eventDriver);
    }

    By headerTitle = new By.ById("Header");
    By addCustomerCard = new By.ByClassName("text-add-contact");
    By customerInput = new By.ByCssSelector("input[placeholder='Type a Customer name']");
    By invoiceDatePicker = new By.ByCssSelector("input.form-control.datepicker.input");

    public WebElement getHeaderTitle() {
        return eventDriver.findElement(headerTitle);
    }

    public WebElement getAddCustomerCard() {
        return eventDriver.findElement(addCustomerCard);
    }

    public WebElement getCustomerInput() {
        return eventDriver.findElement(customerInput);
    }

    public WebElement getInvoiceDatePicker() {
        return eventDriver.findElement(invoiceDatePicker);
    }



}
