import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SmokeTest extends BaseTest{
    //HomePage objHomePage = new HomePage(driver);

    @Test
    public void loginTest(){
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.login(username,password);
        Assert.assertTrue( objLoginPage.objHomePage.getWelcomeMessage().isDisplayed());
    }

    @Test (dependsOnMethods = {"loginTest"})
    public void navigationTest(){
        HomePage objHomePage = new HomePage(driver);
        ConfigurationHomePage objConfHome = new ConfigurationHomePage(driver);
        AuditHomePage objAuditHome = new AuditHomePage(driver);
        ManualValidationHomePage objMVhome = new ManualValidationHomePage(driver);
        MonitoringHomePage objMonitoringHome = new MonitoringHomePage(driver);
        SecurityHomePage objSecurityHome = new SecurityHomePage(driver);
        TransactionManagerHomePage objTMhome = new TransactionManagerHomePage(driver);
        VehicleManagerPage objVMhome = new VehicleManagerPage(driver);

        objHomePage.goToConfiguration();
        wait.until(ExpectedConditions.visibilityOf(objConfHome.getValidationPanel()));
        Assert.assertTrue(objConfHome.getHomeTitle().getText().equals("CONFIGURATION"));

        objConfHome.goToAudit();
        wait.until(ExpectedConditions.visibilityOf(objAuditHome.getViewCentralizedAuditPanel()));
        Assert.assertTrue(objAuditHome.getHomeTitle().getText().equals("AUDIT MANAGER"));

        objHomePage.goToManualValidation();
        wait.until(ExpectedConditions.visibilityOf(objMVhome.getManualValidationPanel()));
        Assert.assertTrue(objMVhome.getHomeTitle().getText().equals("MANUAL VALIDATION"));

        objHomePage.goToMonitoring();
        wait.until(ExpectedConditions.visibilityOf(objMonitoringHome.getSystemEventsPanel()));
        Assert.assertTrue(objMonitoringHome.getHomeTitle().getText().equals("MONITORING CONFIGURATION"));

        objHomePage.goToSecurity();
        wait.until(ExpectedConditions.visibilityOf(objSecurityHome.getSystemGroupsPanel()));
        Assert.assertTrue(objSecurityHome.getHomeTitle().getText().equals("SECURITY"));

        objHomePage.goToTransactionManager();
        wait.until(ExpectedConditions.visibilityOf(objTMhome.getViewTransactionPanel()));
        Assert.assertTrue(objTMhome.getHomeTitle().getText().equals("TRANSACTION MANAGER"));

        objHomePage.goToVehicleManager();
        wait.until(ExpectedConditions.visibilityOf(objVMhome.getVehiclesPanel()));
        Assert.assertTrue(objVMhome.getHomeTitle().getText().equals("VEHICLE MANAGER"));

        objHomePage.logout();

    }
}
