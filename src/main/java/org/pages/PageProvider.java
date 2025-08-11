package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HomePage;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(webDriver);
    }

    public CheckoutPage getCheckoutPage() {
        return new CheckoutPage(webDriver);
    }

    public WishListPage getWishListPage() {
        return new WishListPage(webDriver);
    }
}
