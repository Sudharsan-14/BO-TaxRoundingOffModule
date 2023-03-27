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


public class Inventory_Purchase_Template {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Purchase_Template");

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
			Inventory_Purchase_Purchase_Template_openPAGE(driver);
			Inventory_Purchase_Purchase_Template_add_new(driver);
			Inventory_Purchase_Purchase_Template_edit(driver);
			Inventory_Purchase_Purchase_Template_verify_Purchase_Order_Button(driver);
			Inventory_Purchase_Purchase_Template_delete(driver);
			Inventory_Purchase_Purchase_Template_verifyActive_InActiveButton_Sub_Recipe(driver);
			Thread.sleep(1500);
		}
			
			@Test(enabled=false,priority=2)
			public void Inventory_Purchase_Purchase_Template_openPAGE(WebDriver driver) throws Exception
			{
				Thread.sleep(15000);
/*				//Click the Inventory option
				driver.findElement(By.xpath("//span[.='Inventory']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[contains(.,'Purchases')]"));
				//Scroll the page till the Inventory Menu Items option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Purchases Option		
				driver.findElement(By.xpath("//span[contains(.,'Purchases')]")).click();
				Thread.sleep(5000);
				
		        //Click the Purchases Order Option		
				driver.findElement(By.xpath("//span[contains(.,'Purchase Templates')]")).click();
*/				
				//Get the URl
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"purchaseTemplates");

				Thread.sleep(5000);
				
				//Check Storage Locations page opened or not
				if(driver.findElement(By.xpath("//a[.='Purchase order templates']")).getText().equalsIgnoreCase("Purchase order templates"))
				{
					test.log(LogStatus.PASS, "Purchase order template page Successfully loaded");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order template page loaded Failed");
				}
				
				Thread.sleep(5000);
				
			}
			
			@Test(enabled=false,priority=4)
			public void Inventory_Purchase_Purchase_Template_add_new(WebDriver driver) throws Exception
			{
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				Thread.sleep(5000);
				//Click the Purchase Order Template
				driver.findElement(By.id("purchaseTemplates")).click();
				
				Thread.sleep(2000);
				//Check whether the new form is loaded or not
				if(driver.findElement(By.xpath("//span[contains(.,'New Purchase Order Template')]")).isDisplayed())
				{
					test.log(LogStatus.PASS, "New Purchase Order Template form loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "New Purchase Order Template form loaded Fail");
				}
				Thread.sleep(2000);
				
				//Clear the name field
				driver.findElement(By.id("name")).clear();
				//Enter the Required Name
				driver.findElement(By.id("name")).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				
				Thread.sleep(1500);
				//Click the Vendor Name
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div/a")).click();
				//Enter the Required Vendor
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div/div/div/input")).sendKeys("Sysco Food");
				//Press the Enter button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(2000);
				//Add Inventory Item
				driver.findElement(By.xpath("//i[@class='fa fa-plus-circle ng-binding']")).click();
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(10000);
				//Click the Inventory Item Option
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[1]/div/a")).click();
				//Enter the Required Inventory Option
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[1]/div/div/div/input")).click();
				//Press the Enter button
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[1]/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[3]/input")).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[3]/input")).sendKeys("2");
				
				Thread.sleep(5000);
				//Add Inventory Item
				driver.findElement(By.xpath("//i[@class='fa fa-plus-circle ng-binding']")).click();
				
				//Click the Close button
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[6]/a/i")).click();
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.END);
				Thread.sleep(2000);
				//Click the Place Order Option
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Purchase template Saved Successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order template saved Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order template saved Failed");
				}
				Thread.sleep(5000);
		
			}
			
			@Test(enabled=false,priority=5)
			public void Inventory_Purchase_Purchase_Template_edit(WebDriver driver) throws Exception
			{
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required Keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				
				Thread.sleep(1000);
				//Click the Edit button
				driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();
				
				Thread.sleep(2000);
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[3]/input")).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[3]/input")).sendKeys("3");
		
				
				Thread.sleep(2000);
				//Add Inventory Item
				driver.findElement(By.xpath("//i[@class='fa fa-plus-circle ng-binding']")).click();
				
				Thread.sleep(7500);
				//Click the Inventory Item Option
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]/div/a")).click();
				//Enter the Required Inventory Option
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]/div/div/div/input")).sendKeys("Inve Item");
				//Press the Enter button
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[1]/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Clear the Quantity
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[3]/input")).clear();
				//Enter the Required Quantity
				driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[3]/input")).sendKeys("2");
				
				Thread.sleep(3000);
				//Click the Update button
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Purchase template updated successfully!."))
				{
					test.log(LogStatus.PASS, "Purchase order template updated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order template updated Failed");
				}
				Thread.sleep(5000);
				
			}
			
			@Test(enabled=false,priority=7)
			public void Inventory_Purchase_Purchase_Template_verify_Purchase_Order_Button(WebDriver driver) throws Exception
			{
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required Keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				
				Thread.sleep(1000);
				//Click the purchase order button
				driver.findElement(By.xpath("//i[@class='fa fa-file-archive-o']")).click();
				
				Thread.sleep(8000);
				Thread.sleep(1500);
				//Clear the Expecting Date
				driver.findElement(By.name("expectingDate")).clear();
				//Enter the Required Date
				driver.findElement(By.name("expectingDate")).sendKeys("21-Dec-2020");
				
				Thread.sleep(5000);
				//Select the required placed order via option
				Select placeOrederVia = new Select(driver.findElement(By.name("placedVia")));
				placeOrederVia.selectByVisibleText("Email");
				
				driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
				driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				//Click the Place Order Option
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				
				Thread.sleep(2500);
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
				
		        //Click the Purchases Order Template Option		
				driver.findElement(By.xpath("//span[contains(.,'Purchase Templates')]")).click();
			}
			
			@Test(enabled=false,priority=6)
			public void Inventory_Purchase_Purchase_Template_delete(WebDriver driver) throws Exception
			{
				Thread.sleep(5000);
				//Clear the Search Field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				//Enter the required Keyword
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Inventory_Purchase_Order_Template_Name"));
				
				Thread.sleep(1000);
				//Click the delete purchase order button
				driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
				
				Thread.sleep(2000);
				//Click the Yes button
				driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("purchase template InActivated successfully"))
				{
					test.log(LogStatus.PASS, "Purchase order deleted Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order deleted Failed");
				}
				Thread.sleep(5000);
				
				//Click the Active button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
				
				Thread.sleep(3000);
				//Click the active symbol
				driver.findElement(By.xpath("//i[@class='fa fa-check']")).click();
				
				Thread.sleep(2000);
				//Click the Yes button
				driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
				
				Thread.sleep(2500);
				//Check whether the order is placed or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("purchase template Activated successfully"))
				{
					test.log(LogStatus.PASS, "Purchase order activated Sucessfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Purchase order activated Failed");
				}
				Thread.sleep(5000);
			}
			
			@Test(enabled=false,priority=8)
			public void Inventory_Purchase_Purchase_Template_verifyActive_InActiveButton_Sub_Recipe(WebDriver driver) throws Exception
			{
				Thread.sleep(8000);
				//Clear the search field
				driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				
				Thread.sleep(5000);
				//Click Active or In Active Button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
		
				Thread.sleep(5000);
				//Check the Inactive page is loaded or not
				if(driver.findElement(By.xpath("//i[@class='fa fa-check']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "We are in InActive Page");
					Thread.sleep(10000);
					//Click Active or In Active Button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
		
				}
				else
				{
					test.log(LogStatus.FAIL, "We are in Active Page but User Click the Inactive Page");
				}
				Thread.sleep(5000);
			}

}
