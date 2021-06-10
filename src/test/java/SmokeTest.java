import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.kapsch.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import static baseMain.spreadsheetAutomation.GetGoogleSheetData.getAllCurrentPromoInfo;

public class SmokeTest extends BaseTest{

    @Test
    public void loginTest(){
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        LoginPage objLoginPage = new LoginPage(driver, testsConfig.getSUTurl());
        objLoginPage.login(username,password);
        Assert.assertTrue( objLoginPage.objHomePage.getWelcomeMessage().isDisplayed());
    }

    @Test (dependsOnMethods = {"loginTest"})
    public void navigationTest() throws InterruptedException {
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        HomePage objHomePage = new HomePage(driver);
        ConfigurationHomePage objConfHome = new ConfigurationHomePage(driver);
        AuditHomePage objAuditHome = new AuditHomePage(driver);
        ManualValidationHomePage objMVhome = new ManualValidationHomePage(driver);
        MonitoringHomePage objMonitoringHome = new MonitoringHomePage(driver);
        SecurityHomePage objSecurityHome = new SecurityHomePage(driver);
        TransactionManagerHomePage objTMhome = new TransactionManagerHomePage(driver);
        VehicleManagerHomePage objVMhome = new VehicleManagerHomePage(driver);

        objHomePage.goToConfiguration();
        wait.until(ExpectedConditions.visibilityOf(objConfHome.getValidationPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objConfHome.getHomeTitle().getText().equals("CONFIGURATION"));

        objConfHome.goToAudit();
        wait.until(ExpectedConditions.visibilityOf(objAuditHome.getViewCentralizedAuditPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objAuditHome.getHomeTitle().getText().equals("AUDIT MANAGER"));

        objHomePage.goToManualValidation();
        wait.until(ExpectedConditions.visibilityOf(objMVhome.getManualValidationPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objMVhome.getHomeTitle().getText().equals("MANUAL VALIDATION"));

        objHomePage.goToMonitoring();
        wait.until(ExpectedConditions.visibilityOf(objMonitoringHome.getSystemEventsPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objMonitoringHome.getHomeTitle().getText().equals("MONITORING CONFIGURATION"));

        objHomePage.goToSecurity();
        wait.until(ExpectedConditions.visibilityOf(objSecurityHome.getSystemGroupsPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objSecurityHome.getHomeTitle().getText().equals("SECURITY"));

        objHomePage.goToTransactionManager();
        wait.until(ExpectedConditions.visibilityOf(objTMhome.getViewTransactionPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objTMhome.getHomeTitle().getText().equals("TRANSACTION MANAGER"));

        objHomePage.goToVehicleManager();
        wait.until(ExpectedConditions.visibilityOf(objVMhome.getVehiclesPanel()));
        Thread.sleep(2000);
        Assert.assertTrue(objVMhome.getHomeTitle().getText().equals("VEHICLE MANAGER"));

        objVMhome.scrollIntoMiddle(objVMhome.getHomeTitle());
        objHomePage.logout();
    }


}
