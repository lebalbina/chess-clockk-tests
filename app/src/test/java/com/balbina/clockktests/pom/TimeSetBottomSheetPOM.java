package com.balbina.clockktests;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeSetBottomSheetPOM {

    private final WebDriverWait driverWait;

    public TimeSetBottomSheetPOM(WebDriverWait driverWait) {
        this.driverWait = driverWait;
    }

    private final By secondsLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_seconds\")");

    public WebElement getSeconds() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(secondsLocator));
    }

    private final By hoursLocator = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"sheet_hours\")");

    public WebElement getHours() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(hoursLocator));
    }

    private final By minutesLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_minutes\")");

    public WebElement getMinutes() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(minutesLocator));
    }

    private final By minutesBonusLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_bonus_minutes\")");

    public WebElement getMinutesBonus() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(minutesBonusLocator));
    }

    private final By secondsBonusLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_bonus_seconds\")");

    public WebElement getSecondsBonus() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(secondsBonusLocator));
    }

    private final By sheetDoneBtnLocator = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"sheet_btn\")");

    public WebElement getDoneBtn() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(sheetDoneBtnLocator));
    }

    private final By sheetTitleLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_label\")");

    public WebElement getSheetTitle() {
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(sheetTitleLocator));
    }
}

