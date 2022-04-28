package com.albertmvieira.tests;

import com.albertmvieira.driver.DriverManager;
import com.albertmvieira.util.PropKey;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.albertmvieira.util.PropKey.PORT;
import static com.albertmvieira.util.PropKey.URL;

@DisplayName("Browser Tests")
public class FirstTest extends DriverManager {

    @Disabled
    @Test
    @DisplayName("My First Selenium Test!!!")
    void my_First_Selenium_Test() throws InterruptedException {

        String url = prop.getProperty(URL.getPropVal());
        String port = prop.getProperty(PORT.getPropVal());
        String finalUrl = url + port;

        //driver.get(prop.getProperty(PropKey.URLGOOGLE.getPropVal()));
        driver.get(finalUrl);
        Thread.sleep(4000);
    }
}
