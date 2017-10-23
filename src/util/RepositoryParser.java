package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
 
import org.openqa.selenium.By;
//import org.testng.internal.PropertiesFile;
public class RepositoryParser {
	private FileInputStream stream;
	private String RepositoryFile;
	private Properties propertyfile = new Properties();
	
	public RepositoryParser(String filename)throws IOException{
		this.RepositoryFile = filename;
		stream = new FileInputStream(RepositoryFile);
		propertyfile.load(stream);
	}
	public By getObjectLocator(String locatorName) {
		By locator=null;
		String locatorProperty=propertyfile.getProperty(locatorName);
		System.out.println(locatorProperty.toString());
		String locatorType=locatorProperty.split(":")[0];
		String locatorValue=locatorProperty.split(":")[1];
		switch(locatorType) {
		case "Id":
			locator=By.id(locatorValue);
			break;
		case "Name":
			locator = By.name(locatorValue);
			break;
		case "CssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "LinkText":
			locator = By.linkText(locatorValue);
			break;
		case "PartialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "TagName":
			locator = By.tagName(locatorValue);
			break;
		case "Xpath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}
