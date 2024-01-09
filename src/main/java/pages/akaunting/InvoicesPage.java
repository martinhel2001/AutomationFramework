package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class InvoicesPage extends BasePageInteractions {

public InvoicesPage(EventFiringWebDriver session){
    super(session);
}

private By AddNewBtn = By.linkText("Add New");

public void AddNewBtnClick(){
    eventDriver.findElement(AddNewBtn).click();
}

}
