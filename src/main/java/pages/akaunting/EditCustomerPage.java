package pages.akaunting;

import com.slack.api.scim.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class EditCustomerPage extends BasePageInteractions {

    public EditCustomerPage(EventFiringWebDriver session) {
        super(session);
    }

    WebElement EmailFld = eventDriver.findElement(By.id("email"));
    WebElement PhoneFld = eventDriver.findElement(By.id("phone"));

    public void EmailFldClear = EmailFld.clear();
    public void PhoneFldClear = PhoneFld.clear();


}

}
