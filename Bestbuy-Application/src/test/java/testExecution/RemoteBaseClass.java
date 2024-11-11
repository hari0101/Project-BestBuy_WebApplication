package testExecution;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RemoteBaseClass {

private static Logger log = LogManager.getLogger(RemoteBaseClass.class.getName());	
private static DesiredCapabilities descap;	
	

	public static DesiredCapabilities capabilitiesRemote(String platformName, String browserName) {
		
		 descap = new DesiredCapabilities();
		
		if(platformName.equalsIgnoreCase("Windows")) {
			descap.setPlatform(Platform.WIN11);
		}
		else if(platformName.equalsIgnoreCase("Mac")) {
			descap.setPlatform(Platform.MAC);
		}
		else if(platformName.equalsIgnoreCase("Linux")) {
			descap.setPlatform(Platform.LINUX);
		}
		else {log.error("Invalid Platform name "+platformName+ ", Unable to start the RemoteDrivers!");}
		
		
		switch(browserName.toLowerCase()) {
		
		case "chrome" : descap.setBrowserName("chrome");
		break;
		case "firefox" : descap.setBrowserName("firefox");
		break;
		case "edge" : 	descap.setBrowserName("MicrosoftEdge");
		break;
		default : log.error("Invaild browsername "+browserName+ ", Unable to start the RemoteDrivers!");
		return null;
		}
		
		return descap;
	}

	
	
	
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	URL XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	

	public static void urlResponseStatus(WebDriver driver, String WebAddress) {
		
		BaseClass base = new BaseClass();
		try {
			URL url = new URL(WebAddress);
			HttpURLConnection urlconnect = (HttpURLConnection) url.openConnection();
			urlconnect.setRequestMethod("GET");
			
			int responsecode = urlconnect.getResponseCode();
			String responsename = urlconnect.getResponseMessage();
			
			if(responsecode>=200 && responsecode<400) {
				
				log.info("URL Navigated to "+base.browserInfo("name")+" browser : Status Code " +responsecode+" "+responsename);
				driver.navigate().to(url);
			}
			else {
				
				log.info("URL Failed to Access "+ base.browserInfo("name")+" browser : Status Code " +responsecode+" "+responsename);
				driver.quit();
			}
		} 
		catch (MalformedURLException murle) {
			murle.printStackTrace();
			log.error("Invalid URL string as invoked " + murle.getMessage());	
		} 
		catch (IOException io) {
			io.printStackTrace();
			log.error("HttpURLConnection got Failed " + io.getMessage());
		}
	}
	
	
	
	
	
	
}
