package epicList_Chrome;

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

public class Settings_Other_Payment_Receipt_Template 
{
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings-Other Payment Receipt Template");
	
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
			Other_Payment_method_open_Other_Payment_Receipt_Template(driver);
			Update_Other_Payment_method_Other_Payment_Receipt_Template(driver);
			Verify_Other_Payment_method_Other_Payment_Receipt_Template(driver);
			Thread.sleep(1500);
			
		}
			@Test(priority=35,enabled = false)
			public void Other_Payment_method_open_Other_Payment_Receipt_Template(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
				Thread.sleep(15000);
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"newOtherPaymentReceiptTemplate");
				Thread.sleep(5000);		
				try
				{
				//Check Other Payment Receipt Template page opened or not
				if(driver.findElement(By.xpath(excel.getData(3, 1972, 1))).getText().equalsIgnoreCase("Other Payment Receipt Template"))
				{
					test.log(LogStatus.PASS, "Other Payment Receipt Template page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Other Payment Receipt Template page loaded Failed");
				
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
		
			@Test(priority=36,enabled = false)
			public void Update_Other_Payment_method_Other_Payment_Receipt_Template(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			
				Thread.sleep(2000);
				//Check Whether the Print CC balance in Customer Copy is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1973, 1))).isSelected())
				{
					
				}
				else
				{
					Thread.sleep(1000);
					//Check whether the Print CC balance in Customer Copy is enabled or not
					driver.findElement(By.xpath(excel.getData(3, 1973, 1))).click();
				}
			
				Thread.sleep(2000);
				//Check Whether the Print HA balance in Customer Copy is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1974, 1))).isSelected())
				{
					
				}
				else
				{
					Thread.sleep(1000);
					//Check whether the Print HA balance in Customer Copy is enabled or not
					driver.findElement(By.xpath(excel.getData(3, 1974, 1))).click();
				}
				
				Thread.sleep(2000);
				//Check Whether the Print CC balance in Merchant Copy is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1975, 1))).isSelected())
				{
					
				}
				else
				{
					Thread.sleep(1000);
					//Check whether the Print CC balance in Merchant Copy is enabled or not
					driver.findElement(By.xpath(excel.getData(3, 1975, 1))).click();
				}
				
				Thread.sleep(2000);
				//Check Whether the Print HA balance in Merchant Copy is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1976, 1))).isSelected())
				{
					
				}
				else
				{
					Thread.sleep(1000);
					//Check whether the Print HA balance in Merchant Copy is enabled or not
					driver.findElement(By.xpath(excel.getData(3, 1976, 1))).click();
				}
				
				Thread.sleep(2000);
				//Check Whether the Print Tab Name in Merchant Copy is enabled or not
				if(driver.findElement(By.xpath(excel.getData(3, 1977, 1))).isSelected())
				{
					
				}
				else
				{
					Thread.sleep(1000);
					//Check whether the Print Tab Name in Merchant Copy is enabled or not
					driver.findElement(By.xpath(excel.getData(3, 1977, 1))).click();
				}
				
			if(driver.findElement(By.xpath(excel.getData(3, 2167, 1))).isDisplayed())
				{
				Thread.sleep(5000);
				//Click the Update Template button
				driver.findElement(By.xpath(excel.getData(3, 2167, 1))).click();
		
				WebElement ele=driver.findElement(By.xpath(excel.getData(3, 39, 1)));

				WebDriverWait wait=new WebDriverWait(driver,60);
				//Check weather the Other Payment Receipt Template Updated or not
				if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Receipt Template Saved Successfully"))
				{
					test.log(LogStatus.PASS, "Other Payment Receipt Template Updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Other Payment Receipt Template Updated fail");
				}
	
				
				}
			else
			{
				
			}
				
				
				wb.close();
				Thread.sleep(5000);
			
			}
			@Test(priority=37,enabled = false)
			public void Verify_Other_Payment_method_Other_Payment_Receipt_Template(WebDriver driver) throws Exception
			{
				File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
				
				FileInputStream fis = new FileInputStream(src);
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				
				XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
				
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
				Thread.sleep(5000);
			       //Check whether the Print CC balance in Customer Copy is enabled or not
					if(driver.findElement(By.xpath(excel.getData(3, 1973, 1))).isSelected())
					{
						test.log(LogStatus.PASS, "Print CC balance in Customer Copy enabled in Other Payment Receipt Template Template");
			        }
			        else
			        {
			        	test.log(LogStatus.FAIL, "Print CC balance in Customer Copy disabled in Other Payment Receipt Template Template");
			        }
					
					Thread.sleep(2000);
				       //Check whether the Print HA balance in Customer Copy is enabled or not
						if(driver.findElement(By.xpath(excel.getData(3, 1974, 1))).isSelected())
						{
							test.log(LogStatus.PASS, "Print HA balance in Customer Copy enabled in Other Payment Receipt Template Template");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Print HA balance in Customer Copy disabled in Other Payment Receipt Template Template");
				        }
						

						Thread.sleep(2000);
					       //Check whether the Print CC balance in Merchant Copy is enabled or not
						if(driver.findElement(By.xpath(excel.getData(3, 1975, 1))).isSelected())
						{
							test.log(LogStatus.PASS, "Print CC balance in Merchant Copy enabled in Other Payment Receipt Template Template");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Print CC balance in Merchant Copy disabled in Other Payment Receipt Template Template");
				        }
						

						Thread.sleep(2000);
					       //Check whether the Print HA balance in Merchant Copy is enabled or not
						if(driver.findElement(By.xpath(excel.getData(3, 1976, 1))).isSelected())
						{
							test.log(LogStatus.PASS, "Print HA balance in Merchant Copy enabled in Other Payment Receipt Template Template");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Print HA balance in Merchant Copy disabled in Other Payment Receipt Template Template");
				        }
						

						Thread.sleep(2000);
					       //Check whether the Print Tab Name in Merchant Copy is enabled or not
						if(driver.findElement(By.xpath(excel.getData(3, 1977, 1))).isSelected())
						{
							test.log(LogStatus.PASS, "Print Tab Name in Merchant Copy enabled in Other Payment Receipt Template Template");
				        }
				        else
				        {
				        	test.log(LogStatus.FAIL, "Print Tab Name in Merchant Copy disabled in Other Payment Receipt Template Template");
				        }
						wb.close();  
						Thread.sleep(5000);
			}

}
