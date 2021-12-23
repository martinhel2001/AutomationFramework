package utils.Listeners;

import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.MantisConnector;
import com.aventstack.extentreports.Status;

import java.io.File;

import utils.extentReports.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import BaseTest.BaseTest_UI;

import static Connectors.MantisConnector.encodeFileToBase64Binary;
import static utils.extentReports.ExtentTestManager.getTest;
import static utils.extentReports.ExtentTestManager.startTest;

//import utils.logs.Log;
public class TestListenerUI extends BaseTest_UI implements ITestListener {

    MantisConnector mantisAPI = new MantisConnector(testsConfig.getMantis_url(),testsConfig.getMantis_host(),testsConfig.getMantis_token());

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestClassName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getName();
    }


    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("I am in onStart method " + iTestContext.getName());
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        iTestContext.setAttribute("WebDriver", this.eventDriver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method 2" + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("# "+getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult) + " # test is starting.");
        log.info("Test Description: "+iTestResult.getMethod().getDescription());
        startTest(getTestMethodName(iTestResult),iTestResult.getMethod().getDescription());//Extent Reports
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("# "+getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult) + " # test is succeed.");
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("# "+getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult) + " # test is failed.");

        File f;
        String encodstring=null;

        try {
            f = takeScreenshot(getTestMethodName(iTestResult));
            encodstring = encodeFileToBase64Binary(f);

            String msg = iTestResult.getThrowable().getMessage();
            String shortMsg = iTestResult.getThrowable().getMessage().substring(0,50);

            if (testsConfig.isMantisEnabled().equals("on")){
                String mantisID = mantisAPI.createCompleteIssueWithAttachment(
                        getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult)+": <strong>"+shortMsg+"</strong>",
                        "Issue when running TC <strong>"+getTestMethodName(iTestResult)+"</strong>   <br><br>Error message: <strong>"+msg+"</strong>",
                        "Bug found at "+timestamp, Project.AUTOMATION_BUGS,
                        Category.UI,
                        Priority.normal,
                        Severity.feature,
                        encodstring,
                        false);
                    System.out.println("Bug posted to Mantis with ID: "+ mantisID);
                    log.info("Bug posted to Mantis with ID: "+ mantisID);


                if (testsConfig.isSlackEnabled().equals("on")){
                    slack.postMessageFailedTC(testsConfig.getSlack_token(), testsConfig.getSlack_channel(),getTestMethodName(iTestResult),msg, testsConfig.getFTP_url()+"/screenshots/"+localScreenshotFileName,mantisID,testsConfig.getCI_url(), testsConfig.getMantis_url());
                }

            }

            getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage(),
            getTest().addScreenCaptureFromBase64String(encodstring).getModel().getMedia().get(0));

            if (testsConfig.isUploadScreenshotEnabled().equals("on")){
                uploadFile(localScreenshotCompleteFileName,localScreenshotFileName, "/public_html/screenshots/");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("# "+getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult) + " # test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
