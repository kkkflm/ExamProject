package org.addToCartTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCart() {
        pageProvider.getHomePage()
                .openHomePage()
                .clickOnFirstProductImage();
        pageProvider.getProductPage()
                .selectAnyAvailableSize()
                .addProductToCart()
                .goToCheckout();
        pageProvider.getCheckoutPage()
                .checkIsRedirectedToCheckoutPage()
                .checkProductInCart();
    }
}
