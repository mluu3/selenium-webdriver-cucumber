package pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static core.utils.WaitUtils.waitForElementVisible;
import static org.openqa.selenium.By.*;

public class FindPage {

    @FindBy(id = "searchform")
    SearchForm searchForm;

    @FindBy(id = "forecast-list")
    WebElement forecastList;

    @FindBy(css = "#forecast_list_ul table tr")
    List<WebElement> tableRecords;

    SearchContext context;

    public FindPage(SearchContext context) {
        this.context = context.findElement(xpath("//main[.//div[contains(@class, 'jumbotron-green')]]"));
        PageFactory.initElements(new AjaxElementLocatorFactory(this.context, 30), this);
    }

    /**
        Just want to show lambda
        Easy to get record by xpath locator "String.format("//table//a[contains(text(), '%s')]", title);
    **/
    public Record getRecordByTitle(String title) {
        return new Record(tableRecords.stream()
            .filter(e -> e.findElement(cssSelector("b a")).getText().contains(title))
            .findAny()
            .get());
    }

    //Inner class
    public static class Record {

        @FindBy(css = "td:last-child")
        WebElement detailInformation;

        SearchContext context;

        public Record(SearchContext context) {
            this.context = context;
            PageFactory.initElements(new AjaxElementLocatorFactory(context, 30), this);
        }

        public void clickOnLocation() {
            waitForElementVisible(detailInformation.findElement(cssSelector("b a"))).click();
        }
    }
}
