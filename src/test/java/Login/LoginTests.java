package Login;

import BaseTest.BaseTest_UI;

import org.testng.annotations.Test;
import pages.akaunting.CustomersPage;
import pages.akaunting.LoginPage;


public class LoginTests extends BaseTest_UI {

    @Test
    public void doLogin(){
        LoginPage loginPage = new LoginPage(eventDriver);
        loginPage.doLogin("akaunting-admin@automationcampus.com.ar", "trinity110");
        CustomersPage customersPage = new CustomersPage(eventDriver);
        customersPage.AddNewBtnClick();

        //Assert.assertTrue();
    }
}
