package epicList_Chrome_Account_User;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Enterprise_Menu_Configuration 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_Menu Configuration");

	float unknownValue = 00;

		@AfterMethod
		public void tearDown(ITestResult result)
		{
			if(ITestResult.FAILURE == result.getStatus())
			{
				Utility.captureScreenshot(driver, result.getName());
			}
		}
			
		@AfterClass
		public void flushTest() throws Exception
		{
			Thread.sleep(2000);
			rep.endTest(test);
			rep.flush();
		}

		@Test(priority=1)
		public void login() throws Exception
		{
			Thread.sleep(2000);
			// Call the chrome driver by using local path
			System.setProperty("webdriver.chrome.driver", Utility.getProperty("Chrome_Driver_Path"));
			// Open the Chrome window
			driver = new ChromeDriver();
			// Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			// Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if (Utility.getProperty("Product").equalsIgnoreCase("UPOS")) {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			} else {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser_Account_Level_User a = new Browser_Account_Level_User();
			a.Logout(driver, test);
		}	
	
		@Test(priority = 100)
		public void calling() throws Exception
		{
			Thread.sleep(10000);
			try
			{
					Thread.sleep(1000);System.out.println("Minimize Chat Icon");
					driver.findElement(By.id("zsiq_minimize")).click();			
			}
			catch(Exception e)
			{
				System.out.println("Minimize Chat Icon Missing");
			}
			//ViewDashBoardReports a = new ViewDashBoardReports();
			Thread.sleep(1000);
			Menu_Configuration_Open_Page(driver);
			Thread.sleep(1500);
		}
		
		@Test(priority = 11,enabled = false)
		public void Menu_Configuration_Open_Page(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"pullData");
			
			Thread.sleep(3000);
			//Check whether the Menu Configuration page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2390, 1))).getText().equalsIgnoreCase("Menu Configuration"))
			{
				test.log(LogStatus.PASS, "Menu Configuration page opened Successfully");
				
				String scnsht=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnsht;
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Menu Configuration page Opening Failed");
				
				String scnsht=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnsht;
				
				test.log(LogStatus.INFO, test.addScreenCapture(s));
			}
		
		}
		
		
		@Test(priority = 12,enabled = false)
		public void Pull_New_Menu_Configuration(WebDriver driver) throws Exception
		{
			ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
			//Click the From Store
			driver.findElement(By.xpath(excel.getData(3, 2391, 1))).click();
			//Select the Store
			driver.findElement(By.xpath(excel.getData(3, 2392, 1))).sendKeys(Utility.getProperty("Enterprise_Menu_Config_From_Store"));
		}

}
