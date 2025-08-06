package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_EMAIL_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTests extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputEmail(VALID_EMAIL_UI)
                .enterTextIntoInputPassword(VALID_PASSWORD_UI)
                .clickOnLoginButton()
                .checkUserIsLoggedIn();
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .loginWithInvalidCred()
                .verifyInvalidMessageDisplayed()
                .verifyTextOfInvalidMessage();
    }
}
