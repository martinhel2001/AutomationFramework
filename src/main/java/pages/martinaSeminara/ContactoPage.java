package pages.martinaSeminara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class ContactoPage extends BasePageInteractions {
    public ContactoPage(WebDriver driver) {
        super(driver);
    }

    By mainTitle = new By.ByCssSelector(".la-headings.text-center.spacer-position-middle");
    By contactOptions = new By.ByCssSelector(".vc_row.wpb_row.vc_inner.vc_row-fluid");
    By headerSection = new By.ByCssSelector(".la_parallax_inner");

    public WebElement getMainTitle(){
        return eventDriver.findElement(mainTitle);
    }

    public WebElement getContactOptions(){
        return eventDriver.findElement(contactOptions);
    }

    public WebElement getHeaderSection(){
        return eventDriver.findElement(headerSection);
    }
}
