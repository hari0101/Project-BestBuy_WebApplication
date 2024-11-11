package testExecution;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectRepository.HomePage;
import pageObjectRepository.SignInPage;

public class SignInTest extends BaseClass {

	SignInPage signin;	
	HomePage home;
	
	@Test(description = "Verify we can navigate to SignIn.", priority = 0, groups = {"Smoke", "Sanity"})
	public void signInPage_TC061() {
		
		home = new HomePage(driver);
		
		home.ddlAccountClick();
		home.btnSignInClick();
		
		signin = new SignInPage(driver);
		String signinheader = signin.txtSigninHeader();
		Assert.assertEquals(signinheader, "Sign In to Best Buy");
		log.info("Successfully navigated to the SignIn page!!" + "\n");
		
	}
	
	
	@Test(description = "Verify user can SignIn to the BestBuy.", priority = 1, groups = {"Smoke", "Sanity"})
	public void validateSignINInput_TC068_to_TC071() {
		
		signin = new SignInPage(driver);
		
		signin.tfSignInEmail("bestbuytest140@gmail.com");
		signin.tfSignInPassword("Bestbuy@#12345 bester");
		signin.clkSignInBtn();
		
		if(signin.istopErrorMessageDisplay() == true) {
			
			System.out.println("Unable to SignIn account with valid credentials! TEST FAIL" );
			System.out.println("Error Message Displayed as : " + signin.txttopErrorMessage());
			Assert.assertTrue(false);
			log.error(new AssertionError("Test : validateSignINInput_TC068_to_TC071 Failed"));
		}
		else {
			log.info("SignIn with valid credentials is successful, PAGE TITLE : " + driver.getTitle());
		}
		
		signin.clearEmailPasswordFields();
		driver.navigate().refresh();	
		
	}
	@Test(dataProvider ="SignIn_Account_Invalid_Input", dataProviderClass = utilities.DataProviders.class, 
			description = "Verify user cannot SignIn to the BestBuy.", priority = 2, groups = {"Smoke", "Sanity"})
	public void invalidateSignInInput_TC072_to_TC077(String emailAddress, String password) {
		
		signin = new SignInPage(driver);
		
		signin.tfSignInEmail(emailAddress);
		signin.tfSignInPassword(password);
		signin.clkSignInBtn();
		
		if(signin.istopErrorMessageDisplay() == true) {
			
			System.out.println("Unable to SignIn account with Invalid credentials! TEST PASS" );
		}
		else {
		
			System.out.println("SignIn with Invalid credentials is successful, PAGE TITLE : " + driver.getTitle());
			Assert.assertFalse(true);
			log.error(new AssertionError("Test : invalidateSignInInput_TC072_to_TC077 Failed"));
		}
		
		signin.clearEmailPasswordFields();
		driver.navigate().refresh();
		
	}
	
	@Test(description = "Verify user can SignIn to the BestBuy.", priority = 3, groups = {"Smoke", "Sanity"})
	public void agreementBestbuy_TC085_to_TC087() {
		
		//Getting the parent window
		String parentTab = driver.getWindowHandle();
		System.out.println("TITLE - " + driver.getTitle());
		System.out.println("PARENT ID -> " +  parentTab);
		
		signin = new SignInPage(driver);
		//Click all the agreement to check whether they are navigating right page.
		signin.clkSignInTerms();
		signin.clkSignInPrivacyPolicy();
		signin.clkSignInBestBuyProgram();
		
		List<String> list = new ArrayList<>(driver.getWindowHandles());	
		System.out.println("ARRAYLIST : " + list + "\n");
		
		//Loop the switch window, get title and close all agreement page.
		for(int i=3; i>0; i--) {
		driver.switchTo().window(list.get(i));
		System.out.println("USER AGREEMENT PAGE TITLE : " + driver.getTitle() );
		driver.close();
		}
	
		driver.switchTo().window(parentTab);
		System.out.println("TITLE - " + driver.getTitle());
		
		driver.navigate().refresh();
		
	}
	
	@Test(description = "Verify user can SignIn to the BestBuy.", priority = 4, groups = {"Smoke", "Sanity"})
	public void redirectToCreateAccount_TC092_to_TC094() {
		
		//Enters the Valid details in the SignIn page and click enter.
		
		signin = new SignInPage(driver);
		
		signin.tfSignInEmail("bestbuytest140@gmail.com");
		signin.tfSignInPassword("Bestbuy@#12345 bester");
		signin.clkSignInBtn();
		
		if(signin.istopErrorMessageDisplay() == true) {
			
			System.out.println("ERROR MESSAGE " + signin.txttopErrorMessage());
			signin.linkCreateAnAccount();
			System.out.println("PAGE-TITLE " + driver.getTitle());
		}
		else { 
			System.out.println("PAGE-TITLE " + driver.getTitle()); 
			Assert.assertFalse(true);
			log.error(new AssertionError("Test : redirectToCreateAccount Failed"));
		}
		
		
	}
	@Test(description = "Verify user can SignIn to the BestBuy.", priority = 5, groups = {"Smoke", "Sanity"})
	public void footerLinks_TC095_TC101() {
		
		signin = new SignInPage(driver);
		driver.navigate().refresh();
		
		//Terms and Conditions
		signin.clickFooterterms();
		System.out.println("PAGE TITLE --> " + driver.getTitle());
		driver.navigate().back();
		//Privacy policy
		signin.clickFooterPolicy();
		System.out.println("PAGE TITLE --> " + driver.getTitle());
		driver.navigate().back();
		//Interest Based Ads
		signin.clickFooterInterestAds();
		System.out.println("PAGE TITLE --> " + driver.getTitle());
		driver.navigate().back();
	
	}	
	
}
