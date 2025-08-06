package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.ParentPage;

import java.time.Duration;
import java.util.List;

public class Search extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(id = "search-icon")
    public WebElement searchIconButton;

    @FindBy(xpath = "//div[@id='search']//input[@name='search']")
    public WebElement searchInput;

    @FindBy(xpath = "//div[@id='search']//button[@type='button']")
    public WebElement searchButton;

    @FindBy(xpath = "//a[contains(@class,'product-name')]")
    private List<WebElement> productNames;

    public Search(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    public Search openHomePage() {
        webDriver.get(baseURL + getRelativeURL());
        logger.info("Home page was opened with url " + baseURL + getRelativeURL());
        return this;
    }

    public Search clickOnSearchIcon() {
        clickOnElement(searchIconButton, "'Search icon button'");
        return this;
    }

    public Search enterTextIntoSearchInput(String searchText) {
        clearAndEnterTextToElement(searchInput, searchText);
        return this;
    }

    public Search clickOnSearchButton() {
        clickOnElement(searchButton, "'Search button'");
        return this;
    }

    public Search checkSearchResultsDisplayed() {
        checkTextInElement(searchInput, "Сукня");
        return this;
    }

    public Search checkSearchResultsDisplayed(String query) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(productNames));

        Assert.assertFalse("Результатів пошуку немає!", productNames.isEmpty());
        int count = 0;
        for (WebElement product : productNames) {
            String productName = product.getText().trim();
            logger.info("Знайдено товар: " + productName);
            Assert.assertTrue(
                    "Назва товару не містить слово '" + query + "': " + productName,
                    productName.toLowerCase().contains(query.toLowerCase())
            );
            count++;
        }
        logger.info("Загальна кількість знайдених товарів зі словом '" + query + "': " + count);
        return this;
    }
}
