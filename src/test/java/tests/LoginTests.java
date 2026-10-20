package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTests extends BaseTest {

    @Test
    @Epic("Authentication")
    @Feature("Login")
    @Story("Valid Login")
    @Description("Verify that a standard user successfully login to the application")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Abdulloh")
    public void successfulLoginTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        Allure.step("Logging in with standard user");

        loginPage.login(
                ConfigReader.getProperty("standard.username"),
                ConfigReader.getProperty("password")
        );

        Allure.step("Verifying inventory page");

        Assert.assertTrue(
                page.url().contains("SomethingWrong"),
                "User should be redirected to inventory page."
        );
    }

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        loginPage.login(
                "wrongUser",
                "wrongPassword"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage()
                        .contains("Username and password do not match"),
                "Expected error message was not displayed."
        );
    }
}