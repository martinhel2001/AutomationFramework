package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ManualValidationPage extends KapschBasePage{

    public ManualValidationPage(WebDriver driver) {
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
        this.driver.findElement(btnConfirm).click();
    }

    public WebElement getVRM(){
        return this.driver.findElement(inputVRM);
    }
    public WebElement getCountry(){
        return this.driver.findElement(inputCountry);
    }
    public WebElement getRegion(){
        return this.driver.findElement(inputRegion);
    }
    public WebElement getVehicleClass(){
        return this.driver.findElement(inputVehicleClass);
    }
    public WebElement getBtnConfirm() {
        return this.driver.findElement(btnConfirm);
    }
    public WebElement getPopupNoMoreTrx() {
        return this.driver.findElement(popupNoMoreTransactions);
    }

    public WebElement getPopUpNoMoreTrx_No(){
        return this.driver.findElement(popupNoMoreTransactions_No);
    }


    public ManualValidationPage setVRM(String vrm) {
        wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(inputVRM)));
        this.driver.findElement(inputVRM).clear();
        this.driver.findElement(inputVRM).sendKeys(vrm);
        return this;
    }
    public ManualValidationPage setCountry(String country) {
        this.driver.findElement(inputCountry).clear();
        this.driver.findElement(inputCountry).sendKeys(country);
        return this;
    }
    public ManualValidationPage setRegion(String region) {
        this.driver.findElement(inputRegion).clear();
        this.driver.findElement(inputRegion).sendKeys(region);
        return this;
    }
    public ManualValidationPage setVehicleClass(String vehicleClass) {
        this.driver.findElement(inputVehicleClass).clear();
        this.driver.findElement(inputVehicleClass).sendKeys(vehicleClass);
        return this;
    }


    public boolean isNoMoreTrxPopupDisplayed() {
        return this.isElementPresent(popupNoMoreTransactions);
    }

    public void clickNoMoreTrx_No() {
        driver.findElement(popupNoMoreTransactions_No).click();
    }

    public ManualValidationHomePage goBackHome(){
        driver.findElement(headerHomeOption).click();
        return new ManualValidationHomePage(driver);
    }
}
