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

    @FindBy(xpath = "//a[contains(@class,'product-name')]")
    private List<WebElement> productNames;

    public Search(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/search";
    }

    public Search checkSearchResultsDisplayed(String query) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(productNames));

        Assert.assertFalse("No search results found!", productNames.isEmpty());
        int count = 0;
        for (WebElement product : productNames) {
            String productName = product.getText().trim();
            logger.info("Product found: " + productName);
            Assert.assertTrue(
                    "The product name does not contain the word '" + query + "': " + productName,
                    productName.toLowerCase().contains(query.toLowerCase())
            );
            count++;
        }
        logger.info("Total number of items found with the word '" + query + "': " + count);
        return this;
    }
}
