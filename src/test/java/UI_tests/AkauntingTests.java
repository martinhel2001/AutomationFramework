package UI_tests;

import BaseTest.BaseTest_UI;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.akaunting.*;


public class AkauntingTests extends BaseTest_UI {

    @Test
    public void doLogin(){
        CustomersPage customersPage = new CustomersPage(eventDriver);
        DashboardPage dashboardPage = new DashboardPage(eventDriver);
        CustomerDetailPage customerDetailPage = new CustomerDetailPage(eventDriver);
        AddNewCustomerPage addNewCustomerPage = new AddNewCustomerPage(eventDriver);


        LoginPage loginPage = new LoginPage(eventDriver);
        loginPage.doLogin("akaunting-admin@automationcampus.com.ar", "trinity110");

        dashboardPage.goToCustomers();
        customersPage.AddNewBtnClick();

        //HelpersAkaunting helpersAkaunting = new HelpersAkaunting();
        //helpersAkaunting.addNewCustomer("Miguel Mateos", "miguel@zas.com");


        addNewCustomerPage.addNewCustomer("Tweetie Gonzalez 3", "tweetie3@soda.com.ar");


        wait.until(ExpectedConditions.invisibilityOf(eventDriver.findElement(addNewCustomerPage.getSubmitBtn()) ) );

        Assert.assertEquals(customerDetailPage.getCustomerName().getText(), "Tweetie Gonzalez 3","Customer was not created." );







        //Assert.assertTrue();
    }
}
