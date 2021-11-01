package stepDefinitions;

import core.utils.Screenshots;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static core.utils.DriverManageUtils.*;

public class ServiceHook {

    @Before
    public void setUp() {
        setWebDriver(initDriver());
    }

    @After(order = 1)
    public void endTest(Scenario scenario) {
        if (scenario.isFailed()) {
            Screenshots.takeFailureScreenshot(getDriver(), scenario.getName());
            stopDriver();
            return;
        }

        if (Boolean.getBoolean("enableTakeScreenShot")) {
            Screenshots.takeScreenshot(getDriver(), scenario.getName(), getClass());
        }

        stopDriver();
    }
}
