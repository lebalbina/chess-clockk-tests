import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static io.appium.java_client.AppiumBy.ByAndroidUIAutomator.androidUIAutomator;


public class PlayPauseTests {
    private AppiumDriver driver;
    private WebDriverWait waitDriver;


    @BeforeClass
    public void setUp() {
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

        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testEmulatorConnected() {
        // Get device details
        String platformName = driver.getCapabilities().getCapability("platformName").toString();
        String deviceName = driver.getCapabilities().getCapability("appium:deviceName").toString();

        // Print device info
        System.out.println("Connected to device: " + deviceName + " (Platform: " + platformName + ")");

        // Assert that we are connected
        Assert.assertNotNull(deviceName, "No device connected!");
    }

    @Test
    public void testGameStart() {
        WebElement clockTop = driver.findElement(androidUIAutomator("new UiSelector().resourceId(\"clockTop\")"));
        clockTop.click();

        waitDriver.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(clockTop)));

        Assert.assertFalse(clockTop.isEnabled());
    }
}
