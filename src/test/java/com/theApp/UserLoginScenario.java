package com.theApp;

import com.mobile.core.base.BaseTest;
import com.mobile.core.base.TestUtilities;
import com.mobile.pages.LoginPage;
import com.mobile.pages.WelcomePage;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class UserLoginScenario extends TestUtilities {

    @Test
    public void UserLoginScenario(){
        WelcomePage welcomePage = new WelcomePage (getAppiumDriver (), log);
        welcomePage
                .goToLoginScreen ();
        LoginPage loginPage = new LoginPage (getAppiumDriver (), log);
        loginPage
                .enterUsername ("alice")
                .enterPassword ("mypassword")
                .clickOnLoginButton ();

        assert (loginPage.getConfirmationText ().contains ("alice"));
    }
}
