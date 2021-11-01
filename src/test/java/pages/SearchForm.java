package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static core.utils.WaitUtils.*;

public class SearchForm {

    @FindBy(css = "input[type='text'], #search_str")
    WebElement searchInput;

    SearchContext context;

    public SearchForm(SearchContext context) {
        this.context = context.findElement(By.cssSelector("form[role='search']"));
        PageFactory.initElements(new AjaxElementLocatorFactory(context, 30), this);
    }

    public void search(String city) {
        waitForElementVisible(searchInput).clear();
        searchInput.sendKeys(city);
        waitForElementClickable(searchInput).submit();
    }
}
