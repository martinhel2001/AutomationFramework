package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ViewTrxPage extends KapschBasePage {

    public ViewTrxPage(WebDriver driver) {
        super(driver);
    }

    //protected By inputLPN = By.cssSelector("phoenix-input[translate-code='VIEW_TRANSACTIONS.SEARCH_CANDIDATE.LPN']");
    protected By inputLPN = By.xpath("/html/body/div[1]/ui-view/div[2]/ui-view/view-transaction-list/view-transactions-filters/phoenix-panel/div/div[2]/div/panel-body/form/div[1]/phoenix-input[1]/div/div/input");
    protected By btnSearch = By.id("btn_search_offices");
    protected By moreActions = By.id("grid__more_actions__toggle_0");
    protected By moreActionsView = By.id("COMMON.VIEW_BUTTON_TEXT grid_row_action");
    protected By tableTransactions = By.className("table-box");


    public void clickSearch() {
        this.driver.findElement(btnSearch).click();
    }

    public void setInputLPN(String LPN) {
        wait.until(ExpectedConditions.elementToBeClickable(inputLPN));
        //wait.until(ExpectedConditions.elementToBeSelected(inputLPN));
        //this.driver.findElement(inputLPN).clear();
        this.driver.findElement(inputLPN).click();
        this.driver.findElement(inputLPN).sendKeys(LPN);
    }

    public void moreActionsView(){
        this.mouseOver(driver.findElement(moreActions));
        driver.findElement(moreActionsView).click();
    }

    public void inspectTrxTable(){
        this.htmlTableNumberOfColumns(driver.findElement(tableTransactions));
    }

    public boolean vrmFound(String vrm){
        if (getRows("VRM/Tag ID",vrm,driver.findElement(tableTransactions)).size()>0) {
            return true;
        } else {return false;}
    }

}
