package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("ekaterina.kuchmenko@gmail.com",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "WZYrx4vQuW";
}
