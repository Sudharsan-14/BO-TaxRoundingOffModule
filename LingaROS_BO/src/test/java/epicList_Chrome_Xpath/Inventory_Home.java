package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Inventory_Home {

	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Inventory_Home");

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
			Inventory_Home_openpage(driver);
			Inventory_Home_refresh_Page(driver);
			Inventory_Home_Verify_Reports(driver);
			Thread.sleep(1500);
		}
		
	   @Test(enabled=false,priority=2)
		public void Inventory_Home_openpage(WebDriver driver) throws Exception
		{
			Thread.sleep(15000);		
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"inventoryHome");
			Thread.sleep(4000);
			//Check Inventory Home page opened or not
			if(driver.findElement(By.xpath("//a[.='Inventory Home']")).getText().equalsIgnoreCase("Inventory Home"))
			{
				test.log(LogStatus.PASS, "Inventory Home page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Home page loaded Failed");
			}
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
		
	   @Test(enabled=false,priority=3)
		public void Inventory_Home_refresh_Page(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(4000);
			//Click the refresh button
			driver.findElement(By.xpath("//button[@class='btn btn-md btn-border time-filter btn-refresh']")).click();
			Thread.sleep(4000);
			//Check Inventory Home page opened or not
			if(driver.findElement(By.xpath("//span[.='Live Updates']")).getText().equalsIgnoreCase("Live Updates"))
			{
				test.log(LogStatus.PASS, "Inventory Home page refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inventory Home page refreshed Failed");
			}
		}
			
		@Test(enabled=false,priority=4)
		public void Inventory_Home_Verify_Reports(WebDriver driver) throws Exception
		{
			Thread.sleep(4000);
			//Check the Total Value On Hand value
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[1]")).getText().equalsIgnoreCase("Total Value On Hand"))
			{
				test.log(LogStatus.PASS, "Total Value On Hand report is available");
				
				test.log(LogStatus.INFO, "Total Value On Hand amount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div[1]/div/div[2]/div[2]/span")).getText());
				
			}
		
			else
			{
				test.log(LogStatus.FAIL, "Total Value On Hand report is not available");
			}
			
			Thread.sleep(4000);
			//Check the COGS value
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div/div[1]")).getText().equalsIgnoreCase("COGS"))
			{
				test.log(LogStatus.PASS, "COGS report is available");
				
				test.log(LogStatus.INFO, "Total COGS value is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/span")).getText());
				
			}
		
			else
			{
				test.log(LogStatus.FAIL, "COGS report is not available");
			}
			
			Thread.sleep(4000);
			/*//Check the LOW STOCKS value
			if(driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[1]/h4/a/span/span[2]"))!=null)
			{
				test.log(LogStatus.PASS, "LOW STOCKS report is available");
				
				test.log(LogStatus.INFO, "Total LOW STOCKS value is : "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[1]/h4/a/span/span[2]")).getText());
		*/
			
			//Check the LOW STOCKS value
			if(driver.findElement(By.xpath(("//span[contains(.,'LOW STOCKS')]"))).isDisplayed())
			{
				test.log(LogStatus.PASS, "LOW STOCKS report is available");
						
				test.log(LogStatus.INFO, "Total LOW STOCKS value is : "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[1]/h4/a/span/span[2]")).getText());
						
				driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[1]/h4/a/span/span[1]")).click();
				
				List<WebElement> rows = driver.findElements(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr"));
				
				for(int i = 1; i <= rows.size(); i++)
				{
					
				test.log(LogStatus.INFO, driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[1]")).getText()+" available quantity is "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[3]")).getText()+" but the minimum quantity is "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[1]/div[2]/div/div[2]/div[1]/div/table/tbody/tr["+i+"]/td[2]")).getText());
				
				}
			}
		
			else
			{
				test.log(LogStatus.FAIL, "LOW STOCKS report is not available");
			}
			
			
			Thread.sleep(4000);
			//Check the Non Consume Orders value
			if(driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[3]/div[1]/h4/a/span/span[2]"))!=null)
			{
				test.log(LogStatus.PASS, "Non Consume Orders report is available");
				
				test.log(LogStatus.INFO, "Total Non Consume Order is : "+driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[3]/div[1]/h4/a/span/span[2]")).getText());
				
				Thread.sleep(3000);
				//Click the Non Consume Orders option 
				driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/uib-accordion/div/div[3]/div[1]/h4/a/span/span[1]")).click();
				
				Thread.sleep(3000);
				
				if(driver.findElement(By.xpath("//td[contains(.,'No Records Found')]")).getText().equalsIgnoreCase("No Records Found"))
				{
					test.log(LogStatus.FAIL, "No Records Found for Consume order");
				}
				
				else
				{
					Thread.sleep(2000);
					//Click the Consume Order Option
					driver.findElement(By.xpath("//button[@ng-click='consumeOrder()']")).click();
					Thread.sleep(2000);
				
						Thread.sleep(3000);
					//Check Consume order finished or not
					if(driver.findElement(By.xpath("//sFpan[.='Consume finished successfully']")).getText().equalsIgnoreCase("Consume finished successfully"))
					{
						test.log(LogStatus.PASS, "Consume order finished Successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Consume order finished Failed");
					}
				}
			
			}
			else
			{
				test.log(LogStatus.FAIL, "Non Consume Orders is not available");
				
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}
		
}
