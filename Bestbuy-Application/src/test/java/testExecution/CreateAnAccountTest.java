package testExecution;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjectRepository.CreateAccountPage;
import pageObjectRepository.HomePage;
import pageObjectRepository.SignInPage;

public class CreateAnAccountTest extends BaseClass {
	
	@Test(description = "Verify we can navigate to Create an Account.", 
			priority = 0, groups = {"maventest"})
	public void createAccountPage_TC001_TC009() {
		
		//Homepage Object
		HomePage home = new HomePage(driver);
		//click the account dropdown
		home.ddlAccountClick();
		//click the Create account button
		home.btnAccountClick();
		
		//CreateAccount object
		CreateAccountPage createAcc = new CreateAccountPage(driver);
		//header
		String heading = createAcc.headerCreateAccount();
		try {
		Assert.assertEquals(heading, "Create an Account");
		}
		catch(AssertionError ae) {
			log.error(ae.getMessage());
			System.out.println(ae.getMessage());
		}
		//log.info(heading);
		System.out.println("User Successfully reached : " + heading + "\n");
		
	}
	
		@Test(description = "Verify user can enter to input fields.", 
				priority = 1, dependsOnMethods = {"createAccountPage_TC001_TC009"}, groups = {"smoketest"})
		public void validInputFields_TC003_TC011() {
			
		CreateAccountPage createAcc = new CreateAccountPage(driver);
		
		//Verify it accepts the valid inputs on field.
		createAcc.firstnameTxtField("Mark1");	
		createAcc.lastnameTxtField("tyson2");	
		createAcc.emailTxtField("marktyson012@gmail.com");	
		createAcc.passwordTxtField("Bestbuy!@# 123456");	
		createAcc.confirmPasswordTxtField("Bestbuy!@# 123456");
		
		createAcc.mobileNumberTxtField("9786059987");
		createAcc.btnClkCreateAnAccount();
		
		String errormsg = createAcc.errorMessageText();
		System.out.println(errormsg);
		
		//Test fails if the error message displayed when valid input given
		if(createAcc.errorMessageBool()) { Assert.assertTrue(false); }
		
		
		}
		
		@Test(description = "Verify form should not create account with an Empty fields.", 
				priority = 2, groups = {"Empty"})
		public void emptyCredentials_TC013_to_TC018() {
			
			//Navigate to the Create Account page
			this.createAccountPage_TC001_TC009();
			
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			
			//Verify it accepts the valid inputs on field.
			createAcc.firstnameTxtField("");	
			createAcc.lastnameTxtField("");	
			createAcc.emailTxtField("");	
			createAcc.passwordTxtField("");	
			createAcc.confirmPasswordTxtField("");
			
			createAcc.mobileNumberTxtField("");
			createAcc.btnClkCreateAnAccount();
			
			//Display the warning message.
			createAcc.allWarnmsg();
		}	
		
		@Test(dataProvider = "Create_Account_Invalid_Input", dataProviderClass = utilities.DataProviders.class, description = "Verify the invalid credentials account should not created.", 
				priority = 3, groups = {"invalid"})
		public void invalidCredentials(String firstName, String lastname, String emailaddress, String password, String confirmpassword, String mobilenumber) {
			
			//Dataproviding method should be Alone no other this.(method_name) allowed it will interrupt.
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			createAcc.firstnameTxtField(firstName);	
			createAcc.lastnameTxtField(lastname);	
			createAcc.emailTxtField(emailaddress);	
			createAcc.passwordTxtField(password);	
			createAcc.confirmPasswordTxtField(confirmpassword);
			
			createAcc.mobileNumberTxtField(mobilenumber);
			createAcc.btnClkCreateAnAccount();
			driver.navigate().refresh();
		
		}
		
		
		@Test(description = "Verify we can navigate to SignIn from Account page.", 
				priority = 4, groups = {"sanity"})
		public void navigateToSignIN_049_050() {
			
			driver.navigate().back();
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			this.createAccountPage_TC001_TC009();
			createAcc.clkSignInlink();
			
			SignInPage signin = new SignInPage(driver);
			String signInHeader= signin.txtSigninHeader();
			
			Assert.assertEquals("Sign In to Best Buy", signInHeader);
			log.info("Successfully navigated to SignIn page from Create an Account page!" + "\n");
			System.out.println("Heading : " + signInHeader);
			
		}
		
		@Test(description = "Verify we can go homepage by clicking on Logo and returnpage Links.", 
				priority = 5, groups = {"logo"})
		public void headerLinkToGetBack_TC056_TC058() {
			
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			//Homepage to Create an Account
			this.createAccountPage_TC001_TC009();
			log.info("Current Page : " + driver.getTitle() + "\n");
			
			//Create an Account to Homepage.
			createAcc.logoLink();
			log.info("Current Page : " + driver.getTitle() + "\n");
			
			//Homepage to Create an Account
			this.createAccountPage_TC001_TC009();
			log.info("Current Page : " + driver.getTitle() + "\n");
			
			//Create an Account to which ever page is previous
			createAcc.returnPreviousLink();
			log.info("Current Page : " + driver.getTitle() + "\n");
			
		}
		
		@Test(description = "Verify navigate the agreement pages.", 
				priority = 6, groups = {"Agreements"})
		public void agreementsBestBuy_TC044_TC046() {
			
			//Getting the parent window
			String parentTab = driver.getWindowHandle();
			System.out.println("TITLE - " + driver.getTitle());
			System.out.println("PARENT ID -> " +  parentTab);
			
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			//Click all the agreement to check whether they are navigating right page.
			createAcc.clkTermsAndConditions();
			createAcc.clkPrivacyPolicy();
			createAcc.clkBestBuyProgram();
				
				//Getting all the Windows in the Arraylist.
				List<String> tabList = new ArrayList<>(driver.getWindowHandles());	
				System.out.println("ARRAYLIST : " + tabList + "\n");
				
				//Loop the switch window, get title and close all agreement page.
				for(int i=3; i>0; i--) {
				driver.switchTo().window(tabList.get(i));
				System.out.println("USER AGREEMENT PAGE TITLE - " + driver.getTitle() );
				driver.close();
				}
			//Back to parent 
			driver.switchTo().window(parentTab);
			System.out.println("TITLE - " + driver.getTitle());
			
		}
		
		@Test(description = "Verify all placeholder text are valid.", 
				priority = 7, groups = {"place"})
		public void verifyAllPlaceHolder() {
			
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			
			//Expected Placeholder
			List<String> expectedPlaceHolder = new ArrayList<>();
				expectedPlaceHolder.add("First Name");
				expectedPlaceHolder.add("Last Name");
				expectedPlaceHolder.add("Email Address");
				expectedPlaceHolder.add("Passwordex. \"Nine+twelve=21\"");
				expectedPlaceHolder.add("Confirm Password");
				expectedPlaceHolder.add("Mobile Phone Number");
			
			//Actual PlaceHolder
			List<String> actualPlaceHolder = new ArrayList<>();
				actualPlaceHolder.add(createAcc.firstnamePlcText());
				actualPlaceHolder.add(createAcc.lastnamePlcText());
				actualPlaceHolder.add(createAcc.emailPlcText());
				actualPlaceHolder.add(createAcc.passwordPlcText());
				actualPlaceHolder.add(createAcc.confirmPasswordPlcText());
				actualPlaceHolder.add(createAcc.mobileNumberPlcText());
			
			System.out.println(actualPlaceHolder);
			Assert.assertEquals(actualPlaceHolder, expectedPlaceHolder, "The lists do not match!");
		}
		
		@Test(description = "Verify all footer links navigate to page.", 
				priority = 8, groups = {"footer"})
		public void footerLinks() {
			
			CreateAccountPage createAcc = new CreateAccountPage(driver);
			
			//Terms and Conditions
			createAcc.clkFooterTerms();
			System.out.println("PAGE TITLE --> " + driver.getTitle());
			driver.navigate().back();
			//Privacy policy
			createAcc.clkFooterPrivacy();
			System.out.println("PAGE TITLE --> " + driver.getTitle());
			driver.navigate().back();
			//Interest Based Ads
			createAcc.clkFooterInterestAds();
			System.out.println("PAGE TITLE --> " + driver.getTitle());
			driver.navigate().back();
			//Mobile-Site view
			createAcc.clkMobileFullSite();
			
		}
		

}
		
		
		





