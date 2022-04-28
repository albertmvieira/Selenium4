package com.albertmvieira.tests;

import com.albertmvieira.steps.JPetStoreSteps;
import com.albertmvieira.util.ExcelDataReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.albertmvieira.tags.JPetStoreTags.REGRESSION;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestListener.class)
public class LoginDDTest extends JPetStoreSteps {

    static Object[][] getDatadoLogin(){
        return ExcelDataReader.getData(System.getProperty("user.dir") +"\\src\\test\\resources\\data\\LoginDDTest.xlsx",
                "doLogin");
    }
    @Tag(REGRESSION)
    @ParameterizedTest
    @DisplayName("Login to application with multiple users & verify greeting message")
    @MethodSource("getDatadoLogin")
    void doLogin(String userName,String password,String expectedResult){

        navigateToApp();
        navigateToSignOnPage();
        doLogin(userName,password);

        assertEquals(expectedResult ,getGreetingMessage());

    }
}
