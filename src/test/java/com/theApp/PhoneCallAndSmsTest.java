package com.theApp;

import com.mobile.core.base.TestUtilities;
import com.mobile.pages.LoginPage;
import com.mobile.pages.PhoneNumberVerificationPage;
import com.mobile.pages.WelcomePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class PhoneCallAndSmsTest extends TestUtilities {

    private static final String phoneNumber = "5551237890";

    @Test
    public void phoneCallAndSmsTestScenario() {
        //*********Sleeps are added to visualize execution flow*************
        WelcomePage welcomePage = new WelcomePage (getAppiumDriver (), log);
        welcomePage.goToLoginScreen ();
        makePhoneCall (phoneNumber, GsmCallActions.CALL);
        sleep (2000);
        makePhoneCall (phoneNumber, GsmCallActions.ACCEPT);
        LoginPage loginPage = new LoginPage (getAppiumDriver (), log);
        loginPage.enterUsername ("Nadeera");
        sleep (2000);
        makePhoneCall (phoneNumber, GsmCallActions.CANCEL);
        sleep (2000);
        loginPage.navigateToWelcomePage ();
        welcomePage.goToVerifyPhoneNumber ();
        PhoneNumberVerificationPage phoneNumberVerificationPage = new PhoneNumberVerificationPage (getAppiumDriver (), log);
        assert (phoneNumberVerificationPage.getWaitingLabelText ().contains ("Waiting to receive"));
        sleep (2000);
        sendSMS (phoneNumber, "Your code is 123456");
        sleep (2000);
        assert (phoneNumberVerificationPage.getVerifiedLabelText ().contains ("verified"));
    }
}
