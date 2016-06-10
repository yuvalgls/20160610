package selenium.page.tests;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SendEmail extends tools.selenium {
	public static Logger logger = Logger.getLogger(SendEmail.class);

	@BeforeClass
	public static void setUp() {
		setchromeDriver();
	}

	@AfterClass
	public static void tearDown() {
		try {
			killDriver();
		} catch (Exception e) {
		}
	}

	@Test
	public void test() {
		logger.info("testing send email");
		// HomePagePattern hp = new HomePagePattern(true);
		// hp.loginWithValidUser();
	}

}
