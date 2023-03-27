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
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Edit_CutAndModify {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Edit_CutAndModify");
	
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
			Cut_and_Modify_Method_openCutAndModify(driver);
			Cut_and_Modify_Method_editCutAndModify(driver);
			Cut_and_Modify_Method_cancelButton(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=51)
	public void Cut_and_Modify_Method_openCutAndModify(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"cutAndModify");
		Thread.sleep(5000);
		try
		{
		//Check Gift Cards page opened or not
		if(driver.findElement(By.xpath(excel.getData(3, 2122, 1))).getText().equalsIgnoreCase("Cut and modify"))
		{
			test.log(LogStatus.PASS, "Cut and modify page loaded Successfully");
			
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut and modify page loaded Failed");
		
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
		
	@Test(enabled=false,priority=52)
	public void Cut_and_Modify_Method_editCutAndModify(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(5000);
		//Click the First Edit button in the Cut and modify page
		driver.findElement(By.xpath(excel.getData(3, 2123, 1))).click();
		
		//Clear the percentage field
		driver.findElement(By.name(excel.getData(3, 92, 3))).clear();
		//Enter the required name
		driver.findElement(By.name(excel.getData(3, 92, 3))).sendKeys("51");
		
		//Click the Save button
		driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
		Thread.sleep(2000);
		//Check whether the new discount saved or not
		if(driver.findElement(By.xpath(excel.getData(3, 39, 1))).getText().equalsIgnoreCase("Cut and modify saved successfully"))
		{
			test.log(LogStatus.PASS, "Cut and modify saved successfully for option 1");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut and modify saved successfully for option 1");
		}
		wb.close();
		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=53)
	public void Cut_and_Modify_Method_cancelButton(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(5000);
		//Click the First Edit button in the Cut and modify page
		driver.findElement(By.xpath(excel.getData(3, 2123, 1))).click();
		
		//Click the Close button
		driver.findElement(By.xpath(excel.getData(3, 544, 1))).click();
		Thread.sleep(2000);
		//Check whether the new discount saved or not
		if(driver.findElement(By.xpath(excel.getData(3, 2123, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Cut and Modify canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut and Modify canceled failed");
		}
		wb.close();
		Thread.sleep(5000);
	}
}
