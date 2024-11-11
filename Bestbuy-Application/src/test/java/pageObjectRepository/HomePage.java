package pageObjectRepository;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	
	//Search Box
	@FindBy(id = "gh-search-input") private WebElement searchBoxField;
	@FindBy(className = "header-search-button") private WebElement searchIconButton;
	@FindBy(css = ".added-to-cart") private WebElement addedToCartMessage;
	@FindBy(xpath = "(//button[@type='button'][normalize-space()='Add to Cart'])[2]") private WebElement btnAddToCart;
	
	@FindBy(xpath = "//button[normalize-space()='Continue shopping']") private WebElement clickContinueShop;
	@FindBy(className = "cart-label") private WebElement toCartLogoBtn;
	
	@FindBy(css = ".gvp-cart-icon-lv > a") private WebElement linkCartPage;
	
	public void searchItemAndClick(String itemname) { 
		searchBoxField.sendKeys(itemname); 
		searchIconButton.click();
		}
	
	public void clickAddToCart() { btnAddToCart.click();}
	
	public void clickToCartPage() { 
		sleep = new WebDriverWait(driver, Duration.ofSeconds(10));
		sleep.until(ExpectedConditions.elementToBeClickable(linkCartPage));
		linkCartPage.click(); 
		}
	
	public boolean isAddedToCart() { 	return addedToCartMessage.isDisplayed(); }
	
	public void clickContinueShopping() { clickContinueShop.click();}
	
	public void goAddToCartPage() { toCartLogoBtn.click();}
	
	
	
	//Entrance page flag locator.
	@FindBy(css = "div[lang='en'] a[class='us-link']") private WebElement btnFlagUS;
	//Homepage account dropdown button.
	@FindBy(how = How.ID, using = "account-menu-account-button") private WebElement ddlAccount;
	//Create account button from the dropdown list
	@FindBy(css = "a[data-testid$='createAccountButton']") private WebElement btnCreateAccount;
	//Sign In button from the dropdown list.
	@FindBy(css = "a[data-testid$='signInButton']") private WebElement btnSignIn;
	
//  	<---------------------------------------- LINKS ---------------------------------------- >
	
	//All the href tag in the homepage.
	@FindBy(tagName = "a") private List<WebElement> allHrefLinks;
	
	//Bottom of the HOMEPAGE
	//Order & purchases links
	@FindBy(css = ".main-navigation > div:nth-child(1) > ul > li > a") private List<WebElement> linksOrderPurchases;
	
	@FindBy(xpath = "(//h3[normalize-space()='Support & Services']//following::ul)[1]//child::li") private List<WebElement> linksSupportServices;
	
	@FindBy(xpath = "(//h3[normalize-space()='Partnerships']//following::ul)[1]//child::li") private List<WebElement> linksPartnerShips;
	
	@FindBy(xpath = "(//h3[normalize-space()='Payment Options']//following::ul)[1]//child::li") private List<WebElement> linksPaymentOptions;
	
	@FindBy(xpath = "(//h3[normalize-space()='Rewards & Membership']//following::ul)[1]//child::li") private List<WebElement> linksRewardsMembership;
	
	@FindBy(xpath = "(//h3[normalize-space()='About Best Buy']//following::ul)[1]//child::li") private List<WebElement> linksAboutBestBuy;
	
	
	//Click method for Flag, Account DDL, & Create Account.
	public void btnFlagUSClick() {   btnFlagUS.click();   }
	public void ddlAccountClick() {  ddlAccount.click();  }
	public void btnAccountClick() {  btnCreateAccount.click(); }
	
	//Click the SignIn button
	public void btnSignInClick() {  btnSignIn.click(); }
	
	public List<WebElement> getAllLinksHomepage() { return allHrefLinks; }
	
	//Bottom of Homepage Links
	public List<WebElement> getbottomOrderPurchasesLinks() { return linksOrderPurchases; }
	
	public List<WebElement> getbottomSupportServicesLinks() { 
		
		return linksSupportServices; }
		
	
	public List<WebElement> getbottomPartnerShips() { return linksPartnerShips; }
	
	public List<WebElement> getbottomPaymentOptions() { return linksPaymentOptions; }
	
	public List<WebElement> getbottomRewardsMembership() { return linksRewardsMembership; }
	
	public List<WebElement> getbottomAboutBestBuy() { return linksAboutBestBuy; }
	

}
