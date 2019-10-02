/**
 * 
 */
package com.sample;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Shridhar1287
 *
 */
public class Grid {

	private static Capabilities capabilities = null;
	public static RemoteWebDriver driver;

	public static RemoteWebDriver getRemoteDriver() throws Exception{
		driver  = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities );
		return driver;
	}
	
	public static void setCapabilities(Capabilities capabilities) {
		Grid.capabilities = capabilities;
	}

}
