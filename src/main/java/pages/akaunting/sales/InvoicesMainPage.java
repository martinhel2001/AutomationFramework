package pages.akaunting.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class InvoicesMainPage extends BasePageInteractions {

    public InvoicesMainPage(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    By headerTitle = new By.ById("Header");
    By addNewBtn = new By.ByLinkText("Add New");

    public WebElement getHeaderTitle() {
        return eventDriver.findElement(headerTitle);
    }

    public WebElement getAddNewBtn() {
        return eventDriver.findElement(addNewBtn);
    }

    public NewInvoicePage addNewInvoice() {
        eventDriver.findElement(addNewBtn).click();
        return new NewInvoicePage(eventDriver);
    }
}
