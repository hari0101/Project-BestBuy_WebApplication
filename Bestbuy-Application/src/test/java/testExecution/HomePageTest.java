package testExecution;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObjectRepository.HomePage;

public class HomePageTest extends BaseClass {

	HomePage home;
	
	@Test(description = "Verify the Broken URL in Homepage.", priority = 0, groups = {"null"})
	public void brokenURLHomepage_TC102_to_TC103() {
		
		 
		 home = new HomePage(driver);
		 List<WebElement> listLinks = home.getAllLinksHomepage();
		 System.out.println("No of links : " + listLinks.size());
		 
		 for(WebElement link : listLinks) {
			 
			 js = (JavascriptExecutor) driver;	
			 js.executeScript("arguments[0].scrollIntoView();", link);
			 
			 //WebDriverWait sleeps = new WebDriverWait(driver, Duration.ofSeconds(20));
			 //sleeps.until(ExpectedConditions.visibilityOfAllElements(link));
			 
			 String hrefUrl = link.getAttribute("href");
		 try {
				URL url = new URL(hrefUrl);
			
				HttpURLConnection connecthttps = (HttpURLConnection) url.openConnection();
				connecthttps.setConnectTimeout(5000);
				connecthttps.connect();
				
				int responsecode = connecthttps.getResponseCode();
		 
				if(responsecode>=200 && responsecode<400) {
					
					System.out.println("UnBroken URL : " + link.getText() +", Status Code ="+ responsecode);
				}
				if(responsecode<200 && responsecode>=400)
				{
					System.out.println("Broken URL : " + link.getText() +", Status Code ="+ responsecode);
				}
				
				
		 	} catch (MalformedURLException murle) { System.out.println("Invalid Homepage URL, Pass the proper syntax URL " + murle.getMessage()); }
		 	  catch (IOException ioe) { System.out.println("Unable to communicate and connect with server " + ioe.getMessage()); }
		 	  catch(StaleElementReferenceException sere) { System.out.println("Element no longer available in the DOM " + sere.getMessage());}
		
		 }
	
	}
		 @Test(description = "Verify the below Order-Purchases Links in Homepage.", priority = 1, groups = {"bottom"})
			public void bottomOrderPurchasesLinks_TC104_to_TC110() {
			 
			 home = new HomePage(driver);
			 List<WebElement> orderPurchaseslist = home.getbottomOrderPurchasesLinks();
			 String parentTab = driver.getWindowHandle();
			 
			 for(WebElement link : orderPurchaseslist)
			 {
				 	Actions act = new Actions(driver);
				 	
				 	act.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
				 	List<String> list = new ArrayList<>(driver.getWindowHandles());
				 	driver.switchTo().window(list.get(1));
				 	System.out.println("PAGE TITLE : " + driver.getTitle() + "\n");
				 	driver.close();
				 	
				 	driver.switchTo().window(parentTab);
			 }
			 
			 
			 System.out.println("OUTPUT : All the " + orderPurchaseslist.size() + " links are navigated successfully!" );
			 
		 }
		 
		 @Test(description = "Verify user can search product and add to cart.", priority = 2, 
				 groups = {"cart"})
		 public void searchAddItemsCart() {
			 
			 home = new HomePage(driver);
			 home.searchItemAndClick("laptops");
			 
			 home.clickAddToCart();
			 
			 if(home.isAddedToCart() == true) {
				 
				 home.clickContinueShopping();
				 home.goAddToCartPage();
				 System.out.println("PAGE TITLE " + driver.getTitle());
			 }
			 else
			 {
				 home.goAddToCartPage();
				 System.out.println("PAGE TITLE " + driver.getTitle());
			 }
			 
		 }
		
		 
	
	
	
}
