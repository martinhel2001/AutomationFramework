package utils.Listeners;

import BaseTest.BaseTest;
import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.mantis.MantisConnector;
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

    MantisConnector mantisAPI = new MantisConnector();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("I am in onStart method " + iTestContext.getName());
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        iTestContext.setAttribute("WebDriver", this.driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("#"+getTestMethodName(iTestResult) + "# test is starting.");
        log.info("Test Description: "+iTestResult.getMethod().getDescription());
        startTest(getTestMethodName(iTestResult),iTestResult.getMethod().getDescription());
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is succeed.");
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is failed.");

        String timestamp = ZonedDateTime
                    .now( ZoneId.systemDefault() ).toString();

        String msg = iTestResult.getThrowable().getMessage();
        int statusCodeReceived = mantisAPI.createCompleteIssueWithoutAttachement(
                "Bug from "+getTestMethodName(iTestResult)+" TC",
                "Issue when running TC <strong>"+getTestMethodName(iTestResult)+"</strong>   <br><br>Error message: <strong>"+msg+"</strong>",
                "Bug found at "+timestamp,
                Project.AUTOMATION_BUGS,
                Category.UI,
                Priority.normal,
                Severity.feature,
                false);

        getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage());

        System.out.println("Bug posted to Mantis with Status code: "+ statusCodeReceived);
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
