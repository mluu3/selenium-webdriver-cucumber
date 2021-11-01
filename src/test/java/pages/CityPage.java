package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static core.utils.WaitUtils.waitForElementVisible;

public class CityPage {

    @FindBy(css = "#weather-widget .orange-text")
    WebElement date;

    @FindBy(xpath = "//div[@id='weather-widget']//h2")
    WebElement city;

    @FindBy(css = ".current-temp .heading")
    WebElement celsius;

    SearchContext context;

    public CityPage(SearchContext context) {
        this.context = context.findElement(By.cssSelector("#weather-widget.section"));
        PageFactory.initElements(new AjaxElementLocatorFactory(this.context, 30), this);
    }

    public String getDate() {
        return waitForElementVisible(date).getText().split(",")[0];
    }

    public String getCity() {
        return waitForElementVisible(city).getText();
    }

    public String getCelsius() {
        return waitForElementVisible(celsius).getText();
    }
}
