package core.abstracts;

import core.utils.DriverManageUtils;
import io.cucumber.datatable.DataTable;
import pages.OpenWeatherMapPage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static core.utils.DriverManageUtils.getDriver;

public abstract class StepAbstract {

    public String host = System.getProperty("host") == null ?
            "https://openweathermap.org" : System.getProperty("host");

    public OpenWeatherMapPage goToHomePage() {
        getDriver().get(host);
        DriverManageUtils.showBrowserConsoleLogs(getDriver());
        return new OpenWeatherMapPage(getDriver());
    }

    public List<String> coverToList(String arg) {
        return Arrays.asList(arg.split(",\\s?"));
    }

    public Map<String, String> convertToMap(DataTable dataTable) {
        return dataTable.asMap(String.class, String.class);
    }
}
