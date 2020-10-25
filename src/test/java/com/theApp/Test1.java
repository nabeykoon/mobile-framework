package com.theApp;

import com.mobile.core.base.BaseTest;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;


public class Test1 extends BaseTest {

@Test
public void test1(){
    //driver.findElement(MobileBy.AccessibilityId("Login Screen"));
    List<WebElement> elements = getDriver ().findElements(MobileBy.AccessibilityId("Login Screen"));
    System.out.println ("No of elements: " + elements.size ());
}

}
