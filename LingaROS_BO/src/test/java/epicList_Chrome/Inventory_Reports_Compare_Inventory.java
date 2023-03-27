package epicList_Chrome;

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
			Inventory_Reports_Compare_Inventory_Openpage(driver);
			Inventory_Reports_Compare_Inventory_ALL(driver);
			Inventory_Reports_Compare_Inventory_Item(driver);
			Inventory_Reports_Compare_SubRecipe(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=45) 
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
			  	driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"compareInventory");
				
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
			
	@Test(enabled=false,priority=46) 
	public void Inventory_Reports_Compare_Inventory_ALL(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

	
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
			driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
					
			//Select the TO Date Range
			driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
			//Enter the  required TO Date Range
			driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
			
			Thread.sleep(1000);
			//Click the Run Comparison button
			driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
			
			Thread.sleep(5000);
			 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(2, 261, 1)));
			for(int i = 3; i <= rows.size(); i++)
			{
				//Get the Consumption Log Quantity value
				test.log(LogStatus.PASS,"In Compare inventory "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText() +" "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[10]")).getText()+ " purchased  this much "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText());
					
			}
				
			Thread.sleep(3000);
			//Check Consumption Log page opened or not
			if(driver.findElement(By.xpath(excel.getData(2, 439, 1))).getText().equalsIgnoreCase("Compare Inventory"))
			{
				test.log(LogStatus.PASS, "Consumption Log Inventory report loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Compare Inventory Inventory report loaded Failed");
			}
			
			wb.close();
				Thread.sleep(3000);
			}
			
	@Test(enabled=false,priority=47) 
	public void Inventory_Reports_Compare_Inventory_Item(WebDriver driver) throws Exception
	{
	
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
		Thread.sleep(3000);
				//Select type as ALL 
				Select Inventory = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				Inventory.selectByVisibleText("Inventory Item"); 
			 	
				//Select Category 
				WebElement catlv=driver.findElement(By.xpath(excel.getData(3, 1724, 1)));
				Select CatLvl=new Select(catlv);
				CatLvl.selectByVisibleText("All");
/*				//Send the category 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Search_Inventory_Category_Name"));
				//Enter the category
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
*/
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
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(1000);
				//Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
				
				Thread.sleep(2000);
				//Check Consumption Log page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 439, 1))).getText().equalsIgnoreCase("Compare Inventory"))
				{
					test.log(LogStatus.PASS, "Consumption Log Inventory item report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Compare Inventory Inventory item report loaded Failed");
				}
				wb.close();
			}
			
	@Test(enabled=false,priority=48) 
	public void Inventory_Reports_Compare_SubRecipe(WebDriver driver) throws Exception
	{
		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
				Thread.sleep(3000);
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
				driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportProperty("Date_Range_From"));
						
				//Select the TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
				//Enter the  required TO Date Range
				driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportProperty("Date_Range_To"));
				
				Thread.sleep(1000);
				//Click the Run Comparison button
				driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
				
				Thread.sleep(2000);
				//Check Consumption Log page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 439, 1))).getText().equalsIgnoreCase("Compare Inventory"))
				{
					test.log(LogStatus.PASS, "Consumption Log Sub Recipe report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Compare Inventory Sub Recipe report loaded Failed");
				}
				
				Thread.sleep(2000);
			wb.close();
			}

}
