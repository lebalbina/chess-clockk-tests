package com.balbina.clockktests.pom;

import com.balbina.clockktests.pom.utility.WaitHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainViewPOM {

    private final WaitHelper helper;

    private final By clockTopLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockTop\")");
    private final By clockBottomLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockBottom\")");
    private final By clockTopCurrentTimeLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockCurrentValue\").instance(0)");
    private final By clockBottomCurrentTimeLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockCurrentValue\").instance(1)");
    private final By ppBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"play_pause_button\")");
    private final By clockBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"set_time_button\")");
    private final By restartBtnLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"restart_button\")");
    private final By topCounterLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockMovesCount\").instance(1)");
    private final By bottomCounterLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockMovesCount\").instance(0)");
    private final By topTimeSettingLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockTimeSetting\").instance(0)");
    private final By bottomTimeSettingLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"clockTimeSetting\").instance(1)");
    private final By flagLocator = AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"flag_icon\")");

    public MainViewPOM(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        helper = new WaitHelper(driver);
    }

    public void clickTopClock() {
        getClockTop().click();
    }

    public void clickBottomClock() {
        getClockBottom().click();
    }

    public Boolean isTopClockEnabled() {
        return getClockTop().isEnabled();
    }

    public Boolean isBottomClockEnabled() {
        return getClockBottom().isEnabled();
    }

    public String getTopClockTime() {
        return helper.waitForElement(clockTopCurrentTimeLocator, 5).getText();
    }

    public String getBottomClockTime() {
        return helper.waitForElement(clockBottomCurrentTimeLocator, 5).getText();
    }

    public Boolean isPpBtnEnabled() {
        return getPpBtn().isEnabled();
    }

    public Boolean isClockBtnEnabled() {
        return getClockBtn().isEnabled();
    }

    public Boolean isRestartBtnEnabled() {
        return getRestartBtn().isEnabled();
    }

    public int getTopMovesCounterValue() {
        return parseMovesCount(helper.waitForElement(topCounterLocator, 5).getText());
    }

    public int getBottomMovesCounterValue() {
        return parseMovesCount(helper.waitForElement(bottomCounterLocator, 5).getText());
    }

    public String getTopTimeSetting() {
        return helper.waitForElement(topTimeSettingLocator, 5).getText();
    }

    public String getBottomTimeSetting() {
        return helper.waitForElement(bottomTimeSettingLocator, 5).getText();
    }

    public void clickRestartBtn() {
        getRestartBtn().click();
    }

    public void clickPpBtn() {
        getPpBtn().click();
    }

    public void clickClockBtn() {
        getClockBtn().click();
    }

    public WebElement getFlag() {
        return helper.waitForElement(flagLocator, 5);
    }

    public boolean isFlagVisible() {
        return getFlag().isDisplayed();
    }

    public void processTopClockWait(String time) {
        helper.waitUntilTextPresent(getClockTop(), time, 5);
    }

    public void processBottomClockWait(String time) {
        helper.waitUntilTextPresent(getClockBottom(), time, 5);
    }

    private WebElement getClockTop() {
        return helper.waitForElement(clockTopLocator, 5);
    }

    private WebElement getClockBottom() {
        return helper.waitForElement(clockBottomLocator, 5);
    }

    private WebElement getPpBtn() {
        return helper.waitForElement(ppBtnLocator, 5);
    }

    private WebElement getClockBtn() {
        return helper.waitForElement(clockBtnLocator, 5);
    }

    private WebElement getRestartBtn() {
        return helper.waitForElement(restartBtnLocator, 5);
    }

    private int parseMovesCount(String unformatted) {
        int index = unformatted.indexOf(':');
        String formatted = unformatted.substring(index + 2).trim();
        return Integer.parseInt(formatted);
    }
}


