package com.albertmvieira.util;

import io.qameta.allure.Step;

public class LogHelper {

    /**
     * Method to log step information to reports
     * @param message
     * @return
     */
    public static synchronized boolean logToReport(String message){

        boolean isLogEnabled = PropertyReader.getInstance()
                .getProperty(PropKey.ALLURE_REPORT_STEP_LOG
                        .getPropVal()).equalsIgnoreCase("ENABLE");

        if(isLogEnabled){
            logToAllureReport(message);
            return true;
        }
        return false;
    }

    @Step("{0}")
    private static synchronized void logToAllureReport(String message){

    }
}
