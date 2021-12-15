package utils.Listeners;

import Connectors.SlackConnector;
import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.mantis.MantisConnector;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import utils.extentReports.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import BaseTest.BaseTest;

import static Connectors.mantis.MantisConnector.encodeFileToBase64Binary;
import static utils.extentReports.ExtentTestManager.getTest;
import static utils.extentReports.ExtentTestManager.startTest;

//import utils.logs.Log;
public class TestListenerUI extends BaseTest implements ITestListener {

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
        log.info("I am in onFinish method 2" + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("#"+getTestMethodName(iTestResult) + "# test is starting.");
        log.info("Test Description: "+iTestResult.getMethod().getDescription());
        startTest(getTestMethodName(iTestResult),iTestResult.getMethod().getDescription());//Extent Reports
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
        File f;
        String encodstring=null;
        SlackConnector slack = new SlackConnector();

        try {
            f = takeScreenshot(getTestMethodName(iTestResult));
            encodstring = encodeFileToBase64Binary(f);

            String timestamp = ZonedDateTime
                    .now( ZoneId.systemDefault() ).toString();

            String msg = iTestResult.getThrowable().getMessage();
            String mantisID = mantisAPI.createCompleteIssueWithAttachment(
                    "Bug from "+getTestMethodName(iTestResult)+" TC",
                    "Issue when running TC <strong>"+getTestMethodName(iTestResult)+"</strong>   <br><br>Error message: <strong>"+msg+"</strong>",
                    "Bug found at "+timestamp, Project.AUTOMATION_BUGS,
                    Category.UI,
                    Priority.normal,
                    Severity.feature,
                    encodstring,
                    false);

            getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage(),
                    getTest().addScreenCaptureFromBase64String(encodstring).getModel().getMedia().get(0));

            System.out.println("Bug posted to Mantis with ID: "+ mantisID);

            uploadFile(localScreenshotCompleteFileName,localScreenshotFileName, "/public_html/screenshots/");
            slack.postMessage("xoxb-2835256584579-2832653447381-Q7xdoEQZZVva0AJ1vB967X1w","#ci-runs",getTestMethodName(iTestResult),msg, "https://mantisautomation.000webhostapp.com/screenshots/"+localScreenshotFileName,mantisID);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
