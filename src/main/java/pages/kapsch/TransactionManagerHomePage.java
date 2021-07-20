package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransactionManagerHomePage extends KapschBasePage {

    protected By viewTransactionPanel = By.id("p-0");
    protected By navigationViewTrx = By.cssSelector("span[translate='NAVIGATION.VIEW_TRANSACTIONS_MENU']");


    public TransactionManagerHomePage (WebDriver driver){
        super(driver);
    }

    public ViewTrxPage goToViewTrx() {
        driver.findElement(navigationViewTrx).click();
        return new ViewTrxPage(driver);
    }

    public WebElement getViewTransactionPanel() {return driver.findElement(viewTransactionPanel);}

}
