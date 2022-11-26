package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * 
 * @author TestYantra
 *
 */
public class ListenerImplimentationclass implements ITestListener {

	private ExtentTest test;
	public static ExtentTest testLog;
	private ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		test.assignAuthor("TestYantra");
		test.assignCategory("Playstation Automation");
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName()+"passed");
		test.pass(result.getThrowable());

	}
	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+"test failed");
		test.fail(result.getThrowable());
		AppGenericLib lib=new AppGenericLib();
		String path =lib.TakesScreenShotInBase64(BaseTest.listnerDriver);
		test.addScreenCaptureFromBase64String(path, result.getMethod().getMethodName());

	}
	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+"test skipped");
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark=new ExtentSparkReporter("./emailable-report/extent-reports"+context.getClass().getSimpleName());
		spark.config().setDocumentTitle("PlayStation");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("PlayStation Test Report");

		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Device Name", "Mi A1");
		report.setSystemInfo("Platform", "Android");
		report.setSystemInfo("Platform Version", "9");

		test=report.createTest(context.getName());
		testLog=test;

	}
	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
