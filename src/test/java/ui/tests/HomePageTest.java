package ui.tests;

import framework.config.ConfigManager;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.MyAccountPage;
import ui.pages.NewTransactionPage;
import ui.pages.TransactionDetailPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomePageTest extends BaseTest {

    @Test
    void userCanNavigateToMyAccount() {
        LoginPage loginPage = new LoginPage();
        loginPage.init(page);

        loginPage.openLoginPage(ConfigManager.get("base.url"));

        HomePage homePage = loginPage.login(
                ConfigManager.get("login.username"),
                ConfigManager.get("login.password")
        );

        MyAccountPage myAccountPage = homePage.navigateToMyAccount();

        assertTrue( myAccountPage.isMyAccountPageVisible(),
                "My Account page should be visible after navigation" );
    }

    
        @Test
        void userCanCreateTransactionAndVerifyDetailsInMine() {
        // 1. Login
        LoginPage loginPage = new LoginPage();
        loginPage.init(page);
        loginPage.openLoginPage(ConfigManager.get("base.url"));

        HomePage homePage = loginPage.login(
                ConfigManager.get("login.username"),
                ConfigManager.get("login.password")
        );

        // Datele tranzactiei
        String recipient = "Darrel Ortiz";
        String amount = "25";
        String note = "E2E payment " + System.currentTimeMillis();

        // 2. New
        NewTransactionPage newTransactionPage = homePage.clickNew();

        // 3. Select user
        newTransactionPage.selectUser(recipient);

        // 4. Amount + Note -> Pay
        newTransactionPage.pay(amount, note);

        // 5. Home
        homePage.navigateToHome();

        // 6. Mine
        homePage.selectMineTab();

        // 7-8. Find and open transaction
        TransactionDetailPage details = homePage.openTransaction(note);

        // 9. Validate transaction details
        assertTrue(details.isTransactionDetailVisible());
        assertEquals(recipient, details.getRecipient());
        assertEquals(note, details.getDescription());
        assertEquals("-$" + amount + ".00", details.getAmount());
        }
}