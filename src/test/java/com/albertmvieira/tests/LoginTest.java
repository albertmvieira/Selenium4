package com.albertmvieira.tests;

import com.albertmvieira.steps.JPetStoreSteps;
import io.github.artsok.RepeatedIfExceptionsTest;
import io.qameta.allure.Description;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.albertmvieira.tags.JPetStoreTags.REGRESSION;
import static com.albertmvieira.tags.JPetStoreTags.SMOKE;
import static org.junit.jupiter.api.Assertions.*;

@Tag(REGRESSION)
@ExtendWith(TestListener.class)
@DisplayName("Login Tests")
public class LoginTest extends JPetStoreSteps {

    //@RepeatedIfExceptionsTest(repeats = 3) - When use anotation @RepeatedIfExceptionsTest from rerun junit we cannot use @test anotation together
    @Tag(SMOKE)
    @Test
    @DisplayName("As a valid user, a user must be able to login with valid credentials")
    @Description("Logging into the app via sign on page")
    void doLogin() {
        navigateToApp();
        navigateToSignOnPage();
        doLogin("test", "test");
        assertEquals("Welcome Albert!", getGreetingMessage());

        //Soft Assertion
//        assertAll(
//                () -> assertEquals("Welcome ABC!", getGreetingMessage()),
//                () -> assertEquals("Welcome ABC!", getGreetingMessage())
//        );
    }

    @Disabled
    @Test
    @DisplayName("As a valid user, a user must be able to login with valid credentials")
    @Description("Logging into the app via sign on page")
    void doLogin2() {
        navigateToApp();
        navigateToSignOnPage();
        doLogin("teste", "teste");
    }

    //nested group the tests in a new class
    @Nested
    @DisplayName("Negative Login Tests")
    class NegativeTests{

        @DisplayName("User must not be able to login with invalid credentials")
        @Test
        void invalidLogin(){

            navigateToApp();
            navigateToSignOnPage();
            doLogin("j2ee","j233434");

            assertEquals("Invalid username or password. Signon failed.",
                    getMessageOnInvalidLogin());
        }

    }

}
