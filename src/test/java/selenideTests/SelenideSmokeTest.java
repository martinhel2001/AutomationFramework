package selenideTests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import BaseTest.BaseTest_UI;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;



public class SelenideSmokeTest extends BaseTest_UI {


    @Test (enabled = false) // DISABLED porque ya no tengo acceso a Kapsch OBO
    public void userCanLoginByUsername() {
        open(testsConfig.getOBOurl());
        $("#username").setValue(username);
        $("#password").setValue(password);
        $(By.name("submit")).click();
        $(".welcome-message").should(disappear);

//        $(".loading_progress").should(disappear); // Waits until element disappears
//        $("#username").shouldHave(text("Hello, Johny!")); // Waits until element gets text
    }
}
