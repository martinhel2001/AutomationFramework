package pages.martinaSeminara;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class PortfolioItemPage extends BasePage {

    By pageTitle = new By.ByCssSelector(".page-title.h3");
    By videoWrapper = new By.ByClassName("wpb_video_wrapper");
    By videoDescription = new By.ByCssSelector(".wpb_text_column.wpb_content_element");

    public PortfolioItemPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return driver.findElement(pageTitle);
    }

    public WebElement getVideoWrapper() {
        return driver.findElement(videoWrapper);
    }

    public WebElement getVideoDescription() {
        return driver.findElement(videoDescription);
    }


}
