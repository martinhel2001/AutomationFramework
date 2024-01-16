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

    private By InvoiceNumber = By.id("document_number");

    private By AddAnItemBtn = By.className("item-add-new");

    private By ItemList = By.className("aka-select-menu-options");

    private By ItemQuantity = By.cssSelector("input[name='items.0.quantity']");

    private By ItemPrice = By.cssSelector("input[name='price']");

    private By SaveBtn = By.linkText("Save");

    public void AddCustomer(String customerName, String invoiceNumber){
        eventDriver.findElement(AddCustomerBtn).click();
        eventDriver.findElement(CustomerNameFld).sendKeys(customerName);
        eventDriver.findElement(InvoiceNumber).clear();
        eventDriver.findElement(InvoiceNumber).sendKeys(invoiceNumber);
        eventDriver.findElement(CustomerList).click();

    }


    public void AddAnItem(String item, String quantity, String price){
        eventDriver.findElement(AddAnItemBtn).click();
        eventDriver.findElement(ItemNameFld).sendKeys(item);
        eventDriver.findElement(ItemList).click();
        eventDriver.findElement(ItemQuantity).clear();
        eventDriver.findElement(ItemQuantity).sendKeys(quantity);
        eventDriver.findElement(ItemPrice).clear();
        eventDriver.findElement(ItemPrice).sendKeys(price);
        eventDriver.findElement(SaveBtn).click();
    }

    //public void SaveInvoice (){
     //   eventDriver.findElement(SaveBtn).click();
    //}

    /*
    public void selectCustomer (String customerName){
        eventDriver.findElement(AddCustomerBtn).sendKeys(customerName);
    }

     */
}
