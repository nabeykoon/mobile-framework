package com.theApp;

import com.mobile.core.base.TestUtilities;
import com.mobile.pages.WebViewPage;
import com.mobile.pages.WelcomePage;
import org.testng.annotations.Test;

public class MultipleContextTest extends TestUtilities {

    @Test
    public void multipleContextTest() throws NoContextFound {
        WelcomePage welcomePage = new WelcomePage (getAppiumDriver (), log);
        // Go to Web view page
        welcomePage
                .goToWebView ();
        WebViewPage webViewPage = new WebViewPage (getAppiumDriver (), log);

        //Enter url to visit and click Go button
        webViewPage
                .enterUrl ("https://cloudgrey.io")
                .clickGoButton ();

        //Assert alert text
        String alertText = webViewPage.getAlertText ();
        assert (alertText.contains ("Sorry, you are not allowed to visit that url"));
        webViewPage.acceptAlert ();

        //Assert going back to same page after accepting alert
        changeToWebContext ();
        assert (webViewPage.getBodyText ().contains ("Please navigate to a webpage"));

        //Navigate to correct Url
        changeToNativeContext ();
        webViewPage
                .enterUrl ("https://appiumpro.com")
                .clickGoButton ();
        changeToWebContext ();
        assert (webViewPage.getCurrentPageTitle ().contains ("Appium Pro"));
    }
}
