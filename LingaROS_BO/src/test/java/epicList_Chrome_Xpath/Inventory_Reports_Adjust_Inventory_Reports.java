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


public class Inventory_Reports_Adjust_Inventory_Reports {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Reports_Adjust_Inventory_Report");

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
			Inventory_Reports_Adjust_Inventory_Report_Openpage(driver);
			Inventory_Reports_Adjust_Inventory_Report(driver);
			Inventory_Reports_Adjust_Inventory_SingleReport(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=57) 
	public void Inventory_Reports_Adjust_Inventory_Report_Openpage(WebDriver driver) throws Exception
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
				driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"compareInventory");
				
				Thread.sleep(3000);
				//Click the Adjust Inventory
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/scrollable-tabset/div/div/div/ul/li[6]/a/uib-tab-heading/span")).click();
				
				Thread.sleep(4000);
				//Check Adjust Inventory page opened or not
				if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
				{
					test.log(LogStatus.PASS, "Adjust Inventoryt page loaded Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Adjust Inventory page loaded Failed");
				}
			}
			
	@Test(enabled=false,priority=58) 
	public void Inventory_Reports_Adjust_Inventory_Report(WebDriver driver) throws Exception
	{
			//Click the Reason type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul")).click();
			
			Thread.sleep(3000);	
			//Select Time period option 
			 Select DateRangetype = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[2]/div/div/select")));
			 DateRangetype.selectByVisibleText("Date Range"); 
		 	
			Thread.sleep(3000);	
			//Select the From Date range 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[1]")).clear();
			Thread.sleep(1000);		
			//Enter the From Date range 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[1]")).sendKeys(Utility.getReportProperty("ConsumptionLog_FromDate"));
			
			Thread.sleep(2000);	
			//Select the To Date range 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[2]")).clear();
			Thread.sleep(1000);		
			//Enter the To Date range 
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[3]/div/div/input[2]")).sendKeys(Utility.getReportProperty("ConsumptionLog_ToDate"));
			
			Thread.sleep(2000);	
			 //Click the Get Adjust Inventory Report button
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
			
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    	Thread.sleep(1000);
		    }
		    
			/*List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div/div/table/tbody/tr[2]/td[1]"));
			for(int i = 1; i <= rows.size(); i++)
			{
			//Get print on the reason type(type wise)
			 //tr[@style='background-color:#b4dde9']
				//Get the Adjust Inventory 
				test.log(LogStatus.PASS,"In Adjust Inventory " +driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText() +" "+ driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[6]")).getText()+ " hand on quantity value is  "+" "+  driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText());
					
			}*/
			
			
		Thread.sleep(2000);
		//Check Adjust Inventory page opened or not
		if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
		{
			test.log(LogStatus.PASS, "Adjust Inventory reason report loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Adjust Inventory report reason report loaded Failed");
		}

			}
			
	@Test(enabled=false,priority=59) 
	public void Inventory_Reports_Adjust_Inventory_SingleReport(WebDriver driver) throws Exception
	{
				//Scroll the web page
			    for(int i=1; i <= 25; i++)
			    {
			    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
			    	Thread.sleep(1000);
			    }
				Thread.sleep(3000);
				
				//Click the Reason type
				//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul")).click();
			
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul/li/a"));
				for(int i = 1; i <= rows.size(); i++)
				{
					//Click remove all reason
					Thread.sleep(5000);
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul/li[1]/a")).click();
					}
				
				//Click the reason type
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul")).click();
			Thread.sleep(2000);	

			//Send the reason type
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul/li/input")).sendKeys("waste");
		   // Thread.sleep(2000);	

			//Enter the reason type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/ng-include/div/form/div[1]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		    Thread.sleep(2000);	

			 //Click the Get Adjust Inventory Report button
			driver.findElement(By.cssSelector("button.btn.btn-small.btn-success.ng-binding")).click();
			
			Thread.sleep(3000);
			//Check Adjust Inventory page opened or not
			if(driver.findElement(By.xpath("//a[.='Adjust Inventory']")).getText().equalsIgnoreCase("Adjust Inventory"))
			{
				test.log(LogStatus.PASS, "Adjust Inventory single type reason report loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Adjust Inventory single type  reason report loaded Failed");
			}
			Thread.sleep(1000);
			}

}
