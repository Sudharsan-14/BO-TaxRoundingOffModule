package epicList_Chrome;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			//Close the Browser_Account_Level_User
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
			if(driver.findElement(By.xpath("//h2[.='Sign in']")).getText().equalsIgnoreCase("Sign in"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
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
			StoreHours_hour_update(driver);
			StoreHours_hour_Shift(driver);
			//StoreHours_DayPart(driver);
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newTimings");
		
		Thread.sleep(5000);
		try
		{
		//Check weather the Store Hours page is loaded or not
		if(driver.findElement(By.xpath("//h3[.='Store Business Hours']")).getText().equalsIgnoreCase("Store Business Hours"))
		{
			test.log(LogStatus.PASS, "Store Hours page loaded successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Hours page loaded fail");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		}
		catch(Exception e)
		{
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.FAIL,test.addScreenCapture(s));
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
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", ""); 
		//Check whether the "Get the Confirmation Before Close The Day" check box is selected or not
		if(driver.findElement(By.xpath("//input[@ng-model='store.doNotForceCloseDay']")).isEnabled())
		{}
		else
		{
			driver.findElement(By.xpath("//input[@ng-model='store.doNotForceCloseDay']")).click();
			
			//Click the Yes button
			driver.findElement(By.partialLinkText("Yes")).click();
		}
		
		
		Thread.sleep(2000);
		//Click Reset button
		//driver.findElement(By.xpath("//a[.='Reset']")).click();
		Thread.sleep(2000);
		JavascriptExecutor h = (JavascriptExecutor)driver;
		h.executeScript("window.scrollBy(0,300)", ""); 
		//driver.findElement(By.xpath("//span[@class='fa fa-plus']")).click();
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(@class,'right-line')]/div/input"));
		int i;
		for (i=1; i<=list.size();i++) {
			//System.out.println(list.size());
			WebElement check = driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div["+i+"]/input"));
			if(check.isSelected()) {
				check.click();
		}}
		List<WebElement> lists = driver.findElements(By.xpath("//*[contains(@class,'nopadding')]/div/input"));
		for (i=1; i<=lists.size();i++) {
			//System.out.println(lists.size());
			WebElement checks = driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div["+i+"]/input"));
			if(checks.isSelected()) {
				checks.click();
			}}
		Thread.sleep(1000);
		// THE BELOW FOR SUNDAY
		driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[3]/input")).click();
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
									     
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		//driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[3]/input")).click();
		
		
		// THE BELOW FOR MONDAY 
		driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[1]/input")).click();
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
									     
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		//driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[1]/input")).click();
		
		
		
		// THE BELOW FOR TUESDAY
		driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[2]/input")).click();
		//Click the Up Hour button of Start time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
									     
		//Click the Up minute button of Start time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
										
		//Click the Up Hour button of End time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
		
		//Click the Up minute button of End Time
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}


		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
										
			Thread.sleep(2000);
			//Check it is AM or PM in the End Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
		

			}
		}
		//driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[2]/input")).click();
		
		
		// THE BELOW FOR WEDNESDAY
		driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[3]/input")).click();
		//Click the Up Hour button of Start time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
											     
				//Click the Up minute button of Start time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
												
				//Click the Up Hour button of End time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
				
				//Click the Up minute button of End Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
				
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Check it is AM or PM in the End Time Option
					if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
					{
						Thread.sleep(2000);
						
						//Click the AM or PM option in the End Time
						driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
				

					}


				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
												
					Thread.sleep(2000);
					//Check it is AM or PM in the End Time Option
					if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
					{
						Thread.sleep(2000);
						
						//Click the AM or PM option in the End Time
						driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
				

					}
				}
	    	//driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[3]/input")).click();
		
		
		// THE BELOW FOR THURSDAY
	    	driver.findElement(By.xpath("//*[contains(@class,'right-line')]/div[4]/input")).click();
	    	//Click the Up Hour button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
										     
			//Click the Up minute button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
											
			//Click the Up Hour button of End time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
			
			//Click the Up minute button of End Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}


			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
											
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}
			}
			//driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[3]/input")).click();
			
			
			// THE BELOW FOR MONDAY
			/*driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[1]/input")).click();
			//Click the Up Hour button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
										     
			//Click the Up minute button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
											
			//Click the Up Hour button of End time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
			
			//Click the Up minute button of End Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}


			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
											
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}
			}*/
			
		
		// THE BELOW FOR FRIDAY
			driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[1]/input")).click();
			//Click the Up Hour button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
										     
			//Click the Up minute button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
											
			//Click the Up Hour button of End time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
			
			//Click the Up minute button of End Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}


			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
											
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}
			}
			//driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[1]/input")).click();
			
			
			
		   //THE BELOW FOR SATURDAY
			driver.findElement(By.xpath("//*[contains(@class,'nopadding')]/div[2]/input")).click();
			//Click the Up Hour button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
										     
			//Click the Up minute button of Start time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
											
			//Click the Up Hour button of End time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[1]/a")).click();
			
			//Click the Up minute button of End Time
			driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")).click();
			
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}


			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
											
				Thread.sleep(2000);
				//Check it is AM or PM in the End Time Option
				if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[4]/div/div/div/div[3]/div[2]/div[2]/div/div/div/table/tbody/tr[2]/td[6]/button")).click();
			

				}
			}
		
			Thread.sleep(3000);
			JavascriptExecutor jses = (JavascriptExecutor)driver;
			jses.executeScript("window.scrollBy(0,-350)", ""); 
			//click the Add Hours icon
			driver.findElement(By.xpath("//span[@class='fa fa-plus']")).click();
			Thread.sleep(3000);
			jses.executeScript("window.scrollBy(0,50)", ""); 
			Thread.sleep(1000);
			//Click the Delete Shift button
			driver.findElement(By.xpath("//div[@align='center']")).click();
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
	
		//if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Got the desired output while Get the Confirmation Before Close The Day check box is selected");
		}
		else
		{
			test.log(LogStatus.FAIL, "Failed");
		} 
		Thread.sleep(4000);
		
	}
		//if(driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[3]/div/div[2]/div/span/input")).isSelected())
		//{
		//driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		//Click the Yes button
		//driver.findElement(By.partialLinkText("Yes")).click();
		//}
		//else
		//{}	
	
	@Test(enabled=false,priority=9)
	public void StoreHours_hour_update(WebDriver driver) throws Exception
	{
		JavascriptExecutor jses = (JavascriptExecutor)driver;
	    jses.executeScript("window.scrollBy(0,100)", ""); 
	    Thread.sleep(3000);
	   /* try {
	    	WebElement en = driver.findElement(By.xpath("//input[@ng-model='store.doNotForceCloseDay']"));
		if(en.isEnabled())
		{
			en.click();
		}}
		catch (Exception e)  {}*/
	    //jses.executeScript("window.scrollBy(0,100)", ""); 
	    Thread.sleep(1000);
		/*
		 * //Click the update button
		 * driver.findElement(By.xpath("//span[.='Update']")).click();
		 * Thread.sleep(3000);
		 * if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).isDisplayed
		 * ()) { test.log(LogStatus.PASS, "Store Timings Updated Successfully"); } else
		 * { test.log(LogStatus.FAIL, "Store Timings Updated Failed"); }
		 * Thread.sleep(3000); jses.executeScript("window.scrollBy(0,50)", "");
		 * driver.findElement(By.xpath("//span[.='Update']")).click();
		 * Thread.sleep(3000);
		 * if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).isDisplayed
		 * ()) { test.log(LogStatus.PASS, "Store Timings Updated Successfully"); } else
		 * { test.log(LogStatus.FAIL, "Store Timings Updated Failed"); }
		 */
			driver.findElement(By.xpath("//input[@ng-model='store.closeAllActiveTills']")).click();
			driver.findElement(By.xpath("//input[@ng-model='store.closeAllActiveEmployess']")).click();
			driver.findElement(By.xpath("//input[@ng-model='store.closeAllActiveChecks']")).click();
			 Thread.sleep(2000);
				//Click the update button
					driver.findElement(By.xpath("//span[.='Update']")).click();
					//JavascriptExecutor jsesf = (JavascriptExecutor)driver;
					Thread.sleep(3000);
				    jses.executeScript("window.scrollBy(0,300)", ""); 
				    Thread.sleep(4000);
					//if(driver.findElement(By.xpath("//input[@ng-model='store.doNotForceCloseDay']")).isEnabled())
					//{}
					//else
					//{
					/*
					 * driver.findElement(By.xpath("//input[@ng-model='store.doNotForceCloseDay']"))
					 * .click(); driver.findElement(By.linkText("Yes")).click(); //}
					 * jses.executeScript("window.scrollBy(0,100)", ""); Thread.sleep(1000); //Click
					 * the update button driver.findElement(By.xpath("//span[.='Update']")).click();
					 * Thread.sleep(2000);
					 */
	}
	
	@Test(enabled=false,priority=10)
	public void StoreHours_hour_Shift(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div/div[2]/div[2]/div/label[3]/input")).click();
		Thread.sleep(3000);  
		driver.findElement(By.xpath("//span[@class='fa fa-plus']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body//div[@ng-app='bevo']//div[@id='main-container']//div//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]//input[1]")).sendKeys("Bf");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-model='day.fromTimeHour']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-model='day.fromTimeHour']/table/tbody/tr[1]/td[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@ng-model='day.toTimeHour']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement up=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
		WebDriverWait wait=new WebDriverWait(driver,50);
		//Check whether the Store Hour Saved successfully or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Shift Saved Successfully"))
		{
			test.log(LogStatus.PASS, "Shift Saved Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Shift Saved Failed");
		} 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='fa fa-plus']")).click();
		driver.findElement(By.xpath("//div[@class='box-content nopadding']//div[2]//div[1]//div[1]//div[1]//div[1]//div[2]//input[1]")).sendKeys("lu");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@ng-model='day.fromTimeHour']/table/tbody/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath("//div[@ng-model='day.fromTimeHour']/table/tbody/tr[1]/td[3]/a")).click();
		driver.findElement(By.xpath("//div[@ng-model='day.toTimeHour']/table/tbody/tr[1]/td[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body//div[@ng-app='bevo']//div[@id='main-container']//div//div//div//div//div//div//div//div//div//div//div[2]//div[1]//div[1]//div[3]//div[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Yes")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("//body//div[@ng-app='bevo']//div[@id='main-container']//div//div//div//div//div//div//div//div//div//div//div[1]//div[1]//div[1]//div[3]//div[2]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Yes")).click();
	    try
	    {
	    WebElement up1=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
		WebDriverWait wait1=new WebDriverWait(driver,30);
		//Check whether the Store Hour Saved successfully or not
		if(wait1.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Shift Removed Successfully"))
		{
			test.log(LogStatus.PASS, "Shift Removed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Shift Removed Failed");
		} 
	    }
	    catch(Exception l) {}
	}
	@Test(enabled=false,priority=11)
	public void StoreHours_DayPart(WebDriver driver) throws Exception
	{
		//Enter the URl
		//Thread.sleep(3000);
		//driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newTimings");
		
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



	
