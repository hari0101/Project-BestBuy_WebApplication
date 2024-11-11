package tdd;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class RemoteCapcheck {

private static DesiredCapabilities descap;	
	
public static void main(String[] args) {
	
	DesiredCapabilities newcap = nameDES("linux","edge");
	
	DesiredCapabilities newsecondcap = nameDES("MAC","chrome");
	
	
	System.out.println(newcap);
	System.out.println(newsecondcap);
	
	DesiredCapabilities newmegre = newsecondcap.merge(newcap);
	
	System.out.println();
	System.out.println(newmegre);
}

	public static DesiredCapabilities nameDES(String platformName, String browserName) {
		
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
		else
		{
			System.out.println("Invalid Platform name "+platformName+ ", Unable to start the RemoteDrivers!");
		}
		
		switch(browserName.toLowerCase()) {
		
		case "chrome" : descap.setBrowserName("chrome");
		break;
		case "firefox" : descap.setBrowserName("firefox");
		break;
		case "edge" : 	descap.setBrowserName("MicrosoftEdge");
		break;
		default : System.out.println("Invaild browsername "+browserName+ "Unable to start the RemoteDrivers!");
		return null;
		}
		
		return descap;
		
	}

	
}