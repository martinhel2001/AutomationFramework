package UI_tests;

import BaseTest.BaseTest_UI;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.martinaSeminara.BioPage;
import pages.martinaSeminara.ContactoPage;
import pages.martinaSeminara.HomePage;
import utils.extentReports.ExtentTestManager;

public class MartinaSeminaraTest extends BaseTest_UI {

    @Test(description = "Unit test validations from www.martinaseminara.com.ar home page", groups = {"seminaraCI","regression"})
    public void unitTestValidations_HomePage() throws InterruptedException {
        HomePage msHome = new HomePage(driver, "http://www.martinaseminara.com.ar");

        // Validate visualization of all web elements
        ExtentTestManager.getTest().log(Status.INFO, "Let's validate all web elements are displayed");
        log.info("Let's validate all web elements are displayed 2");

        Assert.assertTrue(isDisplayed(msHome.getBioMenu()), "Bio option is not displayed! Display value is null");
        Assert.assertTrue(isDisplayed(msHome.getMainTitle()), "Main Title is not displayed! Display value is null");
        Assert.assertTrue(msHome.getMainTitle().getText().equals("Martina Seminara"), "Main Title does not read Martina Seminara, it reads : "+msHome.getMainTitle().getText());
        Assert.assertTrue(isDisplayed(msHome.getSectionPageHeader()), "Page Header is not displayed! Display value is null");
        Assert.assertTrue(isDisplayed(msHome.getContactoMenu()), "Contacto option is not displayed! Display value is null");
        Assert.assertTrue(isDisplayed(msHome.getInicioMenu()), "Inicio option is not displayed! Display value is null");
        Assert.assertTrue(isDisplayed(msHome.getPortfolioMain()), "Portfolio is not displayed! Display value is null");
        Assert.assertTrue(isDisplayed(msHome.getSubTitle()), "Subtitle is not displayed! Display value is null");
        Assert.assertTrue(msHome.getSubTitle().getText().equals("Montadora Audiovisual"), "Subtitle does not read Montadora Audiovisual, it reads : "+msHome.getSubTitle().getText());

        // Validate Page Header background
        ExtentTestManager.getTest().log(Status.INFO, "Let's validate background at Header section");
        log.info("Let's validate background at Header section");
        Assert.assertTrue(msHome.getSectionPageHeader().getCssValue("background-image").contains("url(\"http://martinaseminara.com.ar/wordpress/wp-content/uploads"), "Invalid background image in header section: "+msHome.getSectionPageHeader().getCssValue("background-image"));

        // Validate buttons work
        ExtentTestManager.getTest().log(Status.INFO, "Let's validate buttons work OK");
        log.info("Let's validate buttons work OK");

        Assert.assertTrue(msHome.goToBio().getMainTitle().getText().contains("BIO"),"BIO page title is wrong");
        msHome.goToInicio();
        Assert.assertTrue(msHome.goToContacto().getMainTitle().getText().contains("Contacto"), "Contacto page title is wrong");
        msHome.goToInicio();
        wait.until(ExpectedConditions.elementToBeClickable(msHome.getPortfolioItem()));
        Thread.sleep(2000);
        mouseOverOn(msHome.getPortfolioItem());
        Assert.assertTrue(isDisplayed(msHome.goToAnyPortfolioItem().getPageTitle()));
        msHome.goToInicio();

    }

    @Test (description = "Unit test validations from www.martinaseminara.com.ar BIO page", groups = {"seminaraCI","regression"})
    public void unitTestsValidation_BIOpage() {
        HomePage msHome = new HomePage(driver, "http://www.martinaseminara.com.ar");
        BioPage bioPage = msHome.goToBio();

        ExtentTestManager.getTest().log(Status.INFO, "Let's validate text in BIO page");
        log.info("Let's validate text in BIO page");
        Assert.assertTrue(bioPage.getBodyText().getText().contains("Martina Seminara nació en Argentina y vive en Madrid. "));
        Assert.assertTrue(bioPage.getMainTitle().getText().equals("BIO"));

        ExtentTestManager.getTest().log(Status.INFO, "Let's validate background photo in BIO");
        log.info("Let's validate background photo in BIO");
        Assert.assertTrue(bioPage.getHeaderSection().getCssValue("background-image").contains("url(\"http://martinaseminara.com.ar/wordpress/wp-content/uploads"), "Invalid background image in header section: "+bioPage.getHeaderSection().getCssValue("background-image"));

    }

    @Test (description = "Unit test validations from www.martinaseminara.com.ar CONTACT page", groups = {"seminaraCI","regression"})
    public void unitTestsValidation_CONTACTpage() {
        HomePage msHome = new HomePage(driver, "http://www.martinaseminara.com.ar");
        ContactoPage contactoPage = msHome.goToContacto();

        ExtentTestManager.getTest().log(Status.INFO, "Let's validate text in CONTACTO page");
        log.info("Let's validate text in CONTACTO page");
        Assert.assertTrue(contactoPage.getContactOptions().getText().contains("(0034) 656639128"),"Falta el numero de telefono en CONTACTO");
        Assert.assertTrue(contactoPage.getContactOptions().getText().contains("MARSEMINAR@GMAIL.COM"),"Falta el mail en CONTACTO");
        Assert.assertTrue(contactoPage.getMainTitle().getText().equals("Contacto"));

        ExtentTestManager.getTest().log(Status.INFO, "Let's validate background photo in CONTACTO");
        log.info("Let's validate background photo in CONTACTO");
        Assert.assertTrue(contactoPage.getHeaderSection().getCssValue("background-image").contains("url(\"http://martinaseminara.com.ar/wordpress/wp-content/uploads"), "Invalid background image in header section: "+contactoPage.getHeaderSection().getCssValue("background-image"));

    }

}
