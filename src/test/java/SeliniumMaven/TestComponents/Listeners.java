package SeliniumMaven.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeliniumMaven.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener{
	ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	ExtentReports extent = ExtentReportsNG.getReportObject();
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	
	}
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.get().log(Status.PASS,"test is passed");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		test.get().log(Status.FAIL,"test is failed");
		test.get().fail(result.getThrowable()); //- used to get error message
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//take screenshot
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.get().addScreenCaptureFromPath(filePath);
		
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	

}
