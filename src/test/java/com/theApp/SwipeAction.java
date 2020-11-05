package com.theApp;

import com.mobile.core.base.BaseTest;
import com.mobile.core.base.TestUtilities;
import com.mobile.pages.ListDemoPage;
import com.mobile.pages.WelcomePage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

public class SwipeAction extends TestUtilities {

    @Test
    public void swipeAction() throws InterruptedException {
        WelcomePage welcomePage = new WelcomePage (getDriver (), log);
        welcomePage
                .goToListDemo ();
        sleep (1000);
        singleFingerSwipe (520, 1530, 1000, 520, 490);
        ListDemoPage listDemoPage = new ListDemoPage (getDriver (),log);
        listDemoPage.selectListOption ("Stratus");
        sleep (1000);
    }
}
