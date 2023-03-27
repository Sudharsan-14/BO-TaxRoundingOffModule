package epicList_Chrome_Xpath;

import java.util.List;
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


public class Central_Inventory_Add_Edit_Central_WareHouse {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Central_Inventory_Add_Edit_Central_WareHouse");

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
			Central_WareHouse_openpage(driver);
			Central_WareHouse_Creation(driver);
			Central_WareHouse_Edit(driver);
			Central_WareHouse_Dashboard(driver);
			Central_warehouse_Reports(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=2)
	public void Central_WareHouse_openpage(WebDriver driver) throws Exception
	{
/*		Thread.sleep(15000);
		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
        //Click the Central Inventory Reports Option		
		driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
		
		Thread.sleep(2000);
		//Click the Central WareHouse option
		driver.findElement(By.xpath("//span[.='Central WareHouse']")).click();*/
		//Enter the URL for Central Warehouse
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"centralStores/centralWarehouse");
		
		Thread.sleep(5000);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath("//a[.='Central Warehouse']")).getText().equalsIgnoreCase("Central Warehouse"))
		{
			test.log(LogStatus.PASS, "Central Warehouse page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Central Warehouse page loaded Failed");
		}
		
		Thread.sleep(3000);
		
	}
	
	@Test(enabled=false,priority=3)
	public void Central_WareHouse_Creation(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Click the Central Warehouse creation button 
		driver.findElement(By.cssSelector("button.btn.btn-sm.btn-inverse.ng-scope")).click();
			
		Thread.sleep(2000);
		//Check New Central WareHouse page opened or not
		if(driver.findElement(By.xpath("//span[.='New Central WareHouse']")).getText().equalsIgnoreCase("New Central WareHouse"))
		{
			test.log(LogStatus.PASS, "New Central WareHouse page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Central WareHouse page loaded Failed");
		}	
		Thread.sleep(2000);
		 //Click the New Central WareHouse name
		driver.findElement(By.name("name")).clear();
		Thread.sleep(2000);
		//Enter the New Central WareHouse name  
		driver.findElement(By.name("name")).sendKeys(Utility.getProperty("New_Central_WareHouse"));	  
		
		Thread.sleep(2000);
		//Click the description
		driver.findElement(By.name("description")).clear();
		Thread.sleep(2000);
		//Enter the description 
		driver.findElement(By.name("description")).sendKeys("Central place");	  
			
		Thread.sleep(2000);
		//Click the phoneNumber 
		driver.findElement(By.name("phoneNumber")).clear();
		Thread.sleep(2000);
		//Enter the phoneNumber	    
		driver.findElement(By.name("phoneNumber")).sendKeys(Utility.getProperty("Central_phoneNumber"));	  
		
			 
		Thread.sleep(2000);
		//Click the emailId 
		driver.findElement(By.name("emailId")).clear();
		Thread.sleep(2000);
		//Enter the emailId
		driver.findElement(By.name("emailId")).sendKeys(Utility.getProperty("Central_emailId"));	  
		
		Thread.sleep(4000);
		//Click the addressLine1 
		driver.findElement(By.name("addressLine1")).clear();
		Thread.sleep(2000);
		//Enter the addressLine1
		driver.findElement(By.name("addressLine1")).sendKeys(Utility.getProperty("Settings_Store_Information_Address1"));	  
		
		Thread.sleep(4000);
		//Enable existing store option
		driver.findElement(By.xpath("//label[@class='label-checkbox inline']/span")).click();
     
		Thread.sleep(2000);
		//Click the Copy data from Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/form/div[2]/div[1]/div[2]/div/div/a")).click();
		//Enter the Copy data from
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/form/div[2]/div[1]/div[2]/div/div/div/div/input")).sendKeys("LINGABO");
		//Click the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div/div[2]/form/div[2]/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the Time Zone Option
		driver.findElement(By.xpath("//form[@name='wareHouseCreation']/div[2]/div[2]/div/div/a")).click();
		//Enter the required Time Zone
		driver.findElement(By.xpath("//form[@name='wareHouseCreation']/div[2]/div[2]/div/div/div/div/input")).sendKeys(Utility.getProperty("Settings_Store_Information_TimeZone"));
		//Click the Enter button
		driver.findElement(By.xpath("//form[@name='wareHouseCreation']/div[2]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Clear the address2 field
		driver.findElement(By.name("addressLine2")).clear();
		//Enter the address2
		driver.findElement(By.name("addressLine2")).sendKeys(Utility.getProperty("Settings_Store_Information_Address2"));
		
		Thread.sleep(1000);
		//Clear the City field
		driver.findElement(By.name("city")).clear();
		//Enter the City
		driver.findElement(By.name("city")).sendKeys(Utility.getProperty("Settings_Store_Information_City"));
		
		Thread.sleep(1000);
		//Clear the State field
		driver.findElement(By.name("state")).clear();
		//Enter the State
		driver.findElement(By.name("state")).sendKeys(Utility.getProperty("Settings_Store_Information_State"));
		
		Thread.sleep(1000);
		//Clear the Zip Code Field
		driver.findElement(By.name("zipCode")).clear();
		//Enter the Zipcode
		driver.findElement(By.name("zipCode")).sendKeys(Utility.getProperty("Settings_Store_Information_Zipcode"));
		
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@id='saveStore']")).click();
		
		Thread.sleep(3500);
		
		//Check New Central WareHouse page opened or not																			
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase(" Central Warehouse Saved successfully "))
		{
			test.log(LogStatus.PASS, " Central Warehouse Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, " Central Warehouse Saved is Failed");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=4)
	public void Central_WareHouse_Edit(WebDriver driver) throws Exception
	{
		
		Thread.sleep(4000);
		//Click refresh button
		driver.findElement(By.xpath("//div/button[2]")).click();

		Thread.sleep(4000);
		//Click the Central Warehouse creation button 
		List<WebElement> max = driver.findElements(By.xpath("//ul[@class='list-inline-centralEntity']/li"));
		max.size();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='list-inline-centralEntity']/li["+max.size()+"]/div[2]/div[1]")).click();
		

		Thread.sleep(4000);
		//Check New Central WareHouse page opened or not
		if(driver.findElement(By.xpath("//span[.='Central WareHouse']")).getText().equalsIgnoreCase("Central WareHouse"))
		{
			test.log(LogStatus.PASS, "Edit Central WareHouse page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edit Central WareHouse page loaded Failed");
		}	
		Thread.sleep(4000);
		 //Click the New Central WareHouse name
		driver.findElement(By.name("name")).clear();
		Thread.sleep(2000);
		//Enter the New Central WareHouse name  
		driver.findElement(By.name("name")).sendKeys(Utility.getProperty("New_Central_WareHouse")+1);	  
		
		Thread.sleep(4000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@id='saveStore']")).click();
		
		Thread.sleep(4000);
		//Check Edit Central WareHouse page opened or not		
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Central Kitchen updated successfully"))
		{
			test.log(LogStatus.PASS, "Edited Central Warehouse Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edited Central Warehouse is Failed");
		}
		Thread.sleep(3000);
	}	
	
	@Test(enabled=false,priority=5)
	public void Central_WareHouse_Dashboard(WebDriver driver) throws Exception
	{
		
		Thread.sleep(4000);
		//Click refresh button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[1]/div/div/button[2]")).click();
		Thread.sleep(4000);
		//Click the store 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div/ul/li[2]/div[1]/a/div")).click();
		
		{
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath("//button[@class='btn btn-md btn-border time-filter btn-refresh']")).click();
			Thread.sleep(4000);
			//Check Inventory Home page opened or not
			if(driver.findElement(By.xpath("//span[.='Live Updates']")).getText().equalsIgnoreCase("Live Updates"))
			{
				test.log(LogStatus.PASS, "Central Inventory Home page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Central Inventory Home page refreshed Failed");
			}	
		}
		
	}
		
	@Test(enabled=false,priority=6)
	public void Central_warehouse_Reports(WebDriver driver) throws Exception
	{
			Thread.sleep(4000);
			//Check the Total Value On Hand value
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[1]")).getText().equalsIgnoreCase("Total Value On Hand"))
			{
				test.log(LogStatus.PASS, "Total Value On Hand report is available");
				
				test.log(LogStatus.INFO, "Total Value On Hand amount is : "+driver.findElement(By.xpath("//div[contains(.,'Total Value On Hand')]/../div/div[2]/span")).getText());
				
			}
		
			else
			{
				test.log(LogStatus.FAIL, "Total Value On Hand report is not available");
			}
			
			String lsvalue = driver.findElement(By.xpath("//span[contains(.,'LOW STOCKS')]/../span[2]")).getText();
			double lowStackValue = Double.parseDouble(lsvalue);
			if(lowStackValue == 0)
			{
				test.log(LogStatus.PASS, "Low Stocks report is not available");
			}
			else
			{
				test.log(LogStatus.PASS, "Low Stocks report is available");
				
				test.log(LogStatus.INFO, "Total Low Stocks value is : "+driver.findElement(By.xpath("//span[contains(.,'LOW STOCKS')]/../span[2]")).getText());

				//Click the Low Stack
				driver.findElement(By.xpath("//span[contains(.,'LOW STOCKS')]/../span[1]")).click();
			
				List<WebElement> rows = driver.findElements(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr"));
				
				for(int i = 1; i <= rows.size(); i++)
				{
					
				test.log(LogStatus.INFO, driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[1]")).getText()+" available quantity is "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" but the minimum quantity is "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[2]")).getText());
				
				}
			}
			
			Thread.sleep(3000);	
		//Click the Central WareHouse
		//driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[2]/a/span[1]")).click();
		Thread.sleep(3000);		
		
		//Click the Central WareHouse
		//driver.findElement(By.xpath("//span[contains(.,'Central Kitchen')]")).click();

		Thread.sleep(4000);		
		}

}
