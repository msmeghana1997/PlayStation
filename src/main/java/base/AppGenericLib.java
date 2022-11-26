package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;

import com.google.common.io.Files;

import TestAnnotations.TestInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author TestYantra
 */
public class AppGenericLib extends CapabailitySettingLib 
{


	/**
	 * This method is used to initialize logger
	 */
	public static void logger()
	{
		logger = logger == null ? Logger.getLogger(AppGenericLib.class) : logger;
		try
		{
			PropertyConfigurator.configure(new FileInputStream(FilePaths.LOG4J_PROPERTIES));
		} catch (FileNotFoundException e)   
		{
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to wait for the element to be visible
	 * @param driver
	 * @param element
	 */
	public void awaitForElement(AppiumDriver driver, WebElement element) {
		logger.info("waiting for visibility of Element: " + element);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) 
		{
			//wait.until(ExpectedConditions.visibilityOf(element));
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to perform tap action on the particular mobileElement
	 * @param driver
	 * @param element
	 */
	public void tapOnElement(AppiumDriver driver, MobileElement element) {
		logger.info("Tapping on element: " + element);
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
	}
	/**
	 * This method can compare two images and returns match percentage
	 * @param image1
	 * @param image2
	 * @return
	 * @throws IOException
	 */
	public double compareImage(File image1, File image2) throws IOException {
		logger.info("comparing images :" + image1.getAbsolutePath() + " with image: " + image2);
		Pattern pattern1 = new Pattern(image1.getAbsolutePath());
		Pattern pattern2 = new Pattern(image2.getAbsolutePath());
		Finder f = new Finder(pattern1.getImage());
		f.find(pattern2);
		if (f.hasNext())
		{ 
			Match m = f.next();
			logger.info("Match found with: " + (m.getScore()) * 100 + "%");
			return m.getScore() * 100;
		} else 
		{
			logger.info("Image not found similar");
		}
		return 0;
	}
	/**
	 *  This method can take the screenshot of the Entire screen
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public File takeScreenshot(AppiumDriver driver, String screenshotName) throws IOException {
		logger.info("Taking a screenshot: " + screenshotName);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/" + screenshotName + ".PNG");
		Files.copy(src, dest);
		return dest;
	}
	/**
	 * This method can take screenshot of the particular mobileElement
	 * @param element
	 * @param imageName
	 * @return
	 * @throws IOException
	 */

	public File takeScreenshot(WebElement element, String imageName) throws IOException {
		logger.info("Taking screenshot a mobile element: " + element);
		File file = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/" + imageName + ".png");
		Files.copy(file, dest);
		return dest;
	}

	/**
	 * This method is used to take the screenshot of entirewebPage by Base64
	 * @param currentClass
	 * @param javaUtility
	 * @param element
	 * @return 
	 */

	public String TakesScreenShotInBase64(AppiumDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		String path=ts.getScreenshotAs(OutputType.BASE64);
		return path;
	}

	/**
	 * This method is used to perform click action on the particular MobileElement
	 * @param element
	 */
	public void clickOnElement(WebElement element) {
		logger.info("clicking on element: " + element);
		element.click();
	}

	/**
	 * This method is used to pass the values to the text feild
	 * @param element
	 * @param text
	 */
	public void type(WebElement element, String text) {
		logger.info("typing into text field: " + element + " with text: " + text);
		element.sendKeys(text);
	}

	/**
	 * This method is used to open the device Notification
	 * @param driver
	 */
	public void openNotification(AppiumDriver driver) {
		if (driver != null) 
		{
			logger.info("Opening a notification panel");
			AndroidDriver androidDriver = (AndroidDriver) driver;
			androidDriver.openNotifications();

		}
	}

	/**
	 * This method is used to turn Off both mobileData and Wi-Fi network if it's turned on
	 * @param driver
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void turnOffMobileDataAndWifi(AppiumDriver driver) throws IOException, InterruptedException 
	{
		logger.info("turning off both wifi and mobile data");
		AndroidDriver androidDriver = (AndroidDriver) driver;
		if (androidDriver.getConnection().isWiFiEnabled()) {
			androidDriver.toggleWifi();
		}
		if (androidDriver.getConnection().isDataEnabled()) {
			androidDriver.toggleData();
		}

	}

	/**
	 * This method is used to turn on both mobileData and Wi-Fi network if it's not turned on
	 * @param driver
	 */
	public void turnOnDataAndWifi(AppiumDriver driver) 
	{
		logger.info("Turning on both data and wifi");
		AndroidDriver androidDriver = (AndroidDriver) driver;
		if (!androidDriver.getConnection().isWiFiEnabled()) {
			androidDriver.toggleWifi();
		}
		if (!androidDriver.getConnection().isDataEnabled()) {
			androidDriver.toggleData();
		}
	}
	/**
	 * This method is to wait for the particular Duration
	 * @param seconds
	 */
	public void waitOrPause(long seconds) {
		try 
		{
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method can be used to navigate back by pressing the back button in the Device
	 * @param driver
	 */
	public void pressNavigationBack(AppiumDriver driver) {
		logger.info("Pressing android navigate back button");
		AndroidDriver androidDriver = (AndroidDriver) driver;
		androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	/**
	 * This method navigates to home screen of the device
	 * @param driver
	 */
	public void pressHomeButton(AppiumDriver driver) {
		logger.info("Pressing android Home button");
		AndroidDriver androidDriver = (AndroidDriver) driver;
		androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
	}

	/**
	 * This method can be used to execute any Adb commands
	 * @param driver
	 * @param adbCommand
	 */
	public void executeADBcommand(AppiumDriver driver, String adbCommand) 
	{
		driver.execute(adbCommand);
	}

	/**
	 * This method is used to fetch the Toast message
	 * @param driver
	 * @return
	 */
	public String getToastMessage(AppiumDriver driver) {
		logger.info("Capturing Toast Messages");
		WebElement toast = driver.findElement(By.xpath("//android.widget.Toast"));
		awaitForElement(driver, toast);
		return toast.getText();
	}

	/**
	 * This method can be used to close the keyboard after typing or to hide the keyboard
	 * @param driver
	 */
	public void hideKeyboard(AppiumDriver driver) {
		logger.info("Hiding keyboard");
		driver.hideKeyboard();
	}

	/**
	 * This method can be used to perform scroll action till the particular element
	 * @param driver
	 * @param visibleText
	 */
	public void scrollToElement(AppiumDriver driver, String visibleText)
	{
		logger.info("Scrolling to the visible text: " + visibleText);
		driver.findElement(MobileBy
				.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))"));

	}

	/**
	 * This method is used to perform vertical swipe action
	 * @param driver
	 * @param xcordStartPoint
	 * @param ycordStartPoint
	 * @param xcordendPoint
	 * @param ycordendPoint
	 */
	public void swipeByCoordinates(AppiumDriver driver,int xcordStartPoint,int ycordStartPoint,int xcordendPoint,int ycordendPoint )
	{
		logger.info("Performing scrolling action");
		TouchAction touch = new TouchAction(driver);
		touch.longPress(PointOption.point(xcordStartPoint,ycordStartPoint ))
		.moveTo(PointOption.point(xcordendPoint, ycordendPoint))
		.release()
		.perform();
	}

	/**
	 * This method is used to take the screenshot of the webpage in Byte format
	 * @param driver
	 * @return
	 */
	public byte[] getScreenshot(AppiumDriver driver) {
		logger.info("Taking screenshot in byte format");
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BYTES);
	}

	/**
	 * This method helps to wait until the element is clickable 
	 * @param driver
	 * @param element
	 * @throws InterruptedException
	 */
	public void customWait(AppiumDriver driver, WebElement element) throws InterruptedException
	{
		logger.info("Waiting for Element to clickable: " + element);
		try 
		{
			WebDriverWait w = new WebDriverWait(driver, 20);
			w.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e)
		{
			Thread.sleep(15000);
		}
	}

	/**
	 * 
	 * @param driver
	 * @param startpart
	 * @param endPart
	 * @param element
	 */

	public void scrollToHorizontalElementAndClick(AppiumDriver driver, double startpart, double endPart, WebElement element,int count) {

        TouchAction touch = new TouchAction(driver);

        int y = driver.manage().window().getSize().getHeight();
        for (int swipe = 0; swipe <count; swipe++) 
        {
            try {
                if (element.isDisplayed()) {
                    clickOnElement(element);
                    break;
                }
            }catch (Exception e) {
                touch.longPress(PointOption.point((int) ( startpart), y / 2))
                .moveTo(PointOption.point((int) ( endPart), y / 2))
                .release()
                .perform();
            }


        }
    }


	/**
	 * This method helps in horizontal scroll
	 * @param driver
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */

	public void Horizontalscroll(AppiumDriver driver,int startX,int startY,int endX,int endY,int count) {
		logger.info("Performing Horizaontalscrolling action");
		for (int swipeCount = 0; swipeCount < count; swipeCount++) {
			try {
				TouchAction touch = new TouchAction(driver);
				touch.longPress(PointOption.point(startX, startY))
				.moveTo(PointOption.point(endX, endY))
				.release()
				.perform();

			} catch (Exception e) {

			}
		}
	}

	/**
	 * This method helps in Vertical scroll
	 * @param driver
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param Count 
	 */
	public void Verticalscroll(AppiumDriver driver,int startX,int startY,int endX,int endY,int count) {
		logger.info("Performing Horizaontalscrolling action");
		for (int swipeCount = 0; swipeCount < count; swipeCount++) {
			try {
				TouchAction touch = new TouchAction(driver);
				touch.longPress(PointOption.point(startX, startY))
				.moveTo(PointOption.point(endX, endY))
				.release()
				.perform();

			} catch (Exception e) {

			}
		}
	}

	/**
	 * This method helps in Horizontal scroll and click
	 * @param driver
	 * @param element
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void scrollHorizontalAndClick(AppiumDriver driver, WebElement element,int startX,int startY,int endX,int endY,int count) {
		for (int swipeCount = 0; swipeCount < count; swipeCount++) {
			try {
				clickOnElement(element);
				break;
			} catch (Exception e) {
				TouchAction touch = new TouchAction(driver);
				touch.longPress(PointOption.point(startX, startY))
				.moveTo(PointOption.point(endX, endY))
				.release()
				.perform();
			}
		}
	}
	/**
	 * This method is used to turn on wifi if it's disabled
	 */
	public void turnWifiOn(AppiumDriver driver) {
		logger.info("Turning on wifi");
		AndroidDriver androidDriver = (AndroidDriver) driver;
		if (!androidDriver.getConnection().isWiFiEnabled()) {
			androidDriver.toggleWifi();
		}
	}

	/**
	 * Pass class name and method name in string to extract testcase id
	 *
	 * @param className
	 * @param testMethodName
	 * @return testCaseId from annotation
	 * eg: getTestCaseId(Testscript.class,"TestRunMethod")
	 */
	public String getTestCaseId(Class className, String testMethodName) {
		try {
			Method method = className.getMethod(testMethodName);
			TestInfo testinfo = (TestInfo) method.getAnnotation(TestInfo.class);
			if (testinfo != null) {
				return testinfo.testcaseID();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "No Testcase id found: " + testMethodName;
	}

	/**
	 * click on the element by using visible text of the element use this method when app running under different languages
	 *
	 * @param driver
	 * @param visibleText
	 */
	public void clickOnElementByVisibleText(AppiumDriver driver, String visibleText) {
		logger.info("Clicking on element by using visible text: " + visibleText);
		driver.findElement(By.xpath("//*[@text='" + visibleText + "']")).click();
	}

	/**
	 * Type into the text field use this method when app launched with different langauge like hindi, kannada, tamil etc..
	 *
	 * @param driver
	 * @param elementVisibleText
	 * @param text
	 */
	public void typeByVisibleText(AppiumDriver driver, String elementVisibleText, String text)
	{
		logger.info("typing into text field by using visible text of element: " + elementVisibleText + " text: " + text);
		driver.findElement(By.xpath("//*[@text='" + elementVisibleText + "']")).sendKeys(text);
	}

	/**
	 * This method can be used to run the app in the background
	 * @param driver
	 * @param appPackage
	 * @param appActivity
	 */
	public void RunAppInBackground(AppiumDriver driver,String appPackage,String appActivity) {
		AndroidDriver androidDriver=(AndroidDriver)driver;
		androidDriver.startActivity(new Activity(appPackage, appActivity));
	}

	/**
	 * This method is used to switch the app from  webView to nativeview
	 * @param driver
	 */
	public void switchAppFromWebviewToNativeView(AppiumDriver driver,String appPackage)
	{
		AndroidDriver androidDriver=(AndroidDriver)driver;
		androidDriver.context("NATIVEVIEW_"+appPackage);
	}

	/**
	 * This method is used to switch the app from  nativeview to webview
	 * @param driver
	 * @param appPackage
	 */
	public void switchAppFromNativeviewToWebView(AppiumDriver driver,String appPackage)
	{
		AndroidDriver androidDriver=(AndroidDriver)driver;
		androidDriver.context("WEBVIEW__"+appPackage);
	}
	/**
	 * This method is used to drag and drop
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 */

	public void dragAndDrop(int startx,int starty,int endx,int endy) {
		TouchAction action=new TouchAction(driver);
		action
		.press(PointOption.point(startx, starty))
		.waitAction()
		.perform()
		.moveTo(PointOption.point(endx, endy))
		.release()
		.perform();
	}
	/**
	 * This method is used to rotate the screen orientation to landscape mode
	 */
	public void rotateScreenToLandscapeMode() {
		driver.rotate(ScreenOrientation.LANDSCAPE);

	}
	/**
	 * This method is used to rotate the screen to portrait mode
	 */
	public void rotateScreenToPortraitMode() {
		driver.rotate(ScreenOrientation.PORTRAIT);
	}

	/**
	 * This method is used to click and hold the particular element for particular duration
	 * @param webelement
	 */
	public void tapByElement(WebElement webelement,int seconds) {
		TouchAction action=new TouchAction(driver);
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(webelement))).
		waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds))).perform();	

	}

	/**
	 * This method is used to scroll till end of the screen
	 */
	public void scrollTillEnd() {
		driver.findElement(MobileBy.
				AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));

	}
	/**
	 * This method is used to tap at an arbitrary location
	 * @param xCordPoint
	 * @param YcordPoint
	 */
	public void tapByCoordinates(int xCordPoint,int YcordPoint) {
		TouchAction action=new TouchAction(driver);
		action.tap(TapOptions.tapOptions().withPosition(PointOption.point( xCordPoint,YcordPoint))).perform();
	}

	public void pushFileToDevice(String devicePath,String fileLocation) throws Exception {
		AndroidDriver androidDriver=(AndroidDriver)driver;
		try {
			logger.info("pushing files from"+fileLocation+" to "+devicePath+"");
			androidDriver.pushFile(devicePath, new File(fileLocation));
		} catch (IOException e) {
			throw new Exception("Error in pushing file to device "+e.getMessage());
		}
	}
}







