package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends ParentPage{
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//h1[@class='opc-h1']")
    private WebElement checkoutTitle;

    @FindBy(css = "div.cart-item")
    private WebElement cartItem;

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "onepcheckout/";
    }

    public CheckoutPage checkIsRedirectedToCheckoutPage() {
       checkTextInElement(checkoutTitle, "Оформлення замовлення");
        return this;
    }

    public void checkProductInCart() {
        checkIsElementDisplayed(cartItem);
    }
}
