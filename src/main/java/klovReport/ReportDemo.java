package klovReport;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;

public class ReportDemo {

	ExtentReports extent;
	ExtentTest test;
	ExtentTest maintest;
	@BeforeSuite
	public void startReport() {
		extent = new ExtentReports();
		ExtentKlovReporter klov = new ExtentKlovReporter("Regression");
		klov.initMongoDbConnection("localhost", 27017);
		klov.initKlovServerConnection("http://localhost::1100");
		klov.setProjectName("Sample");
		 
		 extent.attachReporter(klov);
		
	}
	
	@BeforeClass
   public void beforeTest(ITestContext xtx)
   {
		maintest=extent.createTest(xtx.getClass().getSimpleName());
   }
	 
	@BeforeMethod
	public void beforeMethod(Method method)
	{
		test=maintest.createNode(method.getName());
	}
	
	@Test
	public void testKlov2()
	{
		
		test.log(Status.INFO, "Hi");
		test.log(Status.INFO, "Hi");
		test.log(Status.INFO, "Hi");
		test.log(Status.INFO, "Hi");
		test.log(Status.INFO, "Hi");
		
	}
	
	@Test
	public void testKlov1()
	{
		 
		test.log(Status.INFO, "BYE");
		test.log(Status.INFO, "BYE");
		test.log(Status.INFO, "BYE");
		test.log(Status.INFO, "BYE");
		test.log(Status.INFO, "BYE");
		test.log(Status.INFO, "BYE");
	}
	
	@AfterMethod
	public void flushReport()
	{
		extent.flush();
	}

}
