package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import static core.utils.DriverManageUtils.stopDriver;

@CucumberOptions(features="src/test/resources/Features/southLane/SearchWithInvalidKey.feature",
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm", "json:target/cucumber-reports/cucumber.json"
        },
        glue={"stepDefinitions"})

public class TestParallelRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

        @AfterSuite
        public void tearDownSuite() {
                stopDriver();
        }
}
