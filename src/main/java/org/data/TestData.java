package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_EMAIL_UI = System.getProperty("defaultEmail",
            ConfigProvider.configHiddenProperties.email());
    public final static String VALID_PASSWORD_UI = "WZYrx4vQuW";
}
