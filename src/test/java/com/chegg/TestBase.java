package com.chegg;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.URL;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    private static final String PLATFORM = "platform_name";
    private static final String PLATFORM_VERSION = "os_version";
    private static final String DEVICE_NAME = "device_name";
    private static final String UDID = "device_id";
    private static final String APP_PACKAGE = "app_package";
    private static final String APP_ACTIVITY = "app_activity";
    private static final String APPIUM_URL = "http://0.0.0.0:4723/wd/hub";

    protected static AppiumDriver<MobileElement> driver;
    protected static Logger logger;

    @BeforeAll
    public static void setup() {
        ThreadContext.put("logFileName", "Interview");
        logger = LoggerFactory.getLogger("TestLog");

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
            caps.setCapability(MobileCapabilityType.UDID, UDID);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            caps.setCapability("appPackage", APP_PACKAGE);
            caps.setCapability("appActivity", APP_ACTIVITY);
            URL url = new URL(APPIUM_URL);
            driver = new AppiumDriver<>(url, caps);
        } catch (Exception exception) {
            logger.error("Failed to initialize Appium Driver", exception);
            System.exit(1);
        }
        logger.info("Appium Driver successfully initialized");
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
