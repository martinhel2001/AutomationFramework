package UI_tests;

import BaseTest.BaseTest_UI;
import BaseTest.BaseTest_UI_parallel;
import Connectors.SlackConnector;
import baseMain.WebDriverFactoryStaticThreadLocal;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.extentReports.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ecommerceSite.HomePage;
import pages.ecommerceSite.ShoppingCart;

import java.lang.reflect.Method;


public class EcommerceSiteTest extends BaseTest_UI_parallel {

    static WebDriver driver;

    @BeforeClass
    public void setUpDriver()
    {
        WebDriverFactoryStaticThreadLocal.setDriverThread();
        driver = WebDriverFactoryStaticThreadLocal.getDriverThread();
        System.out.println("Browser setup by Thread "+Thread.currentThread().getId()+" and Driver reference is : "+driver);
    }

    @Test (groups = {"regression"}, description = "Add 2 Featured Products into the Cart")
    public void add2featuredProductsToCart(Method m){

        System.out.println(m.getName()+" Test executed in thread: "+Thread.currentThread().getId());


        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());
        obj_Home.listFeaturedProducts().addProductFromFeatured(5).continueShopping();
        obj_Home.addProductFromFeatured(3);
        Assert.assertTrue(obj_Home.getTxtQtyItemsInCart().getText().contains("There are 3 items in your cart."),"Cart qty not right; waiting for 3 and found: "+obj_Home.getTxtQtyItemsInCart().getText());
    }

    @Test (groups = {"regression"}, description = "Add 2 Featured Product to Cart")
    public void add1featuredProductToCartAndCheckout(Method m){

        System.out.println(m.getName()+" Test executed in thread: "+Thread.currentThread().getId());


        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());

        ShoppingCart obj_ShoppingCart = obj_Home.addProductFromFeatured(2).proceedToCheckout();
        Assert.assertTrue(obj_ShoppingCart.getHeadingCounter().contains("Your shopping cart contains: 1 Product"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriverClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}
