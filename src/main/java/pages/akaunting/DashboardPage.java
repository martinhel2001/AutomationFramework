package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class DashboardPage extends BasePageInteractions {
    private By SalesMnu = By.linkText("Sales");
    private By CustomersSubMnu = By.linkText("Customers");
    public DashboardPage(EventFiringWebDriver session) {
        super(session);
    }

    public void goToCustomers() {
        eventDriver.findElement(SalesMnu).click();
        eventDriver.findElement(CustomersSubMnu).click();
    }
}


