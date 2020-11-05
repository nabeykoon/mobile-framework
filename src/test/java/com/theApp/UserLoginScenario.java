package com.theApp;

import com.mobile.core.base.BaseTest;
import com.mobile.pages.LoginPage;
import com.mobile.pages.WelcomePage;
import org.testng.annotations.Test;

import java.util.List;


public class UserLoginScenario extends BaseTest {

    @Test
    public void UserLoginScenario(){
        WelcomePage welcomePage = new WelcomePage (getDriver (), log);
        welcomePage
                .goToLoginScreen ();
        LoginPage loginPage = new LoginPage (getDriver (), log);
        loginPage
                .enterUsername ("alice")
                .enterPassword ("mypassword")
                .clickOnLoginButton ();

        assert (loginPage.getConfirmationText ().contains ("alice"));
    }
}
