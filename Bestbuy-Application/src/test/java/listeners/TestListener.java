package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testExecution.BaseClass;
import utilities.Utilities;

public class TestListener implements ITestListener {
	
	ExtentSparkReporter sparkUI;
	ExtentReports report;
	ExtentTest test;
	File extentPath;
	BaseClass base;
	@Override
	public void onStart(ITestContext context) {
		
		extentPath = new File(Utilities.extentReportPathName());
		sparkUI = new ExtentSparkReporter(extentPath);
		sparkUI.config().setTheme(Theme.STANDARD);
		sparkUI.config().setDocumentTitle("BESTBUY REPORT");
		sparkUI.config().setReportName("TEST EXECUTION");
		
		report = new ExtentReports();
		report.attachReporter(sparkUI);
	
		String machine = context.getCurrentXmlTest().getParameter("Machine");
		String OS = context.getCurrentXmlTest().getParameter("OS");
		String browserName = context.getCurrentXmlTest().getParameter("Browser");
		
		if(machine.equalsIgnoreCase("Remote")) {
					
					report.setSystemInfo("EXECUTED MACHINE ", machine);
					report.setSystemInfo("IP ADDRESS ", "http://192.168.148.223:4444");
					report.setSystemInfo("OPERATING SYSTEM ", OS);
					report.setSystemInfo("BROWSER NAME ", browserName);
					report.setSystemInfo("WEB URL", "https://www.bestbuy.com/");
					
					report.setSystemInfo("USERNAME", "Hari Prasath");
		}
		else
		{
					report.setSystemInfo("EXECUTED MACHINE ", machine);
					report.setSystemInfo("OPERATING SYSTEM ", System.getProperty("os.name"));
					report.setSystemInfo("OPERATING SYSTEM VERSION ", System.getProperty("os.version"));
					report.setSystemInfo("WEB URL", "https://www.bestbuy.com/");
					report.setSystemInfo("BROWSER NAME ", browserName);
					report.setSystemInfo("USERNAME", "Hari Prasath");                                     }
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			report.setSystemInfo("Groups", includedGroups.toString());
		}
		
	}
	
		@Override
		public void onTestSuccess(ITestResult result) {
			
			base = new BaseClass();
			Properties proper = Utilities.loadPropertiesFile();
			
			test = report.createTest(result.getTestClass().getName() +" : "+ result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.assignAuthor(proper.getProperty("Author"));
			test.assignDevice(base.browserInfo("nameversion"));
			test.log(Status.PASS, result.getName()+ " TEST PASSED!");
		}
	
		@Override
		public void onTestFailure(ITestResult result) {
			
			base = new BaseClass();
			Properties proper = Utilities.loadPropertiesFile();
			
			test = report.createTest(result.getTestClass().getName() +" : "+ result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.assignAuthor(proper.getProperty("Author"));
			test.assignDevice(base.browserInfo("nameversion"));
			test.log(Status.FAIL, result.getName()+ " TEST FAILED!");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			String capturePath = new BaseClass().screenshotITestFail(base.browserInfo("name") +"_"+ result.getName());
			test.addScreenCaptureFromPath(capturePath);
			//test.log(Status.INFO, "Failed Test screenshot", MediaEntityBuilder.createScreenCaptureFromPath(capturePath, result.getName()).build());
		}
	
		@Override
		public void onTestSkipped(ITestResult result) {
			
			base = new BaseClass();
			Properties proper = Utilities.loadPropertiesFile();
			
			test = report.createTest(result.getTestClass().getName() +" : "+ result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.assignAuthor(proper.getProperty("Author"));
			test.assignDevice(base.browserInfo("nameversion"));
			test.log(Status.SKIP, result.getName()+ " TEST SKIPPED!");
			test.log(Status.INFO, result.getThrowable().getMessage());
		}
	
		@Override
		public void onFinish(ITestContext context) {
		
			report.flush();
			
			//Opens report after the report created.
			try {
				Desktop.getDesktop().browse(extentPath.toURI());
			} catch (IOException io) {
				System.out.println("HTML ExtentReport Not Found!!" + io.getMessage());
			}
			
		}

	
	
	
	
	
	
	
	
	
}
