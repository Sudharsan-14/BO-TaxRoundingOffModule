package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Settings_Tip_Out_Tip_Sharing 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Tip Out/Tip Sharing (Report Settings)");
	
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
			Tip_Out_Settings_Method_openpage(driver);
			Tip_Out_Sharing_Settings_Save_Settings(driver);
			Manual_Tip_Sharing_Settings_Save_Settings(driver);
			
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=30)
	public void Tip_Out_Settings_Method_openpage(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newTipOutTipSharingSettings");
			
				
			Thread.sleep(5000);
			try
			{
			//Check whether the Sale recap Report Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2075, 1))).getText().equalsIgnoreCase("Tip Out"))
			{
				test.log(LogStatus.PASS, "Tip Out/Tip Sharing Settings page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Tip Out/Tip Sharing Settings page loaded fail");
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
			wb.close();
			Thread.sleep(5000);

		}	
	
	@Test(enabled=false,priority=3)
	public void Tip_Out_Sharing_Settings_Save_Settings(WebDriver driver) throws Exception
	{
		
		File f=new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		FileInputStream fis=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet1=wb.getSheetAt(1);
		sheet1.getLastRowNum();
		ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(4000);
		//Check whether the Tip out based on percentage of sales is Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2076, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Check whether the Tip share per Hour is Enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2078, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath(excel.getData(3, 2078, 1))).click();
			}
		}
		else
		{
			Thread.sleep(3000);
			driver.findElement(By.xpath(excel.getData(3, 2076, 1))).click();
			
			Thread.sleep(2000);
			//Check whether the Tip share per Hour is Enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2078, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath(excel.getData(3, 2078, 1))).click();
			}
		}
		
		Thread.sleep(2000);
		//Click Add
		driver.findElement(By.xpath(excel.getData(3, 2077, 1))).click();
		
		Thread.sleep(5000);
		//Clear Tip Out Name
		driver.findElement(By.xpath(excel.getData(3, 2079, 1))).clear();
		//Enter Tip Out Name
		driver.findElement(By.xpath(excel.getData(3, 2079, 1))).sendKeys("Timely Service");
		
		Thread.sleep(1000);
		//Click Role From
		driver.findElement(By.xpath(excel.getData(3, 2080, 1))).click();
		//Select role from
		driver.findElement(By.xpath(excel.getData(3, 2081, 1))).sendKeys("Manager");
		//Enter Role From
		driver.findElement(By.xpath(excel.getData(3, 2081, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(1000);
		//Click Role To
		driver.findElement(By.xpath(excel.getData(3, 2082, 1))).click();
		//Select Role To
		driver.findElement(By.xpath(excel.getData(3, 2083, 1))).sendKeys("Server");
		//Enter Role To
		driver.findElement(By.xpath(excel.getData(3, 2083, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 2084, 1))).click();
		//Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 2085, 1))).sendKeys("FOOD");
		//Enter Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 2085, 1))).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		//Click Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 2084, 1))).click();
		//Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 2085, 1))).sendKeys("BEVERAGE");
		//Enter Select Some Options
		driver.findElement(By.xpath(excel.getData(3, 2085, 1))).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(3000);
		//Clear Tip Percentage
		driver.findElement(By.xpath(excel.getData(3, 2086, 1))).clear();
		Thread.sleep(1000);
		//Enter the Percentage of Tip
		driver.findElement(By.xpath(excel.getData(3, 2086, 1))).sendKeys("100");
		
		
	/*	Thread.sleep(3000);
		//Click Add
		driver.findElement(By.xpath(excel.getData(3, 2077, 1))).click();
		
		
		Thread.sleep(2000);
		//Click Close button
		driver.findElement(By.xpath(excel.getData(3, 2087, 1))).click();
		*/
		Thread.sleep(3000);
		//Click Save button
		driver.findElement(By.xpath(excel.getData(3, 1475, 1))).click();

		Thread.sleep(3000);
		//Check whether the Tip Out/Tip Sharing Settings Saved or Not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Tip Out Sharing Updated Sucessfully"))
		{
			test.log(LogStatus.PASS, "Tip Out Sharing Updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Out Sharing Updated Failed");
		}
		
		Thread.sleep(4000);
		//Check whether the Tip out based on percentage of sales is Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2076, 1))).isSelected())
		{
			
			test.log(LogStatus.PASS, "Tip out based on percentage of sales is Enabled");
			Thread.sleep(2000);
			//Check whether the Tip share per Hour is Enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2078, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Tip share per Hour is Enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tip share per Hour is not Enabled");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip out based on percentage of sales is not Enabled");
		}
		
	
		
	wb.close();		
	Thread.sleep(4000);
	}
	

	@Test(enabled=false,priority=3)
	public void Manual_Tip_Sharing_Settings_Save_Settings(WebDriver driver) throws Exception
	{
		
		File f=new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		FileInputStream fis=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet1=wb.getSheetAt(1);
		sheet1.getLastRowNum();
		ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(4000);
		//Check whether the Tip out based on percentage of sales is Enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2076, 1))).isSelected())
		{
			Thread.sleep(2000);
			//Disable Tip out based on percentage of sales
			driver.findElement(By.xpath(excel.getData(3, 2076, 1))).click();
			
			Thread.sleep(3000);
			//Enable Manual Tip Sharing 
			driver.findElement(By.xpath(excel.getData(3, 2088, 1))).click();
			
			Thread.sleep(2000);
			//Click Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2089, 1))).click();
			//Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys("Admin");
			//Enter Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2089, 1))).click();
			//Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys("Manager");
			//Enter Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys(Keys.ENTER);
			
		}
		else
		{
			Thread.sleep(3000);
			//Enable Manual Tip Sharing 
			driver.findElement(By.xpath(excel.getData(3, 2088, 1))).click();
			
			Thread.sleep(2000);
			//Click Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2089, 1))).click();
			//Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys("Admin");
			//Enter Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2089, 1))).click();
			//Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys("Manager");
			//Enter Select Some Options
			driver.findElement(By.xpath(excel.getData(3, 2090, 1))).sendKeys(Keys.ENTER);
			
		}

		Thread.sleep(3000);
		//Click Save button in Manual Tip Sharing
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		
		Thread.sleep(3000);
		//Check whether the Manual Tip Sharing Settings Saved or Not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Tip Out Sharing Updated Sucessfully"))
		{
			test.log(LogStatus.PASS, "Manual Tip Sharing Updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Manual Tip Sharing Updated Failed");
		}
		wb.close();Thread.sleep(5000);
	}
	
}
