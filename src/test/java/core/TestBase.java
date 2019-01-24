package core;

import configuration.AppSettings;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    private static Platform getPlatform() {
        String osName = System.getProperty("os.name").toUpperCase();
        return osName.contains("WINDOWS") ? Platform.WINDOWS
                : osName.contains("MAC") ? Platform.MACOS
                : osName.contains("LINUX") ? Platform.LINUX
                : null;
    }

    private RemoteWebDriver getDriver(){
        RemoteWebDriver driver = null;
        String platform = getPlatform().toString().toLowerCase();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/" + platform + "/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        System.out.println(chromeOptions);
        System.out.println("Driver is going to run in " + platform + "env with grid mode is " + AppSettings.Instance.UsingSeleniumGrid);
        if (AppSettings.Instance.UsingSeleniumGrid.equals("false")) {
            if (platform == "linux") {
                System.out.println("Linux.....");
                chromeOptions.addArguments("--headless");
            }
            chromeOptions.addArguments("window-size=1920,1080");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--no-sandbox");
            driver = new ChromeDriver(chromeOptions);
        }
        if(AppSettings.Instance.UsingSeleniumGrid.equals("true")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            chromeOptions.addArguments("window-size=1920,1080");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("disable-notifications");
            chromeOptions.addArguments("process-per-site");
            chromeOptions.addArguments("--dns-prefetch-disable");
            if (platform == "linux") {
                chromeOptions.addArguments("--headless");
            }

            try {
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                desiredCapabilities.setJavascriptEnabled(true);
                driver = new RemoteWebDriver(new URL(AppSettings.Instance.SeleniumGridSwarmUrl), desiredCapabilities);
                driver.setFileDetector(new LocalFileDetector());
                System.out.println("Browsername is " + driver.getCapabilities().getBrowserName() +
                        " platform is " + driver.getCapabilities().getPlatform() +
                        " version is  " + driver.getCapabilities().getVersion());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static WebDriver getActiveDriver(){
        return driver;
    }

    @Before
    public void setUpDriver() {
        System.out.println("setupDriver method is running...");
        if (driver == null){
            driver = getDriver();
        }
        wait = new WebDriverWait(driver, 10);
        driver.get(AppSettings.Instance.BaseUrl);
    }

    @After
    public void tearDown() {
        System.out.println("tearDown method is running...");
        if (driver != null){
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
