package epicList_Chrome_Xpath;

import java.util.List;
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


public class Inventory_Reports_Count_Sheet {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Count_Sheet");

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
			Inventory_Reports_Countsheet_Openpage(driver);
			Inventory_Reports_Count_Sheet_Inventory(driver);
			Inventory_Reports_Count_Inventory_Item(driver);
			Inventory_Reports_Count_SubRecipe(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=49) 
	public void Inventory_Reports_Countsheet_Openpage(WebDriver driver) throws Exception
	{
				/*//Click the Inventory option
				driver.findElement(By.xpath("//span[.='Inventory']")).click();
				// Create instance of Java script executor
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath("//span[contains(.,'Reports')]"));
				//Scroll the page till the Inventory Reports option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
		        //Click the Inventory Reports Option		
				driver.findElement(By.xpath("//span[contains(.,'Reports')]")).click();
				 //driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[4]/ul/li[11]/a/i")).click();
				*/
				Thread.sleep(5000);
				//Get the URl
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"countSheet");
				
				//Thread.sleep(3000);
				//Click the count sheet
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/scrollable-tabset/div/div/div/ul/li[4]/a/uib-tab-heading/span")).click();
				
				Thread.sleep(3000);
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath("//a[.='Count Sheet']")).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet page loaded Failed");
				}
				
				
				Thread.sleep(5000);
				
			}
			
	@Test(enabled=false,priority=50) 
	public void Inventory_Reports_Count_Sheet_Inventory(WebDriver driver) throws Exception
	{
				
			Thread.sleep(2000);
			//Select type as ALL 
			Select ALL = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
			ALL.selectByVisibleText("All"); 
		 	
			//Select Storage Location 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
			//Send the Storage Location
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Search_Inventory_Storage"));
			//Enter the Storage Location
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

			Thread.sleep(1000);
			//Click the Get Count Sheet button
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
			
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr/td[2]"));
				for(int i = 1; i <= rows.size(); i++)
				{
					//Get the Consumption Log Quantity value
					test.log(LogStatus.PASS,"In Count sheet " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " hand on quantity value is  "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText());
						
				}
					
				
			Thread.sleep(3000);
			//Check Count Sheet page opened or not
			if(driver.findElement(By.xpath("//span[.='Count Sheet']")).getText().equalsIgnoreCase("Count Sheet"))
			{
				test.log(LogStatus.PASS, "Count Sheet report loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Count Sheet report loaded Failed");
			}
			

				Thread.sleep(1000);
			}
			
	@Test(enabled=false,priority=51) 
	public void Inventory_Reports_Count_Inventory_Item(WebDriver driver) throws Exception
	{
				Thread.sleep(3000);
				//Select type as ALL 
				Select Inventory = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
				Inventory.selectByVisibleText("Inventory Item"); 
			 	
				//Select Storage Location 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
				//Send the Storage Location
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Search_Inventory_Category_Name"));
				//Enter the Storage Location
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

				//Select Category
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/a")).click();
				//Send the Category
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Ingredient_Category"));
				//Enter the Category
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

				//Select Inventory Item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/div/a")).click();
				//Send the Inventory Item
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Inventory_Ingredient_Category"));
				//Enter the Inventory Item
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[4]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

				Thread.sleep(1000);
				//Click the Get Count Sheet button
				driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
				
				Thread.sleep(3000);
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath("//span[.='Count Sheet']")).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet Inventory report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet Inventory report loaded Failed");
				}
				
				
			}
			
	@Test(enabled=false,priority=52) 
	public void Inventory_Reports_Count_SubRecipe(WebDriver driver) throws Exception
	{
				Thread.sleep(3000);
				//Select type as ALL 
				Select Sub = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/select")));
				Sub.selectByVisibleText("Sub Recipe"); 
			 	
				//Select Storage Location 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/a")).click();
				//Send the Storage Location
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("Search_Inventory_Category_Name"));
				//Enter the Storage Location
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);

				//Select Sub Recipe 
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/a")).click();
				//Send the Sub Recipe
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Utility.getProperty("subRecipe_name"));
				//Enter the Sub Recipe
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(1000);
				//Click the Get Count Sheet button
				driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
				
				Thread.sleep(3000);
				//Check Count Sheet page opened or not
				if(driver.findElement(By.xpath("//span[.='Count Sheet']")).getText().equalsIgnoreCase("Count Sheet"))
				{
					test.log(LogStatus.PASS, "Count Sheet SubRecipe report loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Count Sheet SubRecipe report loaded Failed");
				}
				
				Thread.sleep(4000);
			
			}

}
