package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuditHomePage extends BasePage{

    protected By viewCentralizedAuditPanel = By.cssSelector("span[translate='NAVIGATION.MANAGEMENT_MENU']");
    protected By homeTitle = By.cssSelector("span[translate='NAVIGATION.HOME_MENU']");

    public AuditHomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getViewCentralizedAuditPanel() {
        return driver.findElement(viewCentralizedAuditPanel);
    }

    //@Override
    public WebElement getHomeTitle() { return driver.findElement(homeTitle);}

}
