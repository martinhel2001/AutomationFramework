package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransactionManagerHomePage extends BasePage{

    protected By viewTransactionPanel = By.id("p-0");

    public TransactionManagerHomePage (WebDriver driver){
        super(driver);
    }

    public WebElement getViewTransactionPanel() {return driver.findElement(viewTransactionPanel);}
}
