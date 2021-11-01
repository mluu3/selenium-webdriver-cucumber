package core.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.List;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverManageUtils {

    private static final PropertyUtils properties = new PropertyUtils();
    static final Logger log = LogManager.getLogger(DriverManageUtils.class.getName());
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            setWebDriver(initDriver());
        }
        return driver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        DriverManageUtils.driver.set(driver);
    }

    public static WebDriver initDriver() {
        WebDriver driver;
        String webDriver = System.getProperty("browser", "chrome");
        switch (webDriver) {
            case "firefox":
                WebDriverManager.firefoxdriver().driverVersion("0.29.0").setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions()
                        .setAcceptInsecureCerts(true)
                        .setHeadless(Boolean.getBoolean("isHeadless"))
                        .addArguments("--disable-dev-shm-usage") // overcome limited resource problems
                        .addArguments("disable-features=NetworkService")
                        .addArguments("enable-features=NetworkServiceInProcess");
                driver = new FirefoxDriver(Boolean.getBoolean("isJenkinsEnv") ?
                        firefoxOptions.setBinary("/usr/bin/firefox") : firefoxOptions);
                driver.manage().timeouts()
                        .pageLoadTimeout(Integer.parseInt(properties.getProperties("pageLoadTimeout")), TimeUnit.SECONDS)
                        .implicitlyWait(Integer.parseInt(properties.getProperties("timeout")), TimeUnit.SECONDS);
                log.info("------------- Started the "+ webDriver +" browser -------------");
                return driver;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", FileUtils.downloadsDir.getAbsolutePath());
                ChromeOptions options = new ChromeOptions()
                        .setExperimentalOption("prefs", chromePrefs)
                        .setAcceptInsecureCerts(true)
                        .setHeadless(Boolean.getBoolean("isHeadless"))
                        .addArguments("--disable-dev-shm-usage") // overcome limited resource problems
                        .addArguments("disable-features=NetworkService")
                        .addArguments("enable-features=NetworkServiceInProcess");
                driver = new ChromeDriver(Boolean.getBoolean("isJenkinsEnv") ?
                        options.setBinary("/usr/bin/google-chrome") : options);
                driver.manage().window().setSize(new Dimension(1200, 760));
                driver.manage().timeouts()
                        .pageLoadTimeout(Integer.parseInt(properties.getProperties("pageLoadTimeout")), TimeUnit.SECONDS)
                        .implicitlyWait(Integer.parseInt(properties.getProperties("timeout")), TimeUnit.SECONDS);
                log.info("------------- Started the " + webDriver + " browser -------------");
                return driver;
            default:
                throw new RuntimeException("Unsupported web driver: " + webDriver);
        }
    }

    public static void stopDriver() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
                log.info("------------- Closed the browser -------------");
            }
        } catch (UnreachableBrowserException e) {
            log.debug("------------- Can not close the browser with error "+e.getMessage()+" -------------");
            System.out.println("Can not close the browser");
        }
       }

    public static void showBrowserConsoleLogs(WebDriver driver){
       if (driver.toString().contains("chrome")){
           LogEntries logs = driver.manage().logs().get("browser");
           List<LogEntry> logList = logs.getAll();
           for (LogEntry logging: logList){
               log.info("---------" + logging.getLevel().toString() + "----------\n" + logging.getMessage());
           }
       }
    }
}
