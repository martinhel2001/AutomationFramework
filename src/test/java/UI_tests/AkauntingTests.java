package UI_tests;

import BaseTest.BaseTest_UI;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.akaunting.*;


public class AkauntingTests extends BaseTest_UI {

    @Test
    public void doLogin(){
        LoginPage loginPage = new LoginPage(eventDriver);
        loginPage.doLogin("akaunting-admin@automationcampus.com.ar", "trinity110");
        CustomersPage customersPage = new CustomersPage(eventDriver);
        DashboardPage dashboardPage = new DashboardPage(eventDriver);

        dashboardPage.goToCustomers();
        customersPage.AddNewBtnClick();

        //HelpersAkaunting helpersAkaunting = new HelpersAkaunting();
        //helpersAkaunting.addNewCustomer("Miguel Mateos", "miguel@zas.com");

        AddNewCustomerPage addNewCustomerPage = new AddNewCustomerPage(eventDriver);

        addNewCustomerPage.addNewCustomer("Charly Alberti", "alberti@soda.com.ar");

        CustomerDetailPage customerDetailPage = new CustomerDetailPage(eventDriver);

        Assert.assertEquals(customerDetailPage.getCustomerName().getText(),  );







        //Assert.assertTrue();
    }
}
