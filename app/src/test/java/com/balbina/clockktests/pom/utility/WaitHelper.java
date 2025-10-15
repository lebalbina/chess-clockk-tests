package com.balbina.clockktests.pom.utility;

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

    public WebElement waitForElement(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitUntilTextPresent(WebElement element, String text, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                ExpectedConditions.textToBePresentInElement(element, text)
        );
    }
}
