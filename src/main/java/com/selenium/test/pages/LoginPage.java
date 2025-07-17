package com.selenium.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.selenium.test.pages.base.BasePage;

public class LoginPage extends BasePage {
    
    @FindBy(name = "username")
    private WebElement usernameInput;
    
    @FindBy(name = "password")
    private WebElement passwordInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;
    
    @FindBy(css = ".oxd-text.oxd-text--h6")
    private WebElement dashboardHeader;
    
    @FindBy(css = ".oxd-alert-content-text")
    private WebElement alertMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    
    public void enterUsername(String username) {
        waitForElementToBeVisible(usernameInput);
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        waitForElementToBeVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    
    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    public boolean isDashboardDisplayed() {
        try {
            waitForElementToBeVisible(dashboardHeader);
            return dashboardHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getDashboardHeaderText() {
        waitForElementToBeVisible(dashboardHeader);
        return dashboardHeader.getText();
    }
    
    public boolean isAlertMessageDisplayed() {
        try {
            waitForElementToBeVisible(alertMessage);
            return alertMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getAlertMessageText() {
        waitForElementToBeVisible(alertMessage);
        return alertMessage.getText();
    }
}