package UI_tests;

import BaseTest.BaseTest;
import Connectors.SQLConnector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.kapsch.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Listeners(utils.Listeners.TestListener.class)

public class KapschSmokeTest extends BaseTest {
    SQLConnector sql = new SQLConnector();

    @Test (groups = "OPERIANPD-3293")
    public void loginTest(){
       log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        LoginPage objLoginPage = new LoginPage(driver, testsConfig.getOBOurl());
        objLoginPage.login(username,password);
        Assert.assertTrue( objLoginPage.objHomePage.getWelcomeMessage().isDisplayed());
        }

    @Test (groups = "OPERIANPD-3286",dependsOnMethods = {"loginTest"})
    public void logoutTest(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        LoginPage objLoginPage = new LoginPage(driver, testsConfig.getOBOurl());
        HomePage objHomePage = new HomePage(driver);
        objHomePage.logout();
        wait.until(ExpectedConditions.visibilityOf( objLoginPage.getLoginBox()));
        Assert.assertTrue( objLoginPage.getLoginBox().isDisplayed());
    }

    @Test (groups = "OPERIANPD-3298", dependsOnMethods = {"loginTest"})
    public void manualValidationConfirm() {
        HomePage obj_HomePage = new HomePage(driver);
        ManualValidationPage obj_MVpage = new ManualValidationPage(driver);
        ViewTrxPage obj_ViewTrx = new ViewTrxPage(driver);
        String vrm;

        // Step 1: Enter Manual Validation
        obj_HomePage.goToManualValidation().goToManualValidationContent();

        // Step 2: Set a valid VRM, Vehicle class and License Plate Type.
        //Click on Confirm button.
        vrm = obj_MVpage.getVRM().getText();

        obj_MVpage.setVRM("LPN")
                  .setCountry("ES")
                  .setRegion("region")
                  .setVehicleClass("truck")
                  .confirm();

        Assert.assertTrue(obj_MVpage.isClickable(obj_MVpage.getBtnConfirm(),10)||obj_MVpage.isNoMoreTrxPopupDisplayed());

        if (obj_MVpage.isNoMoreTrxPopupDisplayed()) obj_MVpage.clickNoMoreTrx_No();

        // Step 3: Go to View Transaction.
        //Filter by the VRM.
        //Enter the link to see Transaction Details.
        obj_MVpage.goBackHome().goToTransactionManager().goToViewTrx()
                .setInputLPN(vrm)
                .clickSearch();

        Assert.assertTrue(obj_ViewTrx.vrmFound(vrm));
        //obj_ViewTrx.moreActionsView();
    }

    @Test (dependsOnMethods = {"loginTest"})
    public void verifyViewTrxResults(){
        HomePage obj_HomePage = new HomePage(driver);
        ViewTrxPage obj_VTpage = new ViewTrxPage(driver);
        String vrm="AU833GA";

        obj_HomePage.goToTransactionManager().goToViewTrx();
        obj_VTpage.setInputLPN(vrm)
                .clickSearch()
                .inspectTrxTable();
        Assert.assertTrue(obj_VTpage.vrmFound(vrm));

        obj_VTpage.moreActionsView();
        obj_VTpage.inspectTrxTable();

    }

    @Test (dependsOnMethods = {"loginTest"}, groups = "OPERIANPD-3300")
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

    @Test (dependsOnMethods = {"loginTest"})
    public void countTableRowsCols(){
        HomePage obj_HomePage = new HomePage(driver);

        obj_HomePage.goToTransactionManager().goToViewTrx().inspectTrxTable();
    }


    @Test
    public void dbConnectionTest() throws SQLException, ClassNotFoundException {
        Statement stmt = sql.openConn(testsConfig.getDBurl(),testsConfig.getDBuser(),testsConfig.getDBpass());

        //Query to Execute
        String query = "SELECT * FROM [OBO].[mva].[ManValConfiguration];";

        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs= stmt.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (rs.next()){
            String manValConfigurationId = rs.getString(1);
            String maxConfirmOperations = rs.getString(2);
            System. out.println("manValConfigurationId: "+manValConfigurationId+"  "+"maxConfirmOperations: "+maxConfirmOperations);
        }
        // closing DB Connection
        sql.closeDBconn();


    }


}
