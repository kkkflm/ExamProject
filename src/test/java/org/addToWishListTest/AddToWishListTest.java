package org.addToWishListTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class AddToWishListTest extends BaseTest {

    @Test
    public void addToWishList() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage()
                .openHomePage()
                .clickOnFirstWishListButton();
        pageProvider.getWishListPage()
                .openWishListPage()
                .checkThatWishListHasProduct();
    }
}
