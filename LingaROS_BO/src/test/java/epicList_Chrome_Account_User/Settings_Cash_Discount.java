package epicList_Chrome_Account_User;

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


public class Settings_Cash_Discount {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Cash_Discount");
	
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
			Cash_Discount_Method_openpage(driver);
			Cash_Discount_Method_Edit_Amount(driver);
			Cash_Discount_Method_Edit_Percentage(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=25)
	public void Cash_Discount_Method_openpage(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"cashDiscount");
			
			Thread.sleep(8000);
			try
			{
			//Check weather the OT Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 400, 1))).getText().equalsIgnoreCase("Cash Discount"))
			{
				test.log(LogStatus.PASS, "Cash Discount page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Cash Discount page loaded fail");
			
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

			Thread.sleep(3000);wb.close();

		}	
		
	@Test(enabled=false,priority=26)
	public void Cash_Discount_Method_Edit_Amount(WebDriver driver) throws Exception
		{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			Thread.sleep(1000);
			//Click the fee name input field
			driver.findElement(By.name(excel.getData(3, 401, 3))).clear();
			//Enter the required name
			driver.findElement(By.name(excel.getData(3, 401, 3))).sendKeys("15% Off");
			
			Thread.sleep(1000);
			//Click Cash Discount Name field
			driver.findElement(By.xpath(excel.getData(3, 539, 1))).clear();
			//Enter Cash Discount Name
			driver.findElement(By.xpath(excel.getData(3, 539, 1))).sendKeys("Spl");
			
			Thread.sleep(1000);
			//Click the Cash discount type
			driver.findElement(By.xpath(excel.getData(3, 402, 1))).click();
			Thread.sleep(1000);
			//enter the required type
			driver.findElement(By.xpath(excel.getData(3, 403, 1))).click();
			
			Thread.sleep(1000);
			//Click the fee cash discount rate input field
			driver.findElement(By.name(excel.getData(3, 404, 3))).clear();
			//Enter the required cash discount rate
			driver.findElement(By.name(excel.getData(3, 404, 3))).sendKeys("1000");
			
			//Click the update button
			driver.findElement(By.xpath(excel.getData(3, 540, 1))).click();
			
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 406, 1))).getText().equalsIgnoreCase("Cash Discount Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Cash Discount Updated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cash Discount Updated Fail");
			}
			
			Thread.sleep(2000);wb.close();
		}
		
	@Test(enabled=false,priority=27)
	public void Cash_Discount_Method_Edit_Percentage(WebDriver driver) throws Exception
		{
File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(1000);
		//Click the fee name input field
		driver.findElement(By.name(excel.getData(3, 401, 3))).click();
		//Enter the required name
		driver.findElement(By.name(excel.getData(3, 401, 3))).sendKeys(Keys.BACK_SPACE);
	
		Thread.sleep(1000);
		//Click Cash Discount Name field
		driver.findElement(By.xpath(excel.getData(3, 539, 1))).click();
		//Enter Cash Discount Name
		driver.findElement(By.xpath(excel.getData(3, 539, 1))).sendKeys(Keys.BACK_SPACE);

		
		Thread.sleep(1000);
		//Click the Cash discount type
		driver.findElement(By.xpath(excel.getData(3, 402, 1))).click();
		Thread.sleep(1000);
		//enter the required type
		driver.findElement(By.xpath(excel.getData(3, 407, 1))).click();
		
		Thread.sleep(1000);
		//Click the fee cash discount rate input field
		driver.findElement(By.name(excel.getData(3, 404, 3))).clear();
		//Enter the required cash discount rate
		driver.findElement(By.name(excel.getData(3, 404, 3))).sendKeys("1000");
		
		//Click the update button
		driver.findElement(By.xpath(excel.getData(3, 540, 1))).click();
		
		Thread.sleep(2000);
		
		//Check weather the new form loaded or not
		if(driver.findElement(By.xpath(excel.getData(3, 406, 1))).getText().equalsIgnoreCase("Cash Discount Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Cash Discount Updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cash Discount Updated Fail");
		}
		
		Thread.sleep(2000);wb.close();
	}

}
