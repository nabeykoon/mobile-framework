package com.theApp;

import com.mobile.core.base.TestUtilities;
import com.mobile.pages.WelcomePage;
import org.testng.annotations.Test;

public class WebViewTest extends TestUtilities {

    @Test
    public void webViewTest() throws NoContextFound {
        WelcomePage welcomePage = new WelcomePage (getAppiumDriver (), log);
        welcomePage.goToWebView ();
        changeToWebContext ();
        getAppiumDriver ().get ("https://cloudgrey.io/");
        log.info (getAppiumDriver ().getTitle ());
    }
}
