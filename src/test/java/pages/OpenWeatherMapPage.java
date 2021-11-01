package pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static core.utils.DriverManageUtils.getDriver;

public class OpenWeatherMapPage {

    SearchContext context;

    public OpenWeatherMapPage(SearchContext context) {
        this.context = context;
        PageFactory.initElements(new AjaxElementLocatorFactory(context, 30), this);
    }

    public SearchForm getSearchForm() {
        return new SearchForm(getDriver());
    }
}
