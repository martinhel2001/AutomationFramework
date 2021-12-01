package utils.Listeners;

import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.mantis.MantisConnector;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import BaseTest.BaseTest;

import static Connectors.mantis.MantisConnector.encodeFileToBase64Binary;

//import utils.logs.Log;
public class TestListener extends BaseTest implements ITestListener {

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
        //getExtentReports().flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("#"+getTestMethodName(iTestResult) + "# test is starting.");
        log.info("Test Description: "+iTestResult.getMethod().getDescription());
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is succeed.");
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        //ExtentReports log operation for passed tests.
        //getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
//        Object testClass = iTestResult.getInstance();
        //WebDriver driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
//        String base64Screenshot =
//                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        //getTest().log(Status.FAIL, "Test Failed",
                //getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));

        //Take the screenshot
        try {
            File f = takeScreenshot(getTestMethodName(iTestResult));
            String encodstring = encodeFileToBase64Binary(f);

            String timestamp = ZonedDateTime
                    .now( ZoneId.systemDefault() ).toString();

            String msg = iTestResult.getThrowable().getMessage();
            int statusCodeReceived = mantisAPI.createCompleteIssueWithAttachment(
                    "Bug from "+getTestMethodName(iTestResult)+" TC",
                    "Issue when running TC <strong>"+getTestMethodName(iTestResult)+"</strong>   <br><br>Error message: <strong>"+msg+"</strong>",
                    "Bug found at "+timestamp, Project.AUTOMATION_BUGS,
                    Category.UI,
                    Priority.normal,
                    Severity.feature,
                    encodstring,
                    false);
            System.out.println("Bug posted to Mantis with Status code: "+ statusCodeReceived);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        //getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
