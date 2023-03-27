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

public class Inventory_Reports_Consumption_Log {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Consumption_Log");

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
			Inventory_Reports_ConsumptionLog_Reports_Openpage(driver);
			Inventory_Reports_ConsumptionLog_Openpage(driver);
			Inventory_Reports_ConsumptionLog_Inventory_All(driver);
			Inventory_Reports_ConsumptionLog_Inventory_System(driver);
			Inventory_Reports_ConsumptionLog_Inventory_Manual(driver);
			Inventory_Reports_ConsumptionLog_Subrecipe_All(driver);
			Inventory_Reports_ConsumptionLog_Subrecipe_System(driver);
			Inventory_Reports_ConsumptionLog_Subrecipe_Manual(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=37)
	public void Inventory_Reports_ConsumptionLog_Reports_Openpage(WebDriver driver) throws Exception
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
			test.log(LogStatus.FAIL,test.addScreenCapture(s));
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
	
	@Test(enabled=false,priority=38)
	public void Inventory_Reports_ConsumptionLog_Openpage(WebDriver driver) throws Exception
	{

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);
		//Click Consumption Log page
		driver.findElement(By.xpath(excel.getData(2, 251, 1))).click();
		
		Thread.sleep(3000);
		//Check Inventory Items page opened or not
		if(driver.findElement(By.xpath(excel.getData(2, 252, 1))).getText().equalsIgnoreCase("Consumption Log"))
		{
			test.log(LogStatus.PASS, "Consumption Log page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Consumption Log page loaded Failed");
		}
		wb.close();
	}
	
	@Test(enabled=false,priority=39)
	public void Inventory_Reports_ConsumptionLog_Inventory_All(WebDriver driver) throws Exception
	{

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);	
		//Select Type 
		 Select inventorytype = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		 inventorytype.selectByVisibleText("Inventory Item"); 
	 	
		 
		Thread.sleep(3000);	
		//Select the inventory Category
		Select invCat=new Select(driver.findElement(By.xpath(excel.getData(3, 1724, 1))));
		invCat.selectByVisibleText("All");
/*		Thread.sleep(1000);		
		//Select the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/select")).sendKeys(Utility.getProperty("Inventory_Ingredient_Category"));
		Thread.sleep(2000);		
		//Enter the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
	*/
		Thread.sleep(3000);	
		//Select the inventory item
		driver.findElement(By.xpath(excel.getData(3, 1725, 1))).click();
		Thread.sleep(1000);		
		//Select the inventory item
		driver.findElement(By.xpath(excel.getData(3, 1726, 1))).sendKeys(Utility.getProperty("Inventory_Items_Name"));
		Thread.sleep(2000);		
		//Enter the inventory item
		driver.findElement(By.xpath(excel.getData(3, 1726, 1))).sendKeys(Keys.ENTER);
	
		Thread.sleep(2000);	
		//Select Consumption Type as "ALL"
		WebElement cats=driver.findElement(By.xpath(excel.getData(3, 1721, 1)));
		//Click All in category
		 Select Alltype = new Select(cats);
		 Alltype.selectByVisibleText("All"); 
	 	
		 	Thread.sleep(3000);	
		 	//Select Time Period
			 Select TimePeriod = new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));
			 TimePeriod.selectByVisibleText("Date Range"); 
	 	
		Thread.sleep(3000);	
		//Select the From Date range 
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
		Thread.sleep(1000);		
		//Enter the From Date range 
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("ConsumptionLog_FromDate"));
		
		Thread.sleep(2000);	
		//Select the To Date range 
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
		Thread.sleep(1000);		
		//Enter the To Date range 
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("ConsumptionLog_ToDate"));
		
		Thread.sleep(2000);	
		 //Click the Show Log button
		 driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
			
		 Thread.sleep(3000);		
		//Check whether the consumption log are Loaded Or not		
		try		
		{			
		if(driver.findElement(By.xpath(excel.getData(2, 264, 1))).isDisplayed())			
		{				
		test.log(LogStatus.INFO, "There is no record available for the Selected Time Period for Not Finished(consumption log for inventory item)");			
		}		
		}		
		catch (Exception e)		
		{			
		test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(consumption log)");					
		 
		List<WebElement> rows = driver.findElements(By.xpath(excel.getData(2, 261, 1)));
			for(int i = 2; i <= rows.size(); i++)
			{
			 //Get the Consumption Log Quantity value
			Thread.sleep(5000);
			test.log(LogStatus.PASS,"Purchasing inventory item : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText()+" for this reason quantity taken by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+" level and this is done by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[5]")).getText() + "level");
			
			}
		}
		//print the table
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table")).isDisplayed();
		wb.close();
		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=40)
	public void Inventory_Reports_ConsumptionLog_Inventory_System(WebDriver driver) throws Exception
	{	

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis); 
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	Thread.sleep(3000);
	//Click Consumption Type button
	//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")).click();
	
	Thread.sleep(3000);	
	//Click Consumption Type as System	
	 Select Systemtype = new Select(driver.findElement(By.xpath(excel.getData(3, 1721, 1))));
	 Systemtype.selectByVisibleText("System"); 
 	
	Thread.sleep(3000);	
	 //Click the Show Log button
	 driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
	
	 wb.close();
	}
	
	@Test(enabled=false,priority=41)
	public void Inventory_Reports_ConsumptionLog_Inventory_Manual(WebDriver driver) throws Exception
	{		

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	Thread.sleep(3000);
	//Click Consumption Type button
	//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")).click();
	
	Thread.sleep(3000);	
	//Click Consumption Type as Manual
	 Select Manualtype = new Select(driver.findElement(By.xpath(excel.getData(3, 1721, 1))));
	 Manualtype.selectByVisibleText("Manual"); 
 	
	Thread.sleep(2000);	
	 //Click the Show Log button
	 driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
	
	 wb.close();
	}
	
	@Test(enabled=false,priority=42)
	public void Inventory_Reports_ConsumptionLog_Subrecipe_All(WebDriver driver) throws Exception
	{		

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
		//Select the Sub recipe Type
		 Select Subrecipetype = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
		 Subrecipetype.selectByVisibleText("Sub Recipe"); 
	 	
		 
		Thread.sleep(3000);	
		//Select the Sub recipe
		driver.findElement(By.xpath(excel.getData(3, 1718, 1))).click();
		Thread.sleep(1000);		
		//Select the Sub recipe
		driver.findElement(By.xpath(excel.getData(3, 1719, 1))).sendKeys(Utility.getProperty("subRecipe_name"));
		Thread.sleep(2000);		
		//Enter the Sub recipe
		driver.findElement(By.xpath(excel.getData(3, 1719, 1))).sendKeys(Keys.ENTER);

		WebElement cat2=driver.findElement(By.xpath(excel.getData(3, 1721, 1)));
		//Click Consumption Type as "ALL"
		 Select Alltype = new Select(cat2);
		 Alltype.selectByVisibleText("All"); 
	 		
		
		Thread.sleep(3000);	
		//Select Time period option 
		 Select TimePeriod=new Select(driver.findElement(By.xpath(excel.getData(3, 1720, 1))));	 
		 //Type date range
		 TimePeriod.selectByVisibleText("Date Range");
		 
	 	
		Thread.sleep(3000);	
		//Select the From Date range 
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).clear();
		Thread.sleep(1000);		
		//Enter the From Date range 
		driver.findElement(By.xpath(excel.getData(3, 1722, 1))).sendKeys(Utility.getReportPropertyUser("ConsumptionLog_FromDate"));
		
		Thread.sleep(2000);	
		//Select the To Date range 
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).clear();
		Thread.sleep(1000);		
		//Enter the To Date range 
		driver.findElement(By.xpath(excel.getData(3, 1723, 1))).sendKeys(Utility.getReportPropertyUser("ConsumptionLog_ToDate"));
		
		Thread.sleep(2000);	
		 //Click the Show Log button
		 driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
			
		 Thread.sleep(8000);		
		//Check whether the consumption log are Loaded Or not		
		try		
		{			
		if(driver.findElement(By.xpath(excel.getData(2, 264, 1))).isDisplayed())			
		{				
		test.log(LogStatus.INFO, "There is no record available for the Selected Time Period for Not Finished(consumption log)");			
		}		
		}		
		catch (Exception e)		
		{			
		test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(consumption log)");					

		 List<WebElement> rows = driver.findElements(By.xpath(excel.getData(2, 261, 1)));
			for(int i = 1; i <= rows.size(); i++)
			{
			 //Get the Consumption Log Quantity value
			test.log(LogStatus.PASS,"Purchasing Sub Recipe :"+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText()+" for this reason quantity taken by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+" level and this is done by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[5]")).getText() + "level");
			
			}
		}
		
			wb.close();
	}
			
	@Test(enabled=false,priority=43)
	public void Inventory_Reports_ConsumptionLog_Subrecipe_System(WebDriver driver) throws Exception
	{		

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
				//Select the Sub recipe Type
				 Select Subrecipetype = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				 Subrecipetype.selectByVisibleText("Sub Recipe"); 
		
				 Thread.sleep(2000);	
		//Click Consumption Type as "System"
		 Select Systemtype = new Select(driver.findElement(By.xpath(excel.getData(3, 1721, 1))));
		 Systemtype.selectByVisibleText("System"); 
			
			Thread.sleep(2000);	
			 //Click the Show Log button
			 driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
			
			 wb.close();
			}
			
	@Test(enabled=false,priority=44)
	public void Inventory_Reports_ConsumptionLog_Subrecipe_Manual(WebDriver driver) throws Exception
	{		

		File src = new File(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet1 = wb.getSheetAt(1);sheet1.getLastRowNum();
		
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(2000);	
				//Select the Sub recipe Type
				 Select Subrecipetype = new Select(driver.findElement(By.xpath(excel.getData(3, 1717, 1))));
				 Subrecipetype.selectByVisibleText("Sub Recipe"); 
			
				 Thread.sleep(2000);	
			//Click Consumption Type as "Manual"
			 Select Manualtype = new Select(driver.findElement(By.xpath(excel.getData(3, 1721, 1))));
			 Manualtype.selectByVisibleText("Manual"); 
				
			Thread.sleep(2000);	
			 //Click the Show Log button
			 driver.findElement(By.xpath(excel.getData(2, 260, 1))).click();
			 wb.close();
			 Thread.sleep(5000);	
		
	} 
		
}
