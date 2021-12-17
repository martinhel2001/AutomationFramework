package UI_tests;

import BaseTest.BaseTest_UI;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.martinaSeminara.HomePage;
import utils.extentReports.ExtentTestManager;

import static BaseTest.BaseTest.driver;

public class MartinaSeminaraTest extends BaseTest_UI {

    @Test(description = "Unit test validations from www.martinaseminara.com.ar home page")
    public void unitTestValidations_HomePage(){
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
        //driver.navigate().back();
        Assert.assertTrue(msHome.goToContacto().getMainTitle().getText().contains("Contacto"), "Contacto page title is wrong");
        //driver.navigate().back();
        msHome.goToInicio();
        System.out.println("Number of elements:" +msHome.getPortfolioItem.size());


    }
}
