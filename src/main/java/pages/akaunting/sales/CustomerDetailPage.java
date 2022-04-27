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

    public WebElement getCustomerName() {
        return eventDriver.findElement(customerName);
    }

    public WebElement getPaidCard() {
        return eventDriver.findElement(By.xpath(("//*[text()='Paid']")));
    }

    public WebElement getOpenInvoicesCard() {
        return eventDriver.findElement(By.xpath(("//*[text()='Open Invoices']")));
    }

    public WebElement getOverdueInvoicesCard() {
        return eventDriver.findElement(By.xpath(("//*[text()='Overdue Invoices']")));
    }

    public WebElement getCardAmount(WebElement cardElement){
        return cardElement.findElement(cardAmount);
    }

}
