package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage extends BasePage {

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator postalCodeInput;
    private final Locator continueButton;

    private final Locator finishButton;
    private final Locator successMessage;

    public CheckoutPage(Page page) {
        super(page);

        this.firstNameInput = page.locator("#first-name");
        this.lastNameInput = page.locator("#last-name");
        this.postalCodeInput = page.locator("#postal-code");
        this.continueButton = page.locator("#continue");

        this.finishButton = page.locator("#finish");
        this.successMessage = page.locator(".complete-header");
    }

    public void enterFirstName(String firstName) {
        fill(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        fill(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        fill(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public void fillCheckoutInformation(
            String firstName,
            String lastName,
            String postalCode) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        clickContinue();
    }
}