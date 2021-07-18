package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void confirm(){
        this.driver.findElement(btnConfirm).click();
    }

    public String getVRM(){
        return this.driver.findElement(inputVRM).getText();
    }
    public String getCountry(){
        return this.driver.findElement(inputCountry).getText();
    }
    public String getRegion(){
        return this.driver.findElement(inputRegion).getText();
    }
    public String getVehicleClass(){
        return this.driver.findElement(inputVehicleClass).getText();
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





}
