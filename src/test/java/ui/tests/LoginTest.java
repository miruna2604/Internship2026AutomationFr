package ui.tests;

import framework.config.ConfigManager;
import org.junit.jupiter.api.Test;
import ui.base.BaseTest;
import ui.pages.HomePage;
import ui.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

        @Test // presentation ex 1 - login
        void userCanLoginSuccessfully() {
                LoginPage loginPage = new LoginPage();
                loginPage.init(page);

                loginPage.openLoginPage(ConfigManager.get("base.url"));

                HomePage homePage = loginPage.login(
                                ConfigManager.get("login.username"),
                                ConfigManager.get("login.password"));

                assertTrue(homePage.isHomePageVisible(),
                                "Transaction list should be visible after successful login");

                assertTrue(homePage.isLogoutButtonVisible(),
                                "Logout button should be visible after successful login");
        }

        @Test // presentation ex 2 - negative login
        void userCannotLoginWithInvalidCredentials() {
                LoginPage loginPage = new LoginPage();
                loginPage.init(page);
                loginPage.openLoginPage(ConfigManager.get("base.url"));
                loginPage.enterUsername("invalid-user");
                loginPage.enterPassword("invalid-pas");
                loginPage.clickLogin();
                loginPage.waitForErrorMessage();
                assertTrue(loginPage.isErrorMessageVisible(),
                                "Error message should be visible after failed login attempt");
                assertEquals("Username or password is invalid", loginPage.getErrorMessageText(),
                                "Error message text should match expected value");
                assertFalse(loginPage.isHomePageVisible(),
                                "User should not be redirected to the home page");
        }
}