package api.utilities;

//Extent report 5.x

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;//UI of report responsibilty, look&feel of report
	public ExtentReports extent; //env, user, os etc details
	public ExtentTest test; //report entries
	
	String repName;
	
	@BeforeClass
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime()); //time-stamp
		repName ="Test-Report-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+repName);   //specify location of report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//report title
		sparkReporter.config().setReportName("Pet Store Users API"); //name of report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Shikha");
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Application", "Pet Store Users API");
		
	}
	
	@Test
	public void onTestSuccess(ITestResult result)
	{
		test= extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passsed");
	}
	
	@Test
	public void onTestFailure(ITestResult result)
	{
		test= extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
	@Test
	public void onSkipped(ITestResult result)
	{
		test= extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());	
	}
	
	@AfterTest
	public void onFinish(ITestResult result)
	{
		extent.flush();
		//extent.close();
	}
	

}
