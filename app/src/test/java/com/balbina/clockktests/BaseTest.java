package com.balbina.clockktests;

import com.balbina.clockktests.utility.ConfigProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class BaseTest {
    protected AndroidDriver driver;
    protected final String packageName = "com.example.chessclockk";

    @BeforeMethod
    public void setup() {
        try {
            driver = new AndroidDriver(getAppiumServerUrl(), getDesiredCapabilities());
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", ConfigProvider.getProperty("PLATFORM_NAME"));
        caps.setCapability("appium:deviceName", ConfigProvider.getProperty("DEVICE_NAME"));
        caps.setCapability("appium:appPackage", ConfigProvider.getProperty("APP_PACKAGE"));
        caps.setCapability("appium:appActivity", ConfigProvider.getProperty("APP_ACTIVITY"));
        caps.setCapability("appium:automationName", ConfigProvider.getProperty("AUTOMATION_NAME"));
        return caps;
    }

    private URL getAppiumServerUrl() throws MalformedURLException, URISyntaxException {
        return new URI(ConfigProvider.getProperty("APPIUM_SERVER_URL")).toURL();
    }
}


