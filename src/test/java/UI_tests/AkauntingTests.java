package UI_tests;

import BaseTest.BaseTest_UI;

import org.testng.annotations.Test;
import pages.akaunting.AddNewCustomerPage;
import pages.akaunting.CustomersPage;
import pages.akaunting.DashboardPage;
import pages.akaunting.LoginPage;


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

        addNewCustomerPage.addNewCustomer("Gustavo Cerati", "cerati@soda.com.ar");






        //Assert.assertTrue();
    }
}
