package pages.ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
    }

    By banner = new By.ByCssSelector("#header > div.banner > div.container");
    By logo = new By.ByClassName("logo");
    By searchBox = new By.ById("search_query_top");
    By searchSubmit  = By.name("submit_search");
    By menuWomen = new By.ByLinkText("WOMEN");
    By menuDresses = new By.ByLinkText("DRESSES");
    By menuTshirts = new By.ByLinkText("T-SHIRTS");
    By menuPopular = new By.ByLinkText("POPULAR");
    By menuBestSellers = new By.ByLinkText("BEST SELLERS");
    By carrousel = new By.ById("homepage-slider");
    By btnCarrouselLeft = new By.ByCssSelector("#homepage-slider > div > div.bx-controls.bx-has-controls-direction > div > a.bx-prev");
    By btnCarrouselRight = new By.ByCssSelector("#homepage-slider > div > div.bx-controls.bx-has-controls-direction > div > a.bx-next");
    By featuredProducts = new By.ByCssSelector("#homefeatured> li");
    public void search(String product){
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchSubmit);
    }

    public HomePage carrouselLeft(){
        driver.findElement(btnCarrouselLeft).click();
        return this;
    }

    public HomePage carrouselRight() {
        driver.findElement(btnCarrouselRight).click();
        return this;
    }

    public HomePage countFeaturedProducts(){
        ArrayList<WebElement> featuredProductsList = new ArrayList<>(driver.findElements(featuredProducts));
        for (int i=0;i<featuredProductsList.size();i++){
            System.out.println(featuredProductsList.get(i).findElement(new By.ByClassName("product-name")).getText());
        }
        return this;
    }
}
