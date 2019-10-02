/**
 * 
 */
package com.sample;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author robin
 *
 */
public class WebdriverManager {
	public static final String dir = System.getProperty("user.dir");
	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = ThreadLocal
			.withInitial(WebdriverManager::startBrowser);
	private static final String BASE_URL_PROPERTY = "url";
	private static final String BROWSER_PROPERTY = "browser";

	static {
		if (System.getProperty(BASE_URL_PROPERTY) == null) {
			System.setProperty(BASE_URL_PROPERTY, "https://developer.trustedkey.com/");
		}
		if (System.getProperty(BROWSER_PROPERTY) == null) {
			System.setProperty(BROWSER_PROPERTY, "chrome");
		}
	}

	static RemoteWebDriver driverCustome = null;
	RemoteWebDriver friverChr = null;
	// Capabilities capabilities = null;
	static DesiredCapabilities capabilities = null;

	static RemoteWebDriver driver = Grid.getRemoteDriver();

	private static WebDriver startBrowser() {
		String browserName = System.getProperty(BROWSER_PROPERTY);// ConfigHolder.getBrowserType();
		String url = System.getProperty(BASE_URL_PROPERTY);// ConfigHolder.getBaseUrl();

		if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities.firefox();
			driverCustome = new FirefoxDriver();
			FirefoxDriver driverCustome = (FirefoxDriver) driver;
		} else if (browserName.equalsIgnoreCase("chrome")) {
			String localDir = dir;
			if (localDir.endsWith("appTests")) {
				localDir = localDir + "/src/test/resources/drivers/"; // Eclipse
																		// IDE
																		// TestNG
																		// Run
			} else {
				localDir = localDir + "/src/test/resources/drivers/"; // IntelliJ
																		// IDE
																		// TestNG
																		// Run
			}
			if (Platform.getCurrent().is(Platform.LINUX)) {
				System.setProperty("webdriver.chrome.driver", localDir + "chromedriver_linux64");
			} else if (Platform.getCurrent().is(Platform.MAC)) {
				System.setProperty("webdriver.chrome.driver", localDir + "chromedriver_mac");
			} else if (Platform.getCurrent().is(Platform.WINDOWS)) {
				System.setProperty("webdriver.chrome.driver", localDir + "chromedriver.exe");
			}
			DesiredCapabilities.chrome();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("window-size=1600,1200");
			driverCustome = new ChromeDriver(chromeOptions);
			ChromeDriver driverCustome = (ChromeDriver) driver;

			// driver = new ChromeDriver(chromeOptions);
		}
		driver.get(url);

		return driverCustome;
	}

	public static WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
}
