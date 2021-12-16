package BaseTest;

import Connectors.SlackConnector;
import baseMain.TestsConfigReader;
import baseMain.UserPropertiesReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;



public class BaseTest {

    public static WebDriver driver;
    public String username;
    public String password;
    public WebDriverWait wait ;
    public Logger log = Logger.getLogger(BaseTest.class.getName());
    public TestsConfigReader testsConfig = new TestsConfigReader();
    static ExtentTest test;
    static ExtentReports report;
    public String localScreenshotCompleteFileName;
    public String localScreenshotFileName;
    public String timestamp = ZonedDateTime
            .now( ZoneId.systemDefault() )
            .format( DateTimeFormatter.ofPattern( "uuuu.MM.dd.HH.mm.ss" ) );

    public SlackConnector slack = new SlackConnector();


    public void initializeDriver(boolean isResponsive)
    {
        String[] browsers =
                //{
                //"CHROME",
                //"FIREFOX",
                //"EDGE"
                (String[]) testsConfig.getBrowsers().split(",");
                //};

        int idx = new Random().nextInt(browsers.length);
        String browser = (browsers [idx]);
        System.out.println("Browser used for the test: "+browser);


        // CHROME
        if (browser.equals("CHROME")) {
            WebDriverManager.chromedriver().setup();
            WebDriverManager.chromedriver().clearResolutionCache();
            //WebDriverManager.chromedriver().version("83").setup();
            ChromeOptions options = new ChromeOptions();

            if (isResponsive) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                options.setExperimentalOption("mobileEmulation", mobileEmulation);
            } else {
                options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }

            options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770

            // SI QUEREMOS CORRERLO EN MODO HEADLESS   - BEGIN
//            options.addArguments("--headless");
//            options.addArguments("--disable-gpu");
            // SI QUEREMOS CORRERLO EN MODO HEADLESS   - END

             driver = new ChromeDriver(options);
        }

        // FIREFOX
        if (browser.equals("FIREFOX")) {
            WebDriverManager.firefoxdriver().clearResolutionCache();
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            if (isResponsive) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                //options.setExperimentalOption("mobileEmulation", mobileEmulation);
            } else {
                options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }
            driver = new FirefoxDriver(options);
        }

        // EDGE
        if (browser.equals("EDGE")) {
            WebDriverManager.edgedriver().clearResolutionCache();
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();

            if (isResponsive) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
                //options.setExperimentalOption("mobileEmulation", mobileEmulation);
            } else {
                   //options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                   //options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
            }
            driver = new EdgeDriver(options);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (isResponsive) driver.manage().window().setSize(new Dimension(375, 812));
        wait = new WebDriverWait(driver,20);

    }

    protected File takeScreenshot(String methodName){
        File screenshot = null;
        try {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            log.info("Screenshot taken");
        } catch (WebDriverException e) {
            log.info("Exception while taking screenshot");
            e.printStackTrace();
        }

        //Copy the file to a location and use try catch block to handle exception
        String timestamp = ZonedDateTime
                .now( ZoneId.systemDefault() )
                .format( DateTimeFormatter.ofPattern( "uuuu.MM.dd.HH.mm.ss" ) );
        localScreenshotFileName=methodName+"_"+timestamp+".png";
        localScreenshotCompleteFileName ="C:\\Automation\\"+localScreenshotFileName;
        try {
            FileUtils.copyFile(screenshot, new File(localScreenshotCompleteFileName));
            System.out.println("Screenshot saved at: "+ localScreenshotCompleteFileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return screenshot;
    }

    public static class FTPUploader {

        FTPClient ftp = null;

        public FTPUploader(String host, String user, String pwd) throws Exception{
            ftp = new FTPClient();
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            int reply;
            ftp.connect(host,21);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new Exception("Exception in connecting to FTP Server");
            }
            ftp.login(user, pwd);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
        }

        public void uploadFile(String localFileFullName, String fileName, String hostDir)
                throws Exception {
            try(InputStream input = new FileInputStream(new File(localFileFullName))){
                this.ftp.storeFile(hostDir + fileName, input);
            }
        }

        public void disconnect(){
            if (this.ftp.isConnected()) {
                try {
                    this.ftp.logout();
                    this.ftp.disconnect();
                } catch (IOException f) {
                    // do nothing as file is already saved to server
                }
            }
        }
    }

    public void uploadFile (String localFileFullName, String fileName, String hostDir) throws Exception {
        FTPUploader ftpUploader = new FTPUploader(testsConfig.getFTP_host(), testsConfig.getFTP_user(), testsConfig.getFTP_pass());
        ftpUploader.uploadFile(localFileFullName, fileName, hostDir);
        ftpUploader.disconnect();

    }



    @BeforeSuite
    public void setUser(){
        UserPropertiesReader userReader = new UserPropertiesReader("Administrator");
        username = userReader.getUsername();
        password = userReader.getPassword();
    }

    @BeforeSuite
    public static void Setup() {
        // loading log4j.xml file
        DOMConfigurator.configure("log4j.xml");
    }

    @AfterSuite
    public void uploadLog() throws Exception {
        this.uploadFile("./extent-reports/extent-report.html", "report-"+timestamp+".html", "/public_html/extent-reports/");
        this.uploadFile("./Logs/Webliv_Automation_Logs.log", "automationLog-"+timestamp+".log", "/public_html/logs/");
        slack.postMessageTestRunFinished(testsConfig.getSlack_token(), testsConfig.getSlack_channel(),testsConfig.getFTP_url()+"/logs/automationLog-"+timestamp+".log",testsConfig.getFTP_url()+"/extent-reports/report-"+timestamp+".html",testsConfig.getCI_url());
    }
}
