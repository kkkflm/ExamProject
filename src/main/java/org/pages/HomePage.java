package org.pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.ParentPage;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(id = "search-icon")
    private WebElement searchIconButton;

    @FindBy(xpath = "//div[@id='search']//input[@name='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@id='search']//button[@type='button']")
    private WebElement searchButton;

    @FindBy(xpath = "(//div[@class='image']/a/img)[1]")
    private WebElement firstProductImage;

    @FindBy(xpath = "//button[contains(@onclick, 'wishlist.add')]")
    private List<WebElement> wishListButtons;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    public HomePage openHomePage() {
        webDriver.get(baseURL + getRelativeURL());
        logger.info("Home page was opened with url " + baseURL + getRelativeURL());
        return this;
    }

    public HomePage clickOnSearchIcon() {
        clickOnElement(searchIconButton, "'Search icon button'");
        return this;
    }

    public HomePage enterTextIntoSearchInput(String searchText) {
        clearAndEnterTextToElement(searchInput, searchText);
        return this;
    }

    public Search clickOnSearchButton() {
        clickOnElement(searchButton, "'Search button'");
        return new Search(webDriver);
    }

    public HomePage clickOnFirstProductImage() {
        logger.info("Clicking on the first product image");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='image']/a/img)[1]")));

        try {
            ((JavascriptExecutor) webDriver).executeScript(
                    "arguments[0].scrollIntoView({block: 'center'});", firstProductImage);
            new Actions(webDriver).moveToElement(firstProductImage).click().perform();
        } catch (Exception e) {

            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", firstProductImage);
        }
        return this;
    }

    public HomePage clickOnFirstWishListButton() {
        WebElement button = wishListButtons.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Wishlist button not found"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", button);
        return this;
    }
}