package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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


public class Inventory_Store_Settings {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Store_Settings");

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
			//SendMail.snedMailWithAttachment();
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
		a.Logout(driver, test);}
	
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
			Inventory_Store_setting_Pageopen(driver);
			Inventory_Store_setting_Process(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=42)
		public void Inventory_Store_setting_Pageopen(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"storeSetting");
					
		
			Thread.sleep(5000);
			try
			{
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 1319, 1))).getText().equalsIgnoreCase("Store Setting"))
			{
				test.log(LogStatus.PASS, " Store setting page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, " Store setting page loaded Failed");
			
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
	
		@Test(enabled=false,priority=43)
		public void Inventory_Store_setting_Process(WebDriver driver) throws Exception
		{
			 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(2000);
			//Clear the Business Date
			driver.findElement(By.xpath(excel.getData(3, 1320, 1))).clear();
	 		//Enter the Business Date
			driver.findElement(By.xpath(excel.getData(3, 1320, 1))).sendKeys(Utility.getProperty("Inventory_Business_Date"));
			
			// Create object of SimpleDateFormat class and decide the format
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
	
			 //get current date time with Date()
			 Date date = new Date(0);
	
			 // Now format the date
		 String date1= dateFormat.format(date);
	
			 // Print the Date
		 System.out.println(date1);
			
			Thread.sleep(2000);
			//Check whether the Summary Alert field is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 1676, 1))).isSelected()){}
			
			else
			{
				//Enable the Summary Alert Option
				driver.findElement(By.xpath(excel.getData(3, 1676, 1))).click();
			}
			
	
		Thread.sleep(3000);
		//Clear the EmailID's Summary Alert
		driver.findElement(By.xpath(excel.getData(3, 1677, 1))).clear();
		//Enter the EmailID's Summary Alert
		driver.findElement(By.xpath(excel.getData(3, 1677, 1))).sendKeys(Utility.getProperty("Inventory_SummaryEmailID"));
	
		Thread.sleep(3000);
		//Clear the Vendor's CC Alert
		driver.findElement(By.xpath(excel.getData(3, 1678, 1))).clear();
		//Enter the Vendor's CC
		driver.findElement(By.xpath(excel.getData(3, 1678, 1))).sendKeys(Utility.getProperty("Inventory_VendorEmailID"));
		
		Thread.sleep(2000);	
		//Click Update Button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		Thread.sleep(4000);
		//Check Inventory Items page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Settings updated successfully"))
		{
			test.log(LogStatus.PASS, " Store setting updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, " Store setting updated Failed");
		}
		Thread.sleep(3000);wb.close();
		}	

}
