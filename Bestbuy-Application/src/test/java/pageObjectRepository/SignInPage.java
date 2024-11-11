package pageObjectRepository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends BasePage {

	
	public SignInPage(WebDriver driver) {

		super(driver);
		
	}
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX <-- LOCATORS --> XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

	//HEADER OF SIGNIN
	@FindBy(css = ".cia-section-title__header") private WebElement signInTitle;
	
	@FindBy(css = ".v-bg-pure-white > strong > div") private WebElement unableToSignInMsg;
//		<----------------------------- Email Address --------------------------------->		
	
	//Text field of Email Address
	@FindBy(id = "fld-e") private WebElement tfEmailAddress;
	//Placeholder text of email adddress
	@FindBy(css = "label[for='fld-e']") private WebElement plcEmailAddress;
	//Warning message of email address
	@FindBy(id = "fld-e-text") private WebElement wmEmailAddress;
	
//		<-------------------------------- Password ------------------------------------>		
	//Text field of Password
	@FindBy(id = "fld-p1") private WebElement tfPassword;
	//Placeholder text of Password
	@FindBy(css = "label[for='fld-p1']") private WebElement plcPassword;
	//Warning message of password
	@FindBy(id = "fld-p1-text") private WebElement wmPassword;
	//Eye-icon toggles
	@FindBy(id = "show-hide-password-toggle") private WebElement btnEyeIcon;
	
//	<------------------------------- Links and Button ----------------------------------->	
	//Forget password link
	@FindBy(css = ".cia-signin__forgot > a") private WebElement linkForgetPassword;
	
	//Different sign in button,
	//Sign In button.
	@FindBy(css = ".cia-form__controls > button") private WebElement btnSignIn;
	//Passkey button.
	@FindBy(css = ".cia-signin__webauthn-button-container > button") private WebElement btnPassKey;
	//Apple button.
	@FindBy(css = ".cia-signin__social--actions-container > button") private WebElement btnApple;
	//Google button.
	@FindBy(className = "google-container") private WebElement btnGoogle;
	
	
//		<------------------------------- Disclaimer Links ----------------------------------->	
	//Get all the Text.
	@FindBy(className = "cia-disclaimer") private WebElement textDisclaimer;
	
	@FindBy(css = ".cia-disclaimer > a:nth-child(1)") private WebElement linkTermsAndConditions;
	//Privacy policy link.
	@FindBy(css = ".cia-disclaimer > a:nth-child(2)") private WebElement linkPrivacyPolicy;
	//My bestbuy program terms.
	@FindBy(css = ".cia-disclaimer > a:nth-child(3)") private WebElement linkBestBuyProgram;
	
	//Text near to the Create an Account
	@FindBy(css = ".cia-signin__create > strong") private WebElement txtNearCreateAccount;
	//Create an Account hyperlink
	@FindBy(css = ".cia-signin__create > a") private WebElement linkCreateAccount;

//  	<------------------------------- Footer Links ----------------------------------->		
	//Footer terms and condition
	@FindBy(xpath = "(//a[normalize-space()='Terms and Conditions'])[3]") private WebElement footerTerms; 
	//Footer Privacy policy
	@FindBy(xpath = "(//a[normalize-space()='Privacy'])") private WebElement footerPolicy; 
	//Footer Interest Based Ads
	@FindBy(xpath = "//a[normalize-space()='Interest-Based Ads']") private WebElement footerInterestAds;
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX <-- ACTION METHODS --> XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX	
	
	//SignIn header Text
	public String txtSigninHeader() {return signInTitle.getText();}
	public String txttopErrorMessage() { 
		
		sleep = new WebDriverWait(driver, Duration.ofSeconds(15));
		sleep.until(ExpectedConditions.visibilityOfAllElements(unableToSignInMsg));
		return unableToSignInMsg.getText(); 
		}
	//true or false
	public boolean istopErrorMessageDisplay() { 
		
		sleep = new WebDriverWait(driver, Duration.ofSeconds(15));
		sleep.until(ExpectedConditions.visibilityOfAllElements(unableToSignInMsg));
		return unableToSignInMsg.isDisplayed(); 
		}
	
	//ClearBoth EMAIL and PASSWORD fields
	public void clearEmailPasswordFields() {
			tfEmailAddress.clear();
			tfPassword.clear();
	}
	
	//Text send to Sign In email address.
	public void tfSignInEmail(String emailaddress) {  tfEmailAddress.sendKeys(emailaddress);}
	//PlaceholderText of Sign In email address.
	public String plcTxtSignInEmail() { return (plcEmailAddress.getText());}
	//Warning message of Sign In email address.
	public String wmSignInEmail() { return (wmEmailAddress.getText());}
	
	//Send text to the SignIn Password.
	public void tfSignInPassword(String password) { tfPassword.sendKeys(password); }
	//PlaceholderText of Sign In password.
	public String plcTxtSignInpassword() { return (plcPassword.getText());}
	//Warning message of Sign In password.
	public String wmSignInPassword() { return (wmPassword.getText());}
	//Click the Eye-icon toggles
	public void clkEyeIconBtn() { btnEyeIcon.click(); }
	
	//Forget password Link.
	public void clkForgetPassword() {  linkForgetPassword.click(); }
	
	//Sign In button.
	public void clkSignInBtn() { btnSignIn.click(); }
	
	//PassKey button.
	public void clkPassKeyBtn() { btnPassKey.click(); }
	//Apple button.
	public void clkAppleBtn() { btnApple.click(); }
	//Google button
	public void clkGoogleBtn() { btnGoogle.click(); }
	
	//Disclaimer 
	public String txtDisclaimer() { return (textDisclaimer.getText());}
	//Click on the Terms and Conditions link
	public void clkSignInTerms() { linkTermsAndConditions.click(); }
	//Click on the Terms and Conditions link
	public void clkSignInPrivacyPolicy() { linkPrivacyPolicy.click(); }
	//Click on the Terms and Conditions link
	public void clkSignInBestBuyProgram() { linkBestBuyProgram.click(); }
		
	//Create account page link from signin page.
	public void linkCreateAnAccount() { linkCreateAccount.click(); }
	
	//Footer links to click
	public void clickFooterterms() { footerTerms.click();}
	public void clickFooterPolicy() { footerPolicy.click();}
	public void clickFooterInterestAds() { footerInterestAds.click();}
	
	
	
}
