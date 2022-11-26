package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import TestAnnotations.StepInfo;
import base.AppGenericLib;
import base.ElementUtil;
import base.ExcelUtility;
import base.ListenerImplimentationclass;
import io.appium.java_client.AppiumDriver;

public class AajPage extends AppGenericLib {
	ElementUtil elementUtil;
	
	public AajPage(AppiumDriver driver) {
		this.driver=driver;
		 elementUtil=new ElementUtil(driver);
		 PageFactory.initElements(driver, this);
	}
	

	private WebElement aajPageTitle(String aajPageTitleText) {
		String aajPageTitlexapthValue="//android.widget.TextView[@text='"+aajPageTitleText+"']";
		return elementUtil.getElement("xpath",aajPageTitlexapthValue );
	}
	
	//business libraries
	@StepInfo(info="Fetching the aaj(Home feed) page title")
	public String getaajPageTitle() throws Exception 
	{
		try {
		ListenerImplimentationclass.testLog.info("Fetching the aaj(Home feed) page title");
		awaitForElement(driver, aajPageTitle(ExcelUtility.getExcelData("locators", "aajPageTitle","value")));
		return aajPageTitle(ExcelUtility.getExcelData("locators", "aajPageTitle","value")).getText();
		}catch (Exception e)
		{
			throw new Exception("Error in getting aajPageTitle()"+e.getMessage());
		}
		
	}

}
