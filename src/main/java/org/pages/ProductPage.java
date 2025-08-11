package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    @FindBy(id = "go-checkout")
    private WebElement goToCheckoutLink;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/katalog";
    }

    public ProductPage addProductToCart() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal-backdrop, .loading, .overlay")));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartButton);

        try {
            addToCartButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("arguments[0].click();", addToCartButton);
        }
        return this;
    }

    public ProductPage goToCheckout() {
        clickOnElement(goToCheckoutLink);
        return this;
    }

    public ProductPage selectAnyAvailableSize() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        List<WebElement> sizes = webDriver.findElements(By.cssSelector("#options-combinations-container input[type='radio'][name^='option-combinations']"));
        logger.info("Found " + sizes.size() + " size option(s) on the product page.");
        if (sizes.isEmpty()) {
            logger.warn("No size options found for this product. Skipping size selection.");
            return this;
        }
        for (WebElement size : sizes) {
            try {
                WebElement label = size.findElement(By.xpath("./ancestor::label"));
                if (label.isDisplayed() && label.isEnabled()) {
                    ((org.openqa.selenium.JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center'});", label);
                    wait.until(ExpectedConditions.elementToBeClickable(label));
                    label.click();
                    return this;
                }
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("No available size option could be selected!");
    }
}
