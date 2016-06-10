package selenium.page.pattern;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("deprecation")
public class HomePagePattern extends tools.selenium {
	public static Logger logger = Logger.getLogger(HomePagePattern.class);
	String URL = "http://www.gmail.com/";
	String INVALID_PASSWORD_ERROR = "Please enter your password.";
	String MISSING_EMAIL_ERROR = "Please enter your email.";
	String VALID_USERNAME = "selenium20160610";
	String VALID_PASSWORD = "gls27100G";

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

	@FindBy(id = "errormsg_0_Email")
	WebElement txtMissingEmail;

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
		try {
			txtUserName.clear();
			txtUserName.sendKeys(username);
		} catch (Exception e) {
			logger.info("error entering username");
		}
	}

	public void validateTitle() {
		Assert.assertEquals("Gmail", driver.getTitle().toString());
	}

	public void clickOnNext() {
		try {
			btnNext.click();
		} catch (Exception e) {
			logger.info("error clicing on next");
		}
	}

	public void enterPassword(String password) {
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions
					.visibilityOf(txtPassword));
			txtPassword.clear();
			txtPassword.sendKeys(password);
		} catch (Exception e) {
			logger.info("error entering password");
		}
	}

	public void clickOnSignin() {
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions
					.elementToBeClickable(btnSignin));
			btnSignin.click();
		} catch (Exception e) {
			logger.info("error clicking on signin");
		}
	}

	public void enterUserAndPass(String username, String password) {
		logger.info("Logging with " + username + "/" + password);
		enterUsername(username);
		clickOnNext();
		enterPassword(password);
		clickOnSignin();
	}

	public void loginWithValidUser() {
		enterUsername(this.VALID_USERNAME);
		clickOnNext();
		enterPassword(this.VALID_PASSWORD);
		clickOnSignin();
	}

	public void validateMissingEmailError() {
		Assert.assertEquals("", this.MISSING_EMAIL_ERROR, this.txtMissingEmail
				.getText().toString());
	}

	public void validateInvalidPasswordError() {
		Assert.assertEquals("", this.INVALID_PASSWORD_ERROR,
				this.txtInvalidPassword.getText().toString());
	}

	public void validateNavigationMenu() {
		Assert.assertTrue(this.navigationMenu.isDisplayed());
	}

	public void analizeResults(String expected) {
		logger.info("Expected Results: " + expected);
		switch (expected) {
		case "missingEmailError":
			validateMissingEmailError();
			break;
		case "missingPasswordError":
			validateInvalidPasswordError();
			break;
		default:
			validateNavigationMenu();
		}
	}
}
