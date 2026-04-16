package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import basicfunc.Base;

public class Listners extends Base implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
		extentTest.info("TestCase Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getName());
	    extentTest.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed with success Percentage: " + result.getStatus());
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Started: " + context);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Finished: " + context);
		
	}

	

}
