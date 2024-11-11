package pageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;

//Parent for all the Object page initialize the driver
public class BasePage {

		
	public WebDriver driver;
	public JavascriptExecutor js;
	protected WebDriverWait sleep;
	
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	
	
	
	
}
