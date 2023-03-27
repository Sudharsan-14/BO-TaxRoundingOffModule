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

public class Inventory_Storage_Locations {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Storage_Locations");

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
			Inventory_Storage_Locations_openpage(driver);
			Inventory_Storage_Locations_refresh(driver);
			Inventory_Storage_Locations_add_Invetory_Storage(driver);
			Inventory_Storage_Locations_edit_Storage_Locations(driver);
			Inventory_Storage_Locations_delete_Invetory_Storage(driver);
			Inventory_Storage_Locations_closeButton(driver);
			Inventory_Storage_Locations_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=12)
		public void Inventory_Storage_Locations_openpage(WebDriver driver) throws Exception
		{
			
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"masters/storageLocation");

			Thread.sleep(4000);
			//Check Storage Locations page opened or not
			if(driver.findElement(By.xpath("//a[.='Storage Location']")).getText().equalsIgnoreCase("Storage Location"))
			{
				test.log(LogStatus.PASS, "Storage Locations page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Storage Locations page loaded Failed");
			}
			
			Thread.sleep(4000);
			
		}
		
		@Test(enabled=false,priority=14)
		public void Inventory_Storage_Locations_refresh(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]/i")).click();
			Thread.sleep(4000);
			//Check Storage Locations page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Storage Location"))
			{
				test.log(LogStatus.PASS, "Storage Locations page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Storage Locations page refreshed Failed");
			}
			Thread.sleep(4000);

		}

		@Test(enabled=false,priority=15)
		public void Inventory_Storage_Locations_add_Invetory_Storage(WebDriver driver) throws Exception
		{
			  //Scroll the web page
		    for(int i=1; i <= 20; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    } 
		    
			Thread.sleep(4000);
			//Click on the Add Storage Location option
			driver.findElement(By.id("storageLocation")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Storage Location']")).getText().equalsIgnoreCase("New Storage Location"))
			{
				test.log(LogStatus.PASS, "New Storage Location form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_Storage_Locations"));
			Thread.sleep(2000);
			
			//Clear the description field
			driver.findElement(By.name("description")).clear();
			//Enter the description
			driver.findElement(By.name("description")).sendKeys("Inventory Storage Location");
			Thread.sleep(2000);
				
			//Click the Save button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			
			//Check whether the new storage location saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("StorageLocation updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Storage Location Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location Save Failed");
			}

			Thread.sleep(4000);
		}
		
		@Test(enabled=false,priority=17)
		public void Inventory_Storage_Locations_edit_Storage_Locations(WebDriver driver) throws Exception
		{
			Thread.sleep(4000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(4000);
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Storage_Locations"));
			Thread.sleep(4000);
			//Click the Edit icon
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(4000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_Storage_Locations")+"1");
			Thread.sleep(4000);
			//Click the update
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(4000);
			//Check whether the storage locations updated successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("StorageLocation updated successfully!."))
			{
				test.log(LogStatus.PASS, "New Storage Location updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location updated Failed");
			}

			Thread.sleep(4000);

		}
		
		@Test(enabled=false,priority=18)
		public void Inventory_Storage_Locations_delete_Invetory_Storage(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Storage_Locations")+"1");
			Thread.sleep(5000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			Thread.sleep(3000);
			//Click the Yes button in the popup
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check the storage locations deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Storage Location InActivated successfully!."))
			{
				test.log(LogStatus.PASS, "New Storage Location deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location deleted Failed");
			}

			Thread.sleep(4000);
		

			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Check the Storage Location activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Storage Location Activated successfully"))
			{
				test.log(LogStatus.PASS, "Storage Location is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Storage Location is activated Failed");
			}
			
			Thread.sleep(4000);
		}

		@Test(enabled=false,priority=19)
		public void Inventory_Storage_Locations_closeButton(WebDriver driver) throws Exception
		{
			Thread.sleep(4000);
			//Click on the Add storage locations option
			driver.findElement(By.id("storageLocation")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Storage Location']")).getText().equalsIgnoreCase("New Storage Location"))
			{
				test.log(LogStatus.PASS, "New Storage Location form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_Storage_Locations"));
			Thread.sleep(2000);
				
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			Thread.sleep(3000);
			//Check whether the new Storage Location canceled or not
			if(driver.findElement(By.id("storageLocation")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Storage Location Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Storage Location Canceled Failed");
			}

			Thread.sleep(4000);
		}
				
		@Test(enabled=false,priority=20)
		public void Inventory_Storage_Locations_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			
			Thread.sleep(4000);
			//Click Active or In Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();

			Thread.sleep(4000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted storage locations are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();

			}
			else if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted storage locations are not here, we are in Active Page");
			}
			Thread.sleep(4000);
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
