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


public class Inventory_Purchase_Prepare_SubRecipe {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Purchase_Prepare_SubRecipe");

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
			Inventory_Purchase_Prepare_Subrecipe_Openpage(driver);
			Inventory_Purchase_Prepare_Subrecipe_Update(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=15)
	public void Inventory_Purchase_Prepare_Subrecipe_Openpage(WebDriver driver) throws Exception
	{
/*		//Click the Inventory option
		driver.findElement(By.xpath("//span[.='Inventory']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[contains(.,'Purchases')]"));
		//Scroll the page till the Inventory Items option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Inventory Items Option		
		driver.findElement(By.xpath("//span[contains(.,'Purchases')]")).click();
		 //driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/ul/li[11]/a/i")).click();
		*/
		//Get the URl
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"receivedLogs");
		Thread.sleep(5000);
		//Check Inventory Items page opened or not
		if(driver.findElement(By.xpath("//a[.='Received Log']")).getText().equalsIgnoreCase("Received Log"))
		{
			test.log(LogStatus.PASS, "Purchase page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Purchase Inventory page loaded Failed");
		}
		
		Thread.sleep(5000);
		
	}

	@Test(enabled=false,priority=16)
	public void Inventory_Purchase_Prepare_Subrecipe_Update(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Click Prepare SubRecipe
		driver.findElement(By.xpath("//span[contains(.,'Prepare SubRecipe')]")).click();
		
		Thread.sleep(8000);
		//Click the SubRecipe option
		driver.findElement(By.xpath("//div[@id='subRecipe_chosen']/a")).click();
		Thread.sleep(6000);
		//Enter the SubRecipe option
	    driver.findElement(By.xpath("//div[@id='subRecipe_chosen']/div/div/input")).sendKeys(Utility.getProperty("subRecipe_name"));
	  //Enter the SubRecipe option
	   driver.findElement(By.xpath("//div[@id='subRecipe_chosen']/div/div/input")).sendKeys(Keys.ENTER);
	    
	   Thread.sleep(2000);
		//Clear the quantity option
		driver.findElement(By.name("quantity")).clear();
		Thread.sleep(2000);
		//Enter the quantity option
	    driver.findElement(By.name("quantity")).sendKeys("2");

	    
		//Thread.sleep(4000);
				//Click the primary storage
				//driver.findElement(By.xpath("//div[@id='psl_chosen']/a")).click();
				//Thread.sleep(2000);
				//Enter the primary storage
			   // driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name"));
			  //Enter the primary storage
			   //driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
		
	    Thread.sleep(2000);
	 //Click New primary storage
	 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div[2]/form/div[1]/div[2]/div[2]/a/i")).click();
					
				Thread.sleep(2000);
				//Check New primary storage	page loaded successfully or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New Storage"))
				{
					test.log(LogStatus.PASS, "New primary storage page loaded successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New primary storage page loaded Failed");
				}
				
			 
				Thread.sleep(2000);
			 //Click the New primary storage
			driver.findElement(By.name("masterName")).clear();
			Thread.sleep(2000);
			//Enter the New primary storage	    
			driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name"));	  
	
			 	
			Thread.sleep(4000);
			//Click the New primary storage
				driver.findElement(By.name("description")).clear();
				Thread.sleep(2000);
				//Enter the New primary storage	    
				driver.findElement(By.name("description")).sendKeys("Need a place to keep");	  
			
				 
				Thread.sleep(3000);
				//Click the Save button
				driver.findElement(By.cssSelector("button.btn.btn-success.btn-small.ng-binding")).click();
						
		
			Thread.sleep(2000);
			//Check New primary storage	page loaded successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div[1]/h3/span/span")).getText().equalsIgnoreCase("Prepare SubRecipe"))
			{
				test.log(LogStatus.PASS, "Prepare SubRecipe page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Prepare SubRecipe page loaded Failed");
			}
			
			Thread.sleep(4000);
			//Click the Secondary storage
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();
			Thread.sleep(2000);
			//Enter the Secondary storage
		    driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Utility.getProperty("Inventory_Secondary_Storage_Name"));
		  //Enter the Secondary storage
		   driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
		   
/*		 //Click New Secondary storage
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div[2]/form/div[1]/div[3]/div[2]/a/i")).click();
				
			Thread.sleep(2000);
			//Check New Secondary storage	page loaded successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New Storage"))
			{
				test.log(LogStatus.PASS, "New Secondary storage page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Secondary storage page loaded Failed");
			}
			
		 
			Thread.sleep(2000);
		 //Click the New Secondary storage
		driver.findElement(By.name("masterName")).clear();
		Thread.sleep(2000);
		//Enter the New Secondary storage	    
		driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name")+"1");	  
		//Enter the New Secondary storage 	                          
		driver.findElement(By.name("masterName")).sendKeys(Keys.ENTER);
		 	
		Thread.sleep(2000);
		//Click the New Secondary storage
			driver.findElement(By.name("description")).clear();
			Thread.sleep(2000);
			//Enter the New Secondary storage	    
			driver.findElement(By.name("description")).sendKeys("Need a place to keep");	  
			//Enter the New Secondary storage 	                          
			driver.findElement(By.name("description")).sendKeys(Keys.ENTER);
			 
			Thread.sleep(3000);
			//Click the Save button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
					
		
		Thread.sleep(2000);
		//Check New Secondary storage page loaded successfully or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New Storage"))
		{
			test.log(LogStatus.PASS, "New Secondary storage page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Secondary storage page loaded Failed");
		}*/

		
	
	   
	  //Click the Save button
	 //driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div[2]/form/div[2]/div/div/button")).click();
		 Thread.sleep(5000);
	 driver.findElement(By.xpath("//span[.='Save and Continue']")).click();
	
	  Thread.sleep(5000);
		//Check Receive Inventory saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Sub recipe prepared successfully"))
		{
			test.log(LogStatus.PASS, "Sub recipe prepared successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Sub recipe prepared Failed");
		}
		
		Thread.sleep(8000);
		
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
