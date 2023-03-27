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

public class Update_Loyalty_Settings {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Update Loyalty Settings");

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
			Loyalty_page_Setting(driver);
			Loyalty_Settingpage(driver);
			Loyalty_SettingpageDisable(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=11)
	public void Loyalty_page_Setting(WebDriver driver) throws Exception
	{
		/*//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element1 = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[8]/a/span"));
		//Scroll the page till the Reason option present
		je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		//Wait for 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
	    //Click the Loyalty Option		
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[8]/a/span")).click();
		*/
		Thread.sleep(3000);	
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"loyalty");
			
		Thread.sleep(5000);
		//Check Loyalty page opened or not
		if(driver.findElement(By.xpath("//a[.='Loyalty']")).getText().equalsIgnoreCase("Loyalty"))

		{
			test.log(LogStatus.PASS, "Loyalty page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty page loaded Failed");
		}
		
		Thread.sleep(3000);
		
	}

	@Test(enabled=false,priority=12)
	public void Loyalty_Settingpage(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		
		//Check whether the Loyalty Account button is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='loyalty.enableLoyalty']")).isSelected()){}
		
		else
		{
			//Enable the Loyalty Account button Option
			driver.findElement(By.xpath("//input[@ng-model='loyalty.enableLoyalty']")).click();
		}
		
		
		Thread.sleep(3000);
		//Clear the Points For Customer Enrollment
		driver.findElement(By.name("customerEnrollment")).clear();
		//Enter the Customer Enrollment value
		driver.findElement(By.name("customerEnrollment")).sendKeys(Utility.getProperty("customerEnrollment_Value"));
		
		//Clear the Points for Every Visit
		driver.findElement(By.name("visit")).clear();
		//Enter the Points For Every Visit
		driver.findElement(By.name("visit")).sendKeys(Utility.getProperty("Loyalty_visitPrice"));
		
		Thread.sleep(2000);
		//Actions act = new Actions(driver);
		//act.moveToElement(driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[5]/div[1]/input"))).click().build().perform();
		
		try
		{
			if(driver.findElement(By.xpath("//label[.='Points for the amount' and @class='control-label col-sm-3 ng-binding']")).isDisplayed())
			{
				//Clear the Points for amount
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[1]/input")).clear();
				//Enter the Points for amount
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[1]/input")).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
				//Clear the Points for amount
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[2]/input")).clear();
				//Enter the Points for amount
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[2]/input")).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
			}
		}
		catch(Exception e)
		{
			if(driver.findElement(By.xpath("//label[.='Points for Cash Payment']")).isDisplayed())
			{
				//Clear the Points for the Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[5]/div[1]/input")).clear();
				//Enter the Points for the Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[5]/div[1]/input")).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
				//Clear the Points for the Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[5]/div[2]/input")).clear();
				//Enter the Points for the Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[5]/div[2]/input")).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
	
				//Clear the Points for the Non Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[1]/input")).clear();
				//Enter the Points for the Non Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[1]/input")).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
				
				//Clear the Points for the Non Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[2]/input")).clear();
				//Enter the Points for the Non Cash Payment
				driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[6]/div[2]/input")).sendKeys(Utility.getProperty("Loyalty_purchasePoint"));
			}
		}
		
		//Click Add Menu item Based
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[7]/div/div/div/a/i")).click();
		
		//Click the Menu Item Option
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[1]/div/a")).click();
		//Press the Enter Button
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Clear the Quantity Option
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[2]/div/input")).clear();
		//Enter the Quantity
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[2]/div/input")).sendKeys("5");
		
		//Clear the Points
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[3]/div/input")).clear();
		//Enter the Points
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[3]/div/input")).sendKeys("10");
		
		
		//Click the Add Button
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div/div[7]/div/div/div/a/i")).click();
		
		
		//Click the Close Button of newly added option
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[3]/ng-form/div/div[3]/div/a")).click();
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText("Yes")).click();
		
		//Click Add Category Based
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div/div/a")).click();
		
		//Click the Category Option
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[1]/div/a")).click();
		//Press the Enter Button
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Clear the Quantity Option
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[2]/div/input")).clear();
		//Enter the Quantity
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[2]/div/input")).sendKeys("5");
		
		//Clear the Points
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[3]/div/input")).clear();
		//Enter the Points
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[3]/div/input")).sendKeys("8");
		
		//Click the Add Button
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div/div/a")).click();
		
		Thread.sleep(5000);
		//Click the Close Button of newly added option
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[3]/ng-form/div/div[3]/div/a")).click();
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText("Yes")).click();
		
		Thread.sleep(2000);
		//Enter the Redeem points
		driver.findElement(By.xpath("//input[@name='exchangeRate']")).clear();
		//Enter the Redeem points
		driver.findElement(By.xpath("//input[@name='exchangeRate']")).sendKeys("2.00");

		Thread.sleep(2000);
		//Enter the Minimum points required for redemption option
		driver.findElement(By.xpath("//input[@name='minPoints']")).clear();
		//Enter the Minimum points required for redemption option
		driver.findElement(By.xpath("//input[@name='minPoints']")).sendKeys("2");

		
		Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(3000);
		//Check Loyalty updated or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("loyalty Saved successfully"))

		{
			test.log(LogStatus.PASS, "Loyalty updated Successfully ");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty updated Failed");
		}
		Thread.sleep(3000);
		}

	@Test(enabled=false,priority=13)
	public void Loyalty_SettingpageDisable(WebDriver driver) throws Exception
	{

		Thread.sleep(2000);

		//Check whether the Loyalty Account button is enabled or not
		if(!driver.findElement(By.xpath("//input[@ng-model='loyalty.enableLoyalty']")).isEnabled())
		{}
		else
		{
			//Enable the Loyalty Account button Option
			driver.findElement(By.xpath("//input[@ng-model='loyalty.enableLoyalty']")).click();
		}

		//Click the Close Button of newly added option - Menu Item
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[7]/div/div[2]/ng-form/div/div[3]/div/a")).click();
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText("Yes")).click();
		
		//Click the Close Button of newly added option - Category
		driver.findElement(By.xpath("//form[@name='loyaltyCreation']/div[1]/div[8]/div/div[2]/ng-form/div/div[3]/div/a")).click();	
		
		Thread.sleep(2000);
		//Click the Yes Button
		driver.findElement(By.partialLinkText("Yes")).click();
		
		Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(5000);
		//Check Loyalty updated or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("loyalty Saved successfully"))

		{
			test.log(LogStatus.PASS, "Loyalty updated Successfully for disabled option");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty updated Failed for disabled option");
		}
		
		Thread.sleep(3000);
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
