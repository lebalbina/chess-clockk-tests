package com.balbina.clockktests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public abstract class BaseTest {
    protected AndroidDriver driver;
    protected WebDriverWait driverWait;

    @BeforeClass
    public void setup() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", props.getProperty("PLATFORM_NAME"));
        caps.setCapability("appium:deviceName", props.getProperty("DEVICE_NAME"));
        caps.setCapability("appium:appPackage", props.getProperty("APP_PACKAGE"));
        caps.setCapability("appium:appActivity", props.getProperty("APP_ACTIVITY"));
        caps.setCapability("appium:automationName", props.getProperty("AUTOMATION_NAME"));

        try {
            driver = new AndroidDriver(new URL(props.getProperty("APPIUM_SERVER_URL")), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

