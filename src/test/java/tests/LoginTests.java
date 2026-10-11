package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTests extends BaseTest {

    @Test
    public void successfulLoginTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        loginPage.login(
                ConfigReader.getProperty("standard.username"),
                ConfigReader.getProperty("password")
        );

        Assert.assertTrue(
                page.url().contains("inventory"),
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