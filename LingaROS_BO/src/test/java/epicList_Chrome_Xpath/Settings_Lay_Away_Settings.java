package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class Settings_Lay_Away_Settings {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Lay_Away_Settings");
	
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
			open_Lay_Away_Settings(driver);
			edit_Lay_Away_Settings_Percentage_Percentage(driver);
			edit_Lay_Away_Settings_Percentage_Amount(driver);
			edit_Lay_Away_Settings_Amount_Percentage(driver);
			edit_Lay_Away_Settings_Amount_Amount(driver);
			Thread.sleep(1500);
		}

	@Test(priority=2,enabled=false)
	public void open_Lay_Away_Settings(WebDriver driver) throws Exception
	{
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Store']"));
		//Scroll the page till the Store option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Store']")).click();
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
				
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		Thread.sleep(5000);
		
		//Click the rightside button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/scrollable-tabset/div/button[2]")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/scrollable-tabset/div/button[2]")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/scrollable-tabset/div/button[2]")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/scrollable-tabset/div/button[2]")).click();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/scrollable-tabset/div/button[2]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element1 = driver.findElement(By.xpath("//span[contains(.,'Lay Away Settings')]"));
		//Scroll the page till the Store option present
		je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		
		Thread.sleep(3000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(2000);
		//Click the Lay Away Settings Option
		driver.findElement(By.xpath("//span[contains(.,'Lay Away Settings')]")).click();
		Thread.sleep(3000);*/
		
		Thread.sleep(3000);
		//Enter the Users URL
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"layAwaySettings");
		Thread.sleep(3000);
		
		//Check whether the Lay Away Settings page is loaded or not
		if(driver.findElement(By.xpath("//a[.='Lay Away Settings']")).getText().equalsIgnoreCase("Lay Away Settings"))
		{
			test.log(LogStatus.PASS, "Lay Away Settings page loaded successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Lay Away Settings page loaded fail");
		}

		Thread.sleep(5000);

	}	
	
	@Test(priority=3,enabled=false)
	public void edit_Lay_Away_Settings_Percentage_Percentage(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Check weather the Enable Lay Away Settings check box is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).isSelected()){}
		else
		{
			Thread.sleep(1000);
			//Enable the Enable Lay Away Settings check box
			driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Select the required minimum deposit
		Select min_Deposit = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div/select")));
		min_Deposit.selectByVisibleText("Percentage");
		
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInPercentage']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInPercentage']")).sendKeys("500");
		
		//Select the required minimum deposit
		Select min_Cancellation = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[4]/div/select")));
		min_Cancellation.selectByVisibleText("Percentage");
	
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInPercentage']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInPercentage']")).sendKeys("500");

		Thread.sleep(2000);
		//Check weather the Net sale Radio button is enabled or not
		if(driver.findElement(By.xpath("//input[@value='NetSale']")).isSelected())
		{
			Thread.sleep(1000);
			//Enable the Depsit Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='Deposit']")).click();
			Thread.sleep(1000);
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Net sale Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='NetSale']")).click();
			Thread.sleep(1000);
		}
		try
		{
			Thread.sleep(2000);
			//Click the Update button
			driver.findElement(By.xpath("//span[.='Update']")).click();
		}
		catch(Exception e)
		{
			Thread.sleep(2000);
			//Click the Update button
			driver.findElement(By.xpath("//span[.='Save']")).click();
		}
		
		Thread.sleep(2500);
		//Check whether the Lay away settings is updated or not
		if(driver.findElement(By.xpath("//div[@class='content']/div/div/div/div/span/span")).getText().equalsIgnoreCase("Lay Away Settings updated successfully"))
		{
			test.log(LogStatus.PASS, "Lay Away Settings page updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Lay Away Settings page updated fail");
		}
		Thread.sleep(3000);
	}
		
	@Test(priority=4,enabled=false)
	public void edit_Lay_Away_Settings_Percentage_Amount(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Check weather the Enable Lay Away Settings check box is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).isSelected()){}
		else
		{
			Thread.sleep(1000);
			//Enable the Enable Lay Away Settings check box
			driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Select the required minimum deposit
		Select min_Deposit = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div/select")));
		min_Deposit.selectByVisibleText("Percentage");
		
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInPercentage']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInPercentage']")).sendKeys("500");
		
		//Select the required minimum deposit
		Select min_Cancellation = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[4]/div/select")));
		min_Cancellation.selectByVisibleText("Amount");
	
		Thread.sleep(2000);
		//Clear the Amount option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInAmount']")).clear();
		//Enter the Amount
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInAmount']")).sendKeys("500");

		Thread.sleep(2000);
		//Check weather the Net sale Radio button is enabled or not
		if(driver.findElement(By.xpath("//input[@value='NetSale']")).isSelected())
		{
			Thread.sleep(1000);
			//Enable the Depsit Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='Deposit']")).click();
			Thread.sleep(1000);
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Net sale Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='NetSale']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(2500);
		//Check whether the Lay away settings is updated or not
		if(driver.findElement(By.xpath("//div[@class='content']/div/div/div/div/span/span")).getText().equalsIgnoreCase("Lay Away Settings updated successfully"))
		{
			test.log(LogStatus.PASS, "Lay Away Settings page updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Lay Away Settings page updated fail");
		}
		Thread.sleep(3000);
	}
		
	@Test(priority=5,enabled=false)
	public void edit_Lay_Away_Settings_Amount_Percentage(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Check weather the Enable Lay Away Settings check box is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).isSelected()){}
		else
		{
			Thread.sleep(1000);
			//Enable the Enable Lay Away Settings check box
			driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Select the required minimum deposit
		Select min_Deposit = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div/select")));
		min_Deposit.selectByVisibleText("Amount");
		
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInAmount']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInAmount']")).sendKeys("500");
		
		//Select the required minimum deposit
		Select min_Cancellation = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[4]/div/select")));
		min_Cancellation.selectByVisibleText("Percentage");
	
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInPercentage']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInPercentage']")).sendKeys("500");

		Thread.sleep(2000);
		//Check weather the Net sale Radio button is enabled or not
		if(driver.findElement(By.xpath("//input[@value='NetSale']")).isSelected())
		{
			Thread.sleep(1000);
			//Enable the Depsit Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='Deposit']")).click();
			Thread.sleep(1000);
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Net sale Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='NetSale']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(2500);
		//Check whether the Lay away settings is updated or not
		if(driver.findElement(By.xpath("//div[@class='content']/div/div/div/div/span/span")).getText().equalsIgnoreCase("Lay Away Settings updated successfully"))
		{
			test.log(LogStatus.PASS, "Lay Away Settings page updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Lay Away Settings page updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(priority=6,enabled=false)
	public void edit_Lay_Away_Settings_Amount_Amount(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		//Check weather the Enable Lay Away Settings check box is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).isSelected()){}
		else
		{
			Thread.sleep(1000);
			//Enable the Enable Lay Away Settings check box
			driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.isLayAwaySettingsEnable']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Select the required minimum deposit
		Select min_Deposit = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[2]/div/select")));
		min_Deposit.selectByVisibleText("Amount");
		
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInAmount']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.minimumDeposit.depositInAmount']")).sendKeys("500");
		
		//Select the required minimum deposit
		Select min_Cancellation = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[2]/form/div[4]/div/select")));
		min_Cancellation.selectByVisibleText("Amount");
	
		Thread.sleep(2000);
		//Clear the Percentage option
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInAmount']")).clear();
		//Enter the Percentage
		driver.findElement(By.xpath("//input[@ng-model='layAwaySettings.cancellationCharge.cancellationInAmount']")).sendKeys("500");

		Thread.sleep(2000);
		//Check weather the Net sale Radio button is enabled or not
		if(driver.findElement(By.xpath("//input[@value='NetSale']")).isSelected())
		{
			Thread.sleep(1000);
			//Enable the Depsit Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='Deposit']")).click();
			Thread.sleep(1000);
		}
		else
		{
			Thread.sleep(1000);
			//Enable the Net sale Radio button is enabled or not
			driver.findElement(By.xpath("//input[@value='NetSale']")).click();
			Thread.sleep(1000);
		}
		
		Thread.sleep(2000);
		//Click the Update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(2500);
		//Check whether the Lay away settings is updated or not
		if(driver.findElement(By.xpath("//div[@class='content']/div/div/div/div/span/span")).getText().equalsIgnoreCase("Lay Away Settings updated successfully"))
		{
			test.log(LogStatus.PASS, "Lay Away Settings page updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Lay Away Settings page updated fail");
		}
		Thread.sleep(3000);
	}
		
}
