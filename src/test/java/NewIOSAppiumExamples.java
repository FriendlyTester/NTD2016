import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.Console;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by richard on 30/11/2016.
 */
public class NewIOSAppiumExamples
{
    @Test
    public void populateFieldsClickButtonReadText() throws MalformedURLException
    {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));

        AppiumDriverLocalService service = builder.build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 5");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/richard/Documents/workspace/TechnicalMobileWorkshopAppium/Test Apps/TestApp.app");
        IOSDriver driver = new IOSDriver(service.getUrl(), capabilities);


        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")));

        driver.findElement(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]"))
                .sendKeys("1");
        driver.findElement(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[2]"))
                .sendKeys("4");
        driver.findElement(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"))
                .click();

        Assert.assertEquals(driver.findElement(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[1]"))
                .getText(), "5");

        driver.quit();
        service.stop();
    }

    @Test
    public void triggerAlertAndAcceptIt()
    {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"));

        AppiumDriverLocalService service = builder.build();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("An appium server node is not started!");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 5");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/richard/Documents/workspace/TechnicalMobileWorkshopAppium/Test Apps/TestApp.app");
        IOSDriver driver = new IOSDriver(service.getUrl(), capabilities);


        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]")));

        driver.findElement(MobileBy.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[2]"))
                .click();

        Assert.assertEquals(driver.switchTo().alert().getText(), "Cool title\nthis alert is so cool.");
        driver.switchTo().alert().accept();

        driver.quit();
        service.stop();
    }
}