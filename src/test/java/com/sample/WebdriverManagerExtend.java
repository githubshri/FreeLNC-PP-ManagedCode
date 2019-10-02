/**
 * CP:Shridhar
 * 
 */
package com.sample;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author shridhar1287
 *
 */
public class WebdriverManagerExtend extends Grid{
	public static final String dir = System.getProperty("user.dir");
	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = ThreadLocal
			.withInitial(WebdriverManagerExtend::startBrowser);
	// private static final String BASE_URL_PROPERTY = "url";
	private static final String BROWSER_PROPERTY = "browser";

	static {
		if (System.getProperty(BROWSER_PROPERTY) == null) {
			System.setProperty(BROWSER_PROPERTY, "chrome");
		}
	}

//	static RemoteWebDriver driverCustome = null;
	static DesiredCapabilities capabilities = null;


	/**
	 * Method for pickup a browser type and details.
	 * 
	 * @return
	 */
	private static WebDriver startBrowser() {
		String browserName = System.getProperty(BROWSER_PROPERTY);// ConfigHolder.getBrowserType();

		if (browserName.equalsIgnoreCase("firefox")) {
			// setup the firefoxdriver using WebDriverManager
			// WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities.firefox();
			capabilities.setPlatform(Platform.getCurrent());
			Grid.setCapabilities(capabilities);
//			driverCustome = new FirefoxDriver();
//			driverCustome = (FirefoxDriver) driver;
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
			DesiredCapabilities.chrome();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("window-size=1600,1200");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capabilities.setPlatform(Platform.getCurrent());
			Grid.setCapabilities(capabilities);
			
//			driverCustome = new ChromeDriver(chromeOptions);
//			driverCustome = (ChromeDriver) driver;
			//// driver = new ChromeDriver(chromeOptions);
		}

		return driver;
	}

	public static WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
}
