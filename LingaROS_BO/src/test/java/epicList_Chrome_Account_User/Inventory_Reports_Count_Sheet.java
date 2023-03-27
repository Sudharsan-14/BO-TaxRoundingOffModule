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

public class Inventory_Reports_Count_Sheet {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Count_Sheet");

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
			Inventory_Reports_Countsheet_Openpage(driver);
			Inventory_Reports_Count_Sheet_Inventory(driver);
			Inventory_Reports_Count_Inventory_Item(driver);
			Inventory_Reports_Count_SubRecipe(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=49) 
	public void Inventory_Reports_Countsheet_Openpage(WebDriver driver) throws Exception
	{
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
		
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
		
				Thread.sleep(5000);
				//Get the URl
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"countSheet");
				
				//Thread.sleep(3000);
				//Click the count sheet
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/scrollable-tabset/div/div/div/ul/li[4]/a/uib-tab-heading/span")).click();
				
				Thread.sleep(5000);
				try
				{
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 280, 1))).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet page loaded Successfully");
				
					String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
					String s="data:image/png;base64,"+scnShot;
					test.log(LogStatus.INFO,test.addScreenCapture(s));
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet page loaded Failed");
				
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
			
	@Test(enabled=false,priority=50) 
	public void Inventory_Reports_Count_Sheet_Inventory(WebDriver driver) throws Exception
	{
			
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
			Thread.sleep(2000);
			//Select type as ALL 
			Select ALL = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
			ALL.selectByVisibleText("All"); 
		 	
			//Select Storage Location 
			driver.findElement(By.xpath(excel.getData(3, 1727, 1))).click();
			//Thread.sleep(1000);
			//Send the Storage Location
			driver.findElement(By.xpath(excel.getData(3, 1728, 1))).sendKeys(Keys.ARROW_DOWN);
			//Enter the Storage Location
			driver.findElement(By.xpath(excel.getData(3, 1728, 1))).sendKeys(Keys.ENTER);

			Thread.sleep(1000);
			//Click the Get Count Sheet button
			driver.findElement(By.cssSelector(excel.getData(2, 283, 4))).click();
			
			List<WebElement> rows = driver.findElements(By.xpath(excel.getData(2, 284, 1)));
				for(int i = 1; i <= rows.size(); i++)
				{
					//Get the Consumption Log Quantity value
					test.log(LogStatus.PASS,"In Count sheet " +driver.findElement(By.xpath(excel.getData(2, 272, 1))).getText() +" "+ driver.findElement(By.xpath(excel.getData(2, 434, 1))).getText()+ " hand on quantity value is  "+" "+  driver.findElement(By.xpath(excel.getData(2, 274, 1))).getText());
						
				}
				
				Thread.sleep(3000);
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 436, 1))).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet SubRecipe report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet SubRecipe report loaded Failed");
				}
				
				wb.close();
				Thread.sleep(1000);
			}
			
	@Test(enabled=false,priority=51) 
	public void Inventory_Reports_Count_Inventory_Item(WebDriver driver) throws Exception
	{
		  	File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
				Thread.sleep(3000);
				//Select type as ALL
				Select Inventory = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				Inventory.selectByVisibleText("Inventory Item"); 
			 	
				//Select Storage Location 
				driver.findElement(By.xpath(excel.getData(3, 1727, 1))).click();
				Thread.sleep(1000);
				//Send the Storage Location
				driver.findElement(By.xpath(excel.getData(3, 1728, 1))).sendKeys(Utility.getProperty("Search_Inventory_Category_Name"));
				//Enter the Storage Location
				driver.findElement(By.xpath(excel.getData(3, 1728, 1))).sendKeys(Keys.ENTER);

				//Select Category Level
				WebElement cat=driver.findElement(By.xpath(excel.getData(3, 1724, 1)));
				Select cat1=new Select(cat);
				cat1.selectByVisibleText("All");
/*				//Send the Category
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Ingredient_Category"));
				//Enter the Category
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
*/
				//Select Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1725, 1))).click();
				Thread.sleep(1000);
				//Send the Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1726, 1))).sendKeys(Utility.getProperty("Inventory_Ingredient_Category"));
				//Enter the Inventory Item
				driver.findElement(By.xpath(excel.getData(3, 1726, 1))).sendKeys(Keys.ENTER);

				Thread.sleep(1000);
				//Click the Get Count Sheet button
				driver.findElement(By.cssSelector(excel.getData(2, 283, 4))).click();
				
				Thread.sleep(3000);
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 436, 1))).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet Inventory report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet Inventory report loaded Failed");
				}
				
				wb.close();
			}
			
	@Test(enabled=false,priority=52) 
	public void Inventory_Reports_Count_SubRecipe(WebDriver driver) throws Exception
	{
		
		  File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			FileInputStream fis = new FileInputStream(src);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		  
				Thread.sleep(3000);
				//Select type as ALL 
				Select Sub = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				Sub.selectByVisibleText("Sub Recipe"); 
			 	
				//Select Storage Location 
				driver.findElement(By.xpath(excel.getData(3, 1727, 1))).click();
				Thread.sleep(1000);
				//Send the Storage Location
				driver.findElement(By.xpath(excel.getData(3, 1728, 1))).sendKeys(Utility.getProperty("Search_Inventory_Category_Name"));
				//Enter the Storage Location
				driver.findElement(By.xpath(excel.getData(3, 1728, 1))).sendKeys(Keys.ENTER);

				//Select Sub Recipe 
				driver.findElement(By.xpath(excel.getData(3, 1718, 1))).click();
				Thread.sleep(1000);
				//Send the Sub Recipe
				driver.findElement(By.xpath(excel.getData(3, 1719, 1))).sendKeys(Utility.getProperty("subRecipe_name"));
				//Enter the Sub Recipe
				driver.findElement(By.xpath(excel.getData(3, 1719, 1))).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Click the Get Count Sheet button
				driver.findElement(By.cssSelector(excel.getData(2, 283, 4))).click();
				
				Thread.sleep(3000);
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath(excel.getData(2, 436, 1))).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet SubRecipe report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet SubRecipe report loaded Failed");
				}
				wb.close();
				Thread.sleep(4000);
			
			}
 
}
