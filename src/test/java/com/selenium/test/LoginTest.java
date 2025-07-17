package com.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.selenium.test.base.TestBase;
import com.selenium.test.pages.LoginPage;

public class LoginTest extends TestBase {
    
    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");
        
        Assert.assertTrue(loginPage.isDashboardDisplayed(), "Dashboard should be displayed after successful login");
        Assert.assertEquals(loginPage.getDashboardHeaderText().trim(), "Dashboard", "Dashboard header should be visible");
    }
    
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("invalid_user", "invalid_password");
        
        Assert.assertTrue(loginPage.isAlertMessageDisplayed(), "Error message should be displayed for invalid login");
        Assert.assertTrue(loginPage.getAlertMessageText().contains("Invalid credentials"),
            "Error message should indicate invalid credentials");
    }
    
    @Test
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("", "");
        
        // This assertion will fail, triggering screenshot capture
        Assert.assertTrue(loginPage.isDashboardDisplayed(), 
            "Dashboard should not be displayed with empty credentials");
    }
}