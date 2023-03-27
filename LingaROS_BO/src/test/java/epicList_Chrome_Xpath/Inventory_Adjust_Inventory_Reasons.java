package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class Inventory_Adjust_Inventory_Reasons {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Adjust_Inventory_Reasons");

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
			Adjust_Inventory_Reasons_openpage(driver);
			Adjust_Inventory_Reasons_refresh(driver);
			Adjust_Inventory_Reasons_add_PAge(driver);
			Adjust_Inventory_Reasons_edit(driver);
			Adjust_Inventory_Reasons_delete(driver);
			Adjust_Inventory_Reasons_closeButton(driver);
			Adjust_Inventory_Reasons_verifyActive_InActiveButton(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=23)
		public void Adjust_Inventory_Reasons_openpage (WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			
			//Get the URl
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"adjustInventoryItemReasons");
					
			//Check In Adjust Inventory Reasons page opened or not
			if(driver.findElement(By.xpath("//a[.='Adjust Inventory Reasons']")).getText().equalsIgnoreCase("Adjust Inventory Reasons"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory Reasons page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory Reasons page loaded Failed");
			}
			
			Thread.sleep(5000);
			
		}
		
		@Test(enabled=false,priority=24)
		public void Adjust_Inventory_Reasons_refresh(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(3000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a/i")).click();
			Thread.sleep(3000);
			//Check In Adjust Inventory Reasons page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Reasons"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory Reasons page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory Reasons page refreshed Failed");
			}
			Thread.sleep(3000);
	
		}
	
		@Test(enabled=false,priority=26)
		public void Adjust_Inventory_Reasons_add_PAge(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Click on the Add  Adjust Inventory Reasons  option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).click();
			Thread.sleep(3000);
			
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier"));
							
			Thread.sleep(2000);
			//Select the Increase button
			Select Type1 = new Select(driver.findElement(By.xpath("//select[@ng-model='reason.reasonType']")));
			Type1.selectByVisibleText("Increase");
			
			//Click the Save button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
			
			//Check whether the new In House Units saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Reason saved successfully!."))
			{
				test.log(LogStatus.PASS, "Reason saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason saved Failed");
			}
	
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=27)
		public void Adjust_Inventory_Reasons_edit(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier"));
			//Click the Edit icon
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier")+"1");
			Thread.sleep(3000);
			
			Thread.sleep(2000);
			//Select the Increase button
			Select Type1 = new Select(driver.findElement(By.xpath("//select[@ng-model='reason.reasonType']")));
			Type1.selectByVisibleText("Decrease");
			
			
			//Click the update
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
	
			//Check whether the In House Units updated successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Reason updated successfully."))
			{
				test.log(LogStatus.PASS, "Reason updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason updated Failed");
			}
	
			Thread.sleep(5000);
	
		}
		
		@Test(enabled=false,priority=28)
		public void Adjust_Inventory_Reasons_delete(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier")+"1");
			
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Check the In House Units deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Reason InActivated Successfully"))
			{
				test.log(LogStatus.PASS, "Reason InActivated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason InActivated Failed");
			}
	
			Thread.sleep(5000);
		
	
			//Click the Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			Thread.sleep(3000);
			
			//Check the In House Units activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Reason Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Reason Activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Reason Activated Failed");
			}
			
			Thread.sleep(5000);
		}
	
		@Test(enabled=false,priority=29)
		public void Adjust_Inventory_Reasons_closeButton(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click on the Add  Adjust Inventory Reasons  option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).click();
										
			Thread.sleep(3000);
			//Check whether the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Reason']")).getText().equalsIgnoreCase("New Reason"))
			{
				test.log(LogStatus.PASS, "New Reason loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Reason form loaded Failed");
			}
	
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the Name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("New_InventoryReason_name_ForModifier"));
			
			Thread.sleep(3000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			
			Thread.sleep(5000);
			//Check whether the new New Reason canceled or not
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "New Reason Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Reason Canceled Failed");
			}
	
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=30)
		public void Adjust_Inventory_Reasons_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
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
				test.log(LogStatus.FAIL, "Deleted In House Units are not here, we are in Active Page");
			}
			Thread.sleep(5000);
		}

}
