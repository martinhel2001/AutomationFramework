import BaseTest.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ecommerceSite.HomePage;

@Listeners(utils.Listeners.TestListener.class)

public class EcommerceSiteTest extends BaseTest {

    @Test (groups = {"ecommerce"})
    public void navigateCarrousel(){
        HomePage obj_Home = new HomePage(driver, testsConfig.getCommerceUrl());
        obj_Home.listFeaturedProducts().addProductFromFeatured(5);

    }
}
