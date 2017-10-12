package core;

import java.lang.System;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.safari.SafariDriver;

public class browsers extends base {
	public void startBroser(String browser) {
		switch (browser.toUpperCase()) {
			case "FIREFOX":
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\drivers\\geckodriver.exe");
				//System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir")+"\\src\\drivers\\geckodriver.exe");
				//System.out.println(System.getProperty("user.dir"));
				DesiredCapabilities capabilities=DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver = new FirefoxDriver(capabilities);
				driver.manage().window().maximize();
				break;
			}
			case "INTERNETEXPLORER":
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "\\drivers\\IEDriverServer32.exe");
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
				dc.setCapability(InternetExplorerDriver.EXTRACT_PATH, System.getProperty("user.dir")+ "\\drivers\\IE\\");
				//dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS,true);
				dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
				dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				dc.setJavascriptEnabled(true);  
				//If IE fail to work, please remove this line and remove enable protected mode for all the 4 zones from Internet options
				driver = new InternetExplorerDriver(dc);
				driver.manage().window().maximize();
				break;
			}
			case "CHROME":
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
	//			To maximize chrome window
	//			ChromeOptions chromeOptions = new ChromeOptions();
	//			chromeOptions.addArguments("--start-maximized");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				break;
			}
//			case "SAFARI":
//			{
//				driver = new SafariDriver();
//			}
	//		case "Opera":
	//		{
	//			DesiredCapabilities capabilities = new DesiredCapabilities();
	//			capabilities.setCapability("opera.binary", "C://Program Files (x86)//Opera//opera.exe");
	//			capabilities.setCapability("opera.log.level", "CONFIG");
	//			driver = new OperaDriver(capabilities);
	//		}
			default:
			{
				System.out.println("No browser selected");
				break;
			}
		}
	}
}
