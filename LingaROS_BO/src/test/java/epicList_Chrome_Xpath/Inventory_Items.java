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

public class Inventory_Items {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Items");

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
			Inventory_Items_openpage(driver);
			Inventory_Items_refresh(driver);
			Inventory_item_pagination(driver);
			Inventory_Items_add(driver);
			Inventory_Items_edit_Inventory_Items(driver);
			Inventory_Items_delete(driver);
			Inventory_Items_closeButton(driver);
			Inventory_Items_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=37)
		public void Inventory_Items_openpage(WebDriver driver) throws Exception
		{
			
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"inventoryItems");

			Thread.sleep(4000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//span[.='Inventory Item']")).getText().equalsIgnoreCase("Inventory Item"))
			{
				test.log(LogStatus.PASS, "Inventory Items page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Items page loaded Failed");
			}
			
			Thread.sleep(4000);
			
		}
		
		@Test(enabled=false,priority=38)
		public void Inventory_Items_refresh(WebDriver driver) throws InterruptedException
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/a[2]/i")).click();
			Thread.sleep(5000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//span[.='Inventory Item']")).getText().equalsIgnoreCase("Inventory Item"))
			{
				test.log(LogStatus.PASS, "Inventory Items page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Items page refreshed Failed");
			}
			Thread.sleep(5000);

		}

		@Test(enabled=false,priority=39)
		public void Inventory_item_pagination(WebDriver driver) throws InterruptedException
		{
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					
			Thread.sleep(10000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div/ul[1]/li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
			//Create the web element for Inventory Items Table
			List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div/ul[1]/li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
			//Create the web element for Inventory Items Table
			List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 25
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div/ul[1]/li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 25");
			//Create the web element for Inventory Items Table
			List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			Thread.sleep(2000);
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div/ul[1]/li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for Inventory Items Table
			List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
		}
		
		@Test(enabled=false,priority=40)
		public void Inventory_Items_add(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click on the Add Inventory Items option
			driver.findElement(By.id("inventoryItem")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Inventory Item']")).getText().equalsIgnoreCase("New Inventory Item"))
			{
				test.log(LogStatus.PASS, "New Inventory Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
			Thread.sleep(2000);
			
			
			//Click the Add Category button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[1]/div/div[1]/div[2]/div[2]/a/i")).click();
			Thread.sleep(1000);
			
			//Check Whether the new Category form loaded
			if(driver.findElement(By.xpath("//span[.='New Category']")).getText().equalsIgnoreCase("New Category"))
			{
				test.log(LogStatus.PASS, "New Category form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Category form loaded fail");
			}
			Thread.sleep(2000);
			
			//Clear the name field
			driver.findElement(By.name("masterName")).clear();
			//Enter the required name
			driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Category_Name"));
			
			//Clear the description box
			driver.findElement(By.name("description")).clear();
			//Enter the Description
			driver.findElement(By.name("description")).sendKeys("Desc of Inventory Category");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/div/button")).click();
			
			
			Thread.sleep(2000);
			//Click the Add primary storage button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[1]/div/div[1]/div[3]/div[2]/a/i")).click();
			Thread.sleep(2000);
			
			//Check whether the new primary storage location form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Storage']")).getText().equalsIgnoreCase("New Storage"))
			{
				test.log(LogStatus.PASS, "Primary Storage form loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Primary Storage form loaded fail");
			}
			Thread.sleep(2000);
			
			//Clear the name field
			driver.findElement(By.name("masterName")).clear();
			//Enter the required name
			driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name"));
			
			//Clear the description field
			driver.findElement(By.name("description")).clear();
			//Enter the required information
			driver.findElement(By.name("description")).sendKeys("Desc of Inventory Primary Storage");
			
			Thread.sleep(2000);
			//Click the Save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/div/button")).click();
			
			Thread.sleep(2000);
			//Click the Inventory Unit Option
			driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/a")).click();
			//Enter the required Inventory unit
			driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/div/div/input")).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/div/div/input")).sendKeys(Keys.ENTER);
			
			//Clear the Par level option
			driver.findElement(By.name("minInventoryCount")).clear();
			//Enter the required amount of Par level
			driver.findElement(By.name("minInventoryCount")).sendKeys("1");
			
			//Click the secondary storage field
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();
			//Enter the storage location
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys("Freezer");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
			
			//Click the Vendor unit
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[1]/div/div/a")).click();
			//Enter the required vendor
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys("Sysco Food");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Clear the Bar code option
			driver.findElement(By.name("barcode")).clear();
			//Enter the required Bar Code
			driver.findElement(By.name("barcode")).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code"));
			
			//Clear the Brand Name
			driver.findElement(By.name("brandName")).clear();
			//Enter the required Brand Name
			driver.findElement(By.name("brandName")).sendKeys("Brand");
			
			//Click the purchase unit option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[4]/div/div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Clear the unit multiplier option
			driver.findElement(By.name("purchaseUnitMultiplier")).clear();
			//Enter the required unit multipliuer
			driver.findElement(By.name("purchaseUnitMultiplier")).sendKeys("1");
			
			//Clear the Quantity option
			driver.findElement(By.name("receivingQuantity")).clear();
			//Enter the required Quantity
			driver.findElement(By.name("receivingQuantity")).sendKeys("1");
			
			//Clear the price option
			driver.findElement(By.name("price")).clear();
			//Enter the required price
			driver.findElement(By.name("price")).sendKeys("115");
			
			//Clear the yield option
			driver.findElement(By.name("yield")).clear();
			//Enter the required yield
			driver.findElement(By.name("yield")).sendKeys("11");
			
			//Clear the pricePerUnit option
			driver.findElement(By.name("pricePerUnit")).clear();
			//Enter the required pricePerUnit
			driver.findElement(By.name("pricePerUnit")).sendKeys("75");
			
			Thread.sleep(2000);
			//Click the Add new vendor option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[2]/a/uib-tab-heading/a")).click();
			
			//Click the Vendor unit
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[1]/div/div/a")).click();
		/*	//Enter the required vendor  
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys("IV_NameAA1");*/
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Bar code option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[2]/div/input")).clear();
			//Enter the required Bar Code
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"NEW");
			
			Thread.sleep(2000);
			//Clear the Brand Name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[3]/div/input")).clear();
			//Enter the required Brand Name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[3]/div/input")).sendKeys("Brand");
			
			Thread.sleep(2000);
			//Click the purchase unit option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys("liter");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the unit multiplier option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/input[1]")).clear();
			//Enter the required unit multipliuer
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/input[1]")).sendKeys("1000");
			
			Thread.sleep(2000);
			//Clear the Quantity option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[1]/div/input[1]")).clear();
			//Enter the required Quantity
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[1]/div/input[1]")).sendKeys("1");
			
			Thread.sleep(2000);
			//Clear the price option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[2]/div/input")).clear();
			//Enter the required price
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[2]/div/input")).sendKeys("115");
			
			Thread.sleep(2000);
			//Clear the yield option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[3]/div/input")).clear();
			//Enter the required yield
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[3]/div/input")).sendKeys("10");
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[4]/div/input")).click();
			//Clear the pricePerUnit option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[4]/div/input")).clear();
			//Enter the required pricePerUnit
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[2]/div[4]/div/input")).sendKeys("75");
			
			Thread.sleep(2000);
			//Click the New Vendor Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[3]/a/uib-tab-heading/a/i")).click();
			
			Thread.sleep(2000);
			//Click the Close Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[3]/a/uib-tab-heading/a/i")).click();
										 //div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[3]/a/uib-tab-heading/a/i
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Clear the Equal field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div/ng-form/div/div/div[2]/input")).clear();
			//Enter the required number
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div/ng-form/div/div/div[2]/input")).sendKeys("1");
			
			Thread.sleep(2000);
			//Click the Select an Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div/ng-form/div/div/div[3]/div/a")).click();
			//Enter the required unit
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div/ng-form/div/div/div[3]/div/div/div/input")).sendKeys("Liter");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div/ng-form/div/div/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			/*Thread.sleep(7000);
			driver.findElement(By.name("pricePerUnit")).click();
			//Clear the Price Option
			driver.findElement(By.name("pricePerUnit")).clear();
			//Enter the required price per unit
			driver.findElement(By.name("pricePerUnit")).sendKeys("123");*/
			
			Thread.sleep(5000);
			//Click the Add Recipe Unit Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[2]/a/i")).click();
			
			Thread.sleep(1000);
			//Clear the Equal field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[2]/input")).clear();
			//Enter the required number
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[2]/input")).sendKeys("1000");
			
			Thread.sleep(2000);
			//Click the Select an Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[3]/div/a")).click();
			//Enter the required unit
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[3]/div/div/div/input")).sendKeys("liter");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[4]/div/input")).click();
			//Clear the Price Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[4]/div/input")).clear();
			//Enter the required price per unit
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[4]/div/input")).sendKeys("123");

			Thread.sleep(2000);
			//Click the Add Recipe Unit Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[2]/a/i")).click();

			Thread.sleep(2000);
			//Click the Close Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[3]/ng-form/div/div/div[4]/div/a/i")).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
					
			//Click the Save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[4]/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check whether the new Inventory Items saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Inventory item saved successfully!."))
			{
				test.log(LogStatus.PASS, "New Inventory Items Saved Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items Save Failed");
			}

			Thread.sleep(5000);
			}
		
		@Test(enabled=false,priority=41)
		public void Inventory_Items_edit_Inventory_Items(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(4000);
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("i.fa.fa-search")).click();

			Thread.sleep(4000);
			//Click the Edit icon
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_Items_Name")+"1");
			Thread.sleep(2000);

			Thread.sleep(6000);
			//Click the Category Option
			driver.findElement(By.xpath("//div[@id='category_chosen']/a")).click();
			Thread.sleep(2000);
			Thread.sleep(2000);
			//Enter the Required Category
			driver.findElement(By.xpath("//div[@id='category_chosen']/div/div/input")).sendKeys("FOOD");
			Thread.sleep(2000);
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='category_chosen']/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the Primary Storage Option
			driver.findElement(By.xpath("//div[@id='psl_chosen']/a")).click();
			Thread.sleep(3000);
			//Enter the Required Storage
			driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys("Freezer");
			Thread.sleep(2000);
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(5000);
			//Click the secondary storage field
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();
			//Enter the storage location
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys("Syncc");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Click the first vendor
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[1]/a/uib-tab-heading/span")).click();
			//Clear the Barcode option
			driver.findElement(By.name("barcode")).clear();
			//Enter the new Barcode
			driver.findElement(By.name("barcode")).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"1");
			
			Thread.sleep(3000);
			//Click the second vendor
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[2]/a/uib-tab-heading/span")).click();
			//Clear the Barcode option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[2]/div/input")).clear();
			//Enter the new Barcode
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"NEW1");
				
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			Thread.sleep(4000);
			//Click the update
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[4]/div/div/button")).click();
			
			Thread.sleep(2000);
			//Check whether the Inventory Items updated successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Inventory item updated."))
			{
				test.log(LogStatus.PASS, "New Inventory Items updated Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items updated Failed");
			}

			Thread.sleep(5000);

		}
		
		@Test(enabled=false,priority=42)
		public void Inventory_Items_delete(WebDriver driver) throws Exception
		{

			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Items_Name")+"1");
			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr/td[8]/span[1]/a[2]/i")).click();
			
			Thread.sleep(1000);
			//Click the yes button			
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();

			Thread.sleep(3000);
			//Check the Inventory Items deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Inventory item inactivated."))
			{
				test.log(LogStatus.PASS, "New Inventory Items deleted Sucessfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items deleted Failed");
			}

			Thread.sleep(4000);
			//Click the Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[3]/table/tbody/tr/td[8]/span[2]/a/i")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check the Inventory Items activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Inventory item activated."))
			{
				test.log(LogStatus.PASS, "Inventory Items is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Items is activated Failed");
			}
			
			Thread.sleep(4000);
		}

		@Test(enabled=false,priority=43)
		public void Inventory_Items_closeButton(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Click on the Add Inventory Items option
			driver.findElement(By.id("inventoryItem")).click();
			Thread.sleep(3000);
			
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Inventory Item']")).getText().equalsIgnoreCase("New Inventory Item"))
			{
				test.log(LogStatus.PASS, "New Inventory Items form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items form loaded Failed");
			}

			Thread.sleep(3000);
			
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_Items_Name"));
			Thread.sleep(2000);
				
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Cancel']")).click();
			
			Thread.sleep(3000);
			//Check whether the new Inventory Items canceled or not
			if(driver.findElement(By.id("inventoryItem")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Inventory Items Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Inventory Items Canceled Failed");
			}

			Thread.sleep(5000);
		}
				
		@Test(enabled=false,priority=44)
		public void Inventory_Items_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();

			Thread.sleep(5000);
			//Check the Inactive page is loaded or not
			if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted categories are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();

			}
			else if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted Inventory Items are not here, we are in Active Page");
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
