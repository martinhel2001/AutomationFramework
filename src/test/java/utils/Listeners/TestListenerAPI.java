package utils.Listeners;

import BaseTest.BaseTest;
import dataentities.mantis.Category;
import dataentities.mantis.Priority;
import dataentities.mantis.Project;
import dataentities.mantis.Severity;
import Connectors.MantisConnector;
import com.aventstack.extentreports.Status;
import utils.extentReports.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static utils.extentReports.ExtentTestManager.getTest;
import static utils.extentReports.ExtentTestManager.startTest;

public class TestListenerAPI extends BaseTest implements ITestListener {

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
        //iTestContext.setAttribute("WebDriver", this.driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("# "+getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult) + " # test is starting.");
        log.info("Test Description: "+iTestResult.getMethod().getDescription());
        startTest(getTestMethodName(iTestResult),iTestResult.getMethod().getDescription());
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

        String timestamp = ZonedDateTime
                    .now( ZoneId.systemDefault() ).toString();

        String msg = iTestResult.getThrowable().getMessage();

        if (testsConfig.isMantisEnabled().equals("on")){
            String shortMsg = iTestResult.getThrowable().getMessage().substring(0,iTestResult.getThrowable().getMessage().length());
            String mantisID = mantisAPI.createCompleteIssueWithoutAttachment(
            getTestClassName(iTestResult)+"."+getTestMethodName(iTestResult)+": <strong>"+shortMsg+"</strong>",
            "Issue when running TC <strong>"+getTestMethodName(iTestResult)+"</strong>   <br><br>Error message: <strong>"+msg+"</strong>",
            "Bug found at "+timestamp,
            Project.AUTOMATION_BUGS,
            Category.UI,
            Priority.normal,
            Severity.feature,
            false);
            System.out.println("Bug posted to Mantis with ID: "+ mantisID);
            log.info("Bug posted to Mantis with ID: "+ mantisID);
            if (testsConfig.isSlackEnabled().equals("on")){
                slack.postMessageFailedTC(testsConfig.getSlack_token(), testsConfig.getSlack_channel(),getTestMethodName(iTestResult),msg,"",mantisID, testsConfig.getCI_url(),testsConfig.getMantis_url());
            }
        }

        getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage());

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
