package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TransactionManagerHomePage extends KapschBasePage {

    protected By viewTransactionPanel = By.id("p-0");
    protected By navigationViewTrx = By.cssSelector("span[translate='NAVIGATION.VIEW_TRANSACTIONS_MENU']");


    public TransactionManagerHomePage (EventFiringWebDriver driver){
        super(driver);
    }

    public ViewTrxPage goToViewTrx() {
        eventDriver.findElement(navigationViewTrx).click();
        return new ViewTrxPage(eventDriver);
    }

    public WebElement getViewTransactionPanel() {return eventDriver.findElement(viewTransactionPanel);}

}
