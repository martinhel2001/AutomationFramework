package baseMain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ThreadGuard;

public class WebDriverFactoryStaticThreadLocal {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();

    public static void setDriverThread() {
        WebDriverManager.chromedriver().setup();
        WebDriver driverInstance = new ChromeDriver();
        driverInstance = ThreadGuard.protect(driverInstance);
        driverThread.set(driverInstance);
    }

    public static WebDriver getDriverThread() {
        return driverThread.get();
    }

    public static void closeBrowser() {
        driverThread.get().quit();
        driverThread.remove();
    }
}
