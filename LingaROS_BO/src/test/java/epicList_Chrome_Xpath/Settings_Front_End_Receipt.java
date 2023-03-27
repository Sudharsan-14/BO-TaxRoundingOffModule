package epicList_Chrome_Xpath;


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

public class Settings_Front_End_Receipt {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Front_End_Receipt");
	
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
			Front_End_Receipt_method_open_Front_End_Receipt(driver);
			Front_End_Receipt_method_update_FrontEndReceipt_Template(driver);
			Front_End_Receipt_method_verifyUpdatedDetails(driver);
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
	
	@Test(priority=2,enabled = false)
	public void Front_End_Receipt_method_open_Front_End_Receipt(WebDriver driver) throws Exception
	{
		Thread.sleep(15000);
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();Thread.sleep(3000);
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); Thread.sleep(3000);
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();
*/		
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"frontEndReceiptTemplate");
		Thread.sleep(5000);
		//Check Front End Receipt page opened or not
		if(driver.findElement(By.xpath("//a[.='Front End Receipt Template']")).getText().equalsIgnoreCase("Front End Receipt Template"))
		{
			test.log(LogStatus.PASS, "Front End Receipt Template page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Front End Receipt Template page loaded Failed");
		}
		
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		//Thread.sleep(3000);watchTutorial();Thread.sleep(3000);
	}	
		
	@Test(priority=3,enabled = false)
	public void Front_End_Receipt_method_update_FrontEndReceipt_Template(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);

		//Select the Font Size
    	Select fontSize = new Select(driver.findElement(By.xpath("//select[@ng-model='settings.fontSize']")));
		fontSize.selectByVisibleText("Small");
		Thread.sleep(3000);
			
		
		//Check whether the Store Logo is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreLogo']")).isSelected())
		{
			//Select the required store logo
			driver.findElement(By.id("storeImage")).sendKeys(Utility.getProperty("Store_Logo_Path"));
		}
		else
		{
			//Enable the Store Logo option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStoreLogo']/../span")).click();
			Thread.sleep(2000);
			//Select the required store logo
			driver.findElement(By.id("storeImage")).sendKeys(Utility.getProperty("Store_Logo_Path"));
		}
		
		
		
		//Check whether the store name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreName']")).isSelected()){}
		
		else
		{
			//Enable the Store name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStoreName']/../span")).click();
		}
		
		
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']")).isSelected()){}
		
		else
		{
			//Enable the table name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']/../span")).click();
		}
		
		
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSeatNumber']")).isSelected()){}
		
		else
		{
			//Enable the seat number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showSeatNumber']/../span")).click();
		}
		
		
		//Check whether the Check Open Server field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']")).isSelected()){}
		
		else
		{
			//Enable the Check Open Server Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']/../span")).click();
		}
		
		
		//Check whether the Check Close Server field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.closeServerName']")).isSelected()){}
		
		else
		{
			//Enable the Check Close Server Option
			driver.findElement(By.xpath("//input[@ng-model='settings.closeServerName']/../span")).click();
		}
		Thread.sleep(3000);
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		//Check whether the Check number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']")).isSelected())
		{			
			Select position = new Select(driver.findElement(By.xpath("//label[.='Position']/../select")));
			position.selectByVisibleText("P#1");
			
			Thread.sleep(2000);
			
			Select fontSize1 = new Select(driver.findElement(By.xpath("//label[.='Font Size']/../select")));
			fontSize1.selectByVisibleText("2 Height");
			Thread.sleep(2000);
		}
		
		else
		{
			Thread.sleep(2000);
			//Enable the Check number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']/../span")).click();

			Select position = new Select(driver.findElement(By.xpath("//label[.='Position']/../select")));
			position.selectByVisibleText("P#1");
			
			Thread.sleep(2000);
			
			Select fontSize2 = new Select(driver.findElement(By.xpath("//label[.='Font Size']/../select")));
			fontSize2.selectByVisibleText("2 Height");
			Thread.sleep(2000);
			
			
		}
		
		
		//Check whether the Open Date and Time field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDate']")).isSelected()){}
		
		else
		{
			//Enable the Open Date and Time Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showDate']/../span")).click();
		}
		
		
		
		//Check whether the Close Date and Time field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.closeDate']")).isSelected()){}
		
		else
		{
			//Enable the Close Date and Time Option
			driver.findElement(By.xpath("//input[@ng-model='settings.closeDate']/../span")).click();
		}
		
		
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected()){}
		
		else
		{
			//Enable the customer name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']/../span")).click();
		}
		
		
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']")).isSelected()){}
		
		else
		{
			//Enable the Service Type Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']/../span")).click();
		}
		
		
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLocalName']")).isSelected()){}
		
		else
		{
			//Enable the  Show Secondary menu name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showLocalName']/../span")).click();
		}
		
		
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPrice']")).isSelected()){}
		
		else
		{
			//Enable the  Roll Out Modifier Price To Menu Option
			driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPrice']/../span")).click();
		}
		
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Roll Out Modifier Price & Quantity To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPriceAndQuantity']")).isSelected()){}
		
		else
		{
			//Enable the  Roll Out Modifier Price & Quantity To Menu Option
			driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPriceAndQuantity']/../span")).click();
		}
		
		
		//Check whether the Roll Out Modifier To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierNameAndPrice']")).isSelected()){}
		
		else
		{
			//Enable the  Roll Out Modifier To Menu Option
			driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierNameAndPrice']/../span")).click();
		}
		
		
		
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceMenu']")).isSelected()){}
		
		else
		{
			//Enable the Exclude zero price menu item Option
			driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceMenu']/../span")).click();
		}
		
		
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceModifier']")).isSelected()){}
		
		else
		{
			//Enable the Exclude Zero Price Modifier item Option
			driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceModifier']/../span")).click();
		}
		
		
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTaxSummary']")).isSelected()){}
		
		else
		{
			//Enable the Tax Summary Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTaxSummary']/../span")).click();
		}
		
		
		//Check whether the Gratuity field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showGratuity']")).isSelected()){}
		
		else
		{
			//Enable the Gratuity Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showGratuity']/../span")).click();
		}
		
		
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTip']")).isSelected()){}
		
		else
		{
			//Enable the Tip Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTip']/../span")).click();
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Cash Discount field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.cashDiscount']")).isSelected()){}
		
		else
		{
			//Enable the Cash Discount Option
			driver.findElement(By.xpath("//input[@ng-model='settings.cashDiscount']/../span")).click();
		}
		
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTenderDetails']")).isSelected()){}
		
		else
		{
			//Enable the Tender Details Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTenderDetails']/../span")).click();
		}
		
		
		//Check whether the Loyalty Amount field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLoyaltyAmount']")).isSelected()){}
		
		else
		{
			//Enable the Loyalty Amount Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showLoyaltyAmount']/../span")).click();
		}
		
		
		//Check whether the Tip Line field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTipLine']")).isSelected()){}
		
		else
		{
			//Enable the Tip Line Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTipLine']/../span")).click();
		}
		
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSignature']")).isSelected()){}
		
		else
		{
			//Enable the Signature Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showSignature']/../span")).click();
		}
		
		//Check whether the Tip Suggestion field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTipSuggestion']")).isSelected()){}
		
		else
		{
			//Enable the Tip Suggestion Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTipSuggestion']/../span")).click();
		}
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreAddress']")).isSelected()){}
		
		else
		{
			//Enable the Address Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStoreAddress']/../span")).click();
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreEmail']")).isSelected()){}
		
		else
		{
			//Enable the Email Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStoreEmail']/../span")).click();
		}
		
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStorePhoneNumber']")).isSelected()){}
		
		else
		{
			//Enable the Phone Number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStorePhoneNumber']/../span")).click();
		}
		
		//Clear the customer text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.customText']")).clear();
		//Enter the required text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.customText']")).sendKeys("Test");
		Thread.sleep(2000);
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']")).isSelected()){}
		
		else
		{
			//Enable the Address Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']/../span")).click();
		}
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']")).isSelected()){}
		
		else
		{
			//Enable the Email Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']/../span")).click();
		}
		
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']")).isSelected()){}
		
		else
		{
			//Enable the Phone Number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']/../span")).click();
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showBarcode']")).isSelected()){}
		
		else
		{
			//Enable the Barcode Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showBarcode']/../span")).click();
		}
		
		//Clear the free text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.freeText']")).clear();
		//Enter the required text
		driver.findElement(By.xpath("//textarea[@ng-model='settings.freeText']")).sendKeys("Have a nice day, Visit us again!");
		
		//Check whether the Cut Paper After Each print field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.cutReceipt']")).isSelected()){}
		
		else
		{
			//Enable the Cut Paper After Each print Option
			driver.findElement(By.xpath("//input[@ng-model='settings.cutReceipt']/../span")).click();
		}
		
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showNotes']")).isSelected()){}
		
		else
		{
			//Enable the Notes Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showNotes']/../span")).click();
		}
		Thread.sleep(5000);

	
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath("//button[@id='updateFrontEndReceipt']")).click();
		Thread.sleep(4000);
		
		//Check weather the new denomination form saved or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Receipt template saved successfully"))
		{
			test.log(LogStatus.PASS, "Front End Receipt template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Front End Receipt template Updated fail");
		}
		Thread.sleep(3000);
		
		
	}
	
	@Test(priority=4,enabled = false)
	public void Front_End_Receipt_method_verifyUpdatedDetails(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		
		
		//Check whether the Store Logo is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreLogo']")).isSelected())
		{
			test.log(LogStatus.PASS, "Store Logo button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Logo button is disabled");
		}
		
		
		
		//Check whether the store name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Store name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store name button is disabled");
		}
		

		
		//Check whether the table name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Table name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Table name button is disabled");
		}
		
		
		//Check whether the seat number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSeatNumber']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Seat Number button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Seat Number button is disabled");
		}
		
		
		//Check whether the Check Open Server field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Open Server button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Server button is disabled");
		}
		
		
		//Check whether the Check Close Server field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.closeServerName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Close Server button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Close Server button is disabled");
		}
		
		//Check whether the Check number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']")).isSelected())
		{
			test.log(LogStatus.PASS, "Check number button is enabled");
			
			//Check whether the Selected Font Size is displayed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div[10]/div[2]/select/option[2]")).isSelected())		
			{
				test.log(LogStatus.PASS, "2 Height font size is displayed");
			}
			else
			{
				test.log(LogStatus.FAIL, "2 Height font size is not displayed");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "Check number button is disabled");
		}
		
		//Check whether the Open Date and Time field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDate']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Open Date and Time button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Date and Time button is disabled");
		}
		
		
		//Check whether the Close Date and Time field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.closeDate']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Close Date and Time button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Close Date and Time button is disabled");
		}
		
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Customer name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer name button is disabled");
		}
		
		
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Service Type button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Service Type button is disabled");
		}
		
		//Check whether the Show Secondary menu name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLocalName']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Secondary menu name button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Secondary menu name button is disabled");
		}
		
		//Check whether the Roll Out Modifier Price To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPrice']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Roll Out Modifier Price To Menu is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier Price To Menu button is disabled");
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Roll Out Modifier Price & Quantity To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierPriceAndQuantity']")).isSelected())
		{
			test.log(LogStatus.PASS, "Roll Out Modifier Price & Quantity To Menu button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier Price & Quantity To Menu button is not enabled");
		}
		
		//Check whether the Roll Out Modifier To Menu field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.rollOutModifierNameAndPrice']")).isSelected())
		{
			test.log(LogStatus.PASS, "Roll Out Modifier To Menu button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Roll Out Modifier To Menu button is not enabled");
		}
		
		//Check whether the Exclude zero price menu item field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceMenu']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Exclude zero price menu item button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude zero price menu item button is disabled");
		}
		
		//Check whether the Exclude Zero Price Modifier field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.excludeZeroPriceModifier']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Exclude Zero Price Modifier button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Exclude Zero Price Modifier button is disabled");
		}
		
		//Check whether the Tax Summary field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTaxSummary']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Tax Summary button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tax Summary button is disabled");
		}
		
		//Check whether the Gratuity field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showGratuity']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Gratuity button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Gratuity button is disabled");
		}
		
		//Check whether the Tip field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTip']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Tip button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip button is disabled");
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Cash Discount field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.cashDiscount']")).isSelected())
		{
			test.log(LogStatus.PASS, "Cash Discount button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cash Discount button is not enabled");
		}
		
		//Check whether the Tender Details field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTenderDetails']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Tender Details button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tender Details button is disabled");
		}
		
		//Check whether the Loyalty Amount field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showLoyaltyAmount']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Loyalty Amount button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Loyalty Amount button is disabled");
		}
		
		//Check whether the Tip Line field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTipLine']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Tip Line button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Line button is disabled");
		}
		
		//Check whether the Signature field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showSignature']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Signature button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Signature button is disabled");
		}
		
		//Check whether the Tip Suggestion field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTipSuggestion']")).isSelected())	
		{
			test.log(LogStatus.PASS, "Tip Suggestion button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Tip Suggestion button is disabled");
		}
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreAddress']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Address button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Address button is disabled");
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreEmail']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Email button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email button is disabled");
		}
		
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStorePhoneNumber']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Phone Number button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number button is disabled");
		}

		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Address button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Address button is disabled");
		}
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Email button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email button is disabled");
		}
		
		//Check whether the Phone Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Phone Number button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone Number button is disabled");
		}
		
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
		}
		
		//Check whether the Barcode field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showBarcode']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Barcode button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Barcode button is disabled");
		}
		
		
		//Check whether the Cut Paper After Each print field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.cutReceipt']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Cut Paper After Each print button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut Paper After Each print button is disabled");
		}
		
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showNotes']")).isSelected())		
		{
			test.log(LogStatus.PASS, "Notes button is enabled");
		}
		else
		{
			test.log(LogStatus.FAIL, "Notes button is disabled");
		}
		for(int i =0; i <=8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);Thread.sleep(2000);
		}
		
	}
	
}
