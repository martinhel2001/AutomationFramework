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

    private String CustomerName = "David Lebon"+int_random;
    private String CustomerEmail = "david"+int_random+"@test.com.ar";
    private By NewCustomerBanner = By.className("alert");

    private By NewCustomerName = By.cssSelector("div#header h2");

    @Test
    public void addNewCustomerTest(){
        CustomersPage customersPage = new CustomersPage(eventDriver);
        DashboardPage dashboardPage = new DashboardPage(eventDriver);
        CustomerDetailPage customerDetailPage = new CustomerDetailPage(eventDriver);
        AddNewCustomerPage addNewCustomerPage = new AddNewCustomerPage(eventDriver);
        LoginPage loginPage = new LoginPage(eventDriver);
        loginPage.doLogin("akaunting-admin@automationcampus.com.ar", "trinity110");
        dashboardPage.goToCustomers();
        customersPage.AddNewBtnClick();
        addNewCustomerPage.addNewCustomer(CustomerName, CustomerEmail);
        // Assert.assertEquals(NewCustomerBanner.isDisplayed(), "Succesfull customer creation banner is not displayed");
        System.out.println("Texto de banner: "+eventDriver.findElement(NewCustomerBanner).getText());
        Assert.assertTrue(eventDriver.findElement(NewCustomerBanner).getText().contains("Customer added!"), "The customer was not added");
        Assert.assertEquals(eventDriver.findElement(NewCustomerName).getText(), CustomerName, "The customer's name is incorrect");
    }
}
