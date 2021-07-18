package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManualValidationPage extends KapschBasePage{

    public ManualValidationPage(WebDriver driver) {
        super(driver);
    }

    protected By manualValidationHeader = By.id("header-manual-validation");

}
