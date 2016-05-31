import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by richard on 09/05/2016.
 */
public class AppiumExamples
{
    @Test
    public void androidExample() throws InterruptedException
    {
        //This start an Appium server for us
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort());
        service.start();

        //We are waiting to ensure the server has started before we interact with it
        while(!service.isRunning())
        {
            Thread.sleep(250);
        }

        //We are going to create an AndroidDriver

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/richard/Desktop/app-alpha-debug.apk");

        AndroidDriver driver = new AndroidDriver(service.getUrl(), capabilities);

        Thread.sleep(10000);

        driver.quit();
        service.stop();
    }

    @Test
    public void iosExample() throws InterruptedException
    {
        DesiredCapabilities appiumCapabilities = new DesiredCapabilities();
        appiumCapabilities.setCapability("fullReset", "true");

        //This start an Appium server for us
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingAnyFreePort().withCapabilities(appiumCapabilities));
        service.start();

        //We are waiting to ensure the server has started before we interact with it
        while(!service.isRunning())
        {
            Thread.sleep(250);
        }

        //We are going to create an AndroidDriver

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "iPhone 6s (9.3)");
        capabilities.setCapability("deviceName", "iPhone 6s");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("app", "/Users/richard/Desktop/Wikipedia Debug.app");

        IOSDriver driver = new IOSDriver(service.getUrl(), capabilities);

        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")));
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")));
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASwitch[1]")));
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASwitch[1]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")));
        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]")).click();

        driver.switchTo().alert().accept();

        driver.quit();
        service.stop();
    }


}
