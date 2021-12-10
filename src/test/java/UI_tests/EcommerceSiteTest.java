package UI_tests;

import BaseTest.BaseTest_UI;
import com.aventstack.extentreports.Status;
import extentReports.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ecommerceSite.HomePage;
import pages.ecommerceSite.ShoppingCart;



public class EcommerceSiteTest extends BaseTest_UI {

    @Test (groups = {"smoke"}, description = "Add 2 Featured Products into the Cart")
    public void add2featuredProductsToCart(){
        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());
        obj_Home.listFeaturedProducts().addProductFromFeatured(5).continueShopping();
        obj_Home.addProductFromFeatured(3);
        Assert.assertTrue(obj_Home.getTxtQtyItemsInCart().getText().contains("There are 4 items in your cart."),"Cart qty not right; waiting for 3 and found: "+obj_Home.getTxtQtyItemsInCart().getText());
    }

    @Test (groups = {"smoke"}, description = "Add 2 Featured Product to Cart")
    public void add1featuredProductToCartAndCheckout(){
        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());

        ShoppingCart obj_ShoppingCart = obj_Home.addProductFromFeatured(2).proceedToCheckout();
        Assert.assertTrue(obj_ShoppingCart.getHeadingCounter().contains("Your shopping cart contains: 1 Product"));
    }

    @Test
    public void baseTest1() {
        ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test1");
        System.out.println("Hey im in test1 test");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 1");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 2");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 3");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 4");
    }

}
