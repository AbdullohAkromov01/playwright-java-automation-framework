package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage extends BasePage {

    private final Locator backpackItem;
    private final Locator removeBackpackButton;
    private final Locator checkoutButton;

    public CartPage(Page page) {
        super(page);

        this.backpackItem = page.locator("#item_4_title_link");
        this.removeBackpackButton = page.locator("#remove-sauce-labs-backpack");
        this.checkoutButton = page.locator("#checkout");
    }

    public String getProductName() {
        return getText(backpackItem);
    }

    public void removeBackpack() {
        click(removeBackpackButton);
    }

    public void proceedToCheckout() {
        click(checkoutButton);
    }
}