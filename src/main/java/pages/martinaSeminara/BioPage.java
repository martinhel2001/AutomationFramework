package pages.martinaSeminara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class BioPage extends BasePage {

    public BioPage(WebDriver driver) {
        super(driver);
    }

    By mainTitle = new By.ByCssSelector(".la-headings.text-center.spacer-position-middle");
    By bodyText = new By.ByClassName("subheading-tag");
    By headerSection = new By.ByCssSelector(".la_parallax_inner");

    public WebElement getMainTitle(){
        return driver.findElement(mainTitle);
    }

    public WebElement getBodyText() {
        return driver.findElement(bodyText);
    }

    public WebElement getHeaderSection(){
        return driver.findElement(headerSection);
    }
}
