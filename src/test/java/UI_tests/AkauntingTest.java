package UI_tests;

import BaseTest.BaseTest_UI;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.akaunting.*;
import pages.akaunting.sales.*;

public class AkauntingTest extends BaseTest_UI {

    String customerName="";

    @Test (description = "Testing login button")
    public void loginSmokeTest() {
        Login objLogin = new Login(eventDriver,"https://automationcampus.com.ar/akaunting/");
        Dashboard objDashboard = new Dashboard(eventDriver);

        // Step 1: Login to Akaunting successfully
        objLogin.tryLogin("akaunting-admin@automationcampus.com.ar","trinity110",true);
        Assert.assertTrue(objDashboard.getMainPanel().isDisplayed());

    }

    @Test (description = "Testing Invoice creation", dependsOnMethods = "createCustomer")
    public void createInvoice() throws InterruptedException {
        Dashboard objDashboard = new Dashboard(eventDriver);
        InvoicesMainPage invoicesMainPage;
        NewInvoicePage newInvoicePage = new NewInvoicePage(eventDriver);

        // Step 1: Navigate to Invoices page
        invoicesMainPage = objDashboard.goToInvoices();

        // Step 2: Add New Invoice
        invoicesMainPage.getAddNewBtn().click();
        Assert.assertTrue(newInvoicePage.getHeaderTitle().isDisplayed());
        newInvoicePage.getAddCustomerCard().click();
        newInvoicePage.getCustomerInput().sendKeys(customerName);
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(eventDriver.findElement(new By.ByXPath("//span[text()='"+customerName+"']"))));
        eventDriver.findElement(new By.ByXPath("//span[text()='"+customerName+"']")).click();
        newInvoicePage.getInvoiceDatePicker().click();
        newInvoicePage.getInvoiceDatePicker().sendKeys(Keys.ARROW_UP);

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

        customerName="John Snow "+ randomString;
        objNewCustomerPage.getName().sendKeys(customerName);
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
        wait.until(ExpectedConditions.visibilityOf(objCustomerDetailPage.getPaidCard()));
        Assert.assertTrue(objCustomerDetailPage.getCustomerName().getText().equals("John Snow "+ randomString),"Customer name shown: "+objCustomerDetailPage.getCustomerName().getText());
        Assert.assertTrue(objCustomerDetailPage.getPaidCard().getText().equals("PAID"),"Text obtained: "+objCustomerDetailPage.getPaidCard().getText());
        Assert.assertTrue(objCustomerDetailPage.getOpenInvoicesCard().getText().equals("OPEN INVOICES"),"Text obtained: "+objCustomerDetailPage.getOpenInvoicesCard().getText());
        Assert.assertTrue(objCustomerDetailPage.getOverdueInvoicesCard().getText().equals("OVERDUE INVOICES"),"Text obtained: "+objCustomerDetailPage.getOverdueInvoicesCard().getText());

    }

}
