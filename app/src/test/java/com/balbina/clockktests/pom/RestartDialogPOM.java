package com.balbina.clockktests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestartDialogPOM {

    private final WebDriverWait driverWait;

    public RestartDialogPOM(WebDriverWait driverWait) {
        this.driverWait = driverWait;
    }

    private final By dialogRestartLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_restart\")");
    public WebElement getDialogRestart() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(dialogRestartLocator));
    }

    private final By dialogRestartTitleLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_title\")");
    public WebElement getDialogRestartTitle() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(dialogRestartTitleLocator));
    }

    private final By dialogRestartTextLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_text\")");
    public WebElement getDialogRestartText() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(dialogRestartTextLocator));
    }

    private final By dialogRestartDismissBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_restart_dismiss\")");
    public WebElement getDialogRestartDismissBtn() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(dialogRestartDismissBtnLocator));
    }

    private final By dialogRestartConfirmBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"dialog_restart_confirm\")");
    public WebElement getDialogRestartConfirmBtn() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(dialogRestartConfirmBtnLocator));
    }
}

