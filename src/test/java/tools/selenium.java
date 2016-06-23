package tools;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class selenium {
	protected static WebDriver driver;
	public static Logger logger = Logger.getLogger(selenium.class);
	WebElement element;

	public static void setFirefoxDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void setchromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				"seleniumDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void setIEDriver() {
		System.setProperty("webdriver.ie.driver",
				"seleniumDrivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void setHubDriver() {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("internet explorer");
			cap.setVersion("8");
			cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(
					"http://192.168.1.100:4444/wd/hub"), cap);
		} catch (Exception e) {
			logger.info("Error loading selenium hub");
			logger.info(e);
		}
	}

	public static void killDriver() {
		try {
			driver.close();
		} catch (Exception e) {
		}
	}

	public void get(String url) {
		driver.get(url);
	}

	public List<WebElement> findelements(String by, String path) {
		try {
			switch (by) {
			case "id":
				return driver.findElements(By.id(path));
			case "name":
				return driver.findElements(By.name(path));
			default:
				return driver.findElements(By.xpath(path));
			}
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement findElement(String by, String path) {
		try {
			switch (by) {
			case "id":
				return driver.findElement(By.id(path));
			case "name":
				return driver.findElement(By.name(path));
			default:
				return driver.findElement(By.xpath(path));
			}
		} catch (Exception e) {
			return null;
		}
	}
}
