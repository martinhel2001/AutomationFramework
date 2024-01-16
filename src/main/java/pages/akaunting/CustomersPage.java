package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class CustomersPage extends BasePageInteractions {

    public CustomersPage(EventFiringWebDriver session){
        super(session);
    }
private By AddNewBtn = By.linkText("Add New");

public void AddNewBtnClick(){
    eventDriver.findElement(AddNewBtn).click();
}
// public ClickAddNewCustomer ()

}
