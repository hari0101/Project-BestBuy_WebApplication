package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

public class Utilities {

	
public static Properties loadPropertiesFile() {
		
		Properties prop = null;
		
		try {
			prop = new Properties();
			FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Data.properties");
			prop.load(fr);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
public static String nowDateTime() {
	
		LocalDateTime local = LocalDate.now().atTime(LocalTime.now());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E_dd-MMM-yyyy_hh-mm-ss a");
		String randomname = local.format(formatter);
		return randomname;	
}

public static String fileName() {
		
		String datetime = Utilities.nowDateTime();
		 return ("Screenshot_"+ datetime);
		
}

public static String extentReportPathName() {
	
	Properties path = Utilities.loadPropertiesFile();
	String dynamicName= Utilities.nowDateTime();
	String reportPath = System.getProperty("user.dir")+path.getProperty("Extentpath")+"Extent-Test-Report_"+dynamicName+".html";
	return reportPath;
	
}



public static String Screenshot(WebDriver driver, String filename) {
	
		TakesScreenshot capture = (TakesScreenshot) driver;
		File imagefile = capture.getScreenshotAs(OutputType.FILE);
		
		Properties prop = Utilities.loadPropertiesFile();
		String destPath = System.getProperty("user.dir")+prop.getProperty("ScreenshotFile")+filename+".png";

		try {
			
			FileUtils.copyFile(imagefile, new File(destPath));
			System.out.println("SCREENSHOT SAVED!!  at" + destPath + "\n");
			
		} 
		catch (IOException IO) {
			
			System.out.println("INVALID PATH " + destPath +" "+IO.getMessage());
		}
		
		return destPath;
}

public static String fullScreenshot(String filename) {
	
	Screen capture = new Screen();
	
	Properties prop = Utilities.loadPropertiesFile();
	String destPath = System.getProperty("user.dir")+prop.getProperty("FullScreenshot")+filename+".png";

	capture.getImage().save(destPath);
	
	System.out.println("FULL-SCREENSHOT SAVED!!  at" + destPath + "\n");

	return destPath;
}
	




	
}
