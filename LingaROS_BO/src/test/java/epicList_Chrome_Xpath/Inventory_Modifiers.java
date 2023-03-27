package epicList_Chrome_Xpath;

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


public class Inventory_Modifiers {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Modifiers");

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
			Inventory_Modifier_HomePage(driver);
			Inventory_Modifier(driver);
			Inventory_Modifier_Item_Update(driver);
			Inventory_Modifier_new_item_modifiers_Update(driver);
			Inventory_Modifier_SubRecipe_Update(driver);
			Inventory_Modifier__Add_Invetory_Sub_Recipe(driver);
			Inventory_Modifier__Add_Invetory_Menu_Item(driver);
			Inventory_Modifier__Add_Not_an_InventoryItem(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=2)
		public void Inventory_Modifier_HomePage(WebDriver driver) throws Exception
		{
			Thread.sleep(15000);
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"inventory_modifiers");

			Thread.sleep(5000);
			//Check Inventory Items page opened or not
			if(driver.findElement(By.xpath("//a[.='Modifiers']")).getText().equalsIgnoreCase("Modifiers"))
			{
				test.log(LogStatus.PASS, "Inventory Modifiers page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Modifiers page loaded Failed");
			}
			
			Thread.sleep(5000);
			
		}
		
		@Test(enabled=false,priority=4)
		public void Inventory_Modifier(WebDriver driver) throws Exception
		{
		Thread.sleep(3000);
		//Clear the Search 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).clear();
		Thread.sleep(3000);
		//Enter the modifier
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
		Thread.sleep(3000);
		//Enter the modifier
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Keys.ENTER);
		
	 	Thread.sleep(3000);
	 	//Click the modifiers
	 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a/i")).click();
		
	 	Thread.sleep(3000);
	 	//Check whether link modifiers page opened or not
		if(driver.findElement(By.xpath("//a[.='Link Modifier']")).getText().equalsIgnoreCase("Link Modifier"))
		{
			test.log(LogStatus.PASS, "Link Modifier page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Link Modifier page loaded Failed");
		}
					
		Thread.sleep(3000);	
		}
		
		@Test(enabled=false,priority=5)
		public void Inventory_Modifier_Item_Update(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click the Item Type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select")).click();
			Thread.sleep(3000);
		
			/*
			 //Select the Item Type
			 Select temp = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")));
			 Thread.sleep(3000);
			 temp.selectByVisibleText("Item");  
			 Thread.sleep(2000);*/
			 //Select modifier item option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")).click();
			
			Thread.sleep(3000);
			 //Click Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/a")).click();
			 Thread.sleep(3000);
			 
			 //Click the Input field
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).click();
			 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
			 Thread.sleep(3000);
			//Enter Category for Link Modifer
			 //driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			 //Click Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[1]/div/a")).click();
			 Thread.sleep(3000);
			 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[1]/div/div/div/input")).click();
		
			 Thread.sleep(3000);
			 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
			 Thread.sleep(5000);
			 //Click update button						
				driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
				Thread.sleep(2000);
		
		
		}
		
		@Test(enabled=false,priority=6)
		public void Inventory_Modifier_new_item_modifiers_Update(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(3000);
			//Clear the Search 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).clear();
			Thread.sleep(3000);
			//Enter the modifier
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
			Thread.sleep(3000);
			//Enter the modifier
		 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Keys.ENTER);
			
		 	Thread.sleep(3000);
		 	//Click the modifiers
		 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a/i")).click();
			
		 	Thread.sleep(3000);
		 	//Check whether link modifiers page opened or not
			if(driver.findElement(By.xpath("//a[.='Link Modifier']")).getText().equalsIgnoreCase("Link Modifier"))
			{
				test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for modifier level item");
			}
			else
			{
				test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for modifier level item");
			}
						
			
			Thread.sleep(3000);
			//Click the Item Type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select")).click();
		/*	Thread.sleep(3000);
			//Select the Item Type
			 Select temp = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")));
			 temp.selectByVisibleText("Item");  */
			 Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")).click();
			 Thread.sleep(3000);
			 //Click Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/a")).click();
			 Thread.sleep(3000);
			 
			 //Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).sendKeys("FOOD");
		
			 Thread.sleep(3000);
			//Enter Category for Link Modifer
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
			 Thread.sleep(3000);
			 
			 
			//Click new item creation button
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[3]/div[2]/a/i")).click();
			 
				
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
					driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Category_Name_ForModifiers"));
					
					//Clear the description box
					driver.findElement(By.name("description")).clear();
					//Enter the Description
					driver.findElement(By.name("description")).sendKeys("Desc of Inventory Category");
					
					Thread.sleep(2000);
					//Click the Save button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/div/button")).click();
					
					Thread.sleep(2000);
					//Click the Category field
					driver.findElement(By.xpath("//div[@id='category_chosen']/a")).click();
					Thread.sleep(2000);
					//Enter the Category location
					driver.findElement(By.xpath("//div[@id='category_chosen']/div/div/input")).click();
					Thread.sleep(2000);
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='category_chosen']/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(3000);
					//Click the Add primary storage button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[1]/div/div[1]/div[3]/div[2]/a/i")).click();
					Thread.sleep(2000);
					
					/*//Click the primary storage field
					driver.findElement(By.xpath("//div[@id='psl_chosen']/a")).click();
					//Enter the storage location
					driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys("Freezer");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(3000);
					 //Click the new storage 
				     driver.findElement(By.xpath("//i[@class='fa fa-plus-circle']")).click();
					*/
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
					driver.findElement(By.name("masterName")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name_ForModifiers"));
					
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
					Thread.sleep(2000);
					//Enter the required Inventory unit
					driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/div/div/input")).sendKeys("Liter");
					Thread.sleep(2000);
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Clear the Par level option
					driver.findElement(By.name("minInventoryCount")).clear();
					
					Thread.sleep(2000);
					//Enter the required amount of Par level
					driver.findElement(By.name("minInventoryCount")).sendKeys("1");
					
					Thread.sleep(2000);
					//Click the secondary storage field
					driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();
					Thread.sleep(2000);
					//Enter the storage location
					driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys("Freezer");
					
					Thread.sleep(2000);
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Click the Vendor unit
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[1]/div/div/a")).click();
					//Enter the required vendor
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys("Sysco Food");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					
					
					Thread.sleep(2000);//Clear the Bar code option
					driver.findElement(By.name("barcode")).clear();
					//Enter the required Bar Code
					driver.findElement(By.name("barcode")).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code_ForModifiers"));
					
					//Clear the Brand Name
					driver.findElement(By.name("brandName")).clear();
					//Enter the required Brand Name
					driver.findElement(By.name("brandName")).sendKeys("Brand");
					
					Thread.sleep(2000);
					//Click the purchase unit option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[4]/div/div/a")).click();
					Thread.sleep(2000);
					//Enter the required option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys("Liter");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[2]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Clear the unit multiplier option
					driver.findElement(By.name("purchaseUnitMultiplier")).clear();
					//Enter the required unit multipliuer
					driver.findElement(By.name("purchaseUnitMultiplier")).sendKeys("1");
					
					Thread.sleep(2000);
					//Clear the Quantity option
					driver.findElement(By.name("receivingQuantity")).clear();
					//Enter the required Quantity
					driver.findElement(By.name("receivingQuantity")).sendKeys("1");
					
					
					Thread.sleep(2000);
					//Clear the price option
					driver.findElement(By.name("price")).clear();
					//Enter the required price
					driver.findElement(By.name("price")).sendKeys("115");
					
					Thread.sleep(2000);
					//Clear the yield option
					driver.findElement(By.name("yield")).clear();
					//Enter the required yield
					driver.findElement(By.name("yield")).sendKeys("11");
					
					Thread.sleep(2000);
					//Clear the pricePerUnit option
					driver.findElement(By.name("pricePerUnit")).clear();
					//Enter the required pricePerUnit
					driver.findElement(By.name("pricePerUnit")).sendKeys("75");
					
					Thread.sleep(2000);
					Thread.sleep(2000);
					//Click the Add new vendor option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/ul/li[2]/a/uib-tab-heading/a")).click();
					
					//Click the Vendor unit
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[1]/div/div/a")).click();
					/*//Enter the required vendor  
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys("Krishna butcher shop");
				*/	//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					Thread.sleep(2000);
					//Clear the Bar code option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[2]/div/input")).clear();
					//Enter the required Bar Code
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[2]/div/input")).sendKeys(Utility.getProperty("Inventory_Sku_OR_Bar_Code")+"NEW");
					Thread.sleep(2000);
					Thread.sleep(2000);
					//Clear the Brand Name
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[3]/div/input")).clear();
					//Enter the required Brand Name
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[3]/div/input")).sendKeys("Brand");
					
					Thread.sleep(2000);
					//Click the purchase unit option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/div/a")).click();
					
					Thread.sleep(2000);//Enter the required option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys("liter");
					Thread.sleep(2000);//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					
					Thread.sleep(2000);
					//Clear the unit multiplier option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[2]/div/div/div/div[3]/ng-form/div/div/div[1]/div[4]/div/input[1]")).clear();
					Thread.sleep(2000);//Enter the required unit multiplier
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
	
					//click price option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[1]/ng-form/div/div/div[4]/div/input")).click();
					//Clear the Price Option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[1]/ng-form/div/div/div[4]/div/input")).clear();
					//Enter the required price per unit
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[1]/ng-form/div/div/div[4]/div/input")).sendKeys("123");
	
					Thread.sleep(3000);
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
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[3]/div/div/div/input")).sendKeys("Liter");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[3]/div/div/div/div[1]/div/div[2]/ng-form/div/div/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
	
					//click price option
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
					
					//Click the Save button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/form/div[4]/div/div/button")).click();
					Thread.sleep(2000);
					
					Thread.sleep(4000);
					//Check whether the new Inventory Items saved or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Inventory item saved successfully!."))
					{
						test.log(LogStatus.PASS, "New Inventory Items Saved Sucessfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Inventory Items Save Failed");
					}
	
					Thread.sleep(3000);
				
					Thread.sleep(8000);
		 //Click update button
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
		Thread.sleep(5000);
		
	}
		
		@Test(enabled=false,priority=7)
		public void Inventory_Modifier_SubRecipe_Update(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Clear the Search 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).clear();
			Thread.sleep(3000);
			//Enter the modifier
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
			Thread.sleep(3000);
			//Enter the modifier
		 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Keys.ENTER);
			
		 	Thread.sleep(3000);
		 	//Click the modifiers
		 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a/i")).click();
			
		 	Thread.sleep(3000);
		 	//Check whether link modifiers page opened or not
			if(driver.findElement(By.xpath("//a[.='Link Modifier']")).getText().equalsIgnoreCase("Link Modifier"))
			{
				test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for sub recipe");
			}
			else
			{
				test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for sub recipe");
			}
			
			Thread.sleep(3000);
			//Click the Item Type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select")).click();
		/*
			Thread.sleep(3000);
			//Select the Item Type
			 Select temp = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select/option[3]")));
			 temp.selectByVisibleText("Sub Recipe");  
			*/
			
			Thread.sleep(6000);
			//Select modifier sub recipe option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select/option[3]")).click();
			Thread.sleep(3000);
			
			 //Click item field 
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div/a")).click();
			 Thread.sleep(2000);
			 //Select sub recipe
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("carrot");
			 Thread.sleep(2000);
			//Enter the selected sub recipe
			 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
			 
			 Thread.sleep(6000);
			 //Click update button
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
				
		
		}
		
		@Test(enabled=false,priority=8)	
		public void Inventory_Modifier__Add_Invetory_Sub_Recipe(WebDriver driver) throws Exception	
		
			{
			Thread.sleep(2000);
				//Clear the Search 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).clear();
				Thread.sleep(3000);
				//Enter the modifier
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
				Thread.sleep(3000);
				//Enter the modifier
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Keys.ENTER);
				
			 	Thread.sleep(3000);
			 	//Click the modifiers
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a/i")).click();
				
			 	Thread.sleep(3000);
			 	//Check whether link modifiers page opened or not
				if(driver.findElement(By.xpath("//a[.='Link Modifier']")).getText().equalsIgnoreCase("Link Modifier"))
				{
					test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for sub recipe");
				}
				else
				{
					test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for sub recipe");
				}
				
				
				Thread.sleep(3000);
				//Click the Item Type
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select")).click();
			
				Thread.sleep(3000);
				//Select the Item Type
				 Select temp = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")));
				 temp.selectByVisibleText("Item");  
				
				Thread.sleep(3000);
				//Select modifier subreceipe option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select/option[3]")).click();
				 
				
				Thread.sleep(3000);
				 //Click new sub recipe creation button
				 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div[2]/a/i")).click();
				 
				 Thread.sleep(3000);				
				 //Check whether the new form loaded or not		
				 if(driver.findElement(By.xpath("//span[.='New SubRecipe']")).getText().equalsIgnoreCase("New SubRecipe"))	
				 {		
					 test.log(LogStatus.PASS, "New SubRecipe form loaded Successfully");	
				 }		
				 else		
				 {			
					 test.log(LogStatus.FAIL, "New SubRecipe form loaded Failed");		
				 }		
				 
				 Thread.sleep(3000);				
				 //Clear the name field		
				 driver.findElement(By.name("name")).clear();		
				 //Enter the Name		
				 driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Inventory_SubRecipe_Name"));		
				 Thread.sleep(2000);			
				 //Enable the Calculate COGS On Cost Price button		
				 driver.findElement(By.name("costPriceBasedCOGS")).click();				
				 Thread.sleep(2000);		
				 //Add Inventory Item		
				 driver.findElement(By.xpath("//i[contains(.,'Inventory Item')]")).click();				
				 Thread.sleep(1000);		
				 //Click the Category Option		
				 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[1]/div/a")).click();		
				 //Enter the required input		
				// driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys("FOOD");		
				 //EPress the Enter button	
				 Thread.sleep(1000);
				 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys(Keys.ENTER);	
				 	
				 Thread.sleep(3000);		
				 	//Click the Item		
				 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[2]/div/a")).click();		
				//Enter the Required Item		
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[2]/div/div/div/input")).sendKeys("dh");		
				//Press the Enter button		
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[2]/div/div/div/input")).sendKeys(Keys.ENTER);			
				
				Thread.sleep(1000);		
				//Click the Recipe Unit		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[3]/div/a")).click();		
				//Enter the Required Recipe Unit		
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[3]/div/div/div/input")).sendKeys("Pound");	
				//Press the Enter button		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[3]/div/div/div/input")).sendKeys(Keys.ENTER);				
				
				Thread.sleep(1000);		
				//Clear the Quantity		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[5]/input")).clear();
				//Enter the required quantity	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[1]/td[5]/input")).sendKeys("3");				
				Thread.sleep(2000);
				
				//Add Sub Recipe	
				driver.findElement(By.xpath("//i[contains(.,'Sub Recipe')]")).click();	
				Thread.sleep(1000);	
				//Click the Item	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[2]/div/a")).click();
				//Enter the Required Item	
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[2]/div/div/div/input")).sendKeys("pizza base");	
				//Press the Enter button		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[2]/div/div/div/input")).sendKeys(Keys.ENTER);	
				Thread.sleep(1000);	
				
				//Click the Recipe Unit	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[3]/div/a")).click();	
				//Enter the Required Recipe Unit	
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[3]/div/div/div/input")).sendKeys("Kilo gram");	
				//Press the Enter button	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[3]/div/div/div/input")).sendKeys(Keys.ENTER);	
				
				Thread.sleep(1000);	
				//Clear the Quantity		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[5]/input")).clear();		
				//Enter the required quantity	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[2]/td[5]/input")).sendKeys("3");	
				Thread.sleep(2000);	
				
				//Add Manual Entry	
				driver.findElement(By.xpath("//i[contains(.,'Manual Entry')]")).click();	
				Thread.sleep(1000);	
				//clear the Required Item	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[3]/td[2]/input")).clear();		
				//Enter the required Item		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[3]/td[2]/input")).sendKeys("Test");	
				Thread.sleep(1000);	
				
				//clear the Price per Unit Option		
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[3]/td[4]/input")).clear();	
				//Enter the Price per Unit
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[3]/td[4]/input")).sendKeys("500");	
				Thread.sleep(1000);		
				
				//Clear the Quantity	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[3]/td[5]/input")).clear();	
				//Enter the required quantity	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[3]/td[5]/input")).sendKeys("2");	
				Thread.sleep(2000);	
				
				//Add Inventory Item		
				driver.findElement(By.xpath("//i[contains(.,'Inventory Item')]")).click();	
				Thread.sleep(1500);	
				//Click the Close button for Inventory Item	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[4]/td[7]/a/i")).click();	
				Thread.sleep(1000);	
				//Click the Yes Button	
				driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();	
				Thread.sleep(3000);					
				//Add Sub Recipe
				driver.findElement(By.xpath("//i[contains(.,'Sub Recipe')]")).click();		
				Thread.sleep(1500);	
				//Click the Close button for Sub Recipe	
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[2]/div/table/tbody/tr[4]/td[7]/a/i")).click();	
				Thread.sleep(1000);		
				//Click the Yes Button		
				driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();	
				Thread.sleep(1500);				
				
				//Add Manual Entry	
				//Thread.sleep(2000);	
				//driver.findElement(By.xpath("//input[@class='form-control ng-pristine ng-valid ng-touched']")).sendKeys("2");
				
					Thread.sleep(2000);		
					//Clear the notes field		
					driver.findElement(By.name("notes")).clear();	
					//Enter the notes	
					driver.findElement(By.name("notes")).sendKeys("Inventory Sub Recipe Notes");	
					Thread.sleep(2000);					
					//Clear the Quantity Field	
					driver.findElement(By.name("receivingQuantity")).clear();	
					//Enter the Quantity		
					driver.findElement(By.name("receivingQuantity")).sendKeys("2");		
					Thread.sleep(1000);				
					//Click the Inventory Unit	
					driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/a")).click();		
					//Enter the Required Inventory Unit		
					driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/div/div/input")).sendKeys("Liter");	
					//Press the Enter button		
					driver.findElement(By.xpath("//div[@id='inventoryUnit_chosen']/div/div/input")).sendKeys(Keys.ENTER);				
					Thread.sleep(1000);		
					//Clear the Par Level Option		
					driver.findElement(By.id("minInventoryCount")).clear();		
					//Enter the Par Level		
					driver.findElement(By.id("minInventoryCount")).sendKeys("1");				
					Thread.sleep(1000);		
					//Clear the Yield field		
					driver.findElement(By.name("yield")).clear();		
					//Enter the Required Yield		
					driver.findElement(By.name("yield")).sendKeys("20");				
					//Click Add Primary Storage Location		
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/form/div[3]/div/div[2]/div[6]/div[2]/a/i")).click();			
					Thread.sleep(1500);	
					//Check whether the New Storage form loaded or not		
					if(driver.findElement(By.xpath("//span[.='New Storage']")).getText().equalsIgnoreCase("New Storage"))	
					{			
						test.log(LogStatus.PASS, "New Storage form Loaded Successfully");	
						}		
					else	
						{			test.log(LogStatus.FAIL, "New Storage form Loaded Failed");		
						}		
					
					Thread.sleep(2000);			
						
						
						//Clear the name field		
						driver.findElement(By.id("masterName")).clear();		
						//Enter the name		
						driver.findElement(By.id("masterName")).sendKeys(Utility.getProperty("Inventory_SubRecipe_Primary_Storage_Name_ForModifier"));				
						Thread.sleep(1000);		
						//Clear the Description		
						driver.findElement(By.id("description")).clear();		
						//Enter the Description		
						driver.findElement(By.id("description")).sendKeys("Desc of Primary Storage");				
						//Click the Save button		
						driver.findElement(By.xpath("//button[@type='submit']")).click();		
						
						Thread.sleep(2500);			
						//Click the Secondary Storage Location		
						driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();		
						//Enter the Secondary Storage Location		
						driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys("Freezer");		
						//Press the Enter button		
						driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);		
						
						Thread.sleep(1000);		
						//Clear the Input field		
						driver.findElement(By.xpath("//input[@class='form-control ng-pristine ng-valid ng-touched']")).clear();		
						//Enter the required Input	
					 	driver.findElement(By.xpath("//input[@class='form-control ng-pristine ng-valid ng-touched']")).sendKeys("2");
					 	
					
						Thread.sleep(1000);		
						//Click the Required option				
						driver.findElement(By.xpath("//a[@class='chosen-single chosen-default']")).click();		
						Thread.sleep(1000);		
						//Enter the Required Option		
						driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single chosen-with-drop chosen-container-active']/div/div/input")).sendKeys("Liter");	
						//Press the Enter button	
						driver.findElement(By.xpath("//div[@class='chosen-container chosen-container-single chosen-with-drop chosen-container-active']/div/div/input")).sendKeys(Keys.ENTER);			
						driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
						
						Thread.sleep(5000);		
						//Click the Save button		
						driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();		
						
					Thread.sleep(7000);
				
					//Click update button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
				
				 	Thread.sleep(5000);
				  	//Check whether the new Inventory Items saved or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier updated sucessfully!."))
					{
						test.log(LogStatus.PASS, "Modifier updated sucessfully for sub recipe");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier updated Failed for sub recipe");
					}
				Thread.sleep(3000);
				}
			
		@Test(enabled=false,priority=9)	
		public void Inventory_Modifier__Add_Invetory_Menu_Item(WebDriver driver) throws Exception	
		
			{
				//Clear the Search 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).clear();
				Thread.sleep(3000);
				//Enter the modifier
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
				Thread.sleep(3000);
				//Enter the modifier
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Keys.ENTER);
				
			 	Thread.sleep(3000);
			 	//Click the modifiers
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a/i")).click();
				
			 	Thread.sleep(3000);
			 	//Check whether link modifiers page opened or not
				if(driver.findElement(By.xpath("//a[.='Link Modifier']")).getText().equalsIgnoreCase("Link Modifier"))
				{
					test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for Menu Item");
				}
				else
				{
					test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for Menu Item");
				}
				
				
				Thread.sleep(3000);
				//Click the Item Type
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select")).click();
			
				Thread.sleep(3000);
				//Select the Item Type
				 Select temp = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")));
				 temp.selectByVisibleText("Item");  
				
				Thread.sleep(3000);
				//Select modifier level Menu item option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select/option[4]")).click();
				 
				Thread.sleep(3000);
				 //Click new item creation button
				 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/a")).click();
				//Select new item creation button
				 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).sendKeys("CHEE LATTU");
				//Enter new item creation button
				 driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				 
				 Thread.sleep(2000);
				   //Click update button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
					
			
					Thread.sleep(5000);
				  	//Check whether the new Inventory Items saved or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier updated sucessfully!."))
					{
						test.log(LogStatus.PASS, "Modifier updated sucessfully for Menu item");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier updated Failed for Menu item");
					}
				Thread.sleep(3000);
				}	
				 	
		@Test(enabled=false,priority=10)	
		public void Inventory_Modifier__Add_Not_an_InventoryItem(WebDriver driver) throws Exception	
		
			{
				//Clear the Search 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).clear();
				Thread.sleep(3000);
				//Enter the modifier
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Serach_Inventory_modifiers"));
				Thread.sleep(3000);
				//Enter the modifier
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/label/input")).sendKeys(Keys.ENTER);
				
			 	Thread.sleep(3000);
			 	//Click the modifiers
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a/i")).click();
				
			 	Thread.sleep(3000);
			 	//Check whether link modifiers page opened or not
				if(driver.findElement(By.xpath("//a[.='Link Modifier']")).getText().equalsIgnoreCase("Link Modifier"))
				{
					test.log(LogStatus.PASS, "Link Modifier page loaded Successfully for Menu Item");
				}
				else
				{
					test.log(LogStatus.FAIL, "Link Modifier page loaded Failed for Menu Item");
				}
				
				
				Thread.sleep(3000);
				//Click the Item Type
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div[1]/div/select")).click();
			
				Thread.sleep(3000);
				//Select the Item Type
				 Select temp = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[2]")));
				 temp.selectByVisibleText("Item");  
				
				Thread.sleep(3000);
				//Select modifier level Menu item option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/form/div[1]/div/div/div/div[2]/div/div/div/div/div/select/option[5]")).click();
				 
				Thread.sleep(2000);
				//Click update button
					driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
					
			
					Thread.sleep(5000);
				  	//Check whether the new Inventory Items saved or not
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier updated sucessfully!."))
					{
						test.log(LogStatus.PASS, "Modifier updated sucessfully for Menu item");
					}
					else
					{
						test.log(LogStatus.FAIL, "Modifier updated Failed for Menu item");
					}
				Thread.sleep(3000);
				
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
