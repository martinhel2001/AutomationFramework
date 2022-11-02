package pages.martinaSeminara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePageInteractions;

public class HomePage extends BasePageInteractions {

    public HomePage(EventFiringWebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    public HomePage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    BioPage bioPage = new BioPage(eventDriver);
    PortfolioItemPage portfolioItemPage = new PortfolioItemPage(eventDriver);
    ContactoPage contactoPage = new ContactoPage(eventDriver);
    By mainMenu = new By.ById("menu-main_menu");
    By inicioMenu = new By.ByLinkText("INICIO");
    By bioMenu = new By.ByLinkText("BIO");
    By contactoMenu = new By.ByLinkText("CONTACTO");
    By mainTitle = new By.ByCssSelector(".page-title.h2");
    By subTitle = new By.ByCssSelector(".la-breadcrumbs.use-custom-text");
    public By portfolioMain = new By.ByClassName("la-portfolio-masonry");
    By sectionPageHeader = new By.ById("section_page_header");
    By portfolioItem = new By.ByCssSelector(".portfolio-item");
    By portfolioItemTitle = new By.ByCssSelector(".entry-title");



    public void goToInicio() {
        eventDriver.findElement(inicioMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(portfolioItem));
    }

    public BioPage goToBio() {
        eventDriver.findElement(bioMenu).click();
        wait.until(ExpectedConditions.visibilityOf(bioPage.getMainTitle()));
        return bioPage;
    }

    public PortfolioItemPage goToAnyPortfolioItem() {
        wait.until(ExpectedConditions.elementToBeClickable(portfolioItem));
        eventDriver.findElement(portfolioItem).click();
       // driver.findElement(portfolioItem).click();
        wait.until(ExpectedConditions.visibilityOf(portfolioItemPage.getPageTitle()));
        return portfolioItemPage;
    }

    public ContactoPage goToContacto() {
        eventDriver.findElement(contactoMenu).click();
        wait.until(ExpectedConditions.visibilityOf(contactoPage.getMainTitle()));
        return contactoPage;
    }

    public WebElement getMainMenu() {
        return eventDriver.findElement(mainMenu);
    }

    public WebElement getInicioMenu() {
        return eventDriver.findElement(inicioMenu);
    }

    public WebElement getBioMenu() {
        return eventDriver.findElement(bioMenu);
    }

    public WebElement getContactoMenu() {
        return eventDriver.findElement(contactoMenu);
    }

    public WebElement getMainTitle() {
        return eventDriver.findElement(mainTitle);
    }

    public WebElement getSubTitle() {
        return eventDriver.findElement(subTitle);
    }

    public WebElement getPortfolioMain() {
        return eventDriver.findElement(portfolioMain);
    }

    public WebElement getPortfolioItem() {
        return eventDriver.findElement(portfolioItem);
    }

    public WebElement getSectionPageHeader() {
        return eventDriver.findElement(sectionPageHeader);
    }
}
