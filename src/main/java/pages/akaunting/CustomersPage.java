package pages.akaunting;

import org.openqa.selenium.By;
import pages.BasePageInteractions;

public class CustomersPage extends BasePageInteractions {

private By AddNewBtn = By.linkText("Add New");

public void AddNewBtnClick(){
    eventDriver.findElement(AddNewBtn).click();
}
// public ClickAddNewCustomer ()

}
