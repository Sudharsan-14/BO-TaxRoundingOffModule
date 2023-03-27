package epicList_Chrome;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Royalty_Franchise_ACH_Settings {
	public WebDriver driver;
		
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Royalty_Franchise_ACH_Settings");

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
				Browser a = new Browser();
				a.UPOS_login(driver, test);
			} else {
				Browser a = new Browser();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser a = new Browser();
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
			Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_ettings(driver);
			Enterprice_Royalty_Franchise_Settings_method_Update_ACH_Settings(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=12)
	public void Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_ettings(WebDriver driver) throws Exception
	{
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);

		/*driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+("Royalty_Franchise_Settings"));
				
		Thread.sleep(3000);
        //Click the Royalty/Franchise Option		
		driver.findElement(By.xpath("//span[.='Royalty/Franchise']")).click();
		
		Thread.sleep(5000);
		//Check Royalty/Franchise page opened or not
		if(driver.findElement(By.xpath("//a[.='Royalty/Franchise']")).getText().equalsIgnoreCase("Royalty/Franchise"))
		{
			test.log(LogStatus.PASS, "Royalty/Franchise Settings page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Royalty/Franchise Settings page loaded Failed");
		}
		*/
		Thread.sleep(3000);
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"royalty");
		//Click the ACH Settings
		driver.findElement(By.xpath(excel.getData(2, 327, 1))).click();
		Thread.sleep(2500);
		try
		{
		//Check ACH Settings page opened or not
		if(driver.findElement(By.xpath(excel.getData(2, 328, 1))).getText().equalsIgnoreCase("ACH Settings"))
		{
			test.log(LogStatus.PASS, "ACH Settings page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "ACH Settings page loaded Failed");
		
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

	@Test(enabled=false,priority=13)
	public void Enterprice_Royalty_Franchise_Settings_method_Update_ACH_Settings(WebDriver driver) throws Exception
	{

		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(5000);
 
		//Clear the Destination Name
		driver.findElement(By.xpath(excel.getData(2, 329, 1))).clear();
		//Enter the Destination name
		driver.findElement(By.xpath(excel.getData(2, 329, 1))).sendKeys("Dest_Test");
		
		Thread.sleep(1500);
		//Clear the Destination Routing number
		driver.findElement(By.xpath(excel.getData(2, 330, 1))).clear();
		//Enter the Destination Routing number
		driver.findElement(By.xpath(excel.getData(2, 330, 1))).sendKeys("2224222");
		
		Thread.sleep(1500);
		//Clear the Immediate Origin name
		driver.findElement(By.xpath(excel.getData(2, 331, 1))).clear();
		//Enter the Immediate Origin Name
		driver.findElement(By.xpath(excel.getData(2, 331, 1))).sendKeys("Org_Test");
		
		Thread.sleep(1500);
		//Clear the Immediate Origin number
		driver.findElement(By.xpath(excel.getData(2, 332, 1))).clear();
		//Enter the Immediate Origin number
		driver.findElement(By.xpath(excel.getData(2, 332, 1))).sendKeys("4545");
		
		Thread.sleep(1500);
		//Clear the Company name
		driver.findElement(By.xpath(excel.getData(2, 333, 1))).clear();
		//Enter the Company Name
		driver.findElement(By.xpath(excel.getData(2, 333, 1))).sendKeys("Comp_Test");
		
		Thread.sleep(1500);
		//Clear the Company entry description
		driver.findElement(By.xpath(excel.getData(2, 334, 1))).clear();
		//Enter the Company entry description
		driver.findElement(By.xpath(excel.getData(2, 334, 1))).sendKeys("Desc_Test");
		
		Thread.sleep(1500);
		//Clear the Effective Entry Date
		driver.findElement(By.xpath(excel.getData(2, 335, 1))).clear();
		//Enter the Effective Entry Date
		driver.findElement(By.xpath(excel.getData(2, 335, 1))).sendKeys("02-Jan-2020");
		
		Thread.sleep(1500);
		//Clear the Company Discretionary Data
		driver.findElement(By.xpath(excel.getData(2, 336, 1))).clear();
		//Enter the Company Discretionary Data
		driver.findElement(By.xpath(excel.getData(2, 336, 1))).sendKeys("Disc_Data");
		
		Thread.sleep(1500);
		//Clear the Company Identification Number
		driver.findElement(By.xpath(excel.getData(2, 337, 1))).clear();
		//Enter the Company Identification Number
		driver.findElement(By.xpath(excel.getData(2, 337, 1))).sendKeys("2552");
		
		Thread.sleep(1500);
		//Clear the Originating DFI Identification
		driver.findElement(By.xpath(excel.getData(2, 338, 1))).clear();
		//Enter the Originating DFI Identification
		driver.findElement(By.xpath(excel.getData(2, 338, 1))).sendKeys("5555");

		Thread.sleep(3000);
		//Click the Update button
		driver.findElement(By.xpath(excel.getData(2, 339, 1))).click();
		
		Thread.sleep(3000);
		//Check whether the account user is saved or not
		if(driver.findElement(By.xpath(excel.getData(2, 340, 1))).getText().equalsIgnoreCase("Saved Successfully"))
		{
			test.log(LogStatus.PASS, "ACH Settings updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "ACH Settings updated fail");
		}
		wb.close();
		Thread.sleep(3000);
	}

}
