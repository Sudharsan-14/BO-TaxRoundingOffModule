package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Inventory_Adjust_Inventory_Item {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Adjust_Inventory_Item");

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
			Adjust_Inventory_Openpage(driver);
			Adjust_Inventory_item_Close_button(driver);
			Adjust_Inventory_item_Update_button_Increase(driver);
			Adjust_Inventory_item_Update_button_Decrease(driver);
			Adjust_Inventory_item_Bulk_update(driver);
			Adjust_Subrecipe_Bulk_update(driver);
			Adjust_Bulk_update_Backbutton(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=31)
		public void Adjust_Inventory_Openpage(WebDriver driver) throws Exception
		{
		
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"adjustInventoryItem");
		
			Thread.sleep(5000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory page loaded Failed");
			}
			
			Thread.sleep(5000);
			
		}
		
		@Test(enabled=false,priority=33)
		public void  Adjust_Inventory_item_Close_button(WebDriver driver) throws Exception
		{
		
		Thread.sleep(3000);
		//Clear the Search 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).clear();
		Thread.sleep(3000);
		//Enter the Adjust Inventory item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
		Thread.sleep(3000);
		//Enter the Adjust Inventory item
	 	//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).sendKeys(Keys.ENTER);
	 	Thread.sleep(3000);
	 	//Click the Adjust Inventory item
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/span/a/i")).click();
		
	 	Thread.sleep(3000);
	 	//Check whether  Adjust Inventory item page opened or not
		if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
		{
			test.log(LogStatus.PASS, " New Adjust Inventory item page loaded Successfully for close button");
		}
		else
		{
			test.log(LogStatus.FAIL, " New Adjust Inventory item page loaded Failed for close button");
		}
		Thread.sleep(3000);			
		//Create new reason type for increase method
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[2]/span/a/i")).click();
	 	
	 	Thread.sleep(3000);
	 	//Clear the new reason name
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[1]/div/input")).clear();
	 	//Enter the new reason name
	 	 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("Increase_Inventory_Reason_name_ForModifier"));
	
	 	Thread.sleep(3000);
	  	//Click the new reason name
	  	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select")).click();
	  	
	  	Thread.sleep(3000);
	  	//Click increase button
	  	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select/option[1]")).click();
	  	
	  	//Thread.sleep(3000);
		 //Select reasontype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select/option[1]")));
		// reasontype.selectByVisibleText("Item"); 
	 	
		 Thread.sleep(5000);
	 	// Click close button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[3]/div/div/div/a[1]")).click();		
	 	
	 	    Thread.sleep(3000);
	 	    //Click close button for new adjust inventory 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div/a")).click();
			Thread.sleep(5000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory page loaded Successfully for close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory page loaded Failed for close button");
			}
	 	Thread.sleep(5000);	
		}
		
		@Test(enabled=false,priority=34)
		public void  Adjust_Inventory_item_Update_button_Increase(WebDriver driver) throws Exception
		{
		
		Thread.sleep(3000);
		//Clear the Search 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).clear();
		Thread.sleep(3000);
		//Enter the Adjust Inventory item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
		Thread.sleep(3000);
		//Enter the Adjust Inventory item
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).sendKeys(Keys.ENTER);
	 	Thread.sleep(3000);
	 	//Click the Adjust Inventory item
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/span/a/i")).click();
		
	 	Thread.sleep(3000);
	 	//Check whether  Adjust Inventory item page opened or not
		if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
		{
			test.log(LogStatus.PASS, " Increase reason ,Adjust Inventory item page loaded Successfully for update button");
		}
		else
		{
			test.log(LogStatus.FAIL, " Increase reason ,Adjust Inventory item page loaded Failed for update button");
		}
		Thread.sleep(3000);			
		//Create new reason type for increase method
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[2]/span/a/i")).click();
	 	
	 	Thread.sleep(3000);
	 	//Clear the new reason name
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[1]/div/input")).clear();
	 	//Enter the new reason name
	 	 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("Increase_Inventory_Reason_name"));
	
	 	Thread.sleep(3000);
	  	//Click the new reason name
	  	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select")).click();
	  	
	  	Thread.sleep(3000);
	  	//Click increase button
	  	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select/option[1]")).click();
	  	Thread.sleep(3000);
	  	//Thread.sleep(3000);
		 //Select reasontype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select/option[1]")));
		// reasontype.selectByVisibleText("Item"); 
	 	
		 Thread.sleep(5000);
	 	// Click save button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[3]/div/div/div/a[2]")).click();		
		
		Thread.sleep(3000);
		//Clear the increase inventory Vendor option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[4]/div/ng-form/input[2]")).clear();
		//Enter the increase inventory Vendor Quantity
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[4]/div/ng-form/input[2]")).sendKeys("70000");
		//Enter the increase inventory Vendor Quantity
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[4]/div/ng-form/input[2]")).sendKeys(Keys.ENTER);
			
		Thread.sleep(3000);
		//Clear the inventory notes option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[5]/textarea")).clear();
		//Enter the inventory notes
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[5]/textarea")).sendKeys("Needed more");
		//Enter the inventory notes
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[5]/textarea")).sendKeys(Keys.ENTER);
					
		
	 	Thread.sleep(3000);
	 	//Click Adjust button for new adjust inventory 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[6]/div/button")).click();
			Thread.sleep(3000);
			//Check new adjust inventory button update successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Stock adjusted successfully"))
			{
				test.log(LogStatus.PASS, "Stock adjusted successfully for Increase reason");
			}
			else
			{
				test.log(LogStatus.FAIL, "Stock adjusted successfully for Increase reason");
			}
			
	 	Thread.sleep(5000);	
		}
			
		@Test(enabled=false,priority=35)
		public void  Adjust_Inventory_item_Update_button_Decrease(WebDriver driver) throws Exception
		{
		
		Thread.sleep(3000);
		//Clear the Search 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).clear();
		Thread.sleep(3000);
		//Enter the Adjust Inventory item
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
		Thread.sleep(3000);
		//Enter the Adjust Inventory item
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div/label/input")).sendKeys(Keys.ENTER);
	 	Thread.sleep(3000);
	 	//Click the Adjust Inventory item
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[4]/span/a/i")).click();
		
	 	Thread.sleep(3000);
	 	//Check whether  Adjust Inventory item page opened or not
		if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
		{
			test.log(LogStatus.PASS, " Decrease reason ,Adjust Inventory item page loaded Successfully for update button");
		}
		else
		{
			test.log(LogStatus.FAIL, " Decrease reason ,Adjust Inventory item page loaded Failed for update button");
		}
		Thread.sleep(3000);			
		//Create new reason type for increase method
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[2]/span/a/i")).click();
	 	
	 	Thread.sleep(3000);
	 	//Clear the new reason name
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[1]/div/input")).clear();
	 	//Enter the new reason name
	 	 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("Decrease_Inventory_Reason_name"));
	
	 	Thread.sleep(3000);
	  	//Click the new reason name
	  	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select")).click();
	  	
	  	Thread.sleep(3000);
	  	//Click Decrease button
	  	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select/option[2]")).click();
	  	Thread.sleep(3000);
	  	//Thread.sleep(3000);
		 //Select reasontype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[2]/div/select/option[1]")));
		// reasontype.selectByVisibleText("Item"); 
	 	
		 Thread.sleep(5000);
	 	// Click save button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[3]/div/div[2]/form/div[3]/div/div/div/a[2]")).click();		
		
		Thread.sleep(3000);
		//Clear the inventory count option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[4]/div/ng-form/input[2]")).clear();
		//Enter the inventory count Quantity
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[4]/div/ng-form/input[2]")).sendKeys("6000");
		//Enter the inventory count Quantity
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div[4]/div/ng-form/input[2]")).sendKeys(Keys.ENTER);
			
		Thread.sleep(3000);
		//Clear the inventory notes option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/textarea")).clear();
		//Enter the inventory notes
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/textarea")).sendKeys("Needed Less");
		//Enter the inventory notes
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/textarea")).sendKeys(Keys.ENTER);
					
	 	Thread.sleep(3000);
	 	//Click Adjust button for update new adjust inventory 
		driver.findElement(By.xpath("//span[.='Adjust']")).click();
		Thread.sleep(5000);
		//Check new adjust inventory button update successfully or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Stock adjusted successfully"))
		{
			test.log(LogStatus.PASS, "Stock adjusted successfully for Decrease reason");
		}
		else
		{
			test.log(LogStatus.FAIL, "Stock adjusted successfully for Decrease reason");
		}
		
		
	 	Thread.sleep(5000);	
		}
	
		@Test(enabled=false,priority=36)
		public void  Adjust_Inventory_item_Bulk_update(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click bulk update button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[2]")).click();
		
			Thread.sleep(2000);
			//Click refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/a[1]/i")).click();
	
			Thread.sleep(4000);
			//clear the search box
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).clear();
			//Search the  Adjust Inventory Item option	
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
			
			Thread.sleep(2000);
			//Click the reason type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div/a")).click();
			//Enter the required reason type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div/div/div/input")).sendKeys(Utility.getProperty("Decrease_Inventory_Reason_name"));
			//Enter the required reason type
	    	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
					
			Thread.sleep(2000);
			//clear the notes for bulk updates
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[8]")).clear();
			//Enter the reason
		    driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[8]")).sendKeys("Adengappa");
					
			Thread.sleep(4000);
			//clear the search box
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/div/input")).clear();
			//Search the  Adjust Inventory Item option	
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/div/input")).sendKeys("0");
			
			Thread.sleep(4000);
			//click Apply Changes button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success.btn-border-green.ng-binding")).click();
			
		}
	
		@Test(enabled=false,priority=37)
		public void  Adjust_Subrecipe_Bulk_update(WebDriver driver) throws Exception
		{
			Thread.sleep(6000);
			//Click bulk update button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[2]")).click();
		
			Thread.sleep(4000);
			//clear the search box
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).clear();
			//Search the  Adjust Inventory Item option	
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div/label/input")).sendKeys(Utility.getProperty("Search_Subrecipe"));
			
			Thread.sleep(2000);
			//Click the reason type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div/a")).click();
			//Enter the required reason type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div/div/div/input")).sendKeys("INC1");
			//Enter the reason
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(4000);
			//clear the search box
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/div/input")).clear();
			//Search the  Adjust Inventory Item option	
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[6]/div/input")).sendKeys("0");
			
			Thread.sleep(4000);
			//click update button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success.btn-border-green.ng-binding")).click();
			
		}
	
		@Test(enabled=false,priority=38)
		public void  Adjust_Bulk_update_Backbutton(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Click bulk update button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[2]")).click();
		
			Thread.sleep(3000);
			//Click back button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/a[3]/span")).click();
			
			Thread.sleep(5000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory page loaded Successfully after click bulk update back button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory page loaded Failed after click bulk update back button");
			}
			
			Thread.sleep(5000);
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
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
}
