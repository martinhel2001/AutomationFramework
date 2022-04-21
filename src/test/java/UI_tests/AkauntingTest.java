package UI_tests;

import BaseTest.BaseTest_UI;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.akaunting.Dashboard;
import pages.akaunting.InvoicesMain;
import pages.akaunting.Login;
import pages.akaunting.NewInvoice;

public class AkauntingTest extends BaseTest_UI {

    @Test (description = "Testing login button")
    public void createInvoice() {
        Login objLogin = new Login(eventDriver,"https://automationcampus.com.ar/akaunting/");
        Dashboard objDashboard = new Dashboard(eventDriver);
        InvoicesMain objInvoices;
        NewInvoice objNewInvoice = new NewInvoice(eventDriver);

        // Step 1: Login to Akaunting successfully
        objLogin.tryLogin("akaunting-admin@automationcampus.com.ar","trinity110",true);
        Assert.assertTrue(objDashboard.getMainPanel().isDisplayed());

        // Step 2: Navigate to Invoices page
        objInvoices = objDashboard.goToInvoices();

        // Step 3: Add New Invoice
        objInvoices.getAddNewBtn().click();
        Assert.assertTrue(objNewInvoice.getHeaderTitle().isDisplayed());
    }
}
