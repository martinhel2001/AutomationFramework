package pages.akaunting.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class CustomersMainPage extends BasePageInteractions {

    public CustomersMainPage(WebDriver eventDriver) {
        super(eventDriver);
    }

    By createCustomerBtn = new By.ByLinkText("Create Customer");
    By addNewBtnHeader = new By.ByLinkText("Add New");
    By importBtnHeader = new By.ByLinkText("Import");
    By exportBtnHeader = new By.ByLinkText("Create Customer");
    By crmBtnHeader = new By.ByLinkText("CRM");

    public WebElement getCreateCustomerBtn() {
        return eventDriver.findElement(createCustomerBtn);
    }

    public WebElement getAddNewBtnHeader() {
        return eventDriver.findElement(addNewBtnHeader);
    }

    public WebElement getImportBtnHeader() {
        return eventDriver.findElement(importBtnHeader);
    }

    public WebElement getExportBtnHeader() {
        return eventDriver.findElement(exportBtnHeader);
    }

    public WebElement getCrmBtnHeader() {
        return eventDriver.findElement(crmBtnHeader);
    }
}
