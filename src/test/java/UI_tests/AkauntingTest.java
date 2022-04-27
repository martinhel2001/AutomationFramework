package UI_tests;

import BaseTest.BaseTest_UI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.akaunting.*;
import pages.akaunting.sales.*;

public class AkauntingTest extends BaseTest_UI {

    @Test (description = "Testing login button")
    public void loginSmokeTest() {
        Login objLogin = new Login(eventDriver,"https://automationcampus.com.ar/akaunting/");
        Dashboard objDashboard = new Dashboard(eventDriver);

        // Step 1: Login to Akaunting successfully
        objLogin.tryLogin("akaunting-admin@automationcampus.com.ar","trinity110",true);
        Assert.assertTrue(objDashboard.getMainPanel().isDisplayed());

    }

    @Test (description = "Testing Invoice creation", dependsOnMethods = "createCustomer")
    public void createInvoice() {
        Dashboard objDashboard = new Dashboard(eventDriver);
        InvoicesMainPage objInvoices;
        NewInvoicePage objNewInvoicePage = new NewInvoicePage(eventDriver);

        // Step 1: Navigate to Invoices page
        objInvoices = objDashboard.goToInvoices();

        // Step 2: Add New Invoice
        objInvoices.getAddNewBtn().click();
        Assert.assertTrue(objNewInvoicePage.getHeaderTitle().isDisplayed());

        //....
    }


    @Test (description = "Validating regular Customer creation", dependsOnMethods = "loginSmokeTest")
    public void createCustomer() {
        Dashboard objDashboard = new Dashboard(eventDriver);
        CustomersMainPage objCustomersMainPage;
        NewCustomerPage objNewCustomerPage = new NewCustomerPage(eventDriver);

        // Step 1: Navigate to Customer page
        objCustomersMainPage = objDashboard.goToCustomers();

        // Step 2: Add New Customer
        objCustomersMainPage.getAddNewBtnHeader().click();
        Assert.assertTrue(objNewCustomerPage.getHeader().getText().contains("New Customer"));
        String randomString = RandomStringUtils.randomAlphabetic(5);

        objNewCustomerPage.getName().sendKeys("John Snow "+ randomString);
        objNewCustomerPage.getAddress().sendKeys("Elm Street 232");
        objNewCustomerPage.getCity().sendKeys("Toronto");
        objNewCustomerPage.getState().sendKeys("TO");
        objNewCustomerPage.getCountry().sendKeys("Canada");
        objNewCustomerPage.getCurrency().sendKeys("Argentine Peso");
        objNewCustomerPage.getEmail().sendKeys("johnsnow_"+randomString+"@automationcampus.com.ar");
       // if (!objNewCustomerPage.getCanLogin().isSelected()) objNewCustomerPage.getCanLogin().click();
        //objNewCustomerPage.getEnabled().click();
        objNewCustomerPage.getTaxNumber().sendKeys(randomString);
        objNewCustomerPage.getWebsite().sendKeys("http://www.automationcampus.com.ar");
        objNewCustomerPage.getPhone().sendKeys("3424234234234");
        objNewCustomerPage.scrollIntoMiddle(objNewCustomerPage.getSave());
        objNewCustomerPage.getSave().click();

        // Step 3: Validate the adding Customer step finished and the user is placed in Customer Detail page
        CustomerDetailPage objCustomerDetailPage = new CustomerDetailPage(eventDriver);
        wait.until(ExpectedConditions.elementToBeClickable(objCustomerDetailPage.getPaidCard()));
        Assert.assertTrue(objCustomerDetailPage.getCustomerName().getText().equals("John Snow "+ randomString),"Customer name shown: "+objCustomerDetailPage.getCustomerName().getText());
        Assert.assertTrue(objCustomerDetailPage.getPaidCard().getText().equals("PAID"),"Text obtained: "+objCustomerDetailPage.getPaidCard().getText());
        Assert.assertTrue(objCustomerDetailPage.getOpenInvoicesCard().getText().equals("OPEN INVOICES"),"Text obtained: "+objCustomerDetailPage.getOpenInvoicesCard().getText());
        Assert.assertTrue(objCustomerDetailPage.getOverdueInvoicesCard().getText().equals("OVERDUE INVOICES"),"Text obtained: "+objCustomerDetailPage.getOverdueInvoicesCard().getText());

    }

}
