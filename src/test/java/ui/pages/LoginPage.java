package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private Page page;
    private final String signinRoute = "/signin";
    private final String homePageTransactionList = "//div[@data-test='transaction-list']";

    // Locators
    private Locator usernameInput;
    private Locator passwordInput;
    private Locator signButton;  // = login button
    private Locator errorMessage; // = error message in case of invalid login
    private Locator transactionList; // = transaction list after successful login

    // Initialization
    public void init(Page page) {
        this.page = page;

        usernameInput = page.locator("#username");
        passwordInput = page.getByLabel("Password");
        signButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In"));
        errorMessage = page.locator("[data-test='signin-error']");
        transactionList = page.locator(homePageTransactionList);
    }

    public void openLoginPage(String baseUrl) {
        page.navigate(baseUrl + signinRoute);
    }

    public void enterUsername(String username) {
        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickLogin() {
        signButton.click();
    }

    public HomePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();

        page.locator(homePageTransactionList).waitFor();
        HomePage homePage = new HomePage();
        homePage.init(page);

        return homePage;
    }

    public void waitForErrorMessage() {
        errorMessage.waitFor();
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.isVisible();
    }

    public String getErrorMessageText() {
        return errorMessage.innerText();
    }

    public boolean isHomePageVisible() {
        return transactionList.isVisible();
    }
}