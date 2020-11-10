package com.theApp;

import com.mobile.core.base.TestUtilities;
import com.mobile.pages.ListDemoPage;
import com.mobile.pages.WelcomePage;
import org.testng.annotations.Test;

public class SwipeAction extends TestUtilities {

    @Test
    public void swipeAction() throws InterruptedException {
        WelcomePage welcomePage = new WelcomePage (getAppiumDriver (), log);
        welcomePage
                .goToListDemo ();
        sleep (1000);
        singleFingerSwipe (520, 1530, 1000, 520, 490);
        ListDemoPage listDemoPage = new ListDemoPage (getAppiumDriver (),log);
        listDemoPage.selectListOption ("Stratus");
        sleep (1000);
    }
}
