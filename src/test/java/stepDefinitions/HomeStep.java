package stepDefinitions;

import core.abstracts.StepAbstract;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CityPage;
import pages.FindPage;
import pages.OpenWeatherMapPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static core.utils.DriverManageUtils.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class HomeStep extends StepAbstract {

    @Given("go to home page")
    public void goToHome() {
        goToHomePage();
    }

    @When("searching for {string}")
    public void searchingFor(String keySearch) {
        new OpenWeatherMapPage(getDriver()).getSearchForm().search(keySearch);
    }

    @And("click on the link of result search of {string}")
    public void clickOnTheLinkInResultSearchOf(String keySearch) {
        new FindPage(getDriver()).getRecordByTitle(keySearch).clickOnLocation();
    }

    @Then("verify the current date display correct")
    public void verifyTheCurrentDateDisplayCorrect() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d", Locale.US);
        assertEquals(new CityPage(getDriver()).getDate(), LocalDate.now().format(formatter));
    }

    @And("verify city name is {string}")
    public void verifyCityNameIs(String city) {
        assertThat(new CityPage(getDriver()).getCity()).hasToString(city);
    }

    @And("verify the weather display correct")
    public void verifyTheWeatherDisplayCorrect() {
        assertThat(new CityPage(getDriver()).getCelsius()).matches("\\d+°C");
    }

    //Duplicate by just show scenario outline
    @When("^searching for ([^\"]*)$")
    public void searchingForKey(String keySearch) {
        new OpenWeatherMapPage(getDriver()).getSearchForm().search(keySearch);
    }

    @And("^click on the link of result search of ([^\"]*)$")
    public void clickOnTheLinkOfResultSearchOfKey(String keySearch) {
        new FindPage(getDriver()).getRecordByTitle(keySearch).clickOnLocation();
    }

    @And("^verify city name is ([^\"]*)$")
    public void verifyCityNameIsCity(String city) {
        assertThat(new CityPage(getDriver()).getCity()).hasToString(city);
    }
}
