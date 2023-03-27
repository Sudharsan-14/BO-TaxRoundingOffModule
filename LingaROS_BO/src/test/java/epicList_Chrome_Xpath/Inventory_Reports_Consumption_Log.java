package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		{
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//b[.='User Login']")).getText().equalsIgnoreCase("User Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully for Dealer App");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed for Dealer App");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
		else
		{
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[1]/div[2]/div/div/div/div[4]/a/i")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath("//div[@id='x-content-band-1']/div/div[2]/h2")).getText().equalsIgnoreCase("Account Login"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
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
		Thread.sleep(5000);
		//Get the URl
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"compareInventory");
		Thread.sleep(2000);
		//Check Compare Inventory page opened or not
		if(driver.findElement(By.xpath("//a[.='Compare Inventory']")).getText().equalsIgnoreCase("Compare Inventory"))
		{
			test.log(LogStatus.PASS, "Compare Inventory page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Compare Inventory Inventory page loaded Failed");
		}
		
		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=38)
	public void Inventory_Reports_ConsumptionLog_Openpage(WebDriver driver) throws Exception
	{
		
		Thread.sleep(2000);
		//Click Consumption Log page
		driver.findElement(By.xpath("//span[contains(.,'Consumption Log')]")).click();
		
		Thread.sleep(3000);
		//Check Inventory Items page opened or not
		if(driver.findElement(By.xpath("//a[.='Consumption Log']")).getText().equalsIgnoreCase("Consumption Log"))
		{
			test.log(LogStatus.PASS, "Consumption Log page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Consumption Log page loaded Failed");
		}
	}
	
	@Test(enabled=false,priority=39)
	public void Inventory_Reports_ConsumptionLog_Inventory_All(WebDriver driver) throws Exception
	{
		
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);	
		//Select inventory Type 
		 Select inventorytype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
		 inventorytype.selectByVisibleText("Inventory Item"); 
	 	
		 
		Thread.sleep(3000);	
		//Select the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
		Thread.sleep(1000);		
		//Select the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Ingredient_Category"));
		Thread.sleep(2000);		
		//Enter the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(3000);	
		//Select the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/a")).click();
		Thread.sleep(1000);		
		//Select the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
		Thread.sleep(2000);		
		//Enter the inventory Category
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(2000);	
		//Select Consumption Type as "ALL"
		 Select Alltype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")));
		 Alltype.selectByVisibleText("All"); 
	 	
		Thread.sleep(3000);	
		//Select Time period option 
		 Select DateRangetype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/select")));
		 DateRangetype.selectByVisibleText("Date Range"); 
	 	
		Thread.sleep(3000);	
		//Select the From Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[6]/div/div/input[1]")).clear();
		Thread.sleep(1000);		
		//Enter the From Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[6]/div/div/input[1]")).sendKeys(Utility.getReportProperty("ConsumptionLog_FromDate"));
		
		Thread.sleep(2000);	
		//Select the To Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[6]/div/div/input[2]")).clear();
		Thread.sleep(1000);		
		//Enter the To Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[6]/div/div/input[2]")).sendKeys(Utility.getReportProperty("ConsumptionLog_ToDate"));
		
		Thread.sleep(2000);	
		 //Click the Show Log button
		 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[7]/div/div/button")).click();
			
		 Thread.sleep(3000);		
		//Check whether the consumption log are Loaded Or not		
		try		
		{			
		if(driver.findElement(By.xpath("//td[.='No consumption log for selected time period']")).isDisplayed())			
		{				
		test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Finished(consumption log for inventory item)");			
		}		
		}		
		catch (Exception e)		
		{			
		test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(consumption log)");					
		 
		List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]"));
			for(int i = 2; i <= rows.size(); i++)
			{
			 //Get the Consumption Log Quantity value
			Thread.sleep(5000);
			test.log(LogStatus.PASS,"Purchasing inventory item : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText()+" for this reason quantity taken by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+" level and this is done by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[5]")).getText() + "level");
			
			}
		}
		//print the table
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table")).isDisplayed();
		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=40)
	public void Inventory_Reports_ConsumptionLog_Inventory_System(WebDriver driver) throws Exception
	{	
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	Thread.sleep(3000);
	//Click Consumption Type button
	//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")).click();
	
	Thread.sleep(3000);	
	//Click Consumption Type as System	
	 Select Systemtype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")));
	 Systemtype.selectByVisibleText("System"); 
 	
	Thread.sleep(2000);	
	 //Click the Show Log button
	 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]")).click();
	

	}
	
	@Test(enabled=false,priority=41)
	public void Inventory_Reports_ConsumptionLog_Inventory_Manual(WebDriver driver) throws Exception
	{		
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
	Thread.sleep(3000);
	//Click Consumption Type button
	//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")).click();
	
	Thread.sleep(3000);	
	//Click Consumption Type as Manual
	 Select Manualtype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")));
	 Manualtype.selectByVisibleText("Manual"); 
 	
	Thread.sleep(2000);	
	 //Click the Show Log button
	 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]")).click();
	
	}
	
	@Test(enabled=false,priority=42)
	public void Inventory_Reports_ConsumptionLog_Subrecipe_All(WebDriver driver) throws Exception
	{		
		Thread.sleep(2000);	
		//Select the Sub recipe Type
		 Select Subrecipetype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
		 Subrecipetype.selectByVisibleText("Sub Recipe"); 
	 	
		 
		Thread.sleep(3000);	
		//Select the Sub recipe
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
		Thread.sleep(1000);		
		//Select the Sub recipe
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("subRecipe_name"));
		Thread.sleep(2000);		
		//Enter the Sub recipe
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

		//Click Consumption Type as "ALL"
		 Select Alltype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/select")));
		 Alltype.selectByVisibleText("All"); 
	 		
		
		Thread.sleep(3000);	
		//Select Time period option 
		 Select DateRangetype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/select")));
		 DateRangetype.selectByVisibleText("Date Range"); 
	 	
		Thread.sleep(3000);	
		//Select the From Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[1]")).clear();
		Thread.sleep(1000);		
		//Enter the From Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[1]")).sendKeys(Utility.getReportProperty("ConsumptionLog_FromDate"));
		
		Thread.sleep(2000);	
		//Select the To Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[2]")).clear();
		Thread.sleep(1000);		
		//Enter the To Date range 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[5]/div/div/input[2]")).sendKeys(Utility.getReportProperty("ConsumptionLog_ToDate"));
		
		Thread.sleep(2000);	
		 //Click the Show Log button
		 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[6]/div/div/button")).click();
			
		 Thread.sleep(3000);		
		//Check whether the consumption log are Loaded Or not		
		try		
		{			
		if(driver.findElement(By.xpath("//td[.='No consumption log for selected time period']")).isDisplayed())			
		{				
		test.log(LogStatus.FAIL, "There is no record available for the Selected Time Period for Not Finished(consumption log)");			
		}		
		}		
		catch (Exception e)		
		{			
		test.log(LogStatus.PASS, "Received Item Log is available for the Selected time period for Not Finished(consumption log)");					
		}
		
		 List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]"));
			for(int i = 1; i <= rows.size(); i++)
			{
			 //Get the Consumption Log Quantity value
			test.log(LogStatus.PASS,"Purchasing Sub Recipe : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText()+" for this reason quantity taken by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+" level and this is done by "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[5]")).getText() + "level");
			
			}
	}
			
	@Test(enabled=false,priority=43)
	public void Inventory_Reports_ConsumptionLog_Subrecipe_System(WebDriver driver) throws Exception
	{		
				Thread.sleep(2000);	
				//Select the Sub recipe Type
				 Select Subrecipetype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
				 Subrecipetype.selectByVisibleText("Sub Recipe"); 
		
				 Thread.sleep(2000);	
		//Click Consumption Type as "System"
		 Select Systemtype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/select")));
		 Systemtype.selectByVisibleText("System"); 
			
			Thread.sleep(2000);	
			 //Click the Show Log button
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]")).click();
			
			}
			
	@Test(enabled=false,priority=44)
	public void Inventory_Reports_ConsumptionLog_Subrecipe_Manual(WebDriver driver) throws Exception
	{		
				Thread.sleep(2000);	
				//Select the Sub recipe Type
				 Select Subrecipetype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
				 Subrecipetype.selectByVisibleText("Sub Recipe"); 
			
				 Thread.sleep(2000);	
			//Click Consumption Type as "Manual"
			 Select Manualtype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/select")));
			 Manualtype.selectByVisibleText("Manual"); 
				
			Thread.sleep(2000);	
			 //Click the Show Log button
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]")).click();
			 Thread.sleep(5000);	
			}

}
