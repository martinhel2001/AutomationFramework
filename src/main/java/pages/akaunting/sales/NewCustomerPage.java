package pages.akaunting.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.BasePageInteractions;

public class NewCustomerPage extends BasePageInteractions {

    public NewCustomerPage(EventFiringWebDriver eventDriver) {
        super(eventDriver);
    }

    By header = new By.ById("header");
    By name = new By.ById("name");
    By email = new By.ById("email");
    By tax_number = new By.ById("tax_number");
    By currencySelect = new By.ByCssSelector("#customer > div.card-body > div > div:nth-child(4) > div > div > div > input");
    By phone = new By.ById("phone");
    By website = new By.ById("website");
    By address = new By.ById("address");
    By city = new By.ById("city");
    By zip_code = new By.ById("zip_code");
    By state = new By.ById("state");
    By countrySelect = new By.ByCssSelector("#customer > div.card-body > div > div:nth-child(11) > div > div > div > input"); // Select and Text input
    By reference = new By.ById("reference");
    By enabledRadioBtn = new By.ById("enabled-1"); //radio btn
    By disabledRadioBtn = new By.ById("enabled-0"); //radio btn
    By canLoginChkbox = new By.ById("create_user"); // checkbox
    By cancelBtn = new By.ByLinkText("Cancel");
    By saveBtn = new By.ByCssSelector("button");

    public WebElement getHeader() {
        return eventDriver.findElement(header);
    }

    public WebElement getName() {
        return eventDriver.findElement(name);
    }

    public WebElement getEmail() {
        return eventDriver.findElement(email);
    }

    public WebElement getTaxNumber() {
        return eventDriver.findElement(tax_number);
    }

    public WebElement getCurrency() {
        return eventDriver.findElement(currencySelect);
    }

    public WebElement getPhone() {
        return eventDriver.findElement(phone);
    }

    public WebElement getWebsite() {
        return eventDriver.findElement(website);
    }

    public WebElement getAddress() {
        return eventDriver.findElement(address);
    }

    public WebElement getCity() {
        return eventDriver.findElement(city);
    }

    public WebElement getZipCode() {
        return eventDriver.findElement(zip_code);
    }

    public WebElement getState() {
        return eventDriver.findElement(state);
    }

    public WebElement getCountry() {
        return eventDriver.findElement(countrySelect);
    }

    public WebElement getReference() {
        return eventDriver.findElement(reference);
    }

    public WebElement getEnabled() {
        return eventDriver.findElement(enabledRadioBtn);
    }

    public WebElement getDisabled() {
        return eventDriver.findElement(disabledRadioBtn);
    }

    public WebElement getCanLogin() {
        return eventDriver.findElement(canLoginChkbox);
    }

    public WebElement getCancel() {
        return eventDriver.findElement(cancelBtn);
    }

    public WebElement getSave() {
        return eventDriver.findElement(saveBtn);
    }

}
