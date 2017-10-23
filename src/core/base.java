package core;

import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class base {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static util.ReadCSV csvreader; 
	//	base(){
	//		extent = new ExtentReports(System.getProperty("user.dir") + "/Results/test-output/STMExtentReport.html", true);
	//		extent.addSystemInfo("Host Name", "Software Testing Material").addSystemInfo("Environment", "Automation Testing").addSystemInfo("User name", "SM");
	//		extent.loadConfig(new File(System.getProperty("user.dir") + "\\Results\\extent-config.xml"));
	//	}
}
