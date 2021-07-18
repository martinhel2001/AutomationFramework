package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewTrx extends KapschBasePage {

    public ViewTrx(WebDriver driver) {
        super(driver);
    }

    protected By inputLPN = By.id("search_lpn");
    protected By btnSearch = By.id("btn_search_offices");
    protected By moreActions = By.id("grid__more_actions__toggle_0");
    protected By moreActionsView = By.id("COMMON.VIEW_BUTTON_TEXT grid_row_action");

    public void clickSearch() {
        this.driver.findElement(btnSearch).click();
    }

    public void setInputLPN(String LPN) {
        this.driver.findElement(inputLPN).sendKeys(LPN);
    }

    public void moreActionsView(){
        this.mouseOver(driver.findElement(moreActions));
        driver.findElement(moreActionsView).click();
    }
}
