package com.balbina.clockktests.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementVisibility(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresence(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean waitUntilTextPresent(By locator, String text, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text)
        );
    }
}
