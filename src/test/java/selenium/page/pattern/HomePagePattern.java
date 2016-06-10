package selenium.page.pattern;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@SuppressWarnings("deprecation")
public class HomePagePattern extends tools.selenium {
	public static Logger logger = Logger.getLogger(HomePagePattern.class);
	String URL = "http://www.gmail.com/";
	String INVALID_PASSWORD_ERROR = "The email and password you entered don't match.";

	@FindBy(id = "Email")
	WebElement txtUserName;

	@FindBy(id = "next")
	WebElement btnNext;

	@FindBy(id = "Passwd")
	WebElement txtPassword;

	@FindBy(id = "signIn")
	WebElement btnSignin;

	@FindBy(id = "errormsg_0_Passwd")
	WebElement txtInvalidPassword;

	@FindBy(xpath = "//div[@role='navigation']")
	WebElement navigationMenu;

	//
	public HomePagePattern() {
		PageFactory.initElements(driver, this);
	}

	public HomePagePattern(Boolean load) {
		if (load) {
			get(URL);
		}
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		txtUserName.clear();
		txtUserName.sendKeys(username);
	}

	public void clickOnNext() {
		btnNext.click();
	}

	public void enterPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}

	public void clickOnSignin() {
		btnSignin.click();
	}

	public void enterUserAndPass(String username, String password) {
		logger.info("Logging with " + username + "/" + password);
		enterUsername(username);
		clickOnNext();
		enterPassword(password);
		clickOnSignin();
	}

	public void validateInvalidPasswordError() {
		logger.info("");
		Assert.assertEquals("", this.INVALID_PASSWORD_ERROR,
				this.txtInvalidPassword.getText().toString());
	}

	public void validateNavigationMenu() {
		Assert.assertTrue(this.navigationMenu.isDisplayed());
	}
}
