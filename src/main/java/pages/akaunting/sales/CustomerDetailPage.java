package pages.akaunting.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class CustomerDetailPage extends BasePageInteractions {

    public CustomerDetailPage(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    private By customerName = new By.ByCssSelector("div#header h2");
    private By cardAmount = new By.ByCssSelector(".card-body span");
    private By paidCard = new By.ByXPath("//*[text()='Paid']");
    private By openInvoicesCard = new By.ByXPath("//*[text()='Open Invoices']");
    private By overdueInvoicesCard = new By.ByXPath("//*[text()='Overdue Invoices']");
    private By moreActionsDropdown = new By.ByXPath("//button[contains()='More Actions'");
    private By createInvoiceMenuOption = new By.ByLinkText("Create Invoice");


    public WebElement getCustomerName() {
        return eventDriver.findElement(customerName);
    }

    public WebElement getPaidCard() {
        return eventDriver.findElement(paidCard);
    }

    public WebElement getOpenInvoicesCard() {
        return eventDriver.findElement(openInvoicesCard);
    }

    public WebElement getOverdueInvoicesCard() {
        return eventDriver.findElement(overdueInvoicesCard);
    }

    public WebElement getCardAmount(WebElement cardElement){
        return cardElement.findElement(cardAmount);
    }

    public WebElement clickMoreActions() {
        eventDriver.findElement(moreActionsDropdown).click();
        return eventDriver.findElement(moreActionsDropdown);
    }

    public WebElement createInvoice(){
        return eventDriver.findElement(createInvoiceMenuOption);
    }
}
