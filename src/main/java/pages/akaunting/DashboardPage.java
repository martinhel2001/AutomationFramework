package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class DashboardPage extends BasePageInteractions {

    private By SalesMenu = By.linkText("Sales");
    private By CustomersSubMenu = By.linkText("Customers");

    private By DashboardMenu = By.linkText("Dashboard");

    private By ItemsMenu = By.linkText("Items");

    private By InvoicesSubMenu = By.linkText("Invoices");

    private By RevenuesSubMenu = By.linkText("Revenues");

    private By PurchasesMenu = By.linkText("Purchases");

    private By BillsSubMenu = By.linkText("Bills");

    private By PaymentsSubMenu = By.linkText("Payments");

    private By VendorsSubMenu = By.linkText("Vendors");

    private By BankingMenu = By.linkText("Banking");

    private By AccountsSubMenu = By.linkText("Accounts");

    private By TransfersSubMenu = By.linkText("Transfers");

    private By TransactionsSubMenu = By.linkText("Transactions");

    private By ReconciliationsSubMenu = By.linkText("Reconciliations");

    private By ReportsMenu = By.linkText("Reports");

    private By SettingsMenu = By.linkText("Settings");

    private By AppsMenu = By.linkText("Apps");

    public DashboardPage(EventFiringWebDriver session) {
        super(session);
    }

    public void goToCustomers() {
        eventDriver.findElement(SalesMenu).click();
        eventDriver.findElement(CustomersSubMenu).click();
    }
}

    //
