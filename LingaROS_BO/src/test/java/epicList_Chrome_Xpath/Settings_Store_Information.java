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

public class Settings_Store_Information {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Store_Information");
	
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
			Store_Information_openpage(driver);
			Store_Information_editpage(driver);
			Thread.sleep(1500);
		}
		
		@Test(enabled=false,priority=2)
		public void Store_Information_openpage(WebDriver driver) throws Exception
		{
	/*		Thread.sleep(13000);
			//Click the Settings option
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Store']"));
			//Scroll the page till the Store option present
			je.executeScript("arguments[0].scrollIntoView(true);",element);Thread.sleep(3000);
	        //Click the Store Option		
			driver.findElement(By.xpath("//span[.='Store']")).click();*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"edit");
			Thread.sleep(5000);
			//Check Store Information page opened or not
			if(driver.findElement(By.xpath("//a[.='Store Information']")).getText().equalsIgnoreCase("Store Information"))
			{
				test.log(LogStatus.PASS, "Store Information page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Store Information page loaded Failed");
			}
			
			Thread.sleep(4000);

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
		public void Store_Information_editpage(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.name("name")).clear();
			//Enter the name
			driver.findElement(By.name("name")).sendKeys(Utility.getProperty("Settings_Store_Information_Name"));
			
			Thread.sleep(2000);
			//Clear the phone number field
			driver.findElement(By.name("phoneNumber")).clear();
			//Enter the required phone number
			driver.findElement(By.name("phoneNumber")).sendKeys(Utility.getProperty("Settings_Store_Information_Phone"));
			
			
			Thread.sleep(2000);
			//Clear the email Id field
			driver.findElement(By.name("emailId")).clear();
			//Enter the required mail ID
			driver.findElement(By.name("emailId")).sendKeys(Utility.getProperty("Settings_Store_Information_Email"));

			Thread.sleep(2000);
			//Clear the address1 filed
			driver.findElement(By.name("addressLine1")).clear();
			//Enter the address1
			driver.findElement(By.name("addressLine1")).sendKeys(Utility.getProperty("Settings_Store_Information_Address1"));
			
			Thread.sleep(2000);
			//Clear the address2 field
			driver.findElement(By.name("addressLine2")).clear();
			//Enter the address2
			driver.findElement(By.name("addressLine2")).sendKeys(Utility.getProperty("Settings_Store_Information_Address2"));
			
			Thread.sleep(1000);
			//Clear the City field
			driver.findElement(By.name("city")).clear();
			//Enter the City
			driver.findElement(By.name("city")).sendKeys(Utility.getProperty("Settings_Store_Information_City"));
			
			Thread.sleep(1000);
			//Clear the State field
			driver.findElement(By.name("state")).clear();
			//Enter the State
			driver.findElement(By.name("state")).sendKeys(Utility.getProperty("Settings_Store_Information_State"));
			
			Thread.sleep(1000);
			//Clear the Zip Code Field
			driver.findElement(By.name("zipCode")).clear();
			//Enter the Zipcode
			driver.findElement(By.name("zipCode")).sendKeys(Utility.getProperty("Settings_Store_Information_Zipcode"));
			
			Thread.sleep(1000);driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);Thread.sleep(1000);
			//Click the Time Zone Option
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[1]/div/div/a")).click();
			//Enter the required Time Zone
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Utility.getProperty("Settings_Store_Information_TimeZone"));
			//Click the Enter button
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Close button
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[2]/div/div/ul/li/a")).click();
			
			Thread.sleep(2000);
			//Click the Languages Option
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[2]/div/div/ul")).click();
			//Clear the Languages
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[2]/div/div/ul/li/input")).clear();
			//Enter the required Languages
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[2]/div/div/ul/li/input")).sendKeys(Utility.getProperty("Settings_Store_Information_Language"));
			//Press the Enter button
			driver.findElement(By.xpath("//form[@name='storeCreation']/div[2]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			//Scroll the web page
		    for(int i=1; i <= 10; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		    	Thread.sleep(1000);
		    }
			
			
			Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.id("storeImage")).sendKeys(Utility.getProperty("Settings_Store_Information_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath("//a[.='Remove']")).click();
			
			Thread.sleep(2000);
			//Choose the required image
			driver.findElement(By.id("storeImage")).sendKeys(Utility.getProperty("Settings_Store_Information_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Get my location option
			driver.findElement(By.xpath("//button[.='Get my location']")).click();
			
			Thread.sleep(6000);
			//Click the Save button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			Thread.sleep(5000);
			//Check weather the new denomination form saved or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Store updated successfully"))
			{
				test.log(LogStatus.PASS, "Store updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Store updated fail");
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}

}
