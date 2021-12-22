package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManualValidationPage extends KapschBasePage{

    public ManualValidationPage(EventFiringWebDriver driver) {
        super(driver);
    }

    protected By manualValidationHeader = By.id("header-manual-validation");
    protected By btnConfirm = By.id("mv-confirm");
    protected By btnReject = By.id("mv-show-rejetction-reason");
    protected By btnIgnore = By.id("mv-show-ignore");
    protected By btnEscalate = By.id("mv-show-escalation-reason");
    protected By btnLastTransactions = By.id("mv-show-last-transactions");
    protected By btnValidationHistory = By.id("view-nextTransactions");
    protected By inputVRM = By.id("search_lpn");
    protected By inputCountry = By.id("mv-country");
    protected By inputRegion = By.id("mv-region");
    protected By inputVehicleClass = By.id("mv-vehicle-class");
    protected By popupNoMoreTransactions = By.cssSelector("h4[translate='MANUAL_VALIDATION.RETRIEVE_IGNORED_TRANSACTIONS.TITLE']");
    protected By popupNoMoreTransactions_No = By.cssSelector("button[translate='COMMON.NO_TEXT']");
    protected By headerHomeOption = By.cssSelector("span[translate='MANUAL_VALIDATION.NAV.HOME']");

    public void confirm(){
        this.eventDriver.findElement(btnConfirm).click();
    }

    public WebElement getVRM(){
        return this.eventDriver.findElement(inputVRM);
    }
    public WebElement getCountry(){
        return this.eventDriver.findElement(inputCountry);
    }
    public WebElement getRegion(){
        return this.eventDriver.findElement(inputRegion);
    }
    public WebElement getVehicleClass(){
        return this.eventDriver.findElement(inputVehicleClass);
    }
    public WebElement getBtnConfirm() {
        return this.eventDriver.findElement(btnConfirm);
    }
    public WebElement getPopupNoMoreTrx() {
        return this.eventDriver.findElement(popupNoMoreTransactions);
    }

    public WebElement getPopUpNoMoreTrx_No(){
        return this.eventDriver.findElement(popupNoMoreTransactions_No);
    }


    public ManualValidationPage setVRM(String vrm) {
        wait.until(ExpectedConditions.elementToBeClickable(this.eventDriver.findElement(inputVRM)));
        this.eventDriver.findElement(inputVRM).clear();
        this.eventDriver.findElement(inputVRM).sendKeys(vrm);
        return this;
    }
    public ManualValidationPage setCountry(String country) {
        this.eventDriver.findElement(inputCountry).clear();
        this.eventDriver.findElement(inputCountry).sendKeys(country);
        return this;
    }
    public ManualValidationPage setRegion(String region) {
        this.eventDriver.findElement(inputRegion).clear();
        this.eventDriver.findElement(inputRegion).sendKeys(region);
        return this;
    }
    public ManualValidationPage setVehicleClass(String vehicleClass) {
        this.eventDriver.findElement(inputVehicleClass).clear();
        this.eventDriver.findElement(inputVehicleClass).sendKeys(vehicleClass);
        return this;
    }


    public boolean isNoMoreTrxPopupDisplayed() {
        return this.isElementPresent(popupNoMoreTransactions);
    }

    public void clickNoMoreTrx_No() {
        eventDriver.findElement(popupNoMoreTransactions_No).click();
    }

    public ManualValidationHomePage goBackHome(){
        eventDriver.findElement(headerHomeOption).click();
        return new ManualValidationHomePage(eventDriver);
    }
}
