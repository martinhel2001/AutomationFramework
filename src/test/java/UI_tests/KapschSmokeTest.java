package UI_tests;

import BaseTest.BaseTest_UI;
import Connectors.SQLConnector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.kapsch.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@Listeners(utils.Listeners.TestListener.class)

public class KapschSmokeTest extends BaseTest_UI {
    SQLConnector sql = new SQLConnector();

    @Test (groups = "OPERIANPD-3293" , enabled=false )
    public void loginTest(){
       log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        LoginPage objLoginPage = new LoginPage(eventDriver, testsConfig.getOBOurl());
        objLoginPage.login(username,password);
        Assert.assertTrue( objLoginPage.objHomePage.getWelcomeMessage().isDisplayed());
        }

    @Test (groups = "OPERIANPD-3286",dependsOnMethods = {"loginTest"}, enabled=false )
    public void logoutTest(){
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        LoginPage objLoginPage = new LoginPage(eventDriver, testsConfig.getOBOurl());
        HomePage objHomePage = new HomePage(eventDriver);
        objHomePage.logout();
        wait.until(ExpectedConditions.visibilityOf( objLoginPage.getLoginBox()));
        Assert.assertTrue( objLoginPage.getLoginBox().isDisplayed());
    }

    @Test (groups = "OPERIANPD-3298", dependsOnMethods = {"loginTest"}, enabled=false )
    public void manualValidationConfirm() {
        HomePage obj_HomePage = new HomePage(eventDriver);
        ManualValidationPage obj_MVpage = new ManualValidationPage(eventDriver);
        ViewTrxPage obj_ViewTrx = new ViewTrxPage(eventDriver);
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

        //Assert.assertTrue(obj_MVpage.isClickable(obj_MVpage.getBtnConfirm(),10)||obj_MVpage.isNoMoreTrxPopupDisplayed());

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

    @Test (dependsOnMethods = {"loginTest"}, enabled=false )
    public void verifyViewTrxResults(){
        HomePage obj_HomePage = new HomePage(eventDriver);
        ViewTrxPage obj_VTpage = new ViewTrxPage(eventDriver);
        String vrm="AU833GA";

        obj_HomePage.goToTransactionManager().goToViewTrx();
        obj_VTpage.setInputLPN(vrm)
                .clickSearch()
                .inspectTrxTable();
        Assert.assertTrue(obj_VTpage.vrmFound(vrm));

        obj_VTpage.moreActionsView();
        obj_VTpage.inspectTrxTable();

    }

    @Test (dependsOnMethods = {"loginTest"}, groups = "OPERIANPD-3300", enabled=false )
    public void navigationTest() throws InterruptedException {
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+" TEST Has Started");
        HomePage objHomePage = new HomePage(eventDriver);
        ConfigurationHomePage objConfHome = new ConfigurationHomePage(eventDriver);
        AuditHomePage objAuditHome = new AuditHomePage(eventDriver);
        ManualValidationHomePage objMVhome = new ManualValidationHomePage(eventDriver);
        MonitoringHomePage objMonitoringHome = new MonitoringHomePage(eventDriver);
        SecurityHomePage objSecurityHome = new SecurityHomePage(eventDriver);
        TransactionManagerHomePage objTMhome = new TransactionManagerHomePage(eventDriver);
        VehicleManagerHomePage objVMhome = new VehicleManagerHomePage(eventDriver);

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

    @Test (dependsOnMethods = {"loginTest"}, enabled=false )
    public void countTableRowsCols(){
        HomePage obj_HomePage = new HomePage(eventDriver);

        obj_HomePage.goToTransactionManager().goToViewTrx().inspectTrxTable();
    }


    @Test ( enabled=false )
    public void dbKapschConnectionTest() throws SQLException, ClassNotFoundException {
        Statement stmt = sql .openConnSQL(testsConfig.getDBKapsch_url(),testsConfig.getDBKapsch_user(),testsConfig.getDBKapsch_pass());

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
