package com.albertmvieira.tests;

import com.albertmvieira.util.PropKey;
import com.albertmvieira.util.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase_old {

    static WebDriver driver;
    static PropertyReader prop = PropertyReader.getInstance();

    @BeforeAll
    public static void init() {

        if (prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("FIREFOX")) {
            if (prop.getProperty(PropKey.BROWSER_MANAGER.getPropVal()).equalsIgnoreCase("webDriverManager")){
                WebDriverManager.firefoxdriver().setup();
            } else {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
            }
            driver = new FirefoxDriver();
        } else if (prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("CHROME")) {
            if (prop.getProperty(PropKey.BROWSER_MANAGER.getPropVal()).equalsIgnoreCase("webDriverManager")) {
                WebDriverManager.chromedriver().setup();
            } else{
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
            }
            driver = new ChromeDriver();
        } else if (prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("EDGE")) {
            if (prop.getProperty(PropKey.BROWSER_MANAGER.getPropVal()).equalsIgnoreCase("webDriverManager")) {
                WebDriverManager.edgedriver().setup();
            } else {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\msedgedriver.exe");
            }
            driver = new EdgeDriver();
        }
    }

    @AfterAll
    public static void cleanUp(){
        driver.quit();
    }
}
