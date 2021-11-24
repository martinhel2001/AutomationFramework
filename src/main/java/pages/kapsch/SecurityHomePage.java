package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecurityHomePage extends KapschBasePage {
    protected By systemGroupsPanel = By.cssSelector("span[translate='NAVIGATION.GROUP_MENU']");
    protected By homeTitle = By.cssSelector("span[translate='NAVIGATION.HOME_MENU']");


    public SecurityHomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getSystemGroupsPanel() {
        return driver.findElement(systemGroupsPanel);
    }

    public WebElement getHomeTitle() { return driver.findElement(homeTitle);}

}
