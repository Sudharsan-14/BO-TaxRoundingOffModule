package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Settings_Account_Balance_Settings 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Account Balance Settings (Report Settings)");
	
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
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}	
		}
			
		@Test(priority=500)
		public void logout() throws Exception
		{		Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			Account_Balance_Settings_Method_openpage(driver);
			Account_Balance_Settings_Save_Account_Balance_Settings(driver);
			
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=30)
	public void Account_Balance_Settings_Method_openpage(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newAccountBalanceSettings");
			
				
			Thread.sleep(3000);
			try
			{
			//Check whether the Sale recap Report Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2065, 1))).getText().equalsIgnoreCase("Account Balance Settings"))
			{
				test.log(LogStatus.PASS, "Account Balance Settings page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Account Balance Settings page loaded fail");
			
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
			Thread.sleep(3000);

		}	
	
	@Test(enabled=false,priority=3)
	public void Account_Balance_Settings_Save_Account_Balance_Settings(WebDriver driver) throws Exception
	{
		
		File f=new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		FileInputStream fis=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet1=wb.getSheetAt(1);
		sheet1.getLastRowNum();
		ExcelDataConfig excel=new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(5000);
		//Clear Paid Out
		driver.findElement(By.xpath(excel.getData(3, 2066, 1))).clear();
		//Enter Paid Out
		driver.findElement(By.xpath(excel.getData(3, 2066, 1))).sendKeys("200");
		
		Thread.sleep(1000);
		//Clear CC Tip reduction
		driver.findElement(By.xpath(excel.getData(3, 2067, 1))).clear();
		//Enter CC Tip Reduction
		driver.findElement(By.xpath(excel.getData(3, 2067, 1))).sendKeys("10");
				
	
		Thread.sleep(1000);
		//Clear Refund
		driver.findElement(By.xpath(excel.getData(3, 2068, 1))).clear();
		//Enter Refund
		driver.findElement(By.xpath(excel.getData(3, 2068, 1))).sendKeys("4");
		
		
		Thread.sleep(1000);
		//Clear Deposit
		driver.findElement(By.xpath(excel.getData(3, 2069, 1))).clear();
		//Enter Deposit
		driver.findElement(By.xpath(excel.getData(3, 2069, 1))).sendKeys("1000");
				
		Thread.sleep(1000);
		//Clear Over / Shortage
		driver.findElement(By.xpath(excel.getData(3, 2070, 1))).clear();
		//Enter Over / Shortage
		driver.findElement(By.xpath(excel.getData(3, 2070, 1))).sendKeys("2");
		
		Thread.sleep(1000);
		//Clear Delivery Charge cost
		driver.findElement(By.xpath(excel.getData(3, 2071, 1))).clear();
		//Enter Delivery Charge cost
		driver.findElement(By.xpath(excel.getData(3, 2071, 1))).sendKeys("12");
				
		Thread.sleep(1000);
		//Clear HA and GC Recharge
		driver.findElement(By.xpath(excel.getData(3, 2072, 1))).clear();
		//Enter HA and GC Recharge
		driver.findElement(By.xpath(excel.getData(3, 2072, 1))).sendKeys("3");
				
		Thread.sleep(1000);
		//Clear Driver Tip 
		driver.findElement(By.xpath(excel.getData(3, 2073, 1))).clear();
		//Enter Driver Tip 
		driver.findElement(By.xpath(excel.getData(3, 2073, 1))).sendKeys("5");
		
		Thread.sleep(1000);
		//Clear Gratuity
		driver.findElement(By.xpath(excel.getData(3, 2074, 1))).clear();
		//Enter Gratuity
		driver.findElement(By.xpath(excel.getData(3, 2074, 1))).sendKeys("6");
		
		Thread.sleep(2000);
		//Click Save button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

		WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,40);
		//Check whether the Account Balance Settings Saved or Not
		if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Account Balance Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Account Balance Settings Updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Account Balance Updated Failed");
		}
	wb.close();		
	Thread.sleep(4000);
	}
	
	
}
