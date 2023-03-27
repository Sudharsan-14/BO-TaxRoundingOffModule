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

public class Settings_CDS_Settings 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("CDS Settings");
	
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
			CDS_Settings_method_Open_CDS_Settings(driver);
			Update_CDS_Settings_method_CDS_Settings(driver);
			Verify_CDS_Settings_method_CDS_Settings(driver);
			Thread.sleep(1500);
			
		}

		@Test(priority=35,enabled = false)
		public void CDS_Settings_method_Open_CDS_Settings(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
	
			Thread.sleep(15000);
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newCDSSettings");
			Thread.sleep(5000);	
			try
			{
			//Check CDS Settings page opened or not
			if(driver.findElement(By.xpath(excel.getData(3, 2007, 1))).getText().equalsIgnoreCase("CDS Settings"))
			{
				test.log(LogStatus.PASS, "CDS Settings page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "CDS Settings page loaded Failed");
			
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
		
		@Test(priority=36,enabled = false)
		public void Update_CDS_Settings_method_CDS_Settings(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		
			Thread.sleep(5000);
			//Check Whether the Show Advertisements is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2000, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(3000);
				//Check whether the Show Advertisements is enabled or not
				driver.findElement(By.xpath(excel.getData(3, 2000, 1))).click();
			}
		
			Thread.sleep(2000);
			//Check Whether the Customer Profile is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2001, 1))).isSelected())
			{
				
			}
			else
			{
				Thread.sleep(3000);
				//Check whether the Customer Profile is enabled or not
				driver.findElement(By.xpath(excel.getData(3, 2001, 1))).click();
			}
			
			
			Thread.sleep(2000);
			//Click the Discount Text box
			driver.findElement(By.xpath(excel.getData(3, 2002, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2002, 1))).sendKeys("Discount Code Applied");

			Thread.sleep(2000);
			//Click the Loyalty box
			driver.findElement(By.xpath(excel.getData(3, 2003, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2003, 1))).sendKeys("Loyalty Credited");
	
			Thread.sleep(2000);
			//Click the Get Number Text box
			driver.findElement(By.xpath(excel.getData(3, 2004, 1))).clear();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2004, 1))).sendKeys("Number Generated.. AC No:1***520");
	
			Thread.sleep(2000);
			//Click the Choose Theme
			driver.findElement(By.xpath(excel.getData(3, 2005, 1))).click();
			//Enter the required Option
			driver.findElement(By.xpath(excel.getData(3, 2006, 1))).sendKeys("LIGHT");
			//Press the Enter button
			driver.findElement(By.xpath(excel.getData(3, 2006, 1))).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the Update Template button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();
			
			WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			//Check weather the CDS Settings Updated or not
			if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("CDS Settings Updated Successfully"))
			{
				test.log(LogStatus.PASS, "CDS Settings Updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "CDS Settings Updated fail");
			}

			wb.close();
			Thread.sleep(3000);
		
		}
		@Test(priority=37,enabled = false)
		public void Verify_CDS_Settings_method_CDS_Settings(WebDriver driver) throws Exception
		{
			File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			Thread.sleep(5000);
		       //Check whether the Show Advertisements is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 2000, 1))).isSelected())
				{
					test.log(LogStatus.PASS, "Show Advertisements enabled in CDS Settings Template");
		        }
		        else
		        {
		        	test.log(LogStatus.FAIL, "Show Advertisements disabled in CDS Settings Template");
		        }
				
				Thread.sleep(1000);
			       //Check whether the Customer Profile is enabled or not
					if(driver.findElement(By.xpath(excel.getData(3, 2001, 1))).isSelected())
					{
						test.log(LogStatus.PASS, "Customer Profile enabled in CDS Settings Template");
			        }
			        else
			        {
			        	test.log(LogStatus.FAIL, "Customer Profile disabled in CDS Settings Template");
			        }
					

								wb.close();  
					Thread.sleep(5000);
		}



}
