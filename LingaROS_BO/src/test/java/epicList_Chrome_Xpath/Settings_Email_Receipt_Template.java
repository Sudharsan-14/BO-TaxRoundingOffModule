package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Settings_Email_Receipt_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Email_Receipt_Template");
	
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
			Email_Receipt_Template_method_open_Email_Receipt_Template(driver);
			Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
			Verify_Email_Receipt_Template_method_update_Email_Receipt_Template(driver);
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
	
	@Test(priority=8,enabled = false)
	public void Email_Receipt_Template_method_open_Email_Receipt_Template(WebDriver driver) throws Exception
	{
		/*//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"emailTemplate");
		Thread.sleep(5000);
		//Check Email Receipt Template page opened or not
		if(driver.findElement(By.xpath("//a[.='Email Receipt Template']")).getText().equalsIgnoreCase("Email Receipt Template"))
		{
			test.log(LogStatus.PASS, "Email Receipt Template page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Receipt Template page loaded Failed");
		}
		Thread.sleep(3000);
	}	
		
	@Test(priority=9,enabled = false)
	public void Email_Receipt_Template_method_update_Email_Receipt_Template(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Select the required Banner Background using Act Class
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//p[.='Banner Background']/..//div[7]"))).click().build().perform();

		Thread.sleep(2000);
			
		//Check whether the Store Image is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreImage']")).isSelected())
		{
			
		}
		else
		{
			//Enable the Store Image option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStoreImage']/../span")).click();
		}
		
		//Check whether the Customer Feedback field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerFeedBack']")).isSelected()){
		
		}
		else
		{
			//Enable the Customer Feedback Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerFeedBack']/../span")).click();
		}
		
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']")).isSelected()){
		}
		else
		{
			//Enable the table name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']/../span")).click();
		}
		
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSeatNumber']")).isSelected()){
		}
		else
		{
			//Enable the seat number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showSeatNumber']/../span")).click();
		}
		
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']")).isSelected()){
		}
		else
		{
			//Enable the Server name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']/../span")).click();
		}
		
		//Check whether the Check number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']")).isSelected())
		{
		}
		else
		{
			//Enable the Check number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']/../span")).click();
					
		}
		
		//Check whether the Date field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDate']")).isSelected()){
		}
		else
		{
			//Enable the Date Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showDate']/../span")).click();
		}
			
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected()){
		}
		else
		{
			//Enable the customer name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']/../span")).click();
		}
		
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']")).isSelected()){
		}
		else
		{
			//Enable the Service Type Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']/../span")).click();
		}		
		
		//Check whether the Order Summary field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showOrderSummary']")).isSelected()){
		}
		else
		{
			//Enable the Order Summary Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showOrderSummary']/../span")).click();
		}
		
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLocalName']")).isSelected()){
		}
		else
		{
			//Enable the  Show Secondary menu name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showLocalName']/../span")).click();
		}
		
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPrice']")).isSelected()){
		}
		else
		{
			//Enable the  Roll Out Modifier Price To Menu Option
			driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPrice']/../span")).click();
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}Thread.sleep(3000);
		
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceMenu']")).isSelected()){
		}
		else
		{
			//Enable the Exclude zero price menu item Option
			driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceMenu']/../span")).click();
		}
		
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceModifier']")).isSelected()){
		}
		else
		{
			//Enable the Exclude Zero Price Modifier item Option
			driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceModifier']/../span")).click();
		}
		
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTip']")).isSelected()){
		}
		else
		{
			//Enable the Tip Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTip']/../span")).click();
		}
		
		
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTaxSummary']")).isSelected()){
		}
		else
		{
			//Enable the Tax Summary Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTaxSummary']/../span")).click();
		}
		
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}Thread.sleep(3000);
		
		Thread.sleep(2000);
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTenderDetails']")).isSelected()){
		}
		else
		{
			//Enable the Tender Details Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTenderDetails']/../span")).click();
		}
				
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSignature']")).isSelected()){
		}
		else
		{
			//Enable the Signature Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showSignature']/../span")).click();
		}

		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showBarcode']")).isSelected()){
		}
		else
		{
			//Enable the Barcode Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showBarcode']/../span")).click();
		}
		
		Thread.sleep(1000);
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']")).isSelected()){
		}
		else
		{
			//Enable the Address Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']/../span")).click();
		}
		
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']")).isSelected()){
		}
		else
		{
			//Enable the Email Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']/../span")).click();
		}
		
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']")).isSelected()){
		}
		else
		{
			//Enable the Phone Number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']/../span")).click();
		}
	
		
		//Clear the free text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.freeText']")).clear();
		//Enter the required text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.freeText']")).sendKeys("Have a nice day, Visit us again!");
		
		
		//Check whether the Social Icons field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.showSocialIcons']")).isSelected())
		{
			//Check whether the Face book Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.facebook']")).isSelected())
			{
				
			}
			
			else
			{
				//Enable the Face book Icons Option
				driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.facebook']/../span")).click();
			}
			
			//Check whether the Twitter Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.twitter']")).isSelected())
			{
				
			}
			
			else
			{
				//Enable the Twitter Icons Option
				driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.twitter']/../span")).click();
			}
			
			//Check whether the Linked In Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.linkedin']")).isSelected())
			{
				
			}
			
			else
			{
				//Enable the Linked In Icons Option
				driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.linkedin']/../span")).click();
			}
		}
		
		else
		{
			//Enable the Social Icons Option
			driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.showSocialIcons']/../span")).click();
		
			//Check whether the Face book Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.facebook']")).isSelected())
			{
				
			}
			
			else
			{
				//Enable the Face book Icons Option
				driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.facebook']/../span")).click();
			}
			
			//Check whether the Twitter Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.twitter']")).isSelected())
			{
				
			}
			
			else
			{
				//Enable the Twitter Icons Option
				driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.twitter']/../span")).click();
			}
			
			 //Scroll the web page
		    for(int i=1; i <= 5; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		    	Thread.sleep(1000);
		    }
		    
			//Check whether the Linked In Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.linkedin']")).isSelected())
			{
				
			}
			
			else
			{
				//Enable the Linked In Icons Option
				driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.linkedin']/../span")).click();
			}
		}
		
		Thread.sleep(2000);
		//Check whether the Show Linga POS Promotion "Powered by Linga POS" field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLingaPromotion']")).isSelected()){
		
		}
		else
		{
			//Enable the Show Linga POS Promotion "Powered by Linga POS" Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showLingaPromotion']/../span")).click();
		}									
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath("//button[@id='updateEmailTemplate']")).click();
		Thread.sleep(4000);
		//Check weather the new denomination form saved or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Email template saved successfully"))
		{
			test.log(LogStatus.PASS, "Email Receipt template saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Receipt template saved fail");
		}
		Thread.sleep(4000);
		
	
	}

	@Test(priority=10,enabled = false)
	public void Verify_Email_Receipt_Template_method_update_Email_Receipt_Template(WebDriver driver) throws Exception
	{

		Thread.sleep(2000);
			
		//Check whether the Store Image is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreImage']")).isSelected())
		{
			test.log(LogStatus.PASS, "Store Image Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Image Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Customer Feedback field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerFeedBack']")).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Feedback Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Feedback Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Table Name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Table Name Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSeatNumber']")).isSelected())
		{
			test.log(LogStatus.PASS, "Seat Number Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Seat Number Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Server Name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Server Name Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Check number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']")).isSelected())
		{
			test.log(LogStatus.PASS, "Check number Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Check number Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Date field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDate']")).isSelected())
		{
			test.log(LogStatus.PASS, "Date Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date Field is Disabled in the Email Receipt Template");
		}
			
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Customer name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer name Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']")).isSelected())
		{
			test.log(LogStatus.PASS, "Service Type Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Type Field is Disabled in the Email Receipt Template");
		}		
		
		//Check whether the Order Summary field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showOrderSummary']")).isSelected())
		{
			test.log(LogStatus.PASS, "Order Summary Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Order Summary Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLocalName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Show Secondary menu name Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Show Secondary menu name Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPrice']")).isSelected())
		{
			test.log(LogStatus.PASS, "Roll Out Modifier Price To Menu Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier Price To Menu Field is Disabled in the Email Receipt Template");
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceMenu']")).isSelected())
		{
			test.log(LogStatus.PASS, "Exclude zero price menu item Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude zero price menu item Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceModifier']")).isSelected())
		{
			test.log(LogStatus.PASS, "Exclude Zero Price Modifier Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude Zero Price Modifier Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTip']")).isSelected())
		{
			test.log(LogStatus.PASS, "Tip Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Field is Disabled in the Email Receipt Template");
		}
		
		
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTaxSummary']")).isSelected())
		{
			test.log(LogStatus.PASS, "Tax Summary Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Summary Field is Disabled in the Email Receipt Template");
		}
		
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTenderDetails']")).isSelected())
		{
			test.log(LogStatus.PASS, "Tender Details Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tender Details Field is Disabled in the Email Receipt Template");
		}
				
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSignature']")).isSelected())
		{
			test.log(LogStatus.PASS, "Signature Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Signature Field is Disabled in the Email Receipt Template");
		}

		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showBarcode']")).isSelected())
		{
			test.log(LogStatus.PASS, "Barcode Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Barcode Field is Disabled in the Email Receipt Template");
		}
		
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']")).isSelected())
		{
			test.log(LogStatus.PASS, "Address Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Address Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']")).isSelected())
		{
			test.log(LogStatus.PASS, "Email Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email Field is Disabled in the Email Receipt Template");
		}
		
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']")).isSelected())
		{
			test.log(LogStatus.PASS, "Phone Number Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number Field is Disabled in the Email Receipt Template");
		}
	
		
		//Clear the free text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.freeText']")).clear();
		//Enter the required text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.freeText']")).sendKeys("Have a nice day, Visit us again!");
		
		
		//Check whether the Social Icons field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.showSocialIcons']")).isSelected())
		{
			test.log(LogStatus.PASS, "Social Icons Field is Enabled in the Email Receipt Template");
			//Check whether the Face book Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.facebook']")).isSelected())
			{
				test.log(LogStatus.PASS, "Face book Icons Field is Enabled in the Email Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Face book Icons Field is Disabled in the Email Receipt Template");
			}
			
			//Check whether the Twitter Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.twitter']")).isSelected())
			{
				test.log(LogStatus.PASS, "Twitter Icons Field is Enabled in the Email Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Twitter Icons Field is Disabled in the Email Receipt Template");
			}
			
			//Check whether the Linked In Icons field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.socialIcons.linkedin']")).isSelected())
			{
				test.log(LogStatus.PASS, "Linked In Icons Field is Enabled in the Email Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Linked In Icons Field is Disabled in the Email Receipt Template");
			}
		}
		
		else
		{
			test.log(LogStatus.FAIL, "Social Icons Field is Disabled in the Email Receipt Template");
		}
		
		
		//Check whether the Show Linga POS Promotion "Powered by Linga POS" field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLingaPromotion']")).isSelected())
		{
			test.log(LogStatus.PASS, "Show Linga POS Promotion 'Powered by Linga POS' Field is Enabled in the Email Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Show Linga POS Promotion 'Powered by Linga POS' Field is Disabled in the Email Receipt Template");
		}									

		Thread.sleep(5000);
		
	}

}
