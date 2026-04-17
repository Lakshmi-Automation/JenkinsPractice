package basicfunc;

import calc.Calculator;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.Listners;

@Listeners(Listners.class)
public class Base {
	public static ExtentReports extent;
	public static Calculator calculator;
	public static ExtentTest extentTest;
	

	@BeforeMethod
	public void setup(ITestContext context) {
		calculator = new Calculator();
		extentTest=extent.createTest(context.getName());
		String author=context.getCurrentXmlTest().getParameter("author");
		extentTest.info(author);
	}

	@AfterMethod
	public void teardown() {
		calculator = null;
	}

	@BeforeSuite
	public void setUpReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("extentReport/Spark.html");
		extent.attachReporter(spark);
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

	@BeforeClass
	public static void beforeClass() {
		System.out.println("Starting Calculator Tests...");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("Finished Calculator Tests.");
	}

}
