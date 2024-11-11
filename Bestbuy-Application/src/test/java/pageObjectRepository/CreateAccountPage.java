package pageObjectRepository;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage extends BasePage {

	
	//Constructor to initialize the Driver and @FindBy annotation.
	public CreateAccountPage(WebDriver driver) {
	
		super(driver);
	}
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX <-- LOCATORS --> XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//		<----------------------------- Header Links --------------------------------->		
	//Header of the create account page.
	@FindBy(xpath = "(//*[name()='svg'][@class='mini-header__logo'])[1]") private WebElement linkLogo;
	@FindBy(className = "mini-header__nav") private WebElement linkReturnPrevious;
	
	//Header
	@FindBy(css = ".cia-settings-container > div > h1") private WebElement headerAccount;
	//All empty warning
	@FindBy(css = ".tb-validation") private List<WebElement> allWarningMessage;
	//Dynamic heading if the account unable to create.
	@FindBy(css = ".c-alert-content > strong > div") private WebElement errorMessage;
	
//		<------------------------------- First Name --------------------------------->
	//First Name Text field.
	@FindBy(id = "firstName") private WebElement tfFirstName;
	//Placeholder text firstname.
	@FindBy(css = "label[for='firstName']") private WebElement plctextFirstName;
	//Warning msg.
	@FindBy(css = "#firstName-text") private WebElement wmFirstName;
	
//		<------------------------------- Last Name ---------------------------------->
	//Last Name Text field.
	@FindBy(id = "lastName") private WebElement tfLastName;
	//Placeholder text lastname.
	@FindBy(css = "label[for='lastName']") private WebElement plctextLastName;
	//Warning msg.
	@FindBy(css = "#lastName-text") private WebElement wmLastName;
		
//		<------------------------------ Email Address -------------------------------->
	//Email Address text field.
	@FindBy(id = "email") private WebElement tfEmail;
	//Placeholder text lastname.
	@FindBy(css = "label[for='email']") private WebElement plctextEmail;
	//Warning msg.
	@FindBy(css = "#email-text") private WebElement wmEmail;
		
//		<--------------------------------- Password ----------------------------------->
	//Password input field.
	@FindBy(id = "fld-p1") private WebElement tfPassword;
	//Placeholder text Password.
	@FindBy(css = "label[for='fld-p1']") private WebElement plcPassword;
	//Warning password.
	@FindBy(css = "#fld-p1-text") private WebElement wmPassword;
	//Pseudo-code element.
	@FindBy(css = ".tb-label-hint ") private WebElement beforePlcPassword;
	//Password Eye-icon button.
	@FindBy(id = "show-hide-password-toggle") private WebElement eyeBtnPassword;
		
//		<------------------------------ Confirm Password -------------------------------->
	//Confirm Passwword input field.
	@FindBy(id = "reenterPassword") private WebElement tfConfirmPassword;
	//Placeholder text confirm password.
	@FindBy(css = "label[for='reenterPassword']") private WebElement plcConfirmPassword;
	//Confirm password Eye-icon button.
	@FindBy(id = "show-hide-reenter-password-toggle") private WebElement eyeBtnConfirmPassword;
	//Warning message with password and confirm password as empty and DO_NOT_MATCH.
	@FindBy(id = "reenterPassword-text") private WebElement dynaWMConfirmPassword;

//		<----------------------------- Mobile Phone Number ------------------------------->	
	//Mobile number text field.
	@FindBy(name ="phone") private WebElement tfMobileNumber;
	//Placeholder text of mobile number.
	@FindBy(css = "label[for='phone']") private WebElement plcMobileNumber;
	//Warning msg.
	@FindBy(id = "phone-text") private WebElement wmMobileNumber;
	//Info button
	@FindBy(css = "button[aria-label='Mobile Phone Number info']") private WebElement infoBtnMobileNumber;
	//Mobile Number info message
	@FindBy(id = "cia-create-acc-phone") private WebElement infoMsgMobileNumber;
	//Info message close button
	@FindBy(css = "button[class='c-close-icon']") private WebElement infoCloseBtnMobileNumber;
	
//		<----------------------------- Create Account Buttons -------------------------------->
	//Checkbox 
	@FindBy(css = ".c-checkbox-brand") private WebElement checkBoxRecover;
	//Create account button
	@FindBy(css = ".cia-form__controls ") private WebElement btnCreateanAccount; 
	@FindBy(css = "div[class='text']") private WebElement txtOR;
	//Google signup button
	@FindBy(css = ".google-container") private WebElement btnGoogleSignUp;
	
//		<-------------------------------- Agreement Links ------------------------------------>	
	//disclaimer Text of create account.
	@FindBy(className = "cia-disclaimer") private WebElement disclaimerCreateAccount;
	//Terms and Conditions link.
	@FindBy(css = ".cia-disclaimer > a:nth-child(1)") private WebElement linkTermsAndConditions;
	//Privacy policy link.
	@FindBy(css = ".cia-disclaimer > a:nth-child(2)") private WebElement linkPrivacyPolicy;
	//My bestbuy program terms.
	@FindBy(css = ".cia-disclaimer > a:nth-child(3)") private WebElement linkBestBuyProgram;
	
// 		<-------------------------------- SignIn Links ------------------------------------>	
	
	//Text near SigIn
	@FindBy(css = ".cia-create__signin > strong") private WebElement txtNearSignin;
	//SignIn link.
	@FindBy(css = ".cia-create__signin > a") private WebElement linkSignIn;
	
//		<-------------------------------- Footer Links ------------------------------------>	
	
	//All the footer text
	@FindBy(className = "mini-footer__inner") private WebElement txtFooterInfo;
	//Terms and Condition link on footer
	@FindBy(css = ".mini-footer__nav > ul > li:nth-child(1)") private WebElement linkFooterTerms;
	//Privacy link on footer
	@FindBy(css = ".mini-footer__nav > ul > li:nth-child(2)") private WebElement linkFooterPolicy;
	//Interest based ads link on footer.
	@FindBy(css = ".mini-footer__nav > ul > li:nth-child(3)") private WebElement linkFooterInterestAds;
	//Mobile site link on footer.
	@FindBy(css = ".mini-footer__actions > button") private WebElement btnFooterSite;
	
//<------------------------------------------------------------------------------------------------------------------------------------>
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX <-- ACTION METHODS --> XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	/* Header link elements
	 Logo as link */
	public void logoLink() { linkLogo.click(); }
	//Return previous page.
	public void returnPreviousLink() { linkReturnPrevious.click(); }
	//Header of the Create an Account
	public String headerCreateAccount() { return (headerAccount.getText());}
	//Error message Heading if account failed to create account.
		public String errorMessageText() {return (errorMessage.getText()); } 
		//Error message returns boolean.
		public boolean errorMessageBool() { return (errorMessage.isDisplayed()); } 
	//Get all the Warning message
	public void allWarnmsg() {
		
		//If the elements all empty
		List<WebElement> element = allWarningMessage;
		for(WebElement list :element)           {
			System.out.println(list.getText()); }
	
	}	
	
	//First Name methods.
	public void firstnameTxtField(String firstname) {  tfFirstName.sendKeys(firstname);  }
	//returns firstname placeholder text.
	public String firstnamePlcText() {  return (plctextFirstName.getText());  }
	//returns firstname warning msg.
	public String firstnameWM() {  return (wmFirstName.getText()); }
	
	//Last Name text field.
	public void lastnameTxtField(String lastname) {  tfLastName.sendKeys(lastname);  }
	//returns lastname placeholder text.
	public String lastnamePlcText() {  return (plctextLastName.getText());  }
	//returns lastname warning msg.
	public String lastnameWM() {  return (wmLastName.getText()); }
		
	//Email text field.
	public void emailTxtField(String email) {  tfEmail.sendKeys(email);  }
	//returns Email placeholder text.
	public String emailPlcText() {  return (plctextEmail.getText());  }
	//returns Email warning msg.
	public String emailWM() {  return (wmEmail.getText()); }
	
	//Password text field.
	public void passwordTxtField(String password) {  tfPassword.sendKeys(password);  }
	//returns password placeholder text.
	public String passwordPlcText() {  return (plcPassword.getText());  }
	//Before input placeholder text on password field.
	public String passwordBeforePlaceholder() {return (beforePlcPassword.getText());}
	//returns password warning msg.
	public String passwordWM() {  return (wmPassword.getText()); }
	//password Eye-icon button click.
	public void passwordEyeBtn() {  eyeBtnPassword.click(); }
	
	//Confirm Password text field.
	public void confirmPasswordTxtField(String confirmpassword) {  tfConfirmPassword.sendKeys(confirmpassword);  }
	//returns Confirm  password placeholder text.
	public String confirmPasswordPlcText() {  return (plcConfirmPassword.getText());  }
	//It returns two kinds of Warning msg based on password field.
	public String confirmPasswordWM() {  return (dynaWMConfirmPassword.getText()); }
	//Confirm password Eye-icon button click
	public void confirmPasswordEyeBtn() {  eyeBtnConfirmPassword.click(); }
		
	//Mobile Number text field.
	public void mobileNumberTxtField(String mobilenumber) {  
		//javascript.executeScript("arguments[0].scrollIntoView();", tfMobileNumber);
		sleep = new WebDriverWait(driver, Duration.ofSeconds(20));
		sleep.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys(mobilenumber);;
		
		//tfMobileNumber.sendKeys(mobilenumber);  
		}
	
	//Returns Mobile Number placeholder text.
	public String mobileNumberPlcText() {  return (plcMobileNumber.getText());  }
	//Returns the mobile number warning msg.
	public String mobileNumberWM() {  return (wmMobileNumber.getText()); }
	//Click mobile number info btn.
	public void infoBtnClkMobileNumber() {  infoBtnMobileNumber.click(); }
	//Read all the text in the info box.
	public String infoTextMobileNumber() { return (infoMsgMobileNumber.getText());}
	//Close the info box.
	public void closeInfoBoxMobileNumber() { infoCloseBtnMobileNumber.click(); }
	//Return the value of the mobile number
	public String valueofMobileNumber() {  return (tfMobileNumber.getAttribute("value"));}
	
	//Tick the checkBox.
	public void chkBoxRecover() { checkBoxRecover.click();}
	
	//Click the create an account button
	public void btnClkCreateAnAccount() { 
		//javascript.executeScript("arguments[0].scrollIntoView();", btnCreateanAccount);
		sleep = new WebDriverWait(driver, Duration.ofSeconds(20));
		sleep.until(ExpectedConditions.elementToBeClickable(btnCreateanAccount));
		btnCreateanAccount.click();
		}
	//Click the Google signUp button
	
	public void btnClkGoogleSignUp() { btnGoogleSignUp.click();}
	
	//Disclaimer Text and Links.
	public String txtDisclaimerCreateAccount() { return (disclaimerCreateAccount.getText());} 
	//Click on the Terms and conditions link.
	public void clkTermsAndConditions() {  linkTermsAndConditions.click(); }
	//Click on the Privacy policy link.
	public void clkPrivacyPolicy() {  linkPrivacyPolicy.click(); }
	//Click on the BestBuy program terms link.
	public void clkBestBuyProgram() {  linkBestBuyProgram.click(); }
	
	//SignIn from Create an Account.
	public void clkSignInlink() {  
		
		sleep = new WebDriverWait(driver, Duration.ofSeconds(20));
		sleep.until(ExpectedConditions.elementToBeClickable(linkSignIn));
		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", linkSignIn);
		linkSignIn.click(); 
		
	}
	//Get the text near the sign in link.
	public String txtNearSignInLink() { return (txtNearSignin.getText());}
	
	//Complete footer text.
	public String footerText() {  return (txtFooterInfo.getText());}
	//Click the footer terms and conditions.
	public void clkFooterTerms() {  linkFooterTerms.click();}
	//Click the footer privacy policy.
	public void clkFooterPrivacy() {  linkFooterPolicy.click();}
	//Click the footer Interest based ads.
	public void clkFooterInterestAds() {  linkFooterInterestAds.click();}
	
	//Change the view to Mobile site or Full Site.
	public void clkMobileFullSite() {  btnFooterSite.click();}
	
	
	
	
}
