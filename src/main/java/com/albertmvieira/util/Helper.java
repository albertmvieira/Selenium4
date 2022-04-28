package com.albertmvieira.util;

import com.albertmvieira.driver.BrowserType;
import io.qameta.allure.Attachment;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper {

    public static boolean isWebDriverManager(){

        if (PropertyReader.getInstance().
                getProperty(PropKey.BROWSER_MANAGER.getPropVal()).
                equalsIgnoreCase("webDriverManager")){

            return true;
        }
        return false;
    }

    /**
     * Method  to obtain app url from porperty file
     * @return
     */
    public static String getAppUrl() {
        String baseUrl = PropertyReader.getInstance().getProperty(PropKey.URL.getPropVal());
        String port = PropertyReader.getInstance().getProperty(PropKey.PORT.getPropVal());
        String finalUrl = baseUrl + port;
        return finalUrl;
    }

    /**
     *
     * @param driver
     * @param name
     * @return
     */
    public static synchronized  boolean takeScreenShot(WebDriver driver, String name){
        boolean isScreenShot = PropertyReader.getInstance()
                .getProperty(PropKey.SCREEN_SHOT.getPropVal())
                .equalsIgnoreCase("ENABLE");

        if(isScreenShot){
            attachScreenShot(driver,name);
            return true;
        }
        return false;
    }

    /**
     * Method to take screenshot
     * @param driver
     * @param name
     * @return
     */
    @Attachment(value = "{name}" , type = "image/png")
    private static synchronized byte[] attachScreenShot(WebDriver driver,
                                                        String name){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     *
     * @param element
     * @param name
     * @return
     */
    public static synchronized  boolean takeElementScreenShot(WebElement element,
                                                              String name){

        String getElementScreenShot = SystemPropertyHelper.getElementScreenShotValue();



        if(getElementScreenShot.equalsIgnoreCase("ENABLE")){
            attachElementScreenShot(element,name);
            return true;
        }
        return false;
    }

    @Attachment(value = "{name}" , type =  "image/png")
    private static synchronized byte[] attachElementScreenShot(WebElement element,
                                                               String name){
        return element.getScreenshotAs(OutputType.BYTES);
    }

    /**
     *
     * @return
     */
    public static boolean isRemote(){
        String isRemote = SystemPropertyHelper.getRemoteVal();

        if(isRemote.equalsIgnoreCase("TRUE")){
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public static String getHubUrl(){

        String hubUrl = SystemPropertyHelper.getHubUrl();
        return hubUrl;
    }

    /**
     * Return the value of browserPath from property file
     * @return
     */
    public static String getBrowserPath(){

        if (SystemUtils.IS_OS_WINDOWS){
            if (SystemPropertyHelper.getBrowserFromSystemVariable() == BrowserType.CHROME){
                return PropertyReader.getInstance().getProperty(PropKey.CHROME_DRIVER_PATH_WIN.getPropVal());
            }else if (SystemPropertyHelper.getBrowserFromSystemVariable() == BrowserType.FIREFOX){
                return PropertyReader.getInstance().getProperty(PropKey.GECKO_DRIVER_PATH_WIN.getPropVal());
            }else if (SystemPropertyHelper.getBrowserFromSystemVariable() == BrowserType.EDGE){
                return PropertyReader.getInstance().getProperty(PropKey.EDGE_DRIVER_PATH_WIN.getPropVal());
            }else {
                return PropertyReader.getInstance().getProperty(PropKey.CHROME_DRIVER_PATH_WIN.getPropVal());
            }
        } else {
            if (SystemPropertyHelper.getBrowserFromSystemVariable() == BrowserType.CHROME){
                return PropertyReader.getInstance().getProperty(PropKey.CHROME_DRIVER_PATH_LINUX.getPropVal());
            }else if (SystemPropertyHelper.getBrowserFromSystemVariable() == BrowserType.FIREFOX){
                return PropertyReader.getInstance().getProperty(PropKey.GECKO_DRIVER_PATH_LINUX.getPropVal());
            }else {
                return PropertyReader.getInstance().getProperty(PropKey.CHROME_DRIVER_PATH_WIN.getPropVal());
            }
        }
    }

}
