package com.albertmvieira.driver;

import com.albertmvieira.util.Helper;
import com.albertmvieira.util.PropKey;
import com.albertmvieira.util.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static com.albertmvieira.util.Helper.isWebDriverManager;

public enum BrowserType implements DriverSetup {

    CHROME {
        @Override
        public Capabilities getBrowserCapabilities() {
            Capabilities capabilities = getBrowserOptions();
            return capabilities;
        }

        @Override
        public ChromeOptions getBrowserOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.setAcceptInsecureCerts(true);

            return chromeOptions;
        }

        @Override
        public WebDriver getWebDriver() {

            String browserPath = Helper.getBrowserPath();

            if (isWebDriverManager()){
                WebDriverManager.chromedriver().setup();
            } else{
                System.setProperty("webdriver.chrome.driver", browserPath);
            }
            ChromeOptions chromeOptions = getBrowserOptions();
            return new ChromeDriver(chromeOptions);
        }
    },
    FIREFOX {
        @Override
        public Capabilities getBrowserCapabilities() {
            Capabilities capabilities = getBrowserOptions();
            return  capabilities;
        }

        @Override
        public FirefoxOptions getBrowserOptions() {

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();

            //Accept Untrusted Certificates
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);

            //set Firefox profile to capabilities
            firefoxOptions.setProfile(profile);

            return firefoxOptions;
        }

        @Override
        public WebDriver getWebDriver() {

            String browserPath = Helper.getBrowserPath();

            if (isWebDriverManager()){
                WebDriverManager.firefoxdriver().setup();
            } else{
                System.setProperty("webdriver.gecko.driver", browserPath);
            }
            FirefoxOptions firefoxOptions = getBrowserOptions();
            return new FirefoxDriver(firefoxOptions);
        }
    },
    EDGE {
        @Override
        public Capabilities getBrowserCapabilities() {

            Capabilities capabilities = getBrowserOptions();
            return capabilities;
        }

        @Override
        public EdgeOptions getBrowserOptions() {

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("--start-maximized");
            return edgeOptions;
        }

        @Override
        public WebDriver getWebDriver() {

            String browserPath = Helper.getBrowserPath();

            if (isWebDriverManager()) {
                WebDriverManager.edgedriver().setup();
            } else {
                System.setProperty("webdriver.edge.driver", browserPath);
            }
            EdgeOptions edgeOptions = getBrowserOptions();
            return new EdgeDriver(edgeOptions);
        }
    }
};

