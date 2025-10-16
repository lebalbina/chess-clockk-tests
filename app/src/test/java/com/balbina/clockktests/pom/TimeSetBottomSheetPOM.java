package com.balbina.clockktests.pom;

import com.balbina.clockktests.utility.WaitHelper;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TimeSetBottomSheetPOM {

    private final WebDriver driver;

    public TimeSetBottomSheetPOM(WebDriver driver) {
        helper = new WaitHelper(driver);
        this.driver = driver;
    }

    private final WaitHelper helper;

    private final By secondsLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_seconds\")");
    private final By hoursLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_hours\")");
    private final By minutesLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_minutes\")");
    private final By minutesBonusLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_bonus_minutes\")");
    private final By secondsBonusLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_bonus_seconds\")");
    private final By sheetDoneBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"sheet_btn\")");

    public void clickDoneBtn() {
        helper.waitForElementVisibility(sheetDoneBtnLocator, 5).click();
    }

    public void clickHours() {
        getHours().click();
    }

    public void clearAll() {
        getHours().clear();
        getMinutes().clear();
        getSeconds().clear();
        getMinutesBonus().clear();
        getSecondsBonus().clear();
    }

    public void typeHours(String value) {
        WebElement hours = getHours();
        hours.clear();
        //wait for focus
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(hours));
        hours.sendKeys(value);
    }

    public void typeMinutes(String value) {
        WebElement minutes = getMinutes();
        minutes.clear();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(minutes));
        minutes.sendKeys(value);
    }

    public void typeSeconds(String value) {
        WebElement seconds = getSeconds();
        seconds.clear();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(seconds));
        seconds.sendKeys(value);
    }

    public void typeMinutesBonus(String value) {
        WebElement minutesBonus = getMinutesBonus();
        minutesBonus.clear();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(minutesBonus));
        minutesBonus.sendKeys(value);
    }

    public void typeSecondsBonus(String value) {
        WebElement secondsBonus = getSecondsBonus();
        secondsBonus.clear();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(secondsBonus));
        secondsBonus.sendKeys(value);
    }

    private WebElement getSeconds() {
        return helper.waitForElementVisibility(secondsLocator, 5);
    }

    private WebElement getHours() {
        return helper.waitForElementVisibility(hoursLocator, 5);
    }

    private WebElement getMinutes() {
        return helper.waitForElementVisibility(minutesLocator, 5);
    }

    private WebElement getMinutesBonus() {
        return helper.waitForElementVisibility(minutesBonusLocator, 5);
    }

    private WebElement getSecondsBonus() {
        return helper.waitForElementVisibility(secondsBonusLocator, 5);
    }
}


