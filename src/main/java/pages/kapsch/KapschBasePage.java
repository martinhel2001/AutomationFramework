package pages.kapsch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

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

    // HTML TABLE methods - BEGIN

    public void htmlTableNumberOfColumns(WebElement table) {
        //No.of Columns
        List col = table.findElements(By.xpath(".//thead/tr/th"));
        System.out.println("No of cols are : " + col.size());
        //No.of rows
        List rows = table.findElements(By.xpath(".//tbody/tr/td[1]"));
        System.out.println("No of rows are : " + rows.size());
    }

    public List<WebElement> getRows(String colName, String cellValue, WebElement table){
        //To locate titles of table.
        List <WebElement> col = table.findElements(By.xpath(".//thead/tr/th"));
        //To locate rows of table.
        List <WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
        System.out.println("Cantidad de Rows: "+rows.size());
        int colPosition=0;

        for (int i=0;i<col.size();i++){
            System.out.println("Contenido de Col["+i+"]= "+col.get(i).getText());
            if (col.get(i).getText().equals(colName)) {
                colPosition=i+1;break;
            }
        }

        List <WebElement> colValues = table.findElements(By.xpath(".//tbody/tr/td["+colPosition+"]"));

        for (int row=0;row<colValues.size();row++){
            System.out.println("Contenido de Colvalues["+row+"]= "+colValues.get(row).getText());
            if (!colValues.get(row).getText().contains(cellValue)) {
                rows.remove(0);
                }
            }

        return rows;

//        for (int row=0;row<rows.size();row++){
//            List < WebElement > columns_row = rows.get(row).findElements(By.tagName("td"));
//
//            for (int column = 0; column < columns_row.size(); column++) {
//                // To retrieve text from that specific cell.
//                String celtext = columns_row.get(column).getText();
//                System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
//            }
//        }


    }
    // HTML TABLE methods - END


    public void logout() {
        driver.findElement(headerMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(menuLogout));
        driver.findElement(menuLogout).click();
    }
}
