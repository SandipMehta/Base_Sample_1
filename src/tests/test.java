package tests;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import util.GetScreenShot;
import util.ReadCSV;
import core.browsers;
import com.opencsv.*;

public class test extends browsers{
	
//	public static String driverPath = "C:\\Automation\\Selenium\\workspace\\Base_Sample_1\\drivers";
//	//public static WebDriver driver;
//	ExtentReports extent;
//	ExtentTest logger;
	CSVReader csvreader;
	@BeforeTest
	@Parameters({"browser"})
	public void startTest(String browser) {
		extent = new ExtentReports(System.getProperty("user.dir") + "/Results/"+browser+"STMExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Software Testing Material")
				.addSystemInfo("Environment", "Automation Testing").addSystemInfo("User name", "SM");

		extent.loadConfig(new File(System.getProperty("user.dir") + "\\Results\\extent-config.xml"));
		
		//     Open the new chrome browser and open the url: 
//		System.out.println("launching chrome browser");
//		System.setProperty("webdriver.chrome.driver", driverPath+"\\chromedriver.exe");
//		//     To maximize chrome window
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--start-maximized");
//		driver = new ChromeDriver(chromeOptions);
		startBroser(browser);
		driver.navigate().to("https://foxhound87.github.io/mobx-react-form-demo/demo");
	}

	@Test
	public void firstTest() throws FileNotFoundException {
		
		logger = extent.startTest("First Test");
		String dataPath = System.getProperty("user.dir") +"\\data\\data.csv";
		registerDetail regDetail = new registerDetail();
		//csvreader.readCSVFile(dataPath);
		csvreader = new CSVReader(new FileReader(dataPath));
		try {
			List<String[]> records = csvreader.readAll();
			Iterator<String[]> iterator = records.iterator();
			while(iterator.hasNext()) {
				String[] record = iterator.next();
				regDetail.setname(record[0]);
				regDetail.setemail(record[1]);
				regDetail.setconfirmemail(record[2]);
				regDetail.setpassword(record[3]);
				regDetail.setdevskills(record[4]);
				
				// Clear the fields and Insert new values in fields:
				driver.findElement(By.xpath(".//*[@id='username--20']")).clear(); 
				driver.findElement(By.xpath(".//*[@id='username--20']")).sendKeys(regDetail.getname());
				
				driver.findElement(By.xpath(".//*[@id='email--21']")).clear(); 
				driver.findElement(By.xpath(".//*[@id='email--21']")).sendKeys(regDetail.getemail());
				
				driver.findElement(By.xpath(".//*[@id='emailConfirm--22']")).clear(); 
				driver.findElement(By.xpath(".//*[@id='emailConfirm--22']")).sendKeys(regDetail.getconfirmemail());
				
				driver.findElement(By.xpath(".//*[@id='password--23']")).clear(); 
				driver.findElement(By.xpath(".//*[@id='password--23']")).sendKeys(regDetail.getpassword());
				
				driver.findElement(By.xpath(".//*[@id='devSkills--24']")).clear(); 
				driver.findElement(By.xpath(".//*[@id='devSkills--24']")).sendKeys(regDetail.getdevskills());
				
				//Thread.sleep(1000);
				driver.findElement(By.xpath(".//*[@id='terms--25']")).click();
				
				//Thread.sleep(1000);
				driver.findElement(By.xpath(".//*[@id='app']/div/div[4]/div[1]/form/div[7]/button[1]")).click();
				
				
				//Thread.sleep(1000);
				driver.findElement(By.xpath(".//*[@id='app']/div/div[4]")).click();
			}
			logger.log(LogStatus.PASS, "First Test Passed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "First Test Failed");
		}
		
	}
	
	@Test
	public void passTest() {
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test case passed is passTest");
	}

	@Test
	public void failTest() {
		logger = extent.startTest("failTest");
		Assert.assertTrue(false);
		logger.log(LogStatus.FAIL, "Test case (failTest) Status is passed");
	}

	@Test
	public void skipTest() {
		logger = extent.startTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = GetScreenShot.capture(driver, "screenShotName");
			logger.log(LogStatus.FAIL, "Test Case Failed is" + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is" + result.getThrowable());
			logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(screenShotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(logger);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
		driver.close();		
	}
	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}
}