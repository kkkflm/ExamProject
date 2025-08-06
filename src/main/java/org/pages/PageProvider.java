package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.Search;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {this.webDriver = webDriver;}

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public Search getHomePage() { return new Search(webDriver);
    }
}
