package epicList_Chrome_Account_User;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;


public class AddEditDelete_Discount_Mix_and_Match {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_Discount_Mix_and_Match");

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
			
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.Logout(driver, test);
			}
			/*if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath(excel.getData(5, 41, 1)));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath(excel.getData(5, 41, 1))).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath(excel.getData(5, 155, 1))).getText().equalsIgnoreCase("User Login"))
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
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath(excel.getData(5, 41, 1)));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath(excel.getData(5, 41, 1))).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
			if(driver.findElement(By.xpath(excel.getData(5, 156, 1))).getText().equalsIgnoreCase("Account Login"))
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
		}*/
	
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
			Mix_And_Match_method_open_And_refreshMixandMatch(driver);
			Mix_And_Match_method_addMixandMatch_ApplicableTimePeriodAsAlways(driver);
			Mix_And_Match_method_editMixandMatch(driver);
			Mix_And_Match_method_deleteAndActivated_MixandMatch(driver);
			Mix_And_Match_method_cancelButton(driver);
			Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDaysofWeek(driver);
			Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDaysOfMonth(driver);
			Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDateRange(driver);
			Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsSpecificDate(driver);
			Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsStartDateTimeAndEndDateTime(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=101)
	public void Mix_And_Match_method_open_And_refreshMixandMatch(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
/* Create instance of Java script executor
		JavascriptExecutor je1 = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element1 = driver.findElement(By.xpath("//span[.='Products/Items']"));
		//Scroll the page till the Reason option present
		je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
		//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Discounts']"));
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Discounts Option		
		driver.findElement(By.xpath("//span[.='Discounts']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"mix_and_match/discount/list");
		Thread.sleep(8000);
		
		try
		{
		//Check Check based discount page opened or not

		if(driver.findElement(By.xpath(excel.getData(5, 157, 1))).getText().equalsIgnoreCase("Mix And Match"))
		{
			test.log(LogStatus.PASS, "Mix And Match page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix And Match page loaded Failed");
			
			String scnShot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s1="data:image/png;base64,"+scnShot1;
			test.log(LogStatus.INFO,test.addScreenCapture(s1));
		}
		}
		catch(Exception e)
		{
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.FAIL,test.addScreenCapture(s));
		}
		
		Thread.sleep(8000);
		//Click the refresh button
		driver.findElement(By.xpath(excel.getData(5, 158, 1))).click();
		
		Thread.sleep(5000);
		//Check whether the Mix and Match was refreshed successfully or not
		if(driver.findElement(By.xpath(excel.getData(5, 159, 1))).getText().equalsIgnoreCase("Mix And Match"))
		{
			test.log(LogStatus.PASS, "Mix and Match was refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match was refreshed Failed");
		}

		Thread.sleep(3000);
	}

	@Test(enabled=false,priority=103)
	public void Mix_And_Match_method_addMixandMatch_ApplicableTimePeriodAsAlways(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
			driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Click the Add new discount option
		driver.findElement(By.id(excel.getData(5, 160, 2))).click();
		Thread.sleep(2500);
		
		//Clear the name field
		driver.findElement(By.name(excel.getData(5, 161, 3))).clear();
		//Enter the required name    
		driver.findElement(By.name(excel.getData(5, 161, 3))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch"));
		
		Thread.sleep(1000);
		//Enable or Disable the Coupon Only
		driver.findElement(By.xpath("//label[contains(.,'Coupon Only')]/span")).click();
		Thread.sleep(1000);
		//Click the Auto Discount button
		driver.findElement(By.xpath("//label[contains(.,'Auto Discount')]/span")).click();
		
		Thread.sleep(60000);
		//Choose or Click the Category List box
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Choose or Click the Menu Items List box
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ENTER);

		//Select the Required Discount Type
		Select discountType = new Select(driver.findElement(By.xpath(excel.getData(5, 168, 1))));
		discountType.selectByVisibleText("Comp");
		
		//Click the Add symbol for Set Prize Option1
		driver.findElement(By.xpath("//label[contains(.,'Set Price')]/../div/div/a/i")).click();
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("200");
		//Clear the amount field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the amount for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("500");
		
		Thread.sleep(2000);
		//Click the Add symbol for Set Prize Option
		driver.findElement(By.xpath("//label[contains(.,'Set Price')]/../div/div/a/i")).click();
		//Click the Close button of latest added option
		driver.findElement(By.xpath("//label[@ng-if='!discount.setPercentage']/../div/div[4]/ng-form/div/div[3]/a/i")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);Thread.sleep(3000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys("Always");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Choose the Apply field
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div[1]/div/select")).click();
		Thread.sleep(2000);
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(5, 177, 3))));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(5, 178, 3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(5, 178, 3))).sendKeys("3");
		
		driver.findElement(By.xpath("//select[@name='roles']/../div/ul")).click();
		//Enter the required Role
		driver.findElement(By.xpath("//select[@name='roles']/../div/ul/li/input")).sendKeys("Driver");
		//Press the Enter button
		driver.findElement(By.xpath("//select[@name='roles']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		//Clear the Discount Code
		//driver.findElement(By.name("discountCode")).clear();
		//Enter the Discount Code
		//driver.findElement(By.name("discountCode")).sendKeys(Utility.getProperty("MixAndMatch_Discount_Code"));
		
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(5, 180, 3))).click();
		
		//Choose the required color
		driver.findElement(By.xpath(excel.getData(5, 181, 1))).click();
		
		Thread.sleep(2000);
		//Click the save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		
		Thread.sleep(3000);
		//Check whether the Mix and Match was saved successfully or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "Mix and Match saved Successfully for applicable time period as Always");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match saved Failed for applicable time period as Always");
		}

		Thread.sleep(3000);
	}

	@Test(enabled=false,priority=104)
	public void Mix_And_Match_method_editMixandMatch(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"1");Thread.sleep(4000);
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(4000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		Thread.sleep(4000);
		//Clear the Name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the Name
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"1");
		
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		
		//Click the option as Set Each Item Price from the given option
		driver.findElement(By.xpath("//input[@name='isSetEachItemPrice']/../span")).click();
		
		//Select the Recquired Discount Type
		Select discountType = new Select(driver.findElement(By.xpath(excel.getData(5, 168, 1))));
		discountType.selectByVisibleText("Promo");
		
		//Click the Add symbol for Set Prize Option
		driver.findElement(By.xpath("//label[contains(.,'Set Price')]/../div/div/a/i")).click();
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("2");
		//Clear the amount field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the amount for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("500");  
		Thread.sleep(1000);
		
		//Click the update
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		//Thread.sleep(2000);
		WebElement up=driver.findElement(By.xpath(excel.getData(0, 39, 1)));
		WebDriverWait wait=new WebDriverWait(driver,60);
		//Check weather the new Mix and Match is updated or not
		if(wait.until(ExpectedConditions.visibilityOf(up)).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New Mix and Match is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Mix and Match is updated Failed");
		}

		Thread.sleep(5000);
		
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"1");Thread.sleep(4000);
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		Thread.sleep(4000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DiscountName_MixAndMatch")+"1"))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.INFO, "Edits name is updated Failed");
		}
		Thread.sleep(5000);
		
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
//		driver.findElement(By.xpath(excel.getData(5, 184, 1))).click();
	Thread.sleep(5000);	
				
	}
	
	@Test(enabled=false,priority=105)
	public void Mix_And_Match_method_deleteAndActivated_MixandMatch(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"11");
		Thread.sleep(4000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Delete button
		Thread.sleep(5000);driver.findElement(By.cssSelector(excel.getData(5, 68, 4))).click();
		//Click the Yes button in the popup
		Thread.sleep(1000);driver.findElement(By.linkText("Yes")).click();
		Thread.sleep(4000);
		//Check the menu item deleted or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Discount Inactivated Successfully"))
		{
			test.log(LogStatus.PASS, "New Mix and Match Item is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Mix and Match Item is deleted Failed");
		}

		Thread.sleep(6000);

		//Click the Active Button
		driver.findElement(By.xpath(excel.getData(3, 44, 1))).click();
		Thread.sleep(3000);
		
		//Click the success button
		driver.findElement(By.xpath(excel.getData(3, 1260, 1))).click();
		Thread.sleep(1000);
		
		Thread.sleep(2000);
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.linkText("Yes")).click();
		Thread.sleep(4000);
		
		//Check the menu item activated or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Discount activated Successfully"))
		{
			test.log(LogStatus.PASS, "New Mix and Match Item is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Mix and Match Item is activated Failed");
		}
		
		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=106)
	public void Mix_And_Match_method_cancelButton(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));

		Thread.sleep(3000);
		//Click the Add new discount option
		driver.findElement(By.id(excel.getData(5, 160, 2))).click();
		
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the required name    
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"11");
		
		Thread.sleep(3000);
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		//driver.findElement(By.xpath(excel.getData(5, 184, 1))).click();
		Thread.sleep(3000);
		//Check whether the Mix and Match was cancel successfully or not
		if(driver.findElement(By.xpath(excel.getData(5, 159, 1))).getText().equalsIgnoreCase("mix and match"))
		{
			test.log(LogStatus.PASS, "Mix and Match cancel Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match cancel Failed");
		}

		Thread.sleep(5000);
	}

	@Test(enabled=false,priority=107)
	public void Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDaysofWeek(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"11");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(5000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		
		Thread.sleep(3000);
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the required name    
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"20");
		
		Thread.sleep(2000);
		//Enable or Disable the Coupon Only
		driver.findElement(By.xpath("//label[contains(.,'Coupon Only')]/span")).click();		
		
		Thread.sleep(60000);
		//Choose or Click the Category List box
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Choose or Click the Menu Items List box
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("100");
		//Clear the amount field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the amount for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("540");
		
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);Thread.sleep(4000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Days of Week Option
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul")).click();
		//Enter the required DAY for Searching
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter key after enter the keyword
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Enable or disable the Restriction Time Period option
		driver.findElement(By.xpath("//span[contains(.,'Restriction Time')]")).click();
		
		Thread.sleep(3000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@ng-model='discount.endTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Choose the Apply field
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div[1]/div/select")).click();
		Thread.sleep(2000);
		
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(5, 177, 3))));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(5, 178, 3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(5, 178, 3))).sendKeys("3");
		
		Thread.sleep(2000);
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(5, 180, 3))).click();
		
		
		Thread.sleep(3000);
		//Click the save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		
		Thread.sleep(4000);
		//Check whether the Mix and Match was saved successfully or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "Mix and Match updated Successfully for applicable time period as Days of Week");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match updated Failed for applicable time period as Days of Week");
		}

		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=108)
	public void Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDaysOfMonth(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"201");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(5000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		Thread.sleep(3000);
		
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the required name    
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"21");
		
		Thread.sleep(2000);
		//Enable or Disable the Coupon Only
		driver.findElement(By.xpath("//label[contains(.,'Coupon Only')]/span")).click();
			
		Thread.sleep(60000);
		//Choose or Click the Category List box
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).click();
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Choose or Click the Menu Items List box
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).click();
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Set Each Item Price
		driver.findElement(By.xpath(excel.getData(5, 182, 1))).click();
		Thread.sleep(3000);
		
		//Add Set Price
		//driver.findElement(By.xpath("//label[contains(.,'Set Price')]/../div/div/a/i")).click();
		
		Thread.sleep(2000);
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("150");
		//Clear the amount field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the amount for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("400");
		
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);Thread.sleep(4000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys("Days of Month");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Required Date form the Calendar
		driver.findElement(By.xpath("//label[contains(.,'DAYS OF MONTH')]/../div/table/tbody/tr[2]/td[2]/button")).click();
		Thread.sleep(2000);
		
		//Enable or Disable the Restriction Months option
		driver.findElement(By.xpath("//span[contains(.,'Restriction months')]")).click();
		
		Thread.sleep(2000);
		//Click the months field
		driver.findElement(By.xpath("//select[@name='months']/../div/ul")).click();
		//Enter the Required month
		driver.findElement(By.xpath("//select[@name='months']/../div/ul/li/input")).sendKeys("MAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//select[@name='months']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//span[contains(.,'Restriction Time')]")).click();
		Thread.sleep(2000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@ng-model='discount.endTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Choose the Apply field
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div[1]/div/select")).click();
		Thread.sleep(2000);
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(5, 177, 3))));
		applyDiscount.selectByVisibleText("Before Tax");
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(5, 178, 3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(5, 178, 3))).sendKeys("3");
	
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(5, 180, 3))).click();
		
		for(int i=1; i<=5; i++){
			Thread.sleep(1000);
		//scroll the page 
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(3000);
		//Click the save button
		driver.findElement(By.xpath(excel.getData(5, 200, 1))).click();
		Thread.sleep(2000);
		//Check whether the Mix and Match was updated successfully or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "Mix and Match updated Successfully for applicable time period as Days of Month");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match updated Failed for applicable time period as Days of Month");
			
		}

		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=109)
	public void Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsDateRange(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"211");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(5000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		Thread.sleep(3000);
		
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the required name    
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"22");
		
		Thread.sleep(60000);
		//Choose or Click the Category List box
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Choose or Click the Menu Items List box
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		//Click the Set Percentage Option
		driver.findElement(By.xpath(excel.getData(5, 208, 1))).click();
		
		Thread.sleep(2000);
		//Click the Add symbol for Set Prize Option
		driver.findElement(By.xpath("//label[contains(.,'Set Percentage')]/../div/div/a/i")).click();

		
		Thread.sleep(1000);
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("3");
		Thread.sleep(1000);
		//Clear the Discount Quantity field
		driver.findElement(By.name(excel.getData(5, 192, 3))).clear();
		//Enter the Discount Quantity
		driver.findElement(By.name(excel.getData(5, 192, 3))).sendKeys("2");
		Thread.sleep(1000);
		//Clear the Discount Percentage field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the Discount Percentage for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("250");
		
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);Thread.sleep(3000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys("Date Range");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name(excel.getData(5, 187, 3))).clear();
		//Enter the required from Date
		driver.findElement(By.name(excel.getData(5, 187, 3))).sendKeys("25-May-2050");
		//Clear the To Date field
		driver.findElement(By.name(excel.getData(5, 188, 3))).clear();
		//Enter the required To Date
		driver.findElement(By.name(excel.getData(5, 188, 3))).sendKeys("28-May-2050");

		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath("//span[contains(.,'Restriction Days')]")).click();
		
		Thread.sleep(2000);
		//Click the Days of a Week option
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul")).click();
		//Enter the Required Date
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul/li/input")).sendKeys(Keys.ENTER);

		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//span[contains(.,'Restriction Time')]")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@ng-model='discount.endTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Choose the Apply field
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div[1]/div/select")).click();
		Thread.sleep(2000);
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(5, 177, 3))));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(5, 178, 3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(5, 178, 3))).sendKeys("3");
	
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(5, 180, 3))).click();
		
		Thread.sleep(3000);
		//Click the save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		Thread.sleep(4000);
		//Check whether the Mix and Match was updated successfully or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "Mix and Match updated Successfully for applicable time period as Date Range");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match updated Failed for applicable time period as DAte Range");
		}

		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=110)
	public void Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsSpecificDate(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"221");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(4000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		Thread.sleep(3000);
		
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the required name    
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"23");
		
		Thread.sleep(60000);
		//Choose or Click the Category List box
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
/*		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
*/		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Choose or Click the Menu Items List box
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
/*		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
*/		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
/*		//Click the Set Percentage Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div[3]/label/span")).click();
		Thread.sleep(1000);*/
		
		//Click the Most Expensive option
		driver.findElement(By.xpath(excel.getData(5, 204, 1))).click();
		Thread.sleep(2000);
		
/*		Thread.sleep(2000);
		//Click the Add symbol for Set Prize Option
		driver.findElement(By.xpath(//form[@name='discountForm']/div[1]/div[4]/div[5]/div/div[1]/a/i")).click();
		Thread.sleep(2000);
		
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("3");
		Thread.sleep(1000);
		//Clear the Discount Quantity field
		driver.findElement(By.name(excel.getData(5, 192, 3))).clear();
		//Enter the Discount Quantity
		driver.findElement(By.name(excel.getData(5, 192, 3))).sendKeys("2");
		Thread.sleep(1000);
		//Clear the Discount Percentage field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the Discount Percentage for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("250");*/
		Thread.sleep(2000);

		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);Thread.sleep(3000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys("Specific date");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Clear the Date field
		driver.findElement(By.name(excel.getData(5, 203, 3))).clear();
		//Enter the required date
		driver.findElement(By.name(excel.getData(5, 203, 3))).sendKeys("15-May-2050");
		
		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//span[contains(.,'Restriction Time')]")).click();
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@ng-model='discount.endTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Choose the Apply field
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div[1]/div/select")).click();
		Thread.sleep(2000);
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(5, 177, 3))));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(5, 178, 3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(5, 178, 3))).sendKeys("3");
		
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(5, 180, 3))).click();
		
		Thread.sleep(3000);
		//Click the save button
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		
		Thread.sleep(4000);
		//Check whether the Mix and Match was updated successfully or not
		if(driver.findElement(By.cssSelector(excel.getData(5, 49, 4))).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "Mix and Match updated Successfully for applicable time period as Specific Date");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match updated Failed for applicable time period as Specific Date");
		}

		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=111)
	public void Mix_And_Match_method_editMixandMatch_ApplicableTimePeriodAsStartDateTimeAndEndDateTime(WebDriver driver) throws Exception
	{
		ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
		
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).clear();
		//Enter the required keyword
		driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"231");
		Thread.sleep(2000);driver.findElement(By.xpath(excel.getData(5, 47, 1))).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(4000);driver.findElement(By.cssSelector(excel.getData(5, 63, 4))).click();
		Thread.sleep(3000);
		
		//Clear the name field
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).clear();
		//Enter the required name    
		driver.findElement(By.xpath(excel.getData(3, 34, 1))).sendKeys(Utility.getProperty("DiscountName_MixAndMatch")+"24");
		
		Thread.sleep(2000);
		//Enable or Disable the Coupon Only
		driver.findElement(By.xpath("//label[contains(.,'Coupon Only')]/span")).click();
		
		Thread.sleep(60000);
		//Choose or Click the Category List box
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul")).click();
		//Enter the required Category
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
/*		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
*/		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='categories']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Choose or Click the Menu Items List box
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
/*		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
*/		//Press the Enter Key from the keyboard
		driver.findElement(By.xpath("//select[@name='menuItems']/../div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Select the Recquired Discount Type
		Select discountType = new Select(driver.findElement(By.xpath(excel.getData(5, 183, 1))));
		discountType.selectByVisibleText("Donation");
		

		Thread.sleep(1000);
		//Click the Set Price option
		driver.findElement(By.xpath(excel.getData(5, 201, 1))).click();

		Thread.sleep(2000);
		//Click the Add symbol for Set Prize Option
		driver.findElement(By.xpath("//label[contains(.,'Set Price')]/../div/div/a/i")).click();
		Thread.sleep(2000);
		
		Thread.sleep(3000);
		//Clear the Quantity field
		driver.findElement(By.name(excel.getData(5, 170, 3))).clear();
		//Enter the Quantity
		driver.findElement(By.name(excel.getData(5, 170, 3))).sendKeys("200");
		//Clear the amount field
		driver.findElement(By.name(excel.getData(5, 171, 3))).clear();
		//Enter the amount for that item
		driver.findElement(By.name(excel.getData(5, 171, 3))).sendKeys("500");
		
		driver.findElement(By.tagName(excel.getData(5, 174, 5))).sendKeys(Keys.HOME);Thread.sleep(3000);
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys("Start date time & end date time");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//select[@ng-model='discount.timeApplicableType']/../div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name(excel.getData(5, 187, 3))).clear();
		//Enter the required from Date
		driver.findElement(By.name(excel.getData(5, 187, 3))).sendKeys("25-May-2050");
		//Clear the To Date field
		driver.findElement(By.name(excel.getData(5, 188, 3))).clear();
		//Enter the required To Date
		driver.findElement(By.name(excel.getData(5, 188, 3))).sendKeys("28-May-2050");
		
		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath("//span[contains(.,'Restriction Days')]")).click();
		
		Thread.sleep(2000);
		//Click the Days of a Week option
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul")).click();
		//Enter the Required Date
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//select[@name='daysOfWeek']/../div/ul/li/input")).sendKeys(Keys.ENTER);

		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@ng-model='discount.endTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@ng-model='discount.startTime']/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Thread.sleep(2000);
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name(excel.getData(5, 177, 3))));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Clear the priority field
		driver.findElement(By.name(excel.getData(5, 178, 3))).clear();
		//Enter the priority
		driver.findElement(By.name(excel.getData(5, 178, 3))).sendKeys("3");
		
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name(excel.getData(5, 180, 3))).click();
		
		Thread.sleep(3000);
		//Click the save button
		//driver.findElement(By.xpath("//span[.=' Update And Publish']")).click();
		driver.findElement(By.cssSelector(excel.getData(5, 11, 4))).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(excel.getData(5, 145, 1))));
		
//		Thread.sleep(7000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String s1 = driver.findElement(By.xpath(excel.getData(5, 145, 1))).getText();Thread.sleep(3000);
		//System.out.println("Required String  is : "+s1);
		//Check whether the Product Item updated successfully or not
		if(s1.equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "Mix and Match updated and published Successfully for applicable time period as Start Date Time And End Date Time");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mix and Match updated and published Failed for applicable time period as Start Date Time And End Date Time");
		}

		Thread.sleep(3000);
	}

}
