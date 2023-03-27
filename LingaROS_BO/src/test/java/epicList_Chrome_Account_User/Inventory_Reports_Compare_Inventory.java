package epicList_Chrome_Account_User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Inventory_Reports_Compare_Inventory {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Compare_Inventory");

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
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			} else {
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Linga_login(driver, test);
			}
		}

		@Test(priority = 500)
		public void logout() throws Exception {
			Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			Inventory_Reports_Compare_Inventory_Openpage(driver);
			Verify_and_Enable_Compare_Inventory_Toggles(driver);
			Inventory_Reports_Compare_Inventory_ALL_Today(driver);
			Inventory_Reports_Compare_Inventory_ALL_Yesterday(driver);
			Inventory_Reports_Compare_Inventory_ALL_Last_N_days(driver);
			Inventory_Reports_Compare_Inventory_ALL_This_Week(driver);
			Inventory_Reports_Compare_Inventory_ALL_Last_Week(driver);
			Inventory_Reports_Compare_Inventory_ALL_Last_7_days(driver);
			Inventory_Reports_Compare_Inventory_ALL_This_Month(driver);
			Inventory_Reports_Compare_Inventory_ALL_Last_Month(driver);
			Inventory_Reports_Compare_Inventory_ALL_Last_30_days(driver);
			Inventory_Reports_Compare_Inventory_ALL_Specific_Date(driver);
			Inventory_Reports_Compare_Inventory_ALL_Date_Range(driver);
			Inventory_Reports_Compare_Inventory_Inventory_Item(driver);
			Inventory_Reports_Compare_Inventory_SubRecipe(driver);
			Inventory_Reports_Compare_Inventory_Menu_Item(driver);
			Thread.sleep(1500);
		}
	
	@Test(priority=45,enabled = false) 
	public void Inventory_Reports_Compare_Inventory_Openpage(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	 
		/*//Click the Inventory option
				driver.findElement(By.xpath("//span[.='Inventory']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[contains(.,'Reports')]"));
				//Scroll the page till the Inventory Reports option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Inventory Reports Option		
				driver.findElement(By.xpath("//span[contains(.,'Reports')]")).click();
				 //driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/ul/li[11]/a/i")).click();
				*/
				Thread.sleep(5000);
				//Get the URl
			  	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"compareInventory");
				
				Thread.sleep(5000);
				try
				{
				//Check Compare Inventory page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 250, 1))).getText().equalsIgnoreCase("Compare Inventory"))
				{
					test.log(LogStatus.PASS, "Compare Inventory page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Compare Inventory Inventory page loaded Failed");
				
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
			
	
	@Test(priority = 46,enabled = false)
	public void Verify_and_Enable_Compare_Inventory_Toggles(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		test.log(LogStatus.INFO, "Central Inventory Report Starts for Toggle Validation");

		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Date Range"); 
		
		//Select the From Date Range
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
		//Enter the required From Date Range
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
				
		//Select the TO Date Range
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
		//Enter the  required TO Date Range
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
		
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		
		Thread.sleep(2000);
		//Check whether the Beginning Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2629, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Beginning Toggle Available on the Report");
		
		//Check whether the Beginning enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2629, 1))).isEnabled())
		{
			//Check whether the Beginning Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2630, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Beginning Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Beginning Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Beginning
			driver.findElement(By.xpath(excel.getData(3, 2631, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Beginning Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2630, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Beginning Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Beginning Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Beginning Toggle Not Available on the Report");

		}
		
		Thread.sleep(2000);
		//Check whether the Purchase Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2632, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Purchase Toggle Available on the Report");
		
		//Check whether the Purchase enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2632, 1))).isEnabled())
		{
			//Check whether the Purchase Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2633, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Purchase Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Purchase Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Purchase
			driver.findElement(By.xpath(excel.getData(3, 2634, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Purchase Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2633, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Purchase Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Purchase Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Purchase Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Ideal Used Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2635, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Ideal Used Toggle Available on the Report");
		
		//Check whether the Ideal Used enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2635, 1))).isEnabled())
		{
			//Check whether the Ideal Used Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2636, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Ideal Used Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Ideal Used Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Ideal Used
			driver.findElement(By.xpath(excel.getData(3, 2637, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Ideal Used Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2636, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Ideal Used Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Ideal Used Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Ideal Used Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Actual Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2638, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Actual Toggle Available on the Report");
		
		//Check whether the Actual enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2638, 1))).isEnabled())
		{
			//Check whether the Actual Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2639, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Actual Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Actual Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Actual
			driver.findElement(By.xpath(excel.getData(3, 2640, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Actual Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2639, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Actual Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Actual Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Actual Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Variance Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2641, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Variance Toggle Available on the Report");
		
		//Check whether the Variance enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2641, 1))).isEnabled())
		{
			//Check whether the Variance Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2642, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Variance Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Variance Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Variance
			driver.findElement(By.xpath(excel.getData(3, 2643, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Variance Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2642, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Variance Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Variance Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Variance Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Waste Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2644, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Waste Toggle Available on the Report");
		
		//Check whether the Waste enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2644, 1))).isEnabled())
		{
			//Check whether the Waste Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2645, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Waste Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Waste Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Waste
			driver.findElement(By.xpath(excel.getData(3, 2646, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Waste Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2645, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Waste Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Waste Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Waste Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Usage Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2647, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Usage Toggle Available on the Report");
		
		//Check whether the Usage enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2647, 1))).isEnabled())
		{
			//Check whether the Usage Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2648, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Usage Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Usage Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Usage
			driver.findElement(By.xpath(excel.getData(3, 2649, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Usage Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2648, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Usage Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Usage Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Usage Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Transfer in Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2650, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Transfer in Toggle Available on the Report");
		
		//Check whether the Transfer in enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2650, 1))).isEnabled())
		{
			//Check whether the Transfer in Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2651, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Transfer in Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Transfer in Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Transfer in
			driver.findElement(By.xpath(excel.getData(3, 2652, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Transfer in Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2651, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Transfer in Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Transfer in Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Transfer in Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the Transfer out Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2653, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Transfer out Toggle Available on the Report");
		
		//Check whether the Transfer out enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2653, 1))).isEnabled())
		{
			//Check whether the Transfer out Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2654, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Transfer out Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Transfer out Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Transfer out
			driver.findElement(By.xpath(excel.getData(3, 2655, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Transfer out Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2654, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Transfer out Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Transfer out Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Transfer out Toggle Not Available on the Report");

		}
		
		
		Thread.sleep(2000);
		//Check whether the On hand Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2656, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "On hand Toggle Available on the Report");
		
		//Check whether the On hand enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2656, 1))).isEnabled())
		{
			//Check whether the On hand Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2657, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "On hand Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "On hand Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the On hand
			driver.findElement(By.xpath(excel.getData(3, 2658, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the On hand Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2657, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "On hand Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "On hand Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "On hand Toggle Not Available on the Report");

		}
		
		
		
		Thread.sleep(2000);
		//Check whether the Unit Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2659, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Unit Toggle Available on the Report");
		
		//Check whether the Unit enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2659, 1))).isEnabled())
		{
			//Check whether the Unit Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2660, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Unit Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Unit Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Unit
			driver.findElement(By.xpath(excel.getData(3, 2661, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Unit Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2660, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Unit Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Unit Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Unit Toggle Not Available on the Report");

		}
		
		Thread.sleep(2000);
		//Check whether the Type Toggle Available or not
		if(driver.findElement(By.xpath(excel.getData(3, 2662, 1))).isDisplayed())
		{
			test.log(LogStatus.PASS, "Type Toggle Available on the Report");
		
		//Check whether the Type enabled or not
		if(driver.findElement(By.xpath(excel.getData(3, 2662, 1))).isEnabled())
		{
			//Check whether the Type Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2663, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Type Column Displayed on the Report");
			}
			else
			{
				test.log(LogStatus.FAIL, "Type Column Not Displayed on the Report");
			}
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Type
			driver.findElement(By.xpath(excel.getData(3, 2664, 1))).click();
			
			Thread.sleep(1000);
			//Check whether the Type Column Displayed or not
			if(driver.findElement(By.xpath(excel.getData(3, 2663, 1))).isDisplayed())
			{
				test.log(LogStatus.PASS, "Type Column Displayed on the Report");
			} 
			else
			{
				test.log(LogStatus.FAIL, "Type Column Not Displayed on the Report");
			}
		}
		}
		else
		{
			test.log(LogStatus.FAIL, "Type Toggle Not Available on the Report");

		}wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Toggles Validation");

	}
	
	
	@Test(priority = 47,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Today(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Today");
		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Today"); 
		
			
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString().substring(1).toString();	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Today");

	}
	
	
	@Test(priority = 48,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Yesterday(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Yesterday");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Yesterday"); 
		
		
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Yesterday");

	}
	
	@Test(priority = 49,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Last_N_days(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Last 'N' days");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Last 'N' days"); 
		
		//Clear the No of days
		driver.findElement(By.xpath(excel.getData(3, 2453, 1))).clear();
		//Enter the No of days 
		driver.findElement(By.xpath(excel.getData(3, 2453, 1))).sendKeys("5");
		
			
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Last 'N' days");

	}
	
	@Test(priority = 50,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_This_Week(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for This Week");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("This week"); 
		
	
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for This Week");

	}
	
	@Test(priority = 51,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Last_Week(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Last Week");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Last week"); 
		
	
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Last Week");

	}
	
	@Test(priority = 52,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Last_7_days(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Last 7 days");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Last 7 days"); 
		
	
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price+Wastage_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Last 7 days");

	}
	
	@Test(priority = 53,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_This_Month(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for This Month");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("This month"); 
		
	
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		
		test.log(LogStatus.INFO, "Central Inventory Report Ends for This Month");

	}
	
	@Test(priority = 54,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Last_Month(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Last Month");

		
		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Last month"); 
		
	
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Last Month");

	}
	
	@Test(priority = 55,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Last_30_days(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Last 30 days");

		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Last 30 days"); 
		
	
		Thread.sleep(1000);
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Last 30 days");

	}
	
	@Test(priority = 56,enabled = false)
	public void Inventory_Reports_Compare_Inventory_ALL_Specific_Date(WebDriver driver) throws Exception
	{
	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		test.log(LogStatus.INFO, "Central Inventory Report Starts for Specific Date");

		Thread.sleep(3000);
		//Select type as ALL 
		Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		ALL.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);

		//Select type as ALL 
		Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
		TimePeriod.selectByVisibleText("Specific date"); 
		
		//Clear the Specific date
		driver.findElement(By.xpath(excel.getData(3, 2454, 1))).clear();
		//Enter the Specifc date
		driver.findElement(By.xpath(excel.getData(3, 2454, 1))).sendKeys(Utility.getReportPropertyUser("Specific_Date"));
	
		Thread.sleep(1000); 
		//Click the Run Comparison button
		driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
		
		try
		{
			if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
			{
				test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
			}
		}
		catch(Exception d)
		{
			
			test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
		Thread.sleep(5000);
		
		List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
		int LeftPageSize=leftPagination.size();
		
		//Click the pagination for Report row increase button
		driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
		
		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
		 
		for(int i = 3; i <= rows.size();i++)
		{ 
			
			test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
			
			String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
			double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
			
			String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
			double Variance_Quanity=Double.parseDouble(VarianceQty);
			
//			String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//			double Wastage_Quanity=Double.parseDouble(WasteQty);
			
			double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

			String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
			double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
			
			//Check whether the Actual and Expected Usage Quantity is Equal or not
			if(Actual_Usage_Quanity==Expected_Usage_Quantity)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
				test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
			String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
			double Ideal_Used_Price=Double.parseDouble(IdealPrice);
			
			String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Variance_Price=Double.parseDouble(VariancePrice);
			
//			String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//			double Wastage_Price=Double.parseDouble(WastePrice);
			
			double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

			String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
			
			//Check whether the Actual and Expected Usage Price is Equal or not
			if(Actual_Usage_Price==Expected_Usage_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
			}
			else
			{
				double diff=Actual_Usage_Price-Expected_Usage_Price;

				test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
			}
			
			
			//Get the Beginning Quantity
			String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Quanity=Double.parseDouble(BeginningeQty);
			
			//Purchase Quantity
			String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Quanity=Double.parseDouble(PurchaseQty);
			
			//Transfer In Quantity
			String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Quanity=Double.parseDouble(TransferInQty);
			
			//Transfer Out Quantity
			String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
			
			double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
			
			//Actual On Hand Quantity
			String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
			
			//Check whether the Actual and Expected On Hand Quantity is Equal or not
			if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			//Get the Beginning Quantity
			String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Beginning_Price=Double.parseDouble(BeginningePrice);
			
			//Purchase Quantity
			String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Purchase_Price=Double.parseDouble(PurchasePrice);
			
			//Transfer In Quantity
			String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferIn_Price=Double.parseDouble(TransferInPrice);
			
			//Transfer Out Quantity
			String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double TransferOut_Price=Double.parseDouble(TransferOutPrice);
			
			double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
			
			//Actual On Hand Quantity
			String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
			double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
			
			//Check whether the Actual and Expected On Hand Price is Equal or not
			if(Actual_OnHand_Price==Expected_On_Hand_Price)
			{
				test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
			}
			else
			{
				double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
				test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
			}
			
			
		}
		}	
		wb.close();
		test.log(LogStatus.INFO, "Central Inventory Report Ends for Specific Date");

	}
	
	
	@Test(priority= 57,enabled = false) 
	public void Inventory_Reports_Compare_Inventory_ALL_Date_Range(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		test.log(LogStatus.INFO, "Central Inventory Report Starts for Date Range");

	
				Thread.sleep(3000);
			//Select type as ALL 
			Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
			ALL.selectByVisibleText("All"); 
		 	
			Thread.sleep(3000);

			//Select type as ALL 
			Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			TimePeriod.selectByVisibleText("Date Range"); 
			
			//Select the From Date Range
			driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
			//Enter the required From Date Range
			driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
					
			//Select the TO Date Range
			driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
			//Enter the  required TO Date Range
			driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
			
			try
			{
				if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
				{
					test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
				}
			}
			catch(Exception d)
			{
				
				test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
			Thread.sleep(5000);
			
			List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
			int LeftPageSize=leftPagination.size();
			
			//Click the pagination for Report row increase button
			driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
			
			 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
			 
			for(int i = 3; i <= rows.size();i++)
			{ 
				
				test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
				
				String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
				double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
				
				String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
				double Variance_Quanity=Double.parseDouble(VarianceQty);
				
//				String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//				double Wastage_Quanity=Double.parseDouble(WasteQty);
				
				double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

				String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
				double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
				
				//Check whether the Actual and Expected Usage Quantity is Equal or not
				if(Actual_Usage_Quanity==Expected_Usage_Quantity)
				{
					test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
				}
				else
				{
					double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
					test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
				}
				
				
				String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
				double Ideal_Used_Price=Double.parseDouble(IdealPrice);
				
				String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Variance_Price=Double.parseDouble(VariancePrice);
				
//				String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//				double Wastage_Price=Double.parseDouble(WastePrice);
				
				double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

				String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
				
				//Check whether the Actual and Expected Usage Price is Equal or not
				if(Actual_Usage_Price==Expected_Usage_Price)
				{
					test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
				}
				else
				{
					double diff=Actual_Usage_Price-Expected_Usage_Price;

					test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
				}
				
				
				//Get the Beginning Quantity
				String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Beginning_Quanity=Double.parseDouble(BeginningeQty);
				
				//Purchase Quantity
				String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Purchase_Quanity=Double.parseDouble(PurchaseQty);
				
				//Transfer In Quantity
				String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double TransferIn_Quanity=Double.parseDouble(TransferInQty);
				
				//Transfer Out Quantity
				String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
				
				double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
				
				//Actual On Hand Quantity
				String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
				
				//Check whether the Actual and Expected On Hand Quantity is Equal or not
				if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
				{
					test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
				}
				else
				{
					double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
					test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
				}
				
				//Get the Beginning Quantity
				String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Beginning_Price=Double.parseDouble(BeginningePrice);
				
				//Purchase Quantity
				String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Purchase_Price=Double.parseDouble(PurchasePrice);
				
				//Transfer In Quantity
				String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double TransferIn_Price=Double.parseDouble(TransferInPrice);
				
				//Transfer Out Quantity
				String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double TransferOut_Price=Double.parseDouble(TransferOutPrice);
				
				double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
				
				//Actual On Hand Quantity
				String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
				double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
				
				//Check whether the Actual and Expected On Hand Price is Equal or not
				if(Actual_OnHand_Price==Expected_On_Hand_Price)
				{
					test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
				}
				else
				{
					double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
					test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
				}
				
				
			}
				

			
			//On-Hand Quantity = Beginning Quantity + Purchase Quantity – Usage Quantity + Transfer In Quantity – Transfer Out Quantity 
			
			
			}
			wb.close();
				Thread.sleep(3000);
				test.log(LogStatus.INFO, "Central Inventory Report Ends for Date Range");

			}
			
	@Test(priority= 58,enabled = false) 
	public void Inventory_Reports_Compare_Inventory_Inventory_Item(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Central Inventory Report for Inventory Item Selection Starts(Date Range)");

				//Select type as ALL 
				Select Inventory = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				Inventory.selectByVisibleText("Inventory Item"); 
			 	
				//Select Category 
				WebElement catlv=driver.findElement(By.xpath(excel.getData(3, 1724, 1)));
				Select CatLvl=new Select(catlv);
				CatLvl.selectByVisibleText("All");

				
				
				//Select Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1725, 1))).click();
				//Send the Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1726, 1))).sendKeys(Utility.getProperty("Search_Inventory_Items_Name"));
				//Enter the Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1726, 1))).sendKeys(Keys.ENTER);

				Thread.sleep(3000);
				//Click type as Time period
				Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
				TimePeriod.selectByVisibleText("Date Range"); 
	
			 	
				//Select the From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
				//Enter the required From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(1000);
				//Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
				
				Thread.sleep(2000);
				try
				{
					if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
					{
						test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
					}
				}
				catch(Exception d)
				{
					
					test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);
				
				List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize=leftPagination.size();
				
				//Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
				
				 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
				 
				for(int i = 3; i <= rows.size();i++)
				{ 
					
					test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
					
					String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
					double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
					
					String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
					double Variance_Quanity=Double.parseDouble(VarianceQty);
					
//					String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//					double Wastage_Quanity=Double.parseDouble(WasteQty);
					
					double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

					String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
					double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
					
					//Check whether the Actual and Expected Usage Quantity is Equal or not
					if(Actual_Usage_Quanity==Expected_Usage_Quantity)
					{
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
						test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					
					String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
					double Ideal_Used_Price=Double.parseDouble(IdealPrice);
					
					String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Variance_Price=Double.parseDouble(VariancePrice);
					
//					String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//					double Wastage_Price=Double.parseDouble(WastePrice);
					
					double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

					String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
					
					//Check whether the Actual and Expected Usage Price is Equal or not
					if(Actual_Usage_Price==Expected_Usage_Price)
					{
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					}
					else
					{
						double diff=Actual_Usage_Price-Expected_Usage_Price;

						test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
					}
					
					
					//Get the Beginning Quantity
					String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Beginning_Quanity=Double.parseDouble(BeginningeQty);
					
					//Purchase Quantity
					String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Purchase_Quanity=Double.parseDouble(PurchaseQty);
					
					//Transfer In Quantity
					String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferIn_Quanity=Double.parseDouble(TransferInQty);
					
					//Transfer Out Quantity
					String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
					
					double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
					
					//Actual On Hand Quantity
					String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
					
					//Check whether the Actual and Expected On Hand Quantity is Equal or not
					if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
					{
						test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
						test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					//Get the Beginning Quantity
					String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Beginning_Price=Double.parseDouble(BeginningePrice);
					
					//Purchase Quantity
					String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Purchase_Price=Double.parseDouble(PurchasePrice);
					
					//Transfer In Quantity
					String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferIn_Price=Double.parseDouble(TransferInPrice);
					
					//Transfer Out Quantity
					String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferOut_Price=Double.parseDouble(TransferOutPrice);
					
					double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
					
					//Actual On Hand Quantity
					String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
					
					//Check whether the Actual and Expected On Hand Price is Equal or not
					if(Actual_OnHand_Price==Expected_On_Hand_Price)
					{
						test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
						test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
				//	List<WebElement> TypeList=driver.findElements(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.type.value']"));
					String selTypeText=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.type.value']")).getText();
					//Check whether the selected type displays in the Type column or not
					if(selTypeText.equalsIgnoreCase("ITEM"))
					{
						test.log(LogStatus.PASS, "Type column shows selected Type");
					}
					else
					{
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: "+selTypeText);
					}
				}
				}	
				test.log(LogStatus.INFO, "Central Inventory Report for Inventory Item Selection End (Date Range)");

				wb.close();
			}
			
	@Test(priority=59,enabled = false) 
	public void Inventory_Reports_Compare_Inventory_SubRecipe(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection Starts (Date Range)");
				//Select type as ALL 
				Select Sub = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				Sub.selectByVisibleText("Sub Recipe"); 
			 	
				//Select Sub Recipe 
				driver.findElement(By.xpath(excel.getData(3, 1718, 1))).click(); 
				//Send the Sub Recipe
				driver.findElement(By.xpath(excel.getData(3, 1719, 1))).sendKeys(Utility.getProperty("subRecipe_name"));
				//Enter the Sub Recipe
				driver.findElement(By.xpath(excel.getData(3, 1719, 1))).sendKeys(Keys.ENTER);

				Thread.sleep(3000);
				//Click type as Time period
				Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
				TimePeriod.selectByVisibleText("Date Range"); 

			 	
				//Select the From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
				//Enter the required From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(1000);
				//Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
				
				Thread.sleep(2000);
				try
				{
					if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
					{
						test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
					}
				}
				catch(Exception d)
				{
					
					test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);
				
				List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize=leftPagination.size();
				
				//Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
				
				 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
				 
				for(int i = 3; i <= rows.size();i++)
				{ 
					
					test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
					
					String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
					double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
					
					String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
					double Variance_Quanity=Double.parseDouble(VarianceQty);
					
//					String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//					double Wastage_Quanity=Double.parseDouble(WasteQty);
					
					double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

					String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
					double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
					
					//Check whether the Actual and Expected Usage Quantity is Equal or not
					if(Actual_Usage_Quanity==Expected_Usage_Quantity)
					{
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
						test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					
					String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
					double Ideal_Used_Price=Double.parseDouble(IdealPrice);
					
					String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Variance_Price=Double.parseDouble(VariancePrice);
					
//					String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//					double Wastage_Price=Double.parseDouble(WastePrice);
					
					double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

					String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
					
					//Check whether the Actual and Expected Usage Price is Equal or not
					if(Actual_Usage_Price==Expected_Usage_Price)
					{
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					}
					else
					{
						double diff=Actual_Usage_Price-Expected_Usage_Price;

						test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
					}
					
					
					//Get the Beginning Quantity
					String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Beginning_Quanity=Double.parseDouble(BeginningeQty);
					
					//Purchase Quantity
					String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Purchase_Quanity=Double.parseDouble(PurchaseQty);
					
					//Transfer In Quantity
					String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferIn_Quanity=Double.parseDouble(TransferInQty);
					
					//Transfer Out Quantity
					String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
					
					double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
					
					//Actual On Hand Quantity
					String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
					
					//Check whether the Actual and Expected On Hand Quantity is Equal or not
					if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
					{
						test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
						test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					//Get the Beginning Quantity
					String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Beginning_Price=Double.parseDouble(BeginningePrice);
					
					//Purchase Quantity
					String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Purchase_Price=Double.parseDouble(PurchasePrice);
					
					//Transfer In Quantity
					String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferIn_Price=Double.parseDouble(TransferInPrice);
					
					//Transfer Out Quantity
					String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferOut_Price=Double.parseDouble(TransferOutPrice);
					
					double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
					
					//Actual On Hand Quantity
					String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
					
					//Check whether the Actual and Expected On Hand Price is Equal or not
					if(Actual_OnHand_Price==Expected_On_Hand_Price)
					{
						test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
						test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
		String selTypeText=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.type.value']")).getText();
					//Check whether the selected type displays in the Type column or not
					if(selTypeText.equalsIgnoreCase("SUBRECIPE"))
					{
						test.log(LogStatus.PASS, "Type column shows selected Type");
					}
					else
					{
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: "+selTypeText);
					}
				}
				}	
				
				Thread.sleep(2000);
				test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
			}

	@Test(priority=60,enabled = false) 
	public void Inventory_Reports_Compare_Inventory_Menu_Item(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Central Inventory Report for Menu Item Selection Starts (Date Range)");
				//Select type as ALL 
				Select Sub = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				Sub.selectByVisibleText("Menu Item");
			 	 
				
				Thread.sleep(3000);
				//Click type as Time period
				Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
				TimePeriod.selectByVisibleText("Date Range"); 

			 	
				//Select the From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
				//Enter the required From Date Range
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("Date_Range_To"));
				
				Thread.sleep(1000);
				//Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
				
				Thread.sleep(2000);
				try
				{
					if(driver.findElement(By.xpath("//td[contains(.,'No records found')]")).getText().equalsIgnoreCase("No records found"))
					{
						test.log(LogStatus.FAIL, "Comparison Report not available for this Specific Date");
					}
				}
				catch(Exception d)
				{
					
					test.log(LogStatus.PASS, "Comparison Report available for this Specific Date");
				Thread.sleep(5000);
				
				List<WebElement> leftPagination=driver.findElements(By.xpath("//ul[@class='pagination ng-scope']/li"));
				int LeftPageSize=leftPagination.size();
				
				//Click the pagination for Report row increase button
				driver.findElement(By.xpath("//ul[@class='pagination ng-scope']/li["+LeftPageSize+"]/a")).click();
				
				 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(3, 1986, 1)));
				 
				for(int i = 3; i <= rows.size();i++)
				{ 
					
					test.log(LogStatus.INFO, "Item Name is : "+driver.findElement(By.xpath("//tr["+i+"]/td[1]")).getText());
					
					String IdealQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][1]")).getText();	
					double Ideal_Used_Quanity=Double.parseDouble(IdealQty);
					
					String VarianceQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][1]")).getText();	
					double Variance_Quanity=Double.parseDouble(VarianceQty);
					
//					String WasteQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][1]")).getText();	
//					double Wastage_Quanity=Double.parseDouble(WasteQty);
					
					double Expected_Usage_Quantity=Ideal_Used_Quanity+Variance_Quanity;

					String Actual_UsageQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][1]")).getText();	
					double Actual_Usage_Quanity=Double.parseDouble(Actual_UsageQty);
					
					//Check whether the Actual and Expected Usage Quantity is Equal or not
					if(Actual_Usage_Quanity==Expected_Usage_Quantity)
					{
						test.log(LogStatus.PASS, "Actual and Expected Usage Quantity is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_Usage_Quanity-Expected_Usage_Quantity;
						test.log(LogStatus.FAIL, "Actual and Expected Usage Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					
					String IdealPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.idealUsed.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();;	
					double Ideal_Used_Price=Double.parseDouble(IdealPrice);
					
					String VariancePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.variance.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Variance_Price=Double.parseDouble(VariancePrice);
					
//					String WastePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.wastage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
//					double Wastage_Price=Double.parseDouble(WastePrice);
					
					double Expected_Usage_Price=Ideal_Used_Price+Variance_Price;

					String Actual_UsagePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.usage.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_Usage_Price=Double.parseDouble(Actual_UsagePrice);
					
					//Check whether the Actual and Expected Usage Price is Equal or not
					if(Actual_Usage_Price==Expected_Usage_Price)
					{
						test.log(LogStatus.PASS, "Actual and Expected Usage Price is Equal in Compare Inventory");
					}
					else
					{
						double diff=Actual_Usage_Price-Expected_Usage_Price;

						test.log(LogStatus.FAIL, "Actual and Expected Usage Price is not Equal in Compare Inventory. Price Diff is : "+diff);
					}
					
					
					//Get the Beginning Quantity
					String BeginningeQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Beginning_Quanity=Double.parseDouble(BeginningeQty);
					
					//Purchase Quantity
					String PurchaseQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Purchase_Quanity=Double.parseDouble(PurchaseQty);
					
					//Transfer In Quantity
					String TransferInQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferIn_Quanity=Double.parseDouble(TransferInQty);
					
					//Transfer Out Quantity
					String TransferOutQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferOut_Quanity=Double.parseDouble(TransferOutQty);
					
					double Expected_On_Hand_Qty=Beginning_Quanity+Purchase_Quanity-Actual_Usage_Quanity+TransferIn_Quanity-TransferOut_Quanity;
					
					//Actual On Hand Quantity
					String OnHandQty=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][1]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_OnHand_Quanity=Double.parseDouble(OnHandQty);
					
					//Check whether the Actual and Expected On Hand Quantity is Equal or not
					if(Actual_OnHand_Quanity==Expected_On_Hand_Qty)
					{
						test.log(LogStatus.PASS, "Actual and Expected On Hand Quantity is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_OnHand_Quanity-Expected_On_Hand_Qty;
						test.log(LogStatus.FAIL, "Actual and Expected On Hand Quantity is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					//Get the Beginning Quantity
					String BeginningePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.beginning.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Beginning_Price=Double.parseDouble(BeginningePrice);
					
					//Purchase Quantity
					String PurchasePrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.purchase.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Purchase_Price=Double.parseDouble(PurchasePrice);
					
					//Transfer In Quantity
					String TransferInPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferIn.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferIn_Price=Double.parseDouble(TransferInPrice);
					
					//Transfer Out Quantity
					String TransferOutPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.transferOut.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double TransferOut_Price=Double.parseDouble(TransferOutPrice);
					
					double Expected_On_Hand_Price=Beginning_Price+Purchase_Price-Actual_Usage_Price+TransferIn_Price-TransferOut_Price;
					
					//Actual On Hand Quantity
					String OnHandPrice=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.onHand.value'][2]")).getText().replaceAll("[a-zA-Z $ ₹ £ , :]", "").substring(1).toString();	
					double Actual_OnHand_Price=Double.parseDouble(OnHandPrice);
					
					//Check whether the Actual and Expected On Hand Price is Equal or not
					if(Actual_OnHand_Price==Expected_On_Hand_Price)
					{
						test.log(LogStatus.PASS, "Actual and Expected On Hand Price is Equal in Compare Inventory.");
					}
					else
					{
						double diff=Actual_OnHand_Price-Expected_On_Hand_Price;
						test.log(LogStatus.FAIL, "Actual and Expected On Hand Price is not Equal in Compare Inventory. Quantity Diff is : "+diff);
					}
					
					String selTypeText=driver.findElement(By.xpath("//tr["+i+"]/td[@ng-if='reportColumns.type.value']")).getText();
					//Check whether the selected type displays in the Type column or not
					if(selTypeText.equalsIgnoreCase("MENUITEM"))
					{
						test.log(LogStatus.PASS, "Type column shows selected Type");
					}
					else
					{
						test.log(LogStatus.FAIL, "Type column shows Incorrect type for selected type: "+selTypeText);
					}
				} 
				}	
				
				Thread.sleep(2000);
				test.log(LogStatus.INFO, "Central Inventory Report for Sub Recipe Selection End (Date Range)");

			wb.close();
			}
}
