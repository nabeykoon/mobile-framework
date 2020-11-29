package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebViewPage extends BasePageObject {

    private By urlTextFieldLocator = MobileBy.AccessibilityId ("urlInput");
    private By goButtonLocator = MobileBy.AccessibilityId ("navigateBtn");
    private By bodyTextLocator = By.cssSelector ("body");


    public WebViewPage(AppiumDriver driver, Logger log) {
        super (driver, log);
    }


    public WebViewPage enterUrl(String url) {
        waitForVisibilityOf (urlTextFieldLocator, 10);
        WebElement urlTextField = find (urlTextFieldLocator);
        urlTextField.sendKeys (url);
        return this;
    }

    public WebViewPage clickGoButton() {
        WebElement goButton = find (goButtonLocator);
        goButton.click ();
        return this;
    }

    public String getBodyText() {
        String bodyText = find (bodyTextLocator).getText ();
        return bodyText;
    }

    /**
     * Switch to alert and get it's message
     *
     * @return
     */
    public String getAlertText() {
        Alert alert = switchToAlert ();
        String alertText = alert.getText ();
        return alertText;
    }

    /**
     * Switch to alert and press OK
     */
    public void acceptAlert() {
        log.info ("Switching to alert and pressing OK");
        Alert alert = switchToAlert ();
        alert.accept ();
    }

}
