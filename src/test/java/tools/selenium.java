package tools;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class selenium {
	protected static WebDriver driver;
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

	public static void killDriver() {
		try {
			driver.quit();
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
