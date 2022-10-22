package pages.martinaSeminara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.BasePageInteractions;

public class BioPage extends BasePageInteractions {

    public BioPage(EventFiringWebDriver driver) {
        super(driver);
    }

    By mainTitle = new By.ByCssSelector(".la-headings.text-center.spacer-position-middle");
    By bodyText = new By.ByClassName("subheading-tag");
    By headerSection = new By.ByCssSelector(".la_parallax_inner");

    public WebElement getMainTitle(){
        return eventDriver.findElement(mainTitle);
    }

    public WebElement getBodyText() {
        return eventDriver.findElement(bodyText);
    }

    public WebElement getHeaderSection(){
        return eventDriver.findElement(headerSection);
    }
}
