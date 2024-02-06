package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class CustomersPage extends BasePageInteractions {

    private By CustomerUpdatedBanner = By.className("alert");
    private By CustomersTable = By.className("table-responsive");

    private By customerRow = By.linkText("Asoka");

    //private By CustomerSearchFld = By.cssSelector("input[placeholder="Search or filter results.."]");

    public CustomersPage(EventFiringWebDriver session){
        super(session);
    }
private By AddNewBtn = By.linkText("Add New");

    public String searchCustomer(String customer){
        eventDriver.findElement(CustomersTable).
    }

public void AddNewBtnClick(){
    eventDriver.findElement(AddNewBtn).click();
}




}
