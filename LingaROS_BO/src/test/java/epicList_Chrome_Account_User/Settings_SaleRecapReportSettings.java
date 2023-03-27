package epicList_Chrome_Account_User;

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


public class Settings_SaleRecapReportSettings 
{

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_SaleRecapReportSettings");
	
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
			Sale_Recap_Settings_Method_openpage(driver);
			Sale_Recap_Settings_Method_edit(driver);
			Verify_Sale_Recap_Settings_Method(driver);
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

	@Test(enabled=false,priority=30)
	public void Sale_Recap_Settings_Method_openpage(WebDriver driver) throws Exception
		{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
			Thread.sleep(5000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newSaleRecapReportSettings");
			
				
		
			Thread.sleep(6000);
			try
			{
			//Check whether the Sale recap Report Settings page is loaded or not
			if(driver.findElement(By.xpath(excel.getData(3, 2030, 1))).getText().equalsIgnoreCase("SaleRecap Report Settings"))
			{
				test.log(LogStatus.PASS, " Sale recap Report Settings page loaded successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, " Sale recap Report Settings page loaded fail");
			
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
		
	@Test(enabled=false,priority=31)
	public void Sale_Recap_Settings_Method_edit(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		
			Thread.sleep(6000);
			//Check weather the Guests/Checks check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2031, 1))).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the Guests/Checks check box
				driver.findElement(By.xpath(excel.getData(3, 2031, 1))).click();
				Thread.sleep(1000);
			}
			
			
			Thread.sleep(3000);
			//Check weather the Cover is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2032, 1))).isSelected())
			{
				Thread.sleep(2000);
				//Select the Level
				driver.findElement(By.xpath(excel.getData(3, 2033, 1))).click();
				Thread.sleep(1000);
				//Enter the required Option
				driver.findElement(By.xpath(excel.getData(3, 2034, 1))).sendKeys("Category");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 2034, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(60000);
				//Select the Choose Sub Category
				driver.findElement(By.xpath(excel.getData(3, 2035, 1))).click();
				//Enter the required Option
				driver.findElement(By.xpath(excel.getData(3, 2036, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 2036, 1))).sendKeys(Keys.ENTER);

			}
			else
			{
				Thread.sleep(2000);
				//Enable the Cover
				driver.findElement(By.xpath(excel.getData(3, 2032, 1))).click();
				Thread.sleep(2000);
				//Select the Level
				driver.findElement(By.xpath(excel.getData(3, 2033, 1))).click();
				//Enter the required Option
				driver.findElement(By.xpath(excel.getData(3, 2034, 1))).sendKeys("Category");
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 2034, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(60000);
				//Select the Choose Sub Category
				driver.findElement(By.xpath(excel.getData(3, 2035, 1))).click();
				//Enter the required Option
				driver.findElement(By.xpath(excel.getData(3, 2036, 1))).sendKeys(Keys.ARROW_DOWN);
				//Press the Enter button
				driver.findElement(By.xpath(excel.getData(3, 2036, 1))).sendKeys(Keys.ENTER);
				
			}
			
			
			Thread.sleep(2000);
			//Check weather the grandSales check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2037, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the grandSales check box
				driver.findElement(By.xpath(excel.getData(3, 2037, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the grossReceipt check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2038, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the grossReceipt check box
				driver.findElement(By.xpath(excel.getData(3, 2038, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the grossVoid check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2039, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the grossVoid check box
				driver.findElement(By.xpath(excel.getData(3, 2039, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the netVoid check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2040, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the netVoid check box
				driver.findElement(By.xpath(excel.getData(3, 2040, 1))).click();
				Thread.sleep(1000);
			}
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

			
			Thread.sleep(2000);
			//Check weather the creditCardSplitUps check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2041, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the creditCardSplitUp check box
				driver.findElement(By.xpath(excel.getData(3, 2041, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the otherPayments check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2042, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the otherPayments check box
				driver.findElement(By.xpath(excel.getData(3, 2042, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the taxes check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2043, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the taxes check box
				driver.findElement(By.xpath(excel.getData(3, 2043, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the taxExempt check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2044, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the taxExempt check box
				driver.findElement(By.xpath(excel.getData(3, 2044, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Tax with Net Sales check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2045, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the Tax with Net Sales check box
				driver.findElement(By.xpath(excel.getData(3, 2045, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Cash Expected check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2046, 1))).isSelected())
			{
								
			}
			else
			{
				Thread.sleep(2000);
				//Enable the Cash Expected check box
				driver.findElement(By.xpath(excel.getData(3, 2046, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Overage/Shortage check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2051, 1))).isSelected())
			{
								
			}
			else
			{
				Thread.sleep(2000);
				//Enable the Overage/Shortage check box
				driver.findElement(By.xpath(excel.getData(3, 2051, 1))).click();
				Thread.sleep(1000);
			}

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		//	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		//	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			

			
			Thread.sleep(2000);
			//Check weather the Department Summary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2052, 1))).isSelected())
			{
								
			}
			else
			{
				Thread.sleep(2000);
				//Enable the Department Summary check box
				driver.findElement(By.xpath(excel.getData(3, 2052, 1))).click();
				Thread.sleep(1000);
			}
			
			
			Thread.sleep(2000);
			//Check weather the categorySummary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2053, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the categorySummary check box
				driver.findElement(By.xpath(excel.getData(3, 2053, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the hourlySummary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2054, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the hourlySummary check box
				driver.findElement(By.xpath(excel.getData(3, 2054, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the openingBalance check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2055, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the openingBalance check box
				driver.findElement(By.xpath(excel.getData(3, 2055, 1))).click();
				Thread.sleep(1000);
			}
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

			
			Thread.sleep(2000);
			//Check weather the Paid In/Out check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2056, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the paid check box
				driver.findElement(By.xpath(excel.getData(3, 2056, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Cash drop check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2057, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the Cash drop check box
				driver.findElement(By.xpath(excel.getData(3, 2057, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Cash Expected in Summary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2058, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the Cash Expected in Summary check box
				driver.findElement(By.xpath(excel.getData(3, 2058, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Over / Shortage in Summary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2059, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Enable the Over / Shortage in Summary check box
				driver.findElement(By.xpath(excel.getData(3, 2059, 1))).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Exclude Opening Balance Calculation is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2060, 1))).isSelected()) {}
			else
			{
				Thread.sleep(3000);
				//Enable the Exclude Opening Balance Calculation
				driver.findElement(By.xpath(excel.getData(3, 2060, 1))).click();
			}
			
			Thread.sleep(2000);
			//Check weather the Exclude Paid In is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2061, 1))).isSelected()) {}
			else
			{
				Thread.sleep(3000);
				//Enable the Exclude Paid in 
				driver.findElement(By.xpath(excel.getData(3, 2061, 1))).click();
			}
			
			Thread.sleep(2000);
			//Check weather the Tips & Gratuity included in Cash Expected is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2062, 1))).isSelected()) {}
			else
			{
				Thread.sleep(3000);
				//Enable the Tips & Gratuity included in Cash Expected 
				driver.findElement(By.xpath(excel.getData(3, 2062, 1))).click();
			}
			
			Thread.sleep(2000);
			//Check weather the Include Tip Charge In Cash Expected is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2063, 1))).isSelected()) {}
			else
			{
				Thread.sleep(3000);
				//Enable the Include Tip Charge In Cash Expected 
				driver.findElement(By.xpath(excel.getData(3, 2063, 1))).click();
			}
			Thread.sleep(2000);
			for(int i=1;i<=3;i++)
			{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			}
			Thread.sleep(3000);
			try
			{
			//Check whether the User defined name is selected or not
			if(driver.findElement(By.xpath(excel.getData(3, 2064, 1))).isSelected()){}
			else
			{
				Thread.sleep(2000);
				//Click the user defined name check box
				driver.findElement(By.xpath(excel.getData(3, 2064, 1))).click();
			}
			}
			catch(Exception l) {}
			
			Thread.sleep(3000);
			//Click the update button
			driver.findElement(By.xpath(excel.getData(3, 572, 1))).click();

			WebElement SalRe=driver.findElement(By.xpath(excel.getData(3, 39, 1)));
			WebDriverWait wait=new WebDriverWait(driver,200);
			
			//Check weather theSale recap Report Settings is updated or not
			if(wait.until(ExpectedConditions.visibilityOf(SalRe)).getText().equalsIgnoreCase("Sale Recap Report Settings Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Sale recap Report Settings updated successfully");
			}
			
			else
			{
				test.log(LogStatus.FAIL, "Sale recap Report Settings updated fail");
			}
			wb.close();
			Thread.sleep(4000);
			}
	
	@Test(enabled=false,priority=32)
	public void Verify_Sale_Recap_Settings_Method(WebDriver driver) throws Exception
	{
		 File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		
			Thread.sleep(5000);
			//Check weather the Guests/Checks check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2031, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Guests/Checks check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Guests/Checks check box is not enabled");
			}
			
			
			Thread.sleep(2000);
			//Check weather the Cover is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2032, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Cover check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cover check box is not enabled");
			}
			
			
			Thread.sleep(2000);
			//Check weather the grandSales check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2037, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Grand Sales check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Grand Sales check box is not enabled");
			}
			
			Thread.sleep(2000);
			//Check weather the grossReceipt check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2038, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Gross Receipt check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gross Receipt check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the grossVoid check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2039, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Gross Void check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Gross Void check box is not enabled");
			
			}

			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			
			Thread.sleep(2000);
			//Check weather the netVoid check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2040, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Net Void check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Net Void check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the creditCardSplitUps check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2041, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "CC Split Ups check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "CC Split Ups check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the otherPayments check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2042, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Other Payments check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Other Payments check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the taxes check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2043, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Taxs check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Taxs check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the taxExempt check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2044, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Tax Exempt check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tax Exempt check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Tax with Net Sales check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2045, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Tax with Net Sales check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tax with Net Sales check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Cash Expected check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2046, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Cash Expected check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cash Expected check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Overage/Shortage check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2051, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Overage/Shortage check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Overage/Shortage check box is not enabled");
			
			}
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);


			Thread.sleep(2000);
			//Check weather the Department Summary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2052, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Department Summary check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Department Summary check box is not enabled");
			
			}
			
			
			Thread.sleep(2000);
			//Check weather the categorySummary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2053, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Category Summary check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Category Summary check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the hourlySummary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2054, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Hourly Summary check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Hourly Summary check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the openingBalance check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2055, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Opening Balance check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Opening Balance check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Paid In/Out check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2056, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Paid In/Out check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Paid In/Out check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Cash drop check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2057, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Cash Drop check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cash Drop check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Cash Expected in Summary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2058, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Cash Expected check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Cash Expected check box is not enabled");
			
			}
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);

			
			Thread.sleep(2000);
			//Check weather the Over / Shortage in Summary check box is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2059, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "Over / Shortage in Summary check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Over / Shortage in Summary check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Exclude Opening Balance Calculation is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2060, 1))).isSelected()) 
			{
				test.log(LogStatus.PASS, "Exclude Opening Balance Calculation check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Exclude Opening Balance Calculation check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Exclude Paid In is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2061, 1))).isSelected()) 
			{
				test.log(LogStatus.PASS, "Exclude Paid In check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Exclude Paid In check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Tips & Gratuity included in Cash Expected is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2062, 1))).isSelected()) 
			{
				test.log(LogStatus.PASS, "Tips & Gratuity included in Cash Expected check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tips & Gratuity included in Cash Expected check box is not enabled");
			
			}
			
			Thread.sleep(2000);
			//Check weather the Include Tip Charge In Cash Expected is enabled or not
			if(driver.findElement(By.xpath(excel.getData(3, 2063, 1))).isSelected()) 
			{
				test.log(LogStatus.PASS, "Included Tip Charge in Cash Expected check box is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "Included Tip Charge in Cash Expected check box is not enabled");
			
			}
			
			Thread.sleep(1000);
			//Check whether the User defined name is selected or not
			if(driver.findElement(By.xpath(excel.getData(3, 2064, 1))).isSelected())
			{
				test.log(LogStatus.PASS, "User Defined Radio Button is enabled");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Defined Radio Button is not enabled");
			
			}
			wb.close();
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);
			//watchTutorial(driver);
			Thread.sleep(5000);
			}

			Thread.sleep(5000);
			
	}
}
