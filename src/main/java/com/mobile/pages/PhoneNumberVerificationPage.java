package com.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


public class PhoneNumberVerificationPage extends BasePageObject {

    private By smsWaitingLabelText = MobileBy.AccessibilityId ("waiting");
    private By smsVerifiedLabelText = MobileBy.xpath ("//android.widget.TextView[contains(@text, 'verified')]");

    public PhoneNumberVerificationPage(AppiumDriver driver, Logger log) {
        super (driver, log);
    }

    public String getWaitingLabelText() {
        waitForVisibilityOf (smsWaitingLabelText,10);
        return find (smsWaitingLabelText).getText ();
    }

    public String getVerifiedLabelText(){
        waitForVisibilityOf (smsVerifiedLabelText,10);
        return find (smsVerifiedLabelText).getText ();
    }

}
