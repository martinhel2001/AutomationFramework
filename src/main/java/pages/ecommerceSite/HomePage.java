package pages.ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

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
    By modalLayerCart = new By.ById("layer_cart");
    By iconOK = new By.ByXPath("//i[@class=\"icon-ok\"]");
    By btnContinueShopping = new By.ByXPath("//span[@title=\"Continue shopping\"]");
    By btnProceedToCheckout = new By.ByXPath("//a[@title=\"Proceed to checkout\"]");
    By txtQtyItemsInCart = new By.ByClassName("layer_cart_cart");
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
        productList.get(productIndex).findElement(new By.ByClassName("right-block")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(modalLayerCart).findElement(iconOK)));
        return  this;
    }

    public HomePage addProductFromFeatured(int productIndex){
        addToCart(featuredProductsList, productIndex-1);
        return this;
    }

    public void continueShopping() {
        driver.findElement(modalLayerCart).findElement(btnContinueShopping).click();
    }

    public ShoppingCart proceedToCheckout() {
        driver.findElement(modalLayerCart).findElement(btnProceedToCheckout).click();
        return new ShoppingCart(driver);
    }

    public WebElement getTxtQtyItemsInCart() {
        return driver.findElement(txtQtyItemsInCart);
    }
}
