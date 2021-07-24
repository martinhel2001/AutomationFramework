import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ecommerceSite.HomePage;
import pages.ecommerceSite.ShoppingCart;

@Listeners(utils.Listeners.TestListener.class)

public class EcommerceSiteTest extends BaseTest {

    @Test (groups = {"smoke"})
    public void add2featuredProductsToCart(){
        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());
        obj_Home.listFeaturedProducts().addProductFromFeatured(5).continueShopping();
        obj_Home.addProductFromFeatured(3);
        Assert.assertTrue(obj_Home.getTxtQtyItemsInCart().getText().contains("There are 3 items in your cart."),"Cart qty not right; waiting for 3 and found: "+obj_Home.getTxtQtyItemsInCart().getText());
    }

    @Test (groups = {"smoke"})
    public void add1featuredProductToCartAndCheckout(){
        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());

        ShoppingCart obj_ShoppingCart = obj_Home.addProductFromFeatured(2).proceedToCheckout();
        Assert.assertTrue(obj_ShoppingCart.getHeadingCounter().contains("Your shopping cart contains: 1 Product"));
    }
}
