package pages.akaunting;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class NewInvoicePage extends BasePageInteractions {

    public NewInvoicePage(EventFiringWebDriver session){
        super(session);
    }

    private By AddCustomerBtn =By.className("text-add-contact");
    private By CustomerNameFld = By.cssSelector("input[placeholder='Type a Customer name']");
    private By ItemNameFld = By.cssSelector("input[placeholder='Type an item name']");

    private By CustomerList = By.className("aka-select-menu-option");

    private By AddAnItemBtn = By.className("item-add-new");

    public void AddCustomer(String customerName){
        eventDriver.findElement(AddCustomerBtn).click();
        eventDriver.findElement(CustomerNameFld).sendKeys(customerName);
        eventDriver.findElement(CustomerList).click();

    }


    public void AddAnItem(String item){
        eventDriver.findElement(AddAnItemBtn).click();
        eventDriver.findElement()
    }

    /*
    public void selectCustomer (String customerName){
        eventDriver.findElement(AddCustomerBtn).sendKeys(customerName);
    }

     */
}
