package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductsPage extends BasePage {

    private final Locator addBackpackButton;
    private final Locator shoppingCartLink;
    private final Locator inventoryTitle;

    public ProductsPage(Page page) {
        super(page);

        this.addBackpackButton = page.locator("#add-to-cart-sauce-labs-backpack");
        this.shoppingCartLink = page.locator(".shopping_cart_link");
        this.inventoryTitle = page.locator(".title");
    }

    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public void openCart() {
        click(shoppingCartLink);
    }

    public String getInventoryTitle() {
        return getText(inventoryTitle);
    }
}