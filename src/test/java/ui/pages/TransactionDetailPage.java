package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TransactionDetailPage {

    private Locator detailHeader;
    private Locator transactionRecord;
    private Locator sender;
    private Locator recipient;
    private Locator description;
    private Locator amount;

    public void init(Page page) {
        detailHeader = page.locator(
                "[data-test='transaction-detail-header']"
        );
        transactionRecord = page.locator(
                "[data-test^='transaction-item-']"
        );
        sender = page.locator(
                "[data-test^='transaction-sender-']:not([data-test='transaction-sender-avatar'])"
        );
        recipient = page.locator(
                "[data-test^='transaction-receiver-']:not([data-test='transaction-receiver-avatar'])"
        );
        description = page.locator("[data-test='transaction-description']");
        amount = page.locator("[data-test^='transaction-amount-']");
    }

    public boolean isTransactionDetailVisible() {
        return detailHeader.isVisible() && transactionRecord.isVisible();
    }

    public String getSender() {
        return sender.innerText();
    }

    public String getRecipient() {
        return recipient.innerText();
    }

    public String getDescription() {
        return description.innerText();
    }

    public String getAmount() {
        return amount.innerText();
    }
}