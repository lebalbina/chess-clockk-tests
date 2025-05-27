package com.balbina.clockktests.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

//TODO  refactor to method-based lookup
public class MainViewPOM {

    public MainViewPOM(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockTop\")")
    private WebElement clockTop;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockBottom\")")
    private WebElement clockBottom;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockCurrentValue\").instance(0)")
    private WebElement clockTopCurrentTime;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockCurrentValue\").instance(1)")
    private WebElement clockBottomCurrentTime;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"play_pause_button\")")
    private WebElement ppBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"set_time_button\")")
    private WebElement clockBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"restart_button\")")
    private WebElement restartBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockMovesCount\").instance(1)")
    private WebElement topCounter;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockMovesCount\").instance(0)")
    private WebElement bottomCounter;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockTimeSetting\").instance(0)")
    private WebElement topTimeSetting;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"clockTimeSetting\").instance(1)")
    private WebElement bottomTimeSetting;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"flag_icon\")")
    private WebElement flag;

    public void tapClockTop() {
        clockTop.click();
    }

    public void tapBottomClock() {
        clockBottom.click();
    }

    public Boolean isTopClockEnabled() {
        return clockTop.isEnabled();
    }

    public Boolean isBottomClockEnabled() {
        return clockBottom.isEnabled();
    }

    //TODO fix this usage
    public String getTopClockTime() {
        return clockTopCurrentTime.getText();
    }

    public WebElement getTopClockTimeElement() {
        return clockTopCurrentTime;
    }

    public WebElement getBottomClockTimeElement() {
        return clockBottomCurrentTime;
    }

    public String getBottomClockTime() {
        return clockBottomCurrentTime.getText();
    }

    public WebElement getPpBtn() {
        return ppBtn;
    }

    public Boolean isPpBtnEnabled() {
        return ppBtn.isEnabled();
    }

    public WebElement getClockBtn() {
        return clockBtn;
    }

    public Boolean isClockBtnEnabled() {
        return clockBtn.isEnabled();
    }

    public WebElement getRestartBtn() {
        return restartBtn;
    }

    public Boolean isRestartBtnEnabled() {
        return restartBtn.isEnabled();
    }

    public int getTopMovesCounterValue() {
        return parseMovesCount(topCounter.getText());
    }

    public int getBottomMovesCounterValue() {
        return parseMovesCount(bottomCounter.getText());
    }

    public String getTopTimeSetting() {
        return topTimeSetting.getText();
    }

    public String getBottomTimeSetting() {
        return bottomTimeSetting.getText();
    }

    private int parseMovesCount(String unformatted) {
        int index = unformatted.indexOf(':');
        String formatted = unformatted.substring(index + 2).trim();
        return Integer.parseInt(formatted);
    }

    public WebElement getClockTop() {
        return clockTop;
    }

    public WebElement getClockBottom() {
        return clockBottom;
    }

    public WebElement getFlag() {
        return flag;
    }
}

