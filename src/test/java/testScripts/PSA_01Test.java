package testScripts;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;

@Listeners(base.ListenerImplimentationclass.class)
public class PSA_01Test extends BaseTest{

	@Test
	public void userProfileValidationTest() throws Throwable
	{
		Thread.sleep(2000);
		System.out.println("Launched");
		WebElement signInButton = driver.findElementByClassName("android.view.View");
		awaitForElement(driver, signInButton);
		signInButton.click();
		Thread.sleep(5000);
		
		
		
		
	}
}
