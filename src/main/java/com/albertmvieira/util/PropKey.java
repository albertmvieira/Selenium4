package com.albertmvieira.util;

public enum PropKey {

    URL("url"),
    PORT("port"),
    URLGOOGLE("urlGoogle"),
    BROWSER("browser"),
    BROWSER_MANAGER("browserManager"),

    //DRIVER PATHS
    CHROME_DRIVER_PATH_WIN("chromeDriverPathWin"),
    GECKO_DRIVER_PATH_WIN("geckoDriverPathWin"),
    EDGE_DRIVER_PATH_WIN("edgeDriverPathWin"),

    CHROME_DRIVER_PATH_LINUX("chromeDriverPathLinux"),
    GECKO_DRIVER_PATH_LINUX("geckoDriverPathLinux"),

    //Timeouts
    IMPLICIT_WAIT("implictWait"),
    EXPLICIT_WAIT("explicitWait"),

    //Allure
    ALLURE_REPORT_STEP_LOG("allureReportStepLog"),

    //Screenshot
    SCREEN_SHOT("screenshot"),
    ELEMENT_SCREEN_SHOT("elementScreenShot"),

    //Selenium Grid
    REMOTE("remote"),
    HUB_URL("hubUrl");


    private String propVal;

    PropKey(String propVal) {
        this.propVal = propVal;
    }

    public String getPropVal(){
        return propVal;
    }
}
