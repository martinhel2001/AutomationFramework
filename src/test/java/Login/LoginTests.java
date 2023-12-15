package Login;

import BaseTest.BaseTests;
import org.junit.Test;
import pages.akaunting.LoginPage;

public class LoginTests extends BaseTests {

    @Test
    public void testLogin(){
        LoginPage.SetEmail();
    }
}
