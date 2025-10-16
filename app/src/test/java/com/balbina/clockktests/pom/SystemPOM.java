package com.balbina.clockktests.pom;

import com.balbina.clockktests.utility.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SystemPOM {

    private final WaitHelper helper;
    private final By toastLocator = By.xpath("//android.widget.Toast");

    public SystemPOM(WebDriver driver) {
        helper = new WaitHelper(driver);
    }

    public String getToastMessage() {
        return helper.waitForElementPresence(toastLocator, 2).getText();
    }
}

