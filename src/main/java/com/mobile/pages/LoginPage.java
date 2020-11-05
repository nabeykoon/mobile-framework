package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePageObject {

    public LoginPage(AppiumDriver driver, Logger log) {
        super (driver, log);
    }

    private By usernameLocator = MobileBy.AccessibilityId ("username");
    private By passwordLocator = MobileBy.AccessibilityId ("password");
    private By loginBtnLocator = MobileBy.AccessibilityId ("loginBtn");
    private By confirmTextLocator = MobileBy.xpath ("//android.widget.TextView[contains(@text, 'You are logged in')]");

    public LoginPage enterUsername(String usernameText){
        waitForVisibilityOf (usernameLocator, 10);
        WebElement username = find(usernameLocator);
        username.sendKeys (usernameText);
        return this;
    }

    public LoginPage enterPassword(String passwordText){
        waitForVisibilityOf (passwordLocator, 10);
        WebElement password = find (passwordLocator);
        password.sendKeys (passwordText);
        return this;
    }

    public void clickOnLoginButton(){
        waitForVisibilityOf (loginBtnLocator, 10);
        WebElement loginBtn = find (loginBtnLocator);
        loginBtn.click ();
    }

    public String getConfirmationText(){
        waitForVisibilityOf (confirmTextLocator, 10);
        WebElement confirmationText = find (confirmTextLocator);
        return confirmationText.getText ();
    }


}
