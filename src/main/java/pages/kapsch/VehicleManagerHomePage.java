package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class VehicleManagerHomePage extends KapschBasePage {

    protected By vehiclesPanel = By.id("p-0");

    public VehicleManagerHomePage(EventFiringWebDriver driver){
        super(driver);
    }

    public WebElement getVehiclesPanel(){return eventDriver.findElement(vehiclesPanel);}

}
