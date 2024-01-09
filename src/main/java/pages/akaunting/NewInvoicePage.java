package pages.akaunting;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class NewInvoicePage extends BasePageInteractions {

    public NewInvoicePage(EventFiringWebDriver session){
        super(session);
    }

    private By AddCustomerBtn =By.className("text-add-contact");

    public void AddCustomerBtnClick(){
        eventDriver.findElement(AddCustomerBtn).click();
    }

}
