package com.albertmvieira.driver;

import com.albertmvieira.util.PropKey;
import com.albertmvieira.util.PropertyReader;
import com.albertmvieira.util.SystemPropertyHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.albertmvieira.util.Helper.isRemote;
import static com.albertmvieira.util.Helper.getHubUrl;
import static com.albertmvieira.util.TimeUtil.getImplicitWait;

public class DriverFactory {

    public static PropertyReader prop;
    protected static WebDriver driver = null;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Method to get WebDriver
     * @return
     */
    public static WebDriver getDriver(){
        if(driver == null) {

            if (isRemote()) {
                try {
                    driverThreadLocal.set(new RemoteWebDriver(new URL(getHubUrl()),
                            getBrowser().getBrowserCapabilities()));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                driverThreadLocal.set(getBrowser().getWebDriver());
            }
        }
        driverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(getImplicitWait()));
        return driverThreadLocal.get();
    }

    /**
     * Method to quit WebDriver
     */
    public static void quitDriver(){
        driverThreadLocal.get().quit();
    }


    /**
     * Determine browser
     * @return
     */
    private static BrowserType getBrowser(){

        BrowserType browserType = SystemPropertyHelper.getBrowserFromSystemVariable();
        if (browserType != null) {
            return browserType;
        }

        if(prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("CHROME")){
            return BrowserType.CHROME;
        }else if(prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("FIREFOX")) {
            return BrowserType.FIREFOX;
        }else if(prop.getProperty(PropKey.BROWSER.getPropVal()).equalsIgnoreCase("EDGE")){
            return BrowserType.EDGE;
        }else {
            return BrowserType.CHROME;
        }
    }

}
