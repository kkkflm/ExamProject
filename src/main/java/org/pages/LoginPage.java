package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.data.TestData.VALID_EMAIL_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@type='checkbox' and contains(@onchange,'input-password')]")
    private WebElement showPasswordCheckbox;

    @FindBy(xpath = "//input[@type='submit' and @value='Увійти']")
    public WebElement loginButton;

    @FindBy(xpath = "//a[contains(@href,'forgot-password')]")
    public WebElement forgotPasswordLink;

    @FindBy(xpath = "//h2[text()='Мій обліковий запис']/following-sibling::ul/li/a")
    public WebElement myAccountLink;

    @FindBy(css = ".alert.alert-danger")
    private WebElement invalidMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "login/";
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseURL + getRelativeURL());
        logger.info("Login page was opened with url " + baseURL + getRelativeURL());
        return this;
    }

    public LoginPage enterTextIntoInputEmail(String email) {
        clearAndEnterTextToElement(inputEmail, email);
        return this;
    }

    public LoginPage enterTextIntoInputPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        clickOnElement(loginButton);
        return new LoginPage(webDriver);
    }

    public LoginPage loginWithInvalidCred() {
        openLoginPage();
        enterTextIntoInputEmail(VALID_EMAIL_UI);
        enterTextIntoInputPassword("invalidPassword");
        clickOnLoginButton();
        return new LoginPage(webDriver);
    }

    public LoginPage verifyInvalidMessageDisplayed() {
        checkIsElementDisplayed(invalidMessage);
        return this;
    }

    public LoginPage verifyTextOfInvalidMessage() {
        checkTextInElement(invalidMessage, "Не знайдена адреса E-Mail або невірно вказаний пароль");
        return this;
    }

    public void checkUserIsLoggedIn() {
        checkIsElementDisplayed(myAccountLink);
    }

    public LoginPage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputEmail(VALID_EMAIL_UI);
        this.enterTextIntoInputPassword(VALID_PASSWORD_UI);
        clickOnLoginButton();
        return new LoginPage(webDriver);
    }
}
