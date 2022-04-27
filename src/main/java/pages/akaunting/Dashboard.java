package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePageInteractions;
import pages.akaunting.sales.CustomersMainPage;
import pages.akaunting.sales.InvoicesMainPage;

public class Dashboard extends BasePageInteractions {

    public Dashboard(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    InvoicesMainPage objInvoices = new InvoicesMainPage(eventDriver);
    By mainPanel = new By.ById("panel");
    By salesMenuOption = new By.ByLinkText("Sales");
    By invoicesSubMenuOption = new By.ByPartialLinkText("Invoices");
    By customersSubMenuOption = new By.ByPartialLinkText("Customers");

    public InvoicesMainPage goToInvoices() {
        this.getSalesMenuOption().click();
        this.getInvoicesSubMenuOption().click();
        wait.until(ExpectedConditions.visibilityOf(objInvoices.getHeaderTitle()));
        return new InvoicesMainPage(eventDriver);
    }

    public CustomersMainPage goToCustomers() {
        this.getSalesMenuOption().click();
        this.getCustomersSubMenuOption().click();
        wait.until(ExpectedConditions.visibilityOf(objInvoices.getHeaderTitle()));
        return new CustomersMainPage(eventDriver);
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

    public WebElement getCustomersSubMenuOption() {
        return eventDriver.findElement(customersSubMenuOption);
    }
}
