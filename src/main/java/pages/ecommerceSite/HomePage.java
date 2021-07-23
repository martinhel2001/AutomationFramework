package pages.ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {


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
    By btnAddToCart = new By.ByXPath("//*[@class=\"right-block\"]");//new By.ByClassName("a.button.ajax_add_to_cart_button.btn.btn-default");
    By modalProductAddedToCart = new By.ById("layer_cart");
    List<WebElement> featuredProductsList;

    public HomePage(WebDriver driver, String url) {
        super(driver);
        driver.get(url);
        featuredProductsList = driver.findElements(featuredProducts);
    }

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

    public HomePage listFeaturedProducts(){
        for (int i=0;i<featuredProductsList.size();i++){
            System.out.println(featuredProductsList.get(i).findElement(new By.ByClassName("product-name")).getText());
        }
        return this;
    }

    protected HomePage addToCart(List<WebElement> productList, int productIndex) {
        System.out.println("Producto a comprar: "+productList.get(productIndex).findElement(new By.ByClassName("product-name")).getText());
        //System.out.println("Inner HTML del producto a comprar: "+productList.get(productIndex).getAttribute("innerHTML"));
        productList.get(productIndex).findElement(new By.ByClassName("right-block")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(modalProductAddedToCart)));
        return  this;
    }

    public void addProductFromFeatured(int productIndex){
        addToCart(featuredProductsList, productIndex-1);
    }
}
