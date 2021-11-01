package core.utils;

import com.google.common.io.Files;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshots {

    private static final File MAVEN_PROJECT_BUILD_DIRECTORY =
            new File(System.getProperty("maven.project.build.directory", "./target/"));
    private static final File SCREENSHOTS_OUTPUT_DIR = new File(MAVEN_PROJECT_BUILD_DIRECTORY, "screenshots");
    private static final File FAILURES_OUTPUT_DIR = new File(MAVEN_PROJECT_BUILD_DIRECTORY, "failures");

    public static void takeScreenshot(WebDriver driver, String screenshotName, Class<?> testClass) {
        File imageOutputFile = new File(SCREENSHOTS_OUTPUT_DIR,
                testClass.getSimpleName() + "/" + screenshotName + "-screenshot.png");
        takeScreenshot(driver, imageOutputFile);
    }

    public static void takeFailureScreenshot(WebDriver driver, String folderName) {
        File imageOutputFile = new File(FAILURES_OUTPUT_DIR, folderName + "-screenshot.png");
        takeScreenshot(driver, imageOutputFile);
    }

    private static void takeScreenshot(WebDriver driver, File imageOutputFile) {
        try {
            File directory = imageOutputFile.getParentFile();
            FileUtils.forceMkdir(directory);

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, imageOutputFile);
            //attach to allure report
            byte[] bytes = Files.toByteArray(imageOutputFile);
            Allure.addByteAttachmentAsync(imageOutputFile.getName(), "image/png", () -> bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
