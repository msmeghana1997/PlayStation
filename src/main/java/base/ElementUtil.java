package base;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
/**
 * 
 * @author TestYantra
 *
 */
public class ElementUtil extends AppGenericLib {
	public ElementUtil(AppiumDriver driver) {
		this.driver=driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(String locatorType,String locatorValue) {
		return driver.findElement(getBy(locatorType,locatorValue));
	}
	
	public WebElement getElement(String locator) {
		return driver.findElementByAccessibilityId(locator);
	}

	private By  getBy(String locatorType,String locatorValue) {
		By locator=null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator=By.id(locatorValue);
			break;
		case "xpath":
			locator=By.xpath(locatorValue);
			break;
		case "className":
			locator=By.className(locatorValue);
			break;
		default:
			break;
		}return locator;
	}
}
