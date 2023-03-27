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


public class AddEditDelete_Serving_Size_Level {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Serving Size Level");

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
			Serving_Size_Level_method_openServingSizeLevel(driver);
			Serving_Size_Level_method_refreshServingSizeLevel_Page(driver);
			Serving_Size_Level_method_add_ServingSizeLevel(driver);
			Serving_Size_Level_method_edit_ServingSizeLevel(driver);
			Serving_Size_Level_method_delete_ServingSizeLevel(driver);
			Serving_Size_Level_method_cancelCoursing(driver);
			Serving_Size_Level_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=41)
		public void Serving_Size_Level_method_openServingSizeLevel(WebDriver driver) throws Exception
		{
	
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"servingSizeLevel");
			Thread.sleep(5000);
			//Check Serving Size Level page opened or not
			if(driver.findElement(By.xpath("//a[.='Serving Size Levels']")).getText().equalsIgnoreCase("Serving size levels"))
			{
				test.log(LogStatus.PASS, "Serving Size Level page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=42)
		public void Serving_Size_Level_method_refreshServingSizeLevel_Page(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]/i")).click();
			Thread.sleep(5000);
			
			//Check Coursing page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Serving Size Levels"))
			{
				test.log(LogStatus.PASS, "Serving Size Level page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level page loaded Failed");
			}
			Thread.sleep(3000);
		}
	
		@Test(enabled=false,priority=44)
		public void Serving_Size_Level_method_add_ServingSizeLevel(WebDriver driver) throws Exception
		{
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			//Click the Add serving Size Level button
			driver.findElement(By.xpath("//button[@id='servingSizeLevel']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Serving Size Level form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Serving Size Level']")).getText().equalsIgnoreCase("New Serving Size Level"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath("//input[@name='name']")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Utility.getProperty("ServingSizeLevel_Name"));
			
			//Clear the description field
			driver.findElement(By.name("description")).clear();
			//Enter the required description field
			driver.findElement(By.name("description")).sendKeys("New SSL Description");
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath("//button[@id='saveSSl']")).click();
			
			Thread.sleep(2000);
			//Check weather the new SSL is saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Serving size level saved successfully"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level saved fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=45)
		public void Serving_Size_Level_method_edit_ServingSizeLevel(WebDriver driver) throws Exception
		{
	
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"1");
			Thread.sleep(8000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(8000);
			//Click the Edit button
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(8000);
			//Clear the name field
			driver.findElement(By.xpath("//input[@name='name']")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"1");
	
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveSSl']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Serving Size Level is updated or not
			if(driver.findElement(By.xpath("//span[.='Serving-size level updated successfully']")).getText().equalsIgnoreCase("Serving-size level updated successfully"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level is updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level is updated fail");
			}
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=46)
		public void Serving_Size_Level_method_delete_ServingSizeLevel(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"11");
			Thread.sleep(8000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(8000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			Thread.sleep(2000);
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check the Serving Size Level deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Serving-size level inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Serving Size Level is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Serving Size Level is deleted Failed");
			}
			Thread.sleep(5000);
			
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check the Serving Size Level activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Serving-size level activated successfully"))
			{
				test.log(LogStatus.PASS, "Serving Size Level is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=47)
		public void Serving_Size_Level_method_cancelCoursing(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("ServingSizeLevel_Name")+"11");
			Thread.sleep(8000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(8000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			Thread.sleep(2000);
			//Check the serving size level form is closed or not
			if(driver.findElement(By.xpath("//button[@id='servingSizeLevel']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Serving Size Level page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Serving Size Level page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=48)
		public void Serving_Size_Level_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector("i.fa.fa-check")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Inactive page is displayed after click the Active button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inactive page is not displayed after click the Active button");
			}
			
			Thread.sleep(3000);
			//Click the InActive button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector("i.fa.fa-trash-o")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Active page is displayed after click the Inactive button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Active page is not displayed after click the Inactive button");
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
