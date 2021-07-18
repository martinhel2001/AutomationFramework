package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    protected By inputVRM = By.id("mv-write-lpn");
    protected By inputCountry = By.id("mv-country");
    protected By inputRegion = By.id("mv-region");
    protected By inputVehicleClass = By.id("mv-vehicle-class");
    protected By popupNoMoreTransactions = By.cssSelector("h4[translate='MANUAL_VALIDATION.RETRIEVE_IGNORED_TRANSACTIONS.TITLE']");
    protected By popupNoMoreTransactions_No = By.cssSelector("button[translate='COMMON.NO_TEXT']");

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


    public void setVRM(String vrm) {
        this.driver.findElement(inputVRM).sendKeys(vrm);
    }
    public void setCountry(String country) {
        this.driver.findElement(inputCountry).sendKeys(country);
    }
    public void setRegion(String region) {
        this.driver.findElement(inputRegion).sendKeys(region);
    }
    public void setVehicleClass(String vehicleClass) {
        this.driver.findElement(inputVehicleClass).sendKeys(vehicleClass);
    }


    public boolean isNoMoreTrxPopupDisplayed() {
        return this.isElementPresent(popupNoMoreTransactions);
    }

    public void clickNoMoreTrx_No() {
        driver.findElement(popupNoMoreTransactions_No).click();
    }


}
