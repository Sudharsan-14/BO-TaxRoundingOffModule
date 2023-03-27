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


public class Central_Inventory_Add_Edit_Transfer_Request {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Central_Inventory_Add_Edit_Transfer_Request");

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
			Central_Transfer_Request_openpage(driver);
			Central_Transfer_Request_Cancle(driver);
			Central_Transfer_Request_Request(driver);
			Central_Transfer_Request_QuantityNotAvailable(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=19)
	public void Central_Transfer_Request_openpage(WebDriver driver) throws Exception
	{
		/*//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		Thread.sleep(3000);
        //Click the Central Inventory Reports Option		
		driver.findElement(By.xpath("//span[.='Central Inventory']")).click();
		
		Thread.sleep(2000);
		//Click the Central WareHouse option
		driver.findElement(By.xpath("//span[contains(.,'Transfer Request')]")).click();
*/
		Thread.sleep(3000);	
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"centralStores/transferRequest");
		
		Thread.sleep(3000);
		//Check Central Warehouse page opened or not
		if(driver.findElement(By.xpath("//a[.='Transfer Requests']")).getText().equalsIgnoreCase("Transfer Requests"))
		{
			test.log(LogStatus.PASS, "Central Transfer Requests page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Central Transfer Requests page loaded Failed");
		}
		
		Thread.sleep(3000);
		
	}
	
	@Test(enabled=false,priority=20)
	public void Central_Transfer_Request_Cancle(WebDriver driver) throws Exception
	{
		
	Thread.sleep(2000);
	//Click refresh button
	driver.findElement(By.cssSelector("a.btn.btn-md.btn-border.btn-refresh")).click();
	Thread.sleep(3000);
	//Click Transfer Request button
	driver.findElement(By.cssSelector("a.btn.btn-small.btn-inverse.btn--icon.ng-binding")).click();Thread.sleep(3000);
	//Check Central Warehouse page opened or not
			if(driver.findElement(By.xpath("//span[.='New Transfer Request']")).getText().equalsIgnoreCase("New Transfer Request"))
			{
				test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
			}
			
			Thread.sleep(2000);
		//Click From field to select store
		driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[1]/div/div/div/a")).click();
		
		Thread.sleep(1000);
		//Click From field to select store
		//driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Central_Transfer_from"));
		
		Thread.sleep(1000);
		//Click From field to select store
		driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click TO field to select store
		driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[2]/div/div/div/a")).click();
		
		Thread.sleep(1000);
		//Click To field to select store
		driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
		
		Thread.sleep(1000);
		//Click To field to select store
		driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click new Inventory item button
		driver.findElement(By.cssSelector("i.fa.fa-plus-circle.ng-binding")).click();
		
		Thread.sleep(2000);
		//Select Inventory item 
		driver.findElement(By.xpath("//select[@name='inventoryItem']/../div/a")).click();
		//Enter the Required Inventory Item
		driver.findElement(By.xpath("//select[@name='inventoryItem']/../div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Clear the quantity value
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).clear();
		//Enter the quantity value
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).sendKeys("1");
		Thread.sleep(1000);
		//Click remove button for inventory item
		driver.findElement(By.cssSelector("i.fa.fa-times")).click();
		Thread.sleep(2000);
		//Cancel button for inventory item
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=21)
	public void Central_Transfer_Request_Request(WebDriver driver) throws Exception
	{
			
			Thread.sleep(2000);
			//Click refresh button
			driver.findElement(By.cssSelector("a.btn.btn-md.btn-border.btn-refresh")).click();
			Thread.sleep(3000);
			//Click Transfer Request button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-inverse.btn--icon.ng-binding")).click();Thread.sleep(3000);
			//Check Central Warehouse page opened or not
					if(driver.findElement(By.xpath("//span[.='New Transfer Request']")).getText().equalsIgnoreCase("New Transfer Request"))
					{
						test.log(LogStatus.PASS, "New Transfer Request page Successfully loaded ");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Transfer Request page Successfully loaded is failed");
					}
					
					Thread.sleep(2000);
				//Click From field to select store
				driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[1]/div/div/div/a")).click();
				
				Thread.sleep(1000);
				//Click From field to select store
				//driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Central_Transfer_from"));
				
				Thread.sleep(1000);
				//Click From field to select store
				driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[1]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click TO field to select store
				driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[2]/div/div/div/a")).click();
				
				Thread.sleep(1000);
				//Click To field to select store
				driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
				
				Thread.sleep(1000);
				//Click To field to select store
				driver.findElement(By.xpath("//form[@name='createForm']/div[1]/div[1]/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Click new Inventory item button
				driver.findElement(By.cssSelector("i.fa.fa-plus-circle.ng-binding")).click();
				
				Thread.sleep(2000);
				//Select Inventory item 
				driver.findElement(By.xpath("//select[@name='inventoryItem']/../div/a")).click();
				//Enter the Required Inventory Item
				driver.findElement(By.xpath("//select[@name='inventoryItem']/../div/div/div/input")).sendKeys(Keys.ENTER);

				Thread.sleep(1000);
				//Clear the quantity value
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).clear();
				//Enter the quantity value
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/div/table/tbody/tr[1]/td[4]/input")).sendKeys("1");
				Thread.sleep(1000);
			
			//Click Request button for inventory item
			driver.findElement(By.xpath("//a[.='Cancel']/../button")).click();
			Thread.sleep(3000);	
			//Check Central Warehouse page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Transfer request created successfully!."))
			{
				test.log(LogStatus.PASS, "Transfer request created successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Transfer request created is Failed");
			}
		}

	@Test(enabled=false,priority=22)
	public void Central_Transfer_Request_QuantityNotAvailable(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Click the yes button
		List<WebElement> min = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[6]/span/a[2]/i"));
		min.size();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+min.size()+"]/td[6]/span/a[2]/i")).click();
		Thread.sleep(2000);
		//Click that Transfer item button
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.btn-border-green.ng-binding")).click();

		Thread.sleep(1000);
		try
		{
			//Check whether the Received Items are Loaded Or not
			if(driver.findElement(By.xpath("//span[.='*Requested Inventory Items / Sub Recipes not found in this store.Hence Transfer count field is disabled.']")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Requested Inventory Items / Sub Recipes not found in this store.Hence Transfer count field is disabled.");
			}
		}
		catch(Exception e)		
		{
			test.log(LogStatus.PASS, "Requested Inventory Items / Sub Recipes not found in this store.Hence Transfer count field is Enabled.");
						
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr/td[4]"));
			for(int i = 1; i <= rows.size(); i++)
			{
			 //Get the store status
			Thread.sleep(3000);
			test.log(LogStatus.PASS,"The source of the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText()+" store status is "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText()+ " Updated");
						
			test.log(LogStatus.PASS,"The Destination of the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" store status is" +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText()+ " Updated");
			}
		}
		Thread.sleep(2000);	
		
	}

}
