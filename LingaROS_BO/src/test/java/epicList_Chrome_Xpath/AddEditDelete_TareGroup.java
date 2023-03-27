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

public class AddEditDelete_TareGroup {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_TareGroup");

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
			Tare_Group_method_openTareGroupPage(driver);
			Tare_Group_method_refreshTareGroupPage(driver);
			Tare_Group_method_addTareGroup(driver);
			Tare_Group_method_editTareGroup(driver);
			Tare_Group_method_deleteTareGroup(driver);
			Tare_Group_method_cancelButton(driver);
			Tare_Group_method_verifyActive_InActiveButton(driver);
			Tare_Group_method_sameName(driver);
			Thread.sleep(1500);
		}

		@Test(enabled = false, priority=61)
		public void Tare_Group_method_openTareGroupPage(WebDriver driver) throws Exception
		{
	
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"tare");
			Thread.sleep(5000);
			//Check Tare Group page opened or not
			if(driver.findElement(By.cssSelector("h3.ng-binding")).getText().equalsIgnoreCase("Tare Group"))
			{
				test.log(LogStatus.PASS, "Tare Group page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Group page loaded Failed");
			}
		}
		
		@Test(enabled = false,priority=62)
		public void Tare_Group_method_refreshTareGroupPage(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a/i")).click();
			Thread.sleep(5000);
			//Check Tare Group page opened or not
			if(driver.findElement(By.cssSelector("h3.ng-binding")).getText().equalsIgnoreCase("Tare Group"))
			{
				test.log(LogStatus.PASS, "Tare Group page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Group page loaded Failed");
			}
		}
		
		@Test(enabled = false,priority=64)
		public void Tare_Group_method_addTareGroup(WebDriver driver) throws Exception
		{
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			Thread.sleep(3000);
			//Click the Add or new Tare Group button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check whether the new Tare Group form loaded or not
			if(driver.findElement(By.xpath("//span[.='New TareGroup']")).getText().equalsIgnoreCase("New TareGroup"))
			{
				test.log(LogStatus.PASS, "New TareGroup form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New TareGroup form loaded Failed");
			}
			
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("TareGroupNameWithWeight"));
			Thread.sleep(2000);
			
			//Enable the Default button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[2]/div/label/span")).click();
			
			//Click the Add Weight button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[1]/a/i")).click();
			Thread.sleep(2000);
			
			//Enter the Seq.Number
			driver.findElement(By.name("sequenceNumber")).sendKeys("3");
			
			//Enter the name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[2]/div/input")).sendKeys("Weight");
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			//Click the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[3]/div/a/span")).click();
			//Enter the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[3]/div/div/div/input")).sendKeys("kg");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the amount field
			driver.findElement(By.name("quantity")).clear();
			//Enter the Required Amount
			driver.findElement(By.name("quantity")).sendKeys("20000");
			
			//Click the Save button
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Check whether the new up charges form loaded or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tare Saved Successfully"))
			{
				test.log(LogStatus.PASS, "New Tare Saved Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tare saved Failed");
			}
			
		
		}
	
		@Test(enabled = false,priority=65)
		public void Tare_Group_method_editTareGroup(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Edit icon
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			Thread.sleep(3000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).clear();
			Thread.sleep(5000);
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
	
			Thread.sleep(2000);
			//Click the Add weight symbol
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[1]/a/i")).click();
			Thread.sleep(1000);
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div/ng-form/div/div[4]/div/a/i"));
			//Click the close button of new weight button
			int row = rows.size() + 2;
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div["+row+"]/ng-form/div/div[4]/div/a/i")).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,300)", "");
			Thread.sleep(3000);
			//Click the update
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check whether the Product Item updated successfully or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tare Updated Successfully"))
			{
				test.log(LogStatus.PASS, "Tare updated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Updated Failed");
			}
			Thread.sleep(3000);
	
		}
		
		@Test(enabled = false,priority=66)
		public void Tare_Group_method_deleteTareGroup(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
	
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check the menu item deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tare group inactivated successfully"))
			{
				test.log(LogStatus.PASS, "Tare Group is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare Group is deleted Failed");
			}
			Thread.sleep(7000);
		
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			
			//Click the Yes button in the popup
			Thread.sleep(1500);
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(5000);
			
			//Check the item activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tare group activated successfully."))
			{
				test.log(LogStatus.PASS, "Tare group is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tare group is activated Failed");
			}
		
			Thread.sleep(5000);
		}
		
		@Test(enabled = false,priority=67)
		public void Tare_Group_method_cancelButton(WebDriver driver) throws Exception
		{
			Thread.sleep(6000);
			
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			
			//Click the Add or new Tare Group button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).click();
			Thread.sleep(2000);
			//Check whether the new Tare Group form loaded or not
			if(driver.findElement(By.xpath("//span[.='New TareGroup']")).getText().equalsIgnoreCase("New TareGroup"))
			{
				test.log(LogStatus.PASS, "New TareGroup form loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New TareGroup form loaded Failed");
			}
			
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
			Thread.sleep(2000);
	
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			Thread.sleep(2000);
			//Check whether the new discount canceled or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TareGroup Canceled Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "TareGroup Canceled Failed");
			}
	
			Thread.sleep(5000);
		}
		
		@Test(enabled = false,priority=68)
		public void Tare_Group_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		
			Thread.sleep(5000);
			//Click Active or In Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
	
			Thread.sleep(5000);
			//Check the New Display Group form is loaded or not
			if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
				Thread.sleep(10000);
				//Click Active or In Active Button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
	
			}
			else if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger")).isDisplayed())
			{
				test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled = false,priority=69)
		public void Tare_Group_method_sameName(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click the Add or new Tare Group button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).click();
			Thread.sleep(5000);
			
			
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("TareGroupNameWithWeight")+"1");
			Thread.sleep(2000);
			
			//Enable the Default button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[2]/div/label/span")).click();
			
			//Click the Add Weight button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[1]/a/i")).click();
			Thread.sleep(2000);
			
			//Enter the Seq.Number
			driver.findElement(By.name("sequenceNumber")).sendKeys("3");
			
			//Enter the name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[2]/div/input")).sendKeys("Weight");
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			//Click the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[3]/div/a/span")).click();
			//Enter the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[3]/div/div/div/input")).sendKeys("kg");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/div/div[3]/ng-form/div/div[3]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the amount field
			driver.findElement(By.name("quantity")).clear();
			//Enter the Required Amount
			driver.findElement(By.name("quantity")).sendKeys("20000");
			
			//Click the Save button
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check whether the new up charges form loaded or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Validation Error(s)"))
			{
				test.log(LogStatus.PASS, "New Tare Saved Failed and show error message");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tare saved Successfully");
			}
			
		
		}

}
