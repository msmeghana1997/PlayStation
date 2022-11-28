package base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

import enums.AppInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * @author TestYantra
 */
public class CapabailitySettingLib 
{
	public static Logger logger;
	public AppiumDriver driver;
	public SoftAssert softAssert = new SoftAssert();
	public AppiumDriverLocalService AppiumService;

	public AppiumDriverLocalService setupAppiumServer() 
	{
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
				.withAppiumJS(new File(FilePaths.APPIUM_JS_FILE))
				.withIPAddress(FilePaths.APPIUM_SERVER_IP)
				.usingDriverExecutable(new File(FilePaths.APPIUM_NODEJS))
				.usingAnyFreePort()
				.withLogFile(new File("./ServerLogs/appiumPlaystation.log"));
				
		AppiumService = AppiumDriverLocalService.buildService(builder);
		return AppiumService;
	}


	public void startAppiumServer(AppiumDriverLocalService service)
	{
		if (AppiumService != null) {
			AppiumService.start();
		}
	}

	public void stopAppiumServer() {
		if (AppiumService != null) {
			AppiumService.stop();
		}
	}



	public DesiredCapabilities setDesiredCapability(String platform) 
	{
		logger.info("Setting device capability");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platform", platform);
		capabilities.setCapability(CapabilityType.PLATFORM_NAME, AppInfo.ANDROID_PLATFORM_NAME.getLabel());
		capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
	//	capabilities.setCapability(MobileCapabilityType.APP, FilePaths.PLAYSTATION_APK);
		capabilities.setCapability("appPackage", "com.scee.psxandroid");
		capabilities.setCapability("appActivity", ".activity.TopActivity");
//		capabilities.setCapability(MobileCapabilityType.FULL_RESET,true);
		capabilities.setCapability("automationName", AppInfo.ANDROID_AUTOMATION_NAME.getLabel());
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		logger.info("capabilities: " + capabilities.toString());
		return capabilities;
	}
		
	public AppiumDriver launchDriver(URL url, Platform platForm)
	{
		logger.info("launching app: server url: " + url + "\n platform: " + platForm);
		DesiredCapabilities capabilities = null;
		switch (platForm)
		{
		case ANDROID:
			capabilities = setDesiredCapability("android");
			capabilities.setCapability(MobileCapabilityType.UDID, FileUtility.getPropertyValue("UDID"));
			driver = new AndroidDriver(url, capabilities);
			break;
		case IOS:
			driver = new IOSDriver(url, capabilities);
			break;
		default:
			System.out.println("Invalid url: " + url + " platform: " + platForm + " capabilities: " + capabilities.toJson().toString());
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}



	


}
