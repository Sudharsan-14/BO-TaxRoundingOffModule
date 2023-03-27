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


public class Inventory_Low_Stacks {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Low_Stacks");

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
			Inventory_Low_stock_Openpage(driver);
			Inventory_Low_stock_prepare_SubRecipe(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=39)
		public void Inventory_Low_stock_Openpage(WebDriver driver) throws Exception
		{
	
			Thread.sleep(5000);
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"lowStock");
	
			
			//Check Low stock management page opened or not
			if(driver.findElement(By.xpath("//a[.='Low stock management']")).getText().equalsIgnoreCase("Low stock management"))
			{
				test.log(LogStatus.PASS, "Low stock management page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Low stock management page loaded Failed");
			}
			
			Thread.sleep(5000);
		}
		
	/*	@Test(priority=40)
		public void Inventory_Low_stock_Create_Purchase_Order(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='createPurchaseOrder']"));
			//Scroll the page till the Inventory Menu Items option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			
			//Scroll the web page
		    for(int i=1; i <= 20; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    	Thread.sleep(1000);
		    }
		    
			Thread.sleep(5000);
			//Check whether the Create Purchase Order Available
			if(driver.findElement(By.xpath("//div[@ng-if='lowStock.vendorId']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Create Purchase Order is available in Low Stocks");
				List<WebElement> purchaseOrder = driver.findElements(By.xpath("//div[@ng-if='lowStock.vendorId']"));
				purchaseOrder.size();
				
				System.out.println("Count :"+purchaseOrder.size());
				
				// Create instance of Java script executor
				JavascriptExecutor je2 = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element2 = driver.findElement(By.xpath("//div[@ng-if='lowStock.vendorId']"));
				//Scroll the page till the Inventory Menu Items option present
				je2.executeScript("arguments[0].scrollIntoView(true);",element2); 
		       //To identify the Element
				System.out.println("gettext : "+driver.findElement(By.xpath("//div[@ng-if='lowStock.vendorId']/a")).getText());
				
				//Click the createPurchaseOrder Option		
				driver.findElement(By.xpath("//div[@ng-if='lowStock.vendorId']")).click();
				Thread.sleep(3000);
				
				//Scroll the web page
			    for(int i=1; i <= 30; i++)
			    {
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			    	Thread.sleep(1000);
			    }
				//Click the Calendar icon
				driver.findElement(By.xpath("//span[@class='input-group-btn']")).click();
				//Enter the Current Date
				driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm active']")).click();
				
				Thread.sleep(5000);
				//Select the required placed order via option
				Select placeOrederVia = new Select(driver.findElement(By.name("placedVia")));
				placeOrederVia.selectByVisibleText("Email");
	
				Thread.sleep(2000);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				
				Thread.sleep(5000);
				//Click the Place Order Option
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				
				Thread.sleep(2000);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Purchase order saved successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order saved Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order saved Failed");
				}
				Thread.sleep(5000);
				
				
				//Click the Receiving Order Option
				driver.findElement(By.cssSelector("i.fa.fa-recycle")).click();
				
				Thread.sleep(4000);
				//Click the Submit button
				driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
				
				Thread.sleep(2000);
				//Check whether the Receive information updated or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Receive information updated."))
				{
					test.log(LogStatus.PASS, "Receive information updated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Receive information updated Failed");
				}
				Thread.sleep(5000);
				
				// Create instance of Java script executor
				JavascriptExecutor je1 = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element1 = driver.findElement(By.xpath("//span[contains(.,'Low Stocks')]"));
				//Scroll the page till the Inventory Menu Items option present
				je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		      
				//Click the Purchases Option		
				driver.findElement(By.xpath("//span[contains(.,'Low Stocks')]")).click();
				Thread.sleep(5000);
			}
			else
			{
				test.log(LogStatus.FAIL, "There is no Low Stocks Available for Purchase Order");
			}
		}
	*/
		@Test(enabled=false,priority=41)
		public void Inventory_Low_stock_prepare_SubRecipe(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='prepareSubRecipe']"));
			//Scroll the page till the Inventory Menu Items option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			
			Thread.sleep(5000);
			//Check whether the Create Purchase Order Available
			if(driver.findElement(By.xpath("//div[@ng-if='!lowStock.vendorId']")).isDisplayed())
				
			{
				test.log(LogStatus.PASS, "Prepare Subrecipe is available in Low Stocks");
				List<WebElement> prepareSubRecipe = driver.findElements(By.xpath("//div[@ng-if='!lowStock.vendorId']"));
				prepareSubRecipe.size();											
				
				// Create instance of Java script executor
				JavascriptExecutor je2 = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element2 = driver.findElement(By.xpath("//div[@ng-if='!lowStock.vendorId']"));
				//Scroll the page till the Inventory Menu Items option present
				je2.executeScript("arguments[0].scrollIntoView(true);",element2);
		        
				Thread.sleep(2000);
				 //To identify the Element
				System.out.println("gettext : "+driver.findElement(By.xpath("//div[@ng-if='!lowStock.vendorId']/a")).getText());
				
				Thread.sleep(2000);
				//Click the prepareSubRecipe Option		
				driver.findElement(By.xpath("//div[@ng-if='!lowStock.vendorId']")).click();
				Thread.sleep(4000);
				
				Thread.sleep(6000);
				//Click the SubRecipe option
				driver.findElement(By.xpath("//div[@id='subRecipe_chosen']/a")).click();
				Thread.sleep(2000);
				//Enter the SubRecipe option
			    driver.findElement(By.xpath("//div[@id='subRecipe_chosen']/div/div/input")).sendKeys(Utility.getProperty("subRecipe_name"));
			  //Enter the SubRecipe option
			   driver.findElement(By.xpath("//div[@id='subRecipe_chosen']/div/div/input")).sendKeys(Keys.ENTER);
			   
				Thread.sleep(2000);
				//Clear the quantity option
				driver.findElement(By.name("quantity")).clear();
				Thread.sleep(2000);
				//Enter the quantity option
			    driver.findElement(By.name("quantity")).sendKeys("1");
			    Thread.sleep(2000);
	
				Thread.sleep(4000);
						//Click the primary storage
						driver.findElement(By.xpath("//div[@id='psl_chosen']/a")).click();
						//Thread.sleep(2000);
						//Enter the primary storage
					   //driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Utility.getProperty("Inventory_Primary_Storage_Name"));
					  //Enter the primary storage
			driver.findElement(By.xpath("//div[@id='psl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
				
						Thread.sleep(4000);
						//Click the Secondary storage
						driver.findElement(By.xpath("//div[@id='ssl_chosen']/a")).click();
						Thread.sleep(2000);
						//Enter the Secondary storage
					    driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Utility.getProperty("Inventory_Secondary_Storage_Name"));
					  //Enter the Secondary storage
					   driver.findElement(By.xpath("//div[@id='ssl_chosen']/div/div/input")).sendKeys(Keys.ENTER);
					   
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
				Thread.sleep(5000);
				
				// Create instance of Java script executor
				JavascriptExecutor je1 = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element1 = driver.findElement(By.xpath("//span[contains(.,'Low Stocks')]"));
				//Scroll the page till the Inventory Menu Items option present
				je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		        //Click the Purchases Option		
				driver.findElement(By.xpath("//span[contains(.,'Low Stocks')]")).click();
				Thread.sleep(5000);	
				
			}
			else
			{
				test.log(LogStatus.FAIL, "There is no Low Stocks Available for Prepare Subrecipe");
			}
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
