package pages.akaunting;

import dataentities.akaunting.Customer;
import lombok.Getter;
import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

import java.util.Random;

public class AddNewCustomerPage extends BasePageInteractions {

    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By taxNumberField = By.id("tax_number");
    private By currencyField = By.cssSelector("input[placeholder='- Select Currency -']");
    private By phoneField = By.id("phone");
    private By websiteField = By.id("website");
    private By adressField = By.id("address");
    private By cityField = By.id("city");
    private By zipCodeField = By.id("zip_code");
    private By stateField = By.id("state");
    private By countryField = By.cssSelector("input[placeholder='- Select Country -']");
    private By canLoginChk = By.cssSelector("label[for='create_user']");
    @Getter private By submitBtn = By.cssSelector("button[type='submit']");

    public AddNewCustomerPage(EventFiringWebDriver session) {
        super(session);
    }

    public void addNewCustomer(String name, String email){
        Customer customer = new Customer();

        eventDriver.findElement(nameField).sendKeys(name);
        eventDriver.findElement(emailField).sendKeys(email);
        eventDriver.findElement(adressField).sendKeys(customer.getAddress());
        eventDriver.findElement(cityField).sendKeys(customer.getCity());
        eventDriver.findElement(stateField).sendKeys(customer.getState());
        eventDriver.findElement(countryField).sendKeys(customer.getCountry());
        eventDriver.findElement(currencyField).sendKeys(customer.getCurrency());
        eventDriver.findElement(submitBtn).click();
    }
}

