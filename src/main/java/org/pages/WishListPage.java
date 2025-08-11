package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishListPage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(css = "div.cart-product")
    private List<WebElement> productsInWishList;

    public WishListPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "wishlist/";
    }

    public WishListPage openWishListPage() {
        webDriver.get(baseURL + getRelativeURL());
        logger.info("Wishlist page was opened with url " + baseURL + getRelativeURL());
        return this;
    }

    public void checkThatWishListHasProduct() {
        boolean visibleFound = productsInWishList.stream()
                .anyMatch(WebElement::isDisplayed);
        Assert.assertTrue("None of the items in my wishlist are showing up!", visibleFound);
    }
}
