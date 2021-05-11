package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleManagerPage extends BasePage{

    protected By vehiclesPanel = By.id("p-0");

    public VehicleManagerPage(WebDriver driver){
        super(driver);
    }

    public WebElement getVehiclesPanel(){return driver.findElement(vehiclesPanel);}

}
