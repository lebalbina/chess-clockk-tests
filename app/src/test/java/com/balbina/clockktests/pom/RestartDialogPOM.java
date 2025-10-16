package com.balbina.clockktests.pom;

import com.balbina.clockktests.utility.WaitHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestartDialogPOM {

    private final WaitHelper helper;

    public RestartDialogPOM(WebDriver driver) {
        helper = new WaitHelper(driver);
    }

    private final By dialogRestartLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_restart\")");
    private final By dialogRestartTitleLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_title\")");
    private final By dialogRestartTextLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_text\")");
    private final By dialogRestartDismissBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_restart_dismiss\")");
    private final By dialogRestartConfirmBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_restart_confirm\")");

    public boolean isDialogDisplayed() {
        return helper.waitForElementVisibility(dialogRestartLocator, 5).isDisplayed();
    }

    public void clickDismissBtn() {
        helper.waitForElementVisibility(dialogRestartDismissBtnLocator, 5).click();
    }

    public void clickConfirmBtn() {
        helper.waitForElementVisibility(dialogRestartConfirmBtnLocator, 5).click();
    }
}


