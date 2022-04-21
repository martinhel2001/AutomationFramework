package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePageInteractions;

public class Dashboard extends BasePageInteractions {

    public Dashboard(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    InvoicesMain objInvoices = new InvoicesMain(eventDriver);
    By mainPanel = new By.ById("panel");
    By salesMenuOption = new By.ByLinkText("Sales");
    By invoicesSubMenuOption = new By.ByPartialLinkText("Invoices");

    public InvoicesMain goToInvoices() {
        this.getSalesMenuOption().click();
        this.getInvoicesSubMenuOption().click();
        wait.until(ExpectedConditions.visibilityOf(objInvoices.getHeaderTitle()));
        return new InvoicesMain(eventDriver);
    }

    public WebElement getSalesMenuOption() {
        return eventDriver.findElement(salesMenuOption);
    }

    public WebElement getMainPanel() {
        return eventDriver.findElement(mainPanel);
    }

    public WebElement getInvoicesSubMenuOption() {
        return eventDriver.findElement(invoicesSubMenuOption);
    }
}
