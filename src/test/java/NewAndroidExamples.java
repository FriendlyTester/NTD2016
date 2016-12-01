import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by richard on 09/05/2016.
 */
public class NewAndroidExamples
{
    @Test
    public void androidClickAndType() throws InterruptedException
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
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/richard/Documents/workspace/TechnicalMobileWorkshopAppium/Test Apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(service.getUrl(), capabilities);

        driver.findElement(MobileBy.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[11]")).click();
        driver.findElement(MobileBy.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[5]")).click();
        driver.findElement(MobileBy.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.ListView[1]/android.widget.TextView[1]")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")));

        driver.findElement(MobileBy.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]"))
                .sendKeys("Richard");

        Assert.assertEquals(driver.findElement(MobileBy.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")).getText(),
                "Richard");

        driver.quit();
        service.stop();
    }
}
