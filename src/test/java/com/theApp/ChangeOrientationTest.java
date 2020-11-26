package com.theApp;

import com.mobile.core.base.TestUtilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;

public class ChangeOrientationTest extends TestUtilities {
    @Test
    public void testScreenOrientation() {
        changeOrientation (ScreenOrientation.LANDSCAPE);
        Dimension size = getAppiumDriver ().manage ().window ().getSize ();
        log.info ("======================" + size + "========================");
        takeScreenshotInTest ("homepageScreen");
    }
}
