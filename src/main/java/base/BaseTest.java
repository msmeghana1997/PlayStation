package base;


import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Platform;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
/**
 * 
 * @author TestYantra
 *
 */
public class BaseTest extends AppGenericLib 
{

	public static  AppiumDriver listnerDriver;
	@BeforeSuite
	public void configBS() 
	{
		logger();	
	}

	
	@BeforeMethod
	public void launchApp() throws MalformedURLException
	{

		startAppiumServer(setupAppiumServer());
		driver = launchDriver(AppiumService.getUrl(), Platform.ANDROID);

	}	


	@AfterMethod(alwaysRun = true)
	public void closeApp(ITestResult result) throws IOException
	{

		if (result.getStatus() == ITestResult.SUCCESS)
		{
			if (driver != null) 
			{
				driver.closeApp();
			}

		} 
		else if (result.getStatus() == ITestResult.FAILURE) 
		{
			if (driver != null) 
			{

				driver.closeApp();
			}

		} 
		else if (result.getStatus() == ITestResult.SKIP)
		{
			if (driver != null) 
			{
				driver.closeApp();
			}

		}
		stopAppiumServer();
	}

	@AfterClass(alwaysRun = true)
	public void configAC() {

	}

	@AfterSuite(alwaysRun = true)
	public void configAS() {

	}

}
