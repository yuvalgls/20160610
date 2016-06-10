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
	String INVALID_PASSWORD_ERROR = "Please enter your password.";
	String MISSING_EMAIL_ERROR = "Please enter your email.";

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

	public void clickOnNext() {
		try {
			btnNext.click();
		} catch (Exception e) {
			logger.info("error clicing on next");
		}
	}

	public void enterPassword(String password) {
		try {
			txtPassword.clear();
			txtPassword.sendKeys(password);
		} catch (Exception e) {
			logger.info("error entering password");
		}
	}

	public void clickOnSignin() {
		try {
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
