package core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static core.utils.DriverManageUtils.getDriver;

public class WaitUtils {

    static int defaultTimeout = Integer.parseInt(new PropertyUtils().getProperties("timeout"));
    static final Logger log = LogManager.getLogger(WaitUtils.class.getName());

    public static WebElement waitForElementVisible(WebElement element) {
        return waitForElementVisible(element, defaultTimeout);
    }

    public static WebElement waitForElementVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static WebElement waitForElementVisible(By locator) {

        WebDriverWait wait = new WebDriverWait(getDriver(), defaultTimeout);
        wait.pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getDriver().findElement(locator);
    }

    public static WebElement waitForElementVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return getDriver().findElement(locator);
    }

    public static WebElement waitForElementClickable(WebElement element) {
        return waitForElementClickable(element, defaultTimeout);
    }

    public static WebElement waitForElementClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.pollingEvery(Duration.ofMillis(500))
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public static void waitForElementInvisible(WebElement element) {
        waitForElementInvisible(element, defaultTimeout);
    }

    public static void waitForElementInvisible(WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
            wait.pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e){
            log.debug("Wait for Element Invisible get error " + e.getMessage());
        }
    }

    public static void waitForElementInvisible(By locator, int timeout) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.debug("Wait for Element Invisible get error " + e.getMessage());
        }
    }

    public static List<WebElement> waitForCollectNotEmpty(List<WebElement> elements) {
        return waitForCollectNotEmpty(elements, defaultTimeout);
    }

    public static List<WebElement> waitForCollectNotEmpty(List<WebElement> elements, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.pollingEvery(Duration.ofMillis(500))
                .until(browser -> !elements.isEmpty());
        return elements;
    }

    public static void waitForElementHasValue(WebElement element, int timeout) {

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
            wait.pollingEvery(Duration.ofMillis(500))
                    .until(browser -> !element.getAttribute("value").equals(""));
        } catch (Exception e){
            log.debug("Wait for Element Has Value get error " + e.getMessage());
        }
    }

    public static void waitForLoadingCover(By by, int timeout) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(1))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(TimeoutException.class, NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException | NoSuchElementException e) {
            //Do nothing
        }
        waitForElementInvisible(by, timeout);
    }

    public static void sleepInSeconds(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
