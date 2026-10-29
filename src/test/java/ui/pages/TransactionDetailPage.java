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
    private Locator commentInput;
    private Locator commentItems;

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
        commentInput = page.locator(
                "[data-test^='transaction-comment-input-']"
        );
        commentItems = page.locator(
                "[data-test^='comment-list-item-']"
        );
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

    public void addComment(String comment) {
        commentInput.fill(comment);
        commentInput.press("Enter");
        commentItems
                .filter(new Locator.FilterOptions().setHasText(comment))
                .waitFor();
    }

    public boolean hasComment(String comment) {
        return commentItems
                .filter(new Locator.FilterOptions().setHasText(comment))
                .isVisible();
    }
}