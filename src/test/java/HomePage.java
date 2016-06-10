

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import selenium.page.pattern.HomePagePattern;

@RunWith(Parameterized.class)
public class HomePage extends tools.selenium {
	String username;
	String password;
	String expected;

	@Parameters
	public static Collection<String[]> addedNumbers() {
		return Arrays.asList(new String[][] { { "", "", "false" },
				{ "", "password", "false" }, { "username", "", "false" },
				{ "selenium20160610", "gls27100G", "true" } });
	}

	public HomePage(String username, String password, String expected) {
		this.username = username;
		this.password = password;
		this.expected = expected;
	}

	@BeforeClass
	public static void setUp() {
		setchromeDriver();
	}

	@AfterClass
	public static void tearDown() {
		killDriver();
	}

	@Test
	public void login() {
		HomePagePattern hp = new HomePagePattern(true);
		hp.enterUserAndPass(username, password);
		if (Boolean.valueOf(expected)) {
			hp.validateNavigationMenu();
		} else {
			hp.validateInvalidPasswordError();
		}
	}
}