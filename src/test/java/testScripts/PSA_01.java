package testScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;

@Listeners(base.ListenerImplimentationclass.class)
public class PSA_01 extends BaseTest{

	@Test
	public void userProfileValidation()
	{
		System.out.println(" test case");
	}
}
