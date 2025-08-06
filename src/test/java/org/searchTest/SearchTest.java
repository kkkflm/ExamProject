package org.searchTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchTest() {
        pageProvider.getHomePage()
                .openHomePage()
                .clickOnSearchIcon()
                .enterTextIntoSearchInput("Сукня")
                .clickOnSearchButton()
                .checkSearchResultsDisplayed("Сукня");
    }
}