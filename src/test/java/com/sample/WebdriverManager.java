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

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author shridhar1287
 *
 */
public class WebdriverManager {
	public static final String dir = System.getProperty("user.dir");
	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = ThreadLocal.withInitial(() -> {
		try {
			return startBrowser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	});

	private static final String BROWSER_PROPERTY = "browser";
	static RemoteWebDriver driverCustom = null;
	// Capabilities capabilities = null;
	static DesiredCapabilities capabilities = null;
	static WebDriver driver;

	static {
		if (System.getProperty(BROWSER_PROPERTY) == null) {
			System.setProperty(BROWSER_PROPERTY, "chrome");
		}
	}

	/**
	 * Method for pickup a browser type and details.
	 * 
	 * @return
	 * @throws Exception
	 */
	private static WebDriver startBrowser() throws Exception {
		String browserName = System.getProperty(BROWSER_PROPERTY);// ConfigHolder.getBrowserType();

		if (browserName.equalsIgnoreCase("firefox")) {
			// setup the firefoxdriver using WebDriverManager
			// WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities.firefox();
			capabilities.setJavascriptEnabled(true);
			capabilities.setPlatform(capabilities.getPlatform());
			driver = Grid.getRemoteDriver();

			// driverCustom = new FirefoxDriver();
			// driverCustom = (FirefoxDriver) driver;
		} else if (browserName.equalsIgnoreCase("chrome")) {
			String localDir = dir;

			// setup the chromedriver using WebDriverManager
			// WebDriverManager.chromedriver().setup();
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
			// DesiredCapabilities.chrome();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("window-size=1600,1200");

			DesiredCapabilities.chrome();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capabilities.setPlatform(capabilities.getPlatform());
			Grid.setCapabilities(capabilities);
			driver = Grid.getRemoteDriver();

			// driverCustom = new ChromeDriver(chromeOptions);
			// driverCustom = (ChromeDriver) driver;
			//// driver = new ChromeDriver(chromeOptions);
		}

		// return driverCustom;
		return driver;
	}

	public static WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
}
