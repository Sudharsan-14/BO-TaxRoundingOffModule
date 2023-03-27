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


public class Settings_Store_Hours {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Store_Hours");
	
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
			Store_Hours_Setting_Method_pageopen(driver);
			StoreHours_Setting_Method_process(driver);
			StoreHours_DayPart(driver);
			Thread.sleep(1500);
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

	@Test(enabled=false,priority=7)
	public void Store_Hours_Setting_Method_pageopen(WebDriver driver) throws Exception
	{

		
		//Enter the URl
		Thread.sleep(3000);
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"timings");
		
		Thread.sleep(3000);

		
		//Check weather the Store Hours page is loaded or not
		if(driver.findElement(By.xpath("//a[.='Store Hours']")).getText().equalsIgnoreCase("Store Hours"))
		{
			test.log(LogStatus.PASS, "Store Hours page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Hours page loaded fail");
		}

		Thread.sleep(3000);

	}	
	
	@Test(enabled=false,priority=8)
	public void StoreHours_Setting_Method_process(WebDriver driver) throws Exception
	{

		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.HOME);
		html.sendKeys(Keys.HOME);
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Thread.sleep(2000);
		//Check whether the "Get the Confirmation Before Close The Day" check box is selected or not
		if(driver.findElement(By.xpath("//label[contains(.,'Close The Day')]/../input")).isSelected())
		{}
		else
		{
			driver.findElement(By.xpath("//label[contains(.,'Close The Day')]/../input")).click();
			
			//Click the Yes button
			driver.findElement(By.partialLinkText("Yes")).click();
		}
		
		
		Thread.sleep(2000);
		//Click Reset button
		driver.findElement(By.xpath("//a[.='Reset']")).click();
		Thread.sleep(2000);
		
		
		// THE BELOW FOR SUNDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
									     
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		
		
		
		// THE BELOW FOR MONDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
										
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		
		
		
		
		// THE BELOW FOR TUESDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Options
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[5]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		
		
		
		// THE BELOW FOR WEDNESDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[6]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		
		
		
		// THE BELOW FOR THURSDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[7]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		
		
		// THE BELOW FOR FRIDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		
			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[8]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		
			}
		}
		
		// THE BELOW FOR SATURDAY
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//form[@name='storeCreation']/div[9]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		
				//form[@name='storeCreation']
			}
		}
		
			Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
	}

	@Test(enabled=false,priority=9)
	public void StoreHours_DayPart(WebDriver driver) throws Exception
	{
		//Enter the URl
		Thread.sleep(3000);
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"timings");
		
		Thread.sleep(3000);
/*		//Check Store Information page opened or not
		if(driver.findElement(By.xpath("//a[.='Store Information']")).getText().equalsIgnoreCase("Store Information"))
		{
			test.log(LogStatus.PASS, "Store Information page loaded Successfully for Day part");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Information page loaded Failed for Day part");
		}
		 
		Thread.sleep(3000);*/
		
		//Click the Store Hours Option
		//driver.findElement(By.xpath("//span[@id='timing']")).click();
		Thread.sleep(3000);
		
/*		//Check weather the Store Hours page is loaded or not
		if(driver.findElement(By.xpath("//a[.='Store Hours']")).getText().equalsIgnoreCase("Store Hours"))
		{
			test.log(LogStatus.PASS, "Store Hours page loaded successfully for Day part");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Hours page loaded fail for Day part");
		}*/

		
		Thread.sleep(2000);
		//Click Day Part option 
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/span/scrollable-tabset/div/div/div/ul/li[2]/a/uib-tab-heading/span")).click();
			
		try		
		{
			Thread.sleep(4000);
			//Click remove button
			if(driver.findElement(By.cssSelector("i.fa.fa-trash-o")).isDisplayed())
			{
				//Click the Delete button of Second Shift
				driver.findElement(By.xpath("//div/div[2]/div/div/div/a/i[@class='fa fa-trash-o']")).click();
				Thread.sleep(2000);
				//Click yes button
				driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			}
			else
			{}
		}
		catch (Exception e)		
		{}	
		
		try
		{
			Thread.sleep(3000);
			//Click ADD button to create meals type to update
			driver.findElement(By.cssSelector("button.btn.btn-medium.ng-binding")).click();
			Thread.sleep(3000);
			//Clear the day part meal box
			driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/label/div/input")).clear();
			//Enter the day part meal box
			driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/label/div/input")).sendKeys("Food");
			Thread.sleep(3000);
			//Click the Up Hour button of Start time
			driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[1]/div/table/tbody/tr[1]/td[1]/a/span")).click();
			Thread.sleep(2000);
			//Click the Up minute button of Start time
			driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[1]/div/table/tbody/tr[1]/td[3]/a/span")).click();
			Thread.sleep(3000);							
			//Click the Up Hour button of End time
			driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[1]/a/span")).click();
			
			//Click the Up minute button of End Time
			driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/a/span")).click();
			Thread.sleep(2000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
		
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
											
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//form[@name='dayPartCreation']/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
			
		
				}
				Thread.sleep(3000);
				//Click the update button
				driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
				Thread.sleep(3000);
				//Check Store Information page opened or not
				if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Shift Saved Successfully"))
				{
					test.log(LogStatus.PASS, "Shift Saved Successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Shift Saved Failed");
				}
				Thread.sleep(4000);
			}
		
		}
		catch(Exception e)
		{}
	}
}
