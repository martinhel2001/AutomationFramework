package pages.martinaSeminara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    BioPage bioPage = new BioPage(driver);
    PortfolioItemPage portfolioItemPage = new PortfolioItemPage(driver);
    ContactoPage contactoPage = new ContactoPage(driver);
    By mainMenu = new By.ById("menu-main_menu");
    By inicioMenu = new By.ByLinkText("INICIO");
    By bioMenu = new By.ByLinkText("BIO");
    By contactoMenu = new By.ByLinkText("CONTACTO");
    By mainTitle = new By.ByCssSelector(".page-title.h2");
    By subTitle = new By.ByCssSelector(".la-breadcrumbs.use-custom-text");
    public By portfolioMain = new By.ByClassName("la-portfolio-masonry");
    By sectionPageHeader = new By.ById("section_page_header");
    By portfolioItem = new By.ByCssSelector(".portfolio-item");



    public void goToInicio() {
        driver.findElement(inicioMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(portfolioItem));
    }

    public BioPage goToBio() {
        driver.findElement(bioMenu).click();
        wait.until(ExpectedConditions.visibilityOf(bioPage.getMainTitle()));
        return bioPage;
    }

    public PortfolioItemPage goToAnyPortfolioItem() {
        wait.until(ExpectedConditions.elementToBeClickable(portfolioItem));
        driver.findElement(portfolioItem).click();
        wait.until(ExpectedConditions.visibilityOf(portfolioItemPage.getPageTitle()));
        return portfolioItemPage;
    }

    public ContactoPage goToContacto() {
        driver.findElement(contactoMenu).click();
        wait.until(ExpectedConditions.visibilityOf(contactoPage.getMainTitle()));
        return contactoPage;
    }

    public WebElement getMainMenu() {
        return driver.findElement(mainMenu);
    }

    public WebElement getInicioMenu() {
        return driver.findElement(inicioMenu);
    }

    public WebElement getBioMenu() {
        return driver.findElement(bioMenu);
    }

    public WebElement getContactoMenu() {
        return driver.findElement(contactoMenu);
    }

    public WebElement getMainTitle() {
        return driver.findElement(mainTitle);
    }

    public WebElement getSubTitle() {
        return driver.findElement(subTitle);
    }

    public WebElement getPortfolioMain() {
        return driver.findElement(portfolioMain);
    }

    public WebElement getPortfolioItem() {
        return driver.findElement(portfolioItem);
    }

    public WebElement getSectionPageHeader() {
        return driver.findElement(sectionPageHeader);
    }
}
