package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

import static core.utils.DriverManageUtils.stopDriver;

@CucumberOptions(features="src/test/resources/Features/southLane/Search.feature",
        glue={"stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "json:target/cucumber-reports/cucumber.json"
        })

@Test
public class TestSerialRunner extends AbstractTestNGCucumberTests {

        @AfterSuite
        public void tearDownSuite() {
                stopDriver();
        }
}
