package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VehicleManagerHomePage extends KapschBasePage {

    protected By vehiclesPanel = By.id("p-0");

    public VehicleManagerHomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getVehiclesPanel(){return driver.findElement(vehiclesPanel);}

}
