package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Settings_Kitchen_Receipt_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Kitchen_Receipt_Template");
	
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
			Kitchen_Receipt_Template_method_open_Kitchen_Receipt_Template(driver);
			Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
			Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(driver);
			Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
			Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(driver);
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
	
	@Test(priority=5,enabled = false)
	public void Kitchen_Receipt_Template_method_open_Kitchen_Receipt_Template(WebDriver driver) throws Exception
	{
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"kitchenReceiptTemplate");
		Thread.sleep(5000);
		
		//Check whether the Kitchen Receipt Template
		if(driver.findElement(By.xpath("//a[.='Kitchen Receipt Template']")).getText().equalsIgnoreCase("Kitchen Receipt Template"))
		{
			test.log(LogStatus.PASS, "Kitchen Receipt Template page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Receipt Template page loaded Failed");
		}
		Thread.sleep(3000);
	}	
		
	@Test(priority=6,enabled = false)
	public void Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(WebDriver driver) throws Exception
	{
		Thread.sleep(8000);
		//Select the font size
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/select")).click();
		Thread.sleep(2000);
		WebElement mySelectElement = (WebElement)new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/select/option[1]")));
		mySelectElement.click();
		
	/*	driver.findElement(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/select")).click();
		List<WebElement> option = driver.findElements(By.xpath(".//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/select/option"));
		for(int i=0;i<option.size();i++ )
		{
		WebElement element  = option.get(i);
		String innerhtml = element.getAttribute("innerHTML");
		if(innerhtml.contentEquals("Small"))
		{
		element.click();
		break;
		}
		}
		*/
		
		//Check whether the Store name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreName']")).isSelected()){}
		
		else
		{
			//Enable the Store Name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showStoreName']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Table name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']")).isSelected()){}
		
		else
		{
			//Enable the Table Name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']")).isSelected()){}
		
		else
		{
			//Enable the Server Name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']/..//span")).click();
		}
		Thread.sleep(1000);
		
		
		//Check whether the Check Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']")).isSelected())
		{
			//Select the Required Position
			Select position = new Select(driver.findElement(By.xpath("//div[@ng-if='settings.showCheckNumber']/select")));
			position.selectByVisibleText("P#1");
			
			Thread.sleep(2000);
			
			//Select the required font size
			Select fontSize2 = new Select(driver.findElement(By.xpath("//div[@ng-if='settings.showCheckNumber']/..//div[2]/select")));
			fontSize2.selectByVisibleText("2 Height");
			Thread.sleep(2000);
		}
		else
		{
			//Enable the Check Number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']/..//span")).click();
			
			//Select the Required Position
			Select position = new Select(driver.findElement(By.xpath("//div[@ng-if='settings.showCheckNumber']/select")));
			position.selectByVisibleText("P#1");
			
			Thread.sleep(2000);
			
			//Select the required font size
			Select fontSize2 = new Select(driver.findElement(By.xpath("//div[@ng-if='settings.showCheckNumber']/..//div[2]/select")));
			fontSize2.selectByVisibleText("2 Height");
			Thread.sleep(2000);
		}
		Thread.sleep(1000);
		
		//Check whether the Date & Time field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDate']")).isSelected()){}
		
		else
		{
			//Enable the Date & Time Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showDate']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected()){}
		
		else
		{
			//Enable the Customer Name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']/..//span")).click();
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		Thread.sleep(2000);
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']")).isSelected())
		{
			//Select the Required Position
			Select position = new Select(driver.findElement(By.xpath("//div[@ng-if='settings.showServiceType']/select")));
			position.selectByVisibleText("P#2");
			
			Thread.sleep(2000);
		}
		else
		{
			//Enable the Service Type Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']/..//span")).click();
			
			//Select the Required Position
			Select position = new Select(driver.findElement(By.xpath("//div[@ng-if='settings.showServiceType']/select")));
			position.selectByVisibleText("P#2");
			
			Thread.sleep(2000);
		}
		Thread.sleep(1000);
		
		
		//Check whether the Split By Course field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.splitByCourse']")).isSelected()){}
		
		else
		{
			//Enable the Split By Course Option
			driver.findElement(By.xpath("//input[@ng-model='settings.splitByCourse']/..//span")).click();
		}
		Thread.sleep(1000);
		
		
		//Check whether the Customer Information Position field - Below is enabled or not
		if(driver.findElement(By.xpath("//label[.='Customer Information Position']/../lablel[1]/input")).isSelected()){}
		
		else
		{
			//Enable the Customer Information Position Option - Below
			driver.findElement(By.xpath("//label[.='Customer Information Position']/../lablel[1]/input")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']")).isSelected()){}
		
		else
		{
			//Enable the Address Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']")).isSelected()){}
		
		else
		{
			//Enable the Email Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']/..//span")).click();
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}Thread.sleep(2000);
		
		//Check whether the Phone number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']")).isSelected()){}
		
		else
		{
			//Enable the Phone Number Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Cut Paper After Each print field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.cutReceipt']")).isSelected()){}
		
		else
		{
			//Enable the Cut Paper After Each print Option
			driver.findElement(By.xpath("//input[@ng-model='settings.cutReceipt']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Print quantity before the menu name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.printQuantityBeforeMenuName']")).isSelected()){}
		
		else
		{
			//Enable the Print quantity before the menu name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.printQuantityBeforeMenuName']/..//span")).click();
		}
		Thread.sleep(1000);
		
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showNotes']")).isSelected()){}
		
		else
		{
			//Enable the Notes Option
			driver.findElement(By.xpath("//input[@ng-model='settings.showNotes']/..//span")).click();
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		try
		{
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']")).isSelected())
			{
				//Disable the Consolidate Menu In Kitchen Option
				driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']/..//span")).click();
			
				Thread.sleep(1000);
				
				//Check whether the split Menu Quantity field is enabled or not
				if(driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']")).isSelected())
				{
					test.log(LogStatus.FAIL, "Split Menu Quantity field is enabled when Consolidate Menu In Kitchen Option is disabled");
				}
				else
				{
					//Enable the split Menu Quantity Option
					driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']/..//span")).click();
				}
			}	
		}
		catch(Exception e)
		{
			Thread.sleep(1000);
			
			//Check whether the split Menu Quantity field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']")).isSelected()){}
			
			else
			{
				//Enable the split Menu Quantity Option
				driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']/..//span")).click();
			}
		}

		
		Thread.sleep(1000);
		//Check whether the Is Other Language Menu Name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.isOtherLanguageMenuName']")).isSelected()){}
		
		else
		{
			//Enable the Is Other Language Menu Name Option
			driver.findElement(By.xpath("//input[@ng-model='settings.isOtherLanguageMenuName']/..//span")).click();
		}
		
		Thread.sleep(1000);
		//Check whether the Print all Modifiers in Modifier field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.printAllModifiersInModifierPrint']")).isSelected()){}
		
		else
		{
			//Enable the Print all Modifiers in Modifier Option
			driver.findElement(By.xpath("//input[@ng-model='settings.printAllModifiersInModifierPrint']/..//span")).click();
		}
		
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath("//button[@id='updateKitchenReceipt']")).click();
		
		Thread.sleep(5000);
		//Check weather the new denomination form saved or not									
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Kitchen Receipt template saved successfully"))
		{
			test.log(LogStatus.PASS, "Receipt template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt template Updated fail");
		}
		Thread.sleep(3000);
		
		
	}

	@Test(priority=7,enabled = false)
	public void Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Check Whether the Font Size is updated or not
		try
		{
			if(driver.findElement(By.xpath("//select[@ng-model='settings.fontSize']/option[.='Small']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Font Size updated successfully in Kitchen Receipt Template");
			}
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Font Size updated Fail in Kitchen Receipt Template");
		}

		
		//Check whether the Store name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showStoreName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Store Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Store Name field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Table name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTableName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Table Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Table Name field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Server name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServerName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Server Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Server Name field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		
		//Check whether the Check Number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCheckNumber']")).isSelected())
		{
			test.log(LogStatus.PASS, "Check Number field is Enabled in Kitchen Receipt Template");
			
			//Check Whether the Position is updated or not
			try
			{
				if(driver.findElement(By.xpath("//select[@ng-model='settings.checkNumberPosition']/option[.='P#1']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Position - Check Number updated successfully in Kitchen Receipt Template");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Position - Check Number updated Fail in Kitchen Receipt Template");
			}
			
			//Check Whether the Position Font Size is updated or not
			try
			{
				if(driver.findElement(By.xpath("//select[@ng-model='settings.checkNumberFs']/option[.='2 Height']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Position Font Size - Check Number updated successfully in Kitchen Receipt Template");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Position Font Size - Check Number updated Fail in Kitchen Receipt Template");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "Check Number field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Date & Time field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDate']")).isSelected())
		{
			test.log(LogStatus.PASS, "Date & Time field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Date & Time field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Customer name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Name field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		//Check whether the Service Type field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showServiceType']")).isSelected())
		{
			test.log(LogStatus.PASS, "Service Type field is Enabled in Kitchen Receipt Template");
			
			//Check Whether the Position is updated or not
			try
			{
				if(driver.findElement(By.xpath("//select[@ng-model='settings.serviceTypePosition']/option[.='P#2']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Position - Service Type updated successfully in Kitchen Receipt Template");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Position - Service Type updated Fail in Kitchen Receipt Template");
			}

		}
		else
		{
			test.log(LogStatus.FAIL, "Service Type field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		
		//Check whether the Customer field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.splitByCourse']")).isSelected())
		{
			test.log(LogStatus.PASS, "Customer field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		
		//Check whether the Customer Information Position field - Below is enabled or not
		if(driver.findElement(By.xpath("//label[.='Customer Information Position']/../lablel[1]/input")).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Information Position field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Customer Information Position field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		//Check whether the Address field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showAddress']")).isSelected())
		{
			test.log(LogStatus.PASS, "Address field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Address field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Email field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showEmail']")).isSelected())
		{
			test.log(LogStatus.PASS, "Email field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Email field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Phone number field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showPhoneNumber']")).isSelected())
		{
			test.log(LogStatus.PASS, "Phone number field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Phone number field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Cut Paper After Each print field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.cutReceipt']")).isSelected())
		{
			test.log(LogStatus.PASS, "Cut Paper After Each print field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cut Paper After Each print field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		//Check whether the Print quantity before the menu name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.printQuantityBeforeMenuName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Print quantity before the menu name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Print quantity before the menu name field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		//Check whether the Notes field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showNotes']")).isSelected())
		{
			test.log(LogStatus.PASS, "Notes field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Notes field is disabled in Kitchen Receipt Template");
		}
		Thread.sleep(1000);
				
		try
		{
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']")).isSelected())
			{
				test.log(LogStatus.FAIL, "Consolidate Menu In Kitchen field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.PASS, "Consolidate Menu In Kitchen field is disabled in Kitchen Receipt Template");
			}
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			//Check whether the Split Menu Quantity is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']")).isSelected())
			{
				test.log(LogStatus.PASS, "Split Menu Quantity field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Split Menu Quantity field is disabled in Kitchen Receipt Template");
			}
		}
		
		Thread.sleep(1000);
		//Check whether the Is Other Language Menu Name field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.isOtherLanguageMenuName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Is Other Language Menu Name field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Is Other Language Menu Name field is disabled in Kitchen Receipt Template");
		}
		
		//Check whether the Print all modifiers in Modifier print field is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.printAllModifiersInModifierPrint']")).isSelected())
		{
			test.log(LogStatus.PASS, "Print all modifiers in Modifier print field is Enabled in Kitchen Receipt Template");
		}
		else
		{
			test.log(LogStatus.FAIL, "Print all modifiers in Modifier print field is disabled in Kitchen Receipt Template");
		}
	}

	@Test(priority=8,enabled = false)
	public void Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);

		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		try
		{
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']")).isSelected())
			{
				//Disable the Consolidate Menu In Kitchen Option
				driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']/..//span")).click();
			}	
		}
		catch(Exception e)
		{			//Check whether the split Menu Quantity field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']")).isSelected())
			{
				//Disable the split Menu Quantity Option
				driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']/..//span")).click();
			}
			
			else
			{}
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']")).isSelected())
			{
				test.log(LogStatus.FAIL, "Consolidate Menu In Kitchen field is enabled when disable the Split Menu Quantity");
			}	
			else
			{
				//Enable the Consolidate Menu In Kitchen Option
				driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']/..//span")).click();
			}
		}
		
		Thread.sleep(5000);
		//Click the update button
		driver.findElement(By.xpath("//button[@id='updateKitchenReceipt']")).click();
		
		Thread.sleep(5000);
		//Check weather the new denomination form saved or not									
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Kitchen Receipt template saved successfully"))
		{
			test.log(LogStatus.PASS, "Receipt template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Receipt template Updated fail");
		}
		Thread.sleep(3000);
		
		
	}

	@Test(priority=9,enabled = false)
	public void Verify_Kitchen_Receipt_Template_method_update_Kitchen_Receipt_Template_Enable_Consolidate_Menu_In_Kitchen(WebDriver driver) throws Exception
	{

		
		for(int i = 1; i <= 16; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}

				
		try
		{
			//Check whether the Consolidate Menu In Kitchen field is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.consolidateMenuInKitchen']")).isSelected())
			{
				test.log(LogStatus.PASS, "Consolidate Menu In Kitchen field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.FAIL, "Consolidate Menu In Kitchen field is disabled in Kitchen Receipt Template");
			}
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			//Check whether the Split Menu Quantity is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='settings.splitMenuQty']")).isSelected())
			{
				test.log(LogStatus.FAIL, "Split Menu Quantity field is Enabled in Kitchen Receipt Template");
			}
			else
			{
				test.log(LogStatus.PASS, "Split Menu Quantity field is disabled in Kitchen Receipt Template");
			}
		}
		Thread.sleep(1000);

	}

}
