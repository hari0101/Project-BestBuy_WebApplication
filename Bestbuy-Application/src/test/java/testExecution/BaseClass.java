package testExecution;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.Utilities;


public class BaseClass {
	
	//Base class contains the method which are boilercode!
	
	public static WebDriver driver;
	protected static Logger log = LogManager.getLogger(BaseClass.class.getName());
	protected JavascriptExecutor js;
	
	@BeforeClass(groups = {"Driver_checkin"})
	@Parameters({"Machine","OS", "Browser"})
	public void setUp(String machine,String os, String browser) {
	
	if(machine.equalsIgnoreCase("REMOTEHOST"))
	{
		
		DesiredCapabilities remoteCap = RemoteBaseClass.capabilitiesRemote(os, browser);
		try {
				driver = new RemoteWebDriver(new URL("http://192.168.148.223:4444"), remoteCap);
				log.info("RemoteWebDriver Launched at Platform : " + machine +" | OS : " + os + " | Browser : " +browser + "\n");
				
		} 
		catch (MalformedURLException murle) {
			
			log.error("Invalid Selenium Grid URI" + murle.getMessage());
		}
	}
	
	else if(machine.equalsIgnoreCase("LOCALHOST")) {
		
	switch(browser.toLowerCase())
	{
	case "chrome" :	driver = new ChromeDriver();
	break;
	case "edge" : 	driver = new EdgeDriver();
	break;
	case "firefox" : driver = new FirefoxDriver();
	break;
	default : System.out.println("INVALID BROWSER NAME DRIVER CANNOT INITIATE!!");
	return;
	}
	log.info(browser + " Browser as Launched Successfully!." + "\n");
	}
	driver.manage().window().maximize();	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().deleteAllCookies();
	
	driver.get("https://www.bestbuy.com/?intl=nosplash");
	
	}
	
	@AfterClass(groups = {"Driver_checkout"})
	@Parameters({"Browser"})
	public void tearDown(String browserName) {
		
		driver.quit();	
		
		log.info("The "+browserName+" Browser windows are closed!." + "\n");
	}
	
	
	
// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX DRIVER-LEVEL-METHODS XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	
	
	//Returns BrowserInfo Dynamically.
	public String browserInfo(String browserAttribute) {
		
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

		if(browserAttribute.equalsIgnoreCase("name")) {
			String browserName = cap.getBrowserName();
			return browserName;
		}
		else if(browserAttribute.equalsIgnoreCase("version")) {
			String browserVersion = cap.getBrowserVersion();
			return browserVersion;
		}
		else if(browserAttribute.equalsIgnoreCase("nameversion")) {
			String browserNameVer = cap.getBrowserName() + "_V" + cap.getBrowserVersion();
			return browserNameVer;
		}
		
		return null;
	}
	
	
	public String screenshotITestFail(String filename) {
		
		TakesScreenshot capture = (TakesScreenshot) driver;
		File imagefile = capture.getScreenshotAs(OutputType.FILE);
		
		Properties prop = Utilities.loadPropertiesFile();
		String destPath = System.getProperty("user.dir")+prop.getProperty("ScreenshotFailTest")+Utilities.nowDateTime()+"_FAILED_"+filename+".png";

		try {
			
			FileUtils.copyFile(imagefile, new File(destPath));
			System.out.println("ON-TEST-FAIL SCREENSHOT SAVED!!  at" + destPath + "\n");
			
		} 
		catch (IOException IO) {
			
			System.out.println("INVALID PATH " + destPath +" "+IO.getMessage());
		}
		
		return destPath;
}
	
	
	
	
}
