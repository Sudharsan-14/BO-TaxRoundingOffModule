package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class Settings_Notification_Settings {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Notification_Settings");
	
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
			//Call the chrome driver
			System.setProperty("webdriver.chrome.driver",Utility.getProperty("Chrome_Driver_Path"));
			//Open the Chrome window
			driver = new ChromeDriver();
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Maximize the Chrome window
			driver.manage().window().maximize();
			Thread.sleep(1000);
			//Launch the URL
			driver.get(Utility.getProperty("appURL"));
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser a = new Browser();
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
			Notification_Settings_Method_open(driver);
			Notification_Settings_Method_edit(driver);
			Thread.sleep(1500);
		}

	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Click the Watch Tutorial Option
		driver.findElement(By.xpath("//span[.='Watch Tutorial']")).click();
		WebElement iframe = driver.findElement(By.xpath("//div[@class='modal-content']/span/iframe"));
		driver.switchTo().frame(iframe);
		Thread.sleep(3500);
		try
		{
			if(driver.findElement(By.xpath("//button/div[.='Watch later']")).isDisplayed()&&driver.findElement(By.xpath("//button/div[.='Share']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Watch Tutorial Video Played Well");
				Thread.sleep(2500);
			}
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Watch Tutorial Video not Played");
		}
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		//Click the Close button
		driver.findElement(By.xpath("//span[.='x' and @title='close']")).click();
	}
	
	@Test(enabled=false,priority=28)
	public void Notification_Settings_Method_open(WebDriver driver) throws Exception
		{
			
			Thread.sleep(5000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newNotificationSettings");
			
			Thread.sleep(3000);
			
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			

			
			Thread.sleep(3000);
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
			try
			{
			//Check weather the notification Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 1479, 1))).getText().equalsIgnoreCase("Notification Settings"))
			{
				test.log(LogStatus.PASS, "Notification Settings page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Notification Settings page loaded fail");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			}
			catch(Exception e)
			{
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.FAIL,test.addScreenCapture(s));
			}

			Thread.sleep(5000);wb.close();

		}	
		
	@Test(enabled=false,priority=29)
	public void Notification_Settings_Method_edit(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
		
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Clear the Discount amount
			driver.findElement(By.xpath(excel.getData(3, 1480, 1))).clear();
			//Enter the required Discount amount
			driver.findElement(By.xpath(excel.getData(3, 1480, 1))).sendKeys("125");
			
			Thread.sleep(2000);
			//Clear the Void amount
			driver.findElement(By.xpath(excel.getData(3, 1481, 1))).clear();
			//Enter the required Void amount
			driver.findElement(By.xpath(excel.getData(3, 1481, 1))).sendKeys("110");
			
			Thread.sleep(2000);
			//Clear the Max No Cash Drawer Accessed For No Sale 
			driver.findElement(By.xpath(excel.getData(3, 1482, 1))).clear();
			//Enter the required Max No Cash Drawer Accessed For No Sale 
			driver.findElement(By.xpath(excel.getData(3, 1482, 1))).sendKeys("2");
			
			Thread.sleep(3000);
			//Check weather the Discount mail check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1483, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1484, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1484, 1))).sendKeys("raja.s@benseron.com");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1483, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1484, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1484, 1))).sendKeys("raja.s@benseron.com");
			
			
			}
			
			Thread.sleep(2000);
			//Check weather the Discount text check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1486, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1487, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1487, 1))).sendKeys("+919840961087");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first SMS check box
				driver.findElement(By.xpath(excel.getData(3, 1486, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1487, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1487, 1))).sendKeys("+919840961087");
				
			}
			
			
			Thread.sleep(2000);
			//Check weather the Void mail check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1489, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1485, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1485, 1))).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1489, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1485, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1485, 1))).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Void SMS text box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1492, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1488, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1488, 1))).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1492, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1488, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1488, 1))).sendKeys("+919626928016");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Max No Cash Drawer Accessed For No Sale mail check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1490, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1491, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1491, 1))).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1490, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1491, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1491, 1))).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Max No Cash Drawer Accessed For No Sale text check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1492, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1493, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1493, 1))).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1492, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1493, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1493, 1))).sendKeys("+919626928016");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Time Clock mail check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1494, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1495, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1495, 1))).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1494, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1495, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1495, 1))).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Time Clock text check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1496, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1497, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1497, 1))).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1496, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1497, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1497, 1))).sendKeys("+919626928016");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Daily KPI mail check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1498, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1499, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1499, 1))).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1498, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1499, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1499, 1))).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			Thread.sleep(2000);
			//Check weather the Daily KPI text check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1500, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1501, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1501, 1))).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1500, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1501, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1501, 1))).sendKeys("+919626928016");
				
			}
			
			Thread.sleep(2000);
			//Check weather the End Of the Day Alert  mail check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1502, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1503, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1503, 1))).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1502, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1503, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1503, 1))).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			Thread.sleep(2000);
			//Check weather the End Of the Day Alert text check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1504, 1))).isSelected())
			{

				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1505, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1505, 1))).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(2000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath(excel.getData(3, 1504, 1))).click();
				
				Thread.sleep(2000);
				//CLear the first text are
				driver.findElement(By.xpath(excel.getData(3, 1505, 1))).clear();
				//Enter the required test mail
				driver.findElement(By.xpath(excel.getData(3, 1505, 1))).sendKeys("+919626928016");
				
			}
			
			
			Thread.sleep(2000);
			//CLear the first text are
			driver.findElement(By.xpath(excel.getData(3, 1506, 1))).clear();
			//Enter the required test mail
			driver.findElement(By.xpath(excel.getData(3, 1506, 1))).sendKeys("sappanimuthub@benseron.com");
			
		
		
			Thread.sleep(2000);
			//Check weather the Wait List Alert text check box is enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 1507, 1))).isSelected())
		{

			Thread.sleep(2000);
			//CLear the first text are
			driver.findElement(By.xpath(excel.getData(3, 1508, 1))).clear();
			//Enter the required test mail
			driver.findElement(By.xpath(excel.getData(3, 1508, 1))).sendKeys("+919626928016");
			
		}
		else
		{

			Thread.sleep(2000);
			//Enable or disable the first email check box
			driver.findElement(By.xpath(excel.getData(3, 1507, 1))).click();
			
			Thread.sleep(2000);
			//CLear the first text are
			driver.findElement(By.xpath(excel.getData(3, 1508, 1))).clear();
			//Enter the required test mail
			driver.findElement(By.xpath(excel.getData(3, 1508, 1))).sendKeys("+919626928016");
			
		}
			
		Thread.sleep(2000);
		//Check whether the SaleRecap Report Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2091, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Clear the Email ID
			driver.findElement(By.xpath(excel.getData(3, 2092, 1))).clear();
			//Enter Email ID
			driver.findElement(By.xpath(excel.getData(3, 2092, 1))).sendKeys("raja.s@benseron.com");
		}
		else
		{
			Thread.sleep(2000);
			//Enable SaleRecap Report
			driver.findElement(By.xpath(excel.getData(3, 2091, 1))).click();
			Thread.sleep(2000);
			//Clear the Email ID
			driver.findElement(By.xpath(excel.getData(3, 2092, 1))).clear();
			//Enter Email ID
			driver.findElement(By.xpath(excel.getData(3, 2092, 1))).sendKeys("raja.s@benseron.com");
		}
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(2000);
		//Check whether the One Page PDF Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2093, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Clear the Email ID
			driver.findElement(By.xpath(excel.getData(3, 2094, 1))).clear();
			//Enter Email ID
			driver.findElement(By.xpath(excel.getData(3, 2094, 1))).sendKeys("raja.s@benseron.com");
		}
		else
		{
			Thread.sleep(2000);
			//Enable One Page PDF
			driver.findElement(By.xpath(excel.getData(3, 2093, 1))).click();
			Thread.sleep(2000);
			//Clear the Email ID
			driver.findElement(By.xpath(excel.getData(3, 2094, 1))).clear();
			//Enter Email ID
			driver.findElement(By.xpath(excel.getData(3, 2094, 1))).sendKeys("raja.s@benseron.com");
		}
		
		
			Thread.sleep(3000);
			//Click the update button
			driver.findElement(By.xpath(excel.getData(3, 1475, 1))).click();
			
			Thread.sleep(4000);
			//Check weather the notification setting is updated or not  Notification Settings updated successfully
			if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Notification Settings Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Notification Settings updated successfully");
			}
			
			else{
				test.log(LogStatus.FAIL, "Notification Settings updated fail");
			}
			wb.close();
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else
			{
				Thread.sleep(5000);
			//watchTutorial(driver);
				Thread.sleep(5000);
			}
			}
		

}
