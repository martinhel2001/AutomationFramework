package Login;

import BaseTest.BaseTest_UI;
import org.junit.Test;
import pages.akaunting.LoginPage;


public class LoginTests extends BaseTest_UI {

    @Test
    public void doLogin(){
        LoginPage loginPage = new LoginPage(eventDriver);
        loginPage.doLogin("akaunting-admin@automationcampus.com.ar", "trinity110");

        //Assert.assertTrue();
    }
}
