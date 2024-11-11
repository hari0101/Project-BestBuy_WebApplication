package tdd;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjectRepository.HomePage;

public class TestDriven {

	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.bestbuy.com/?intl=nosplash");
		
		HomePage page = new HomePage(driver);
		
		List<WebElement> elements = page.getAllLinksHomepage();
		
		for(WebElement links : elements)
		{
					String url = links.getAttribute("href");
					System.out.println(url);
		}
		
	}

}
