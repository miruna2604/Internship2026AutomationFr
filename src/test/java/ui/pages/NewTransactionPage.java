package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class NewTransactionPage {

    private Page page;
    private Locator userListItems;
    private Locator amountInput;
    private Locator noteInput;
    private Locator payButton;

    public void init(Page page) {
        this.page = page;
        userListItems = page.locator("[data-test^='user-list-item-']");
        amountInput = page.locator("#amount");
        noteInput = page.locator("#transaction-create-description-input");
        payButton = page.locator("[data-test='transaction-create-submit-payment']");
    }

    public void selectUser(String recipient) {
        userListItems
                .filter(new Locator.FilterOptions().setHasText(recipient))
                .click();
        amountInput.waitFor();
    }

    public void pay(String amount, String note) {
        amountInput.fill(amount);
        noteInput.fill(note);
        payButton.click();
        page.getByText("Complete", new Page.GetByTextOptions().setExact(true)).waitFor();
    }
}