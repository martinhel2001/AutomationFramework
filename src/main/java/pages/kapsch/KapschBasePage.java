package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class KapschBasePage extends BasePage {

    public KapschBasePage(WebDriver driver){
        super(driver);
    }

    protected By headerMenu = By.cssSelector("li[ng-if='vm.NgOidcClient.getUserInfo().isAuthenticated']");
    protected By menuGoTo = By.id("id-goto");
    protected By menuConfiguration = By.cssSelector("span[translate='CARDS.CFG.TITLE']");
    protected By menuManualValidation = By.cssSelector("span[translate='CARDS.MV.TITLE']");
    protected By menuMonitoring = By.cssSelector("span[translate='CARDS.MON.TITLE']");
    protected By menuSecurity = By.cssSelector("span[translate='CARDS.SEC.TITLE']");
    protected By menuTransactionManager = By.cssSelector("span[translate='CARDS.VT.TITLE']");
    protected By menuVehicleManager = By.cssSelector("span[translate='CARDS.VM.TITLE']");
    protected By menuAudit = By.cssSelector("span[translate='CARDS.AUD.TITLE']");
    protected By menuLogout = By.cssSelector("a[translate='HEADER.SIGN_OUT_MENU_TEXT']");
    protected By homeTitle = By.cssSelector("span[translate='HOME.TITLE']");

    Actions actions = new Actions(driver);

    public void mouseOver(WebElement webElement ) {
        actions.moveToElement(webElement);
    }

    public WebElement getHomeTitle() { return driver.findElement(homeTitle);}


    public void goToConfiguration() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuConfiguration));
        driver.findElement(menuConfiguration).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
        wait.until(ExpectedConditions.visibilityOf(getHomeTitle()));
    }

    public void goToAudit() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuAudit));
        driver.findElement(menuAudit).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
    }

    public void goToManualValidation() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuManualValidation));
        driver.findElement(menuManualValidation).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
    }

    public void goToMonitoring() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuMonitoring));
        driver.findElement(menuMonitoring).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
    }

    public void goToSecurity() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuSecurity));
        driver.findElement(menuSecurity).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
    }
    public void goToTransactionManager() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuTransactionManager));
        driver.findElement(menuTransactionManager).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
    }


    public void goToVehicleManager() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menuGoTo)));
        driver.findElement(menuGoTo).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuVehicleManager));
        driver.findElement(menuVehicleManager).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerMenu));
    }



    public void logout() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuLogout));
        driver.findElement(menuLogout).click();
    }
}
