package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigReader;

public class E2ETests extends BaseTest {

    @Test
    public void completeOrderFlowTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        loginPage.login(
                ConfigReader.getProperty("standard.username"),
                ConfigReader.getProperty("password")
        );

        ProductsPage productsPage = new ProductsPage(page);

        Assert.assertEquals(
                productsPage.getInventoryTitle(),
                "Products"
        );

        productsPage.addBackpackToCart();
        productsPage.openCart();

        CartPage cartPage = new CartPage(page);

        Assert.assertEquals(
                cartPage.getProductName(),
                "Sauce Labs Backpack"
        );

        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(page);

        checkoutPage.fillCheckoutInformation(
                "Abdulloh",
                "Akromov",
                "100000"
        );

        checkoutPage.clickFinish();

        Assert.assertEquals(
                checkoutPage.getSuccessMessage(),
                "Thank you for your order!"
        );
    }
}