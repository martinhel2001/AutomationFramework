package UI_tests;

import BaseTest.BaseTest_UI;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.akaunting.*;

import java.util.Random;


public class AkauntingTests extends BaseTest_UI {

    private Random rand = new Random();
    private int upperbound = 9999;
    private int int_random = rand.nextInt(upperbound);

    private By NewCustomerBanner = By.className("alert");

    CustomersPage customersPage = new CustomersPage;



    @Test
    public void EditCustomerTest(){
        EditCustomerPage editCustomerPage = new EditCustomerPage(eventDriver);
        DashboardPage dashboardPage = new DashboardPage(eventDriver);
        CustomerDetailPage customerDetailPage = new CustomerDetailPage(eventDriver);
        AddNewCustomerPage addNewCustomerPage = new AddNewCustomerPage(eventDriver);
        LoginPage loginPage = new LoginPage(eventDriver);
        loginPage.doLogin("akaunting-admin@automationcampus.com.ar", "trinity110");
        dashboardPage.goToCustomers();
        customersPage.



        public String setEmail(String email) {
            eventDriver.findElement(EditCustomerPage.EmailFld).sendKeys(email);
        }
    }
}
