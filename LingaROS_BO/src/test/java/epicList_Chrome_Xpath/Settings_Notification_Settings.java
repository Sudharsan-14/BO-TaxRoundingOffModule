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


public class Settings_Notification_Settings {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Notification_Settings");
	
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
			Notification_Settings_Method_open(driver);
			Notification_Settings_Method_edit(driver);
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
	
	@Test(enabled=false,priority=28)
	public void Notification_Settings_Method_open(WebDriver driver) throws Exception
		{
			
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"notificationSettings");
			
			Thread.sleep(3000);
			
			
			
			Thread.sleep(3000);
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		
			//Check weather the notification Settings page is loaded or not
			if(driver.findElement(By.xpath("//a[.='Notification Settings']")).getText().equalsIgnoreCase("Notification Settings"))
			{
				test.log(LogStatus.PASS, "Notification Settings page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Notification Settings page loaded fail");
			}

			Thread.sleep(3000);

		}	
		
	@Test(enabled=false,priority=29)
	public void Notification_Settings_Method_edit(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Clear the Discount amount
			driver.findElement(By.name("discountAmount")).clear();
			//Enter the required Discount amount
			driver.findElement(By.name("discountAmount")).sendKeys("125");
			
			Thread.sleep(2000);
			//Clear the Void amount
			driver.findElement(By.name("voidAmount")).clear();
			//Enter the required Void amount
			driver.findElement(By.name("voidAmount")).sendKeys("110");
			
			Thread.sleep(2000);
			//Clear the Max No Cash Drawer Accessed For No Sale 
			driver.findElement(By.name("noSale")).clear();
			//Enter the required Max No Cash Drawer Accessed For No Sale 
			driver.findElement(By.name("noSale")).sendKeys("2");
			
			Thread.sleep(3000);
			//Check weather the Discount mail check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.discount.enableEmail']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[1]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
			
			
			}
			
			//Check weather the Discount text check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.discount.enableText']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[2]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[4]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			
			
			//Check weather the Void mail check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.void.enableEmail']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[1]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			//Check weather the Void text check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.void.enableText']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[2]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[5]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			
			//Check weather the Max No Cash Drawer Accessed For No Sale mail check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.noSale.enableEmail']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[1]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			//Check weather the Max No Cash Drawer Accessed For No Sale text check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.noSale.enableText']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[2]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[6]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			
			//Check weather the Time Clock mail check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.timeClock.enableEmail']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[1]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			//Check weather the Time Clock text check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.timeClock.enableText']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[2]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[7]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			
			//Check weather the Daily KPI mail check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.dailyKPI.enableEmail']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[1]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			//Check weather the Daily KPI text check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.dailyKPI.enableText']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[2]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[8]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			
			//Check weather the End Of the Day Alert  mail check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.closeTheDay.enableEmail']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
					
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[1]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[1]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
				
			}
			
			//Check weather the End Of the Day Alert text check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.closeTheDay.enableText']")).isSelected())
			{

				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			else
			{

				Thread.sleep(1000);
				//Enable or disable the first email check box
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[2]/input")).click();
				
				Thread.sleep(1000);
				//CLear the first text are
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[2]/div/textarea")).clear();
				//Enter the required test mail
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[9]/div[2]/div/textarea")).sendKeys("+919626928016");
				
			}
			
			
			Thread.sleep(1000);
			//CLear the first text are
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[1]/div/textarea")).clear();
			//Enter the required test mail
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[1]/div/textarea")).sendKeys("sappanimuthub@benseron.com");
			
		
		
		//Check weather the End Of the Day Alert text check box is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='notificationSettings.waitlist.enableText']")).isSelected())
		{

			Thread.sleep(1000);
			//CLear the first text are
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[2]/div/textarea")).clear();
			//Enter the required test mail
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[2]/div/textarea")).sendKeys("+919626928016");
			
		}
		else
		{

			Thread.sleep(1000);
			//Enable or disable the first email check box
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[2]/input")).click();
			
			Thread.sleep(1000);
			//CLear the first text are
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[2]/div/textarea")).clear();
			//Enter the required test mail
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/form/div[1]/div/div[10]/div[2]/div/textarea")).sendKeys("+919626928016");
			
		}
			
			Thread.sleep(3000);
			//Click the update button
			driver.findElement(By.xpath("//span[.='Update']")).click();
			Thread.sleep(3000);
			
			//Check weather the notification setting is updated or not  Notification Settings updated successfully
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Notification Settings updated successfully"))
			{
				test.log(LogStatus.PASS, "Notification Settings updated successfully");
			}
			
			else{
				test.log(LogStatus.FAIL, "Notification Settings updated fail");
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}

}
