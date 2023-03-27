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
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class AddEditDelete_DiscountCheckBased {

	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Add_Edit_Delete_Discount_Check_Based");
	
	float unknownValue = 00;

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
			Check_Based_Discount_method_openDiscount_CheckBasedPage(driver);
			Check_Based_Discount_method_refreshDiscount_CheckBasedPage(driver);
			Check_Based_Discount_method_addDiscount_CheckBased(driver);
			Check_Based_Discount_method_editDiscount_CheckBased(driver);
			Check_Based_Discount_method_deleteDiscount_CheckBased(driver);
			Check_Based_Discount_method_cancelButton(driver);
			Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsAlways_SaveAndPublish(driver);
			Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsDaysOfMonth(driver);
			Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodasDateRange(driver);
			Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsSpecificDate(driver);
			Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsStartDateTimeAndEndDateTime(driver);
			Check_Based_Discount_method_verifyActive_InActiveButton(driver);
			Check_Based_Discount_method_addSameName(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=61)
	public void Check_Based_Discount_method_openDiscount_CheckBasedPage(WebDriver driver) throws Exception
	{
/*		//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Discounts']"));
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Discounts Option		
		driver.findElement(By.xpath("//span[.='Discounts']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"check_based/discount/list");

		Thread.sleep(5000);
		//Check Check based discount page opened or not
		if(driver.findElement(By.xpath("//span[.='Check Based']")).getText().equalsIgnoreCase("Check Based"))
		{
			test.log(LogStatus.PASS, "Check Based discount page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Check Based discount page loaded Failed");
		}
		
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=62)
	public void Check_Based_Discount_method_refreshDiscount_CheckBasedPage(WebDriver driver) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/div/div/a/i")).click();
		Thread.sleep(5000);
		//Check Check based discount page opened or not
		if(driver.findElement(By.xpath("//span[.='Check Based']")).getText().equalsIgnoreCase("Check Based"))
		{
			test.log(LogStatus.PASS, "Check Based discount page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Check Based discount page refreshed Failed");
		}
		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=64)
	public void Check_Based_Discount_method_addDiscount_CheckBased(WebDriver driver) throws Exception
	{
		for (int i = 0; i < 10; i++) {
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("newDiscount")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//h3[.='Discounts']")).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "New Discounts Item form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discounts Item form loaded Failed");
		}

		
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount"));
		Thread.sleep(5000);
		
		//Enable or Disable the Coupon only check box button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[2]/div/label/span")).click();
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
		//Click the Add symbol for adding offers
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[1]/a")).click();

		
		//Clear the amount field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).clear();
		//Enter the amount When it is applicable
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).sendKeys("1000000");
		//Clear the amount field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/input")).clear();
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/input")).sendKeys("500");
		
		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Select the Discount type
		Select discType = new Select(driver.findElement(By.name("discountCouponType")));
		discType.selectByVisibleText("Promo");
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Days of Week Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/div/ul")).click();
		//Enter the required DAY for Searching
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter key after enter the keyword
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[3]/div/label/span")).click();
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("Before Tax");

		for(int i = 1; i <= 5;i++){driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);}Thread.sleep(3000);
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		Thread.sleep(5000);
		//CLick the Roles Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/div/ul")).click();
		//Enter the required Role
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/div/ul/li/input")).sendKeys("Driver");
		//Press the Enter Key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Clear the Discount Code Name
		//driver.findElement(By.name("discountCode")).clear();
		//Enter the Discount Code
		//driver.findElement(By.name("discountCode")).sendKeys(Utility.getProperty("CheckBased_Discount_Code"));
		
		Thread.sleep(5000);
		//click the attach customer option
		driver.findElement(By.name("isCustomerAttach")).click();
		
		//Choose any one color from the given
		driver.findElement(By.xpath("//div[@class='email-template-color color-swatch ng-scope'][6]")).click(); 	
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		//Check whether the new discount saved or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Saved successfully"))
		{
			test.log(LogStatus.PASS, "New Item based(CheckBased_Amount) discount saved Successfully for Time Period as Days of Month");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Item based(CheckBased_Amount) discount saved Failed for Time Period as Days of Month");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=65)
	public void Check_Based_Discount_method_editDiscount_CheckBased(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"1");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"1");

		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[2]/a")).click();

		
		//Clear the Percentage field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).clear();
		//Enter the Percentage When it is applicable
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).sendKeys("1000000");
		//Clear the Percentage discount field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/input")).clear();
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/input")).sendKeys("200");
		
		Select discType = new Select(driver.findElement(By.name("discountCouponType")));
		discType.selectByVisibleText("Comp");
		
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		Thread.sleep(3500);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed");
		}

		Thread.sleep(5000);
		
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"11");Thread.sleep(5000);
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(3000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DiscountName_CheckBased_Amount")+"1"))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits name is updated Failed");
		}
		Thread.sleep(5000);
		
		//Click the Close button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
	}
	
	@Test(enabled=false,priority=66)
	public void Check_Based_Discount_method_deleteDiscount_CheckBased(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"11");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(4500);
		//Click the Delete button
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		
		//Click the Yes button in the popup
		Thread.sleep(2500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		
		Thread.sleep(3500);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Check the menu item deleted or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Discount Inactivated Successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is deleted Failed");
		}

		Thread.sleep(5000);		
		//Click the Active or Inactive button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
		
		Thread.sleep(5000);
		//Click the success button
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		Thread.sleep(1000);
		
		//Click the Yes button in the popup
		Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		Thread.sleep(3000);

		//Check the menu item activated or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Discount activated Successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is activated Failed");
		}
		
		Thread.sleep(3000);
		//Click the Active or Inactive button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();

	}

	@Test(enabled=false,priority=67)
	public void Check_Based_Discount_method_cancelButton(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("newDiscount")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//h3[.='Discounts']")).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "New Discounts Item form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discounts Item form loaded Failed");
		}

		
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"2");
		Thread.sleep(5000);
			
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3500);
		//Check whether the new discount canceled or not
		if(driver.findElement(By.xpath("//a[.='Check based discounts']")).getText().equalsIgnoreCase("Check based discounts"))
		{
			test.log(LogStatus.PASS, "New Item based discount Canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Item based discount Canceled Failed");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=68)
	public void Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsAlways_SaveAndPublish(WebDriver driver) throws Exception
	{

		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"11");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"20");
		Thread.sleep(5000);
		
		//Enable or Disable the Coupon only check box button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[2]/div/label/span")).click();
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
		//Click the Add symbol for adding offers
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[3]/a")).click();

		
		//Clear the amount field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).clear();
		//Enter the amount When it is applicable
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).sendKeys("1000000");
		//Clear the amount field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/input")).clear();
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/input")).sendKeys("500");
		
		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		Select discType = new Select(driver.findElement(By.name("discountCouponType")));
		discType.selectByVisibleText("Donation");
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Always");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("After Tax");

		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		Thread.sleep(5000);
		//click the attach customer option
		driver.findElement(By.name("isCustomerAttach")).click();
		
		//Choose any one color from the given 
		//driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div[6]/div/div/div[2]/div/div[6]")).click(); 	
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		Thread.sleep(3500);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed");
		}

		Thread.sleep(5000);

	}
	
	@Test(enabled=false,priority=69)
	public void Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsDaysOfMonth(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"201");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(5000);

		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"21");
		Thread.sleep(5000);
		
		//Enable or Disable the Coupon only check box button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[2]/div/label/span")).click();
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
/*		//Click the Add symbol
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		//Enter the amount When it is applicable
		driver.findElement(By.name("offers")).sendKeys("500.00");
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.name("offDis")).sendKeys("50.00");
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[1]/a")).click();
*/		
/*		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys("PIZZA");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys("COKE");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);*/
		
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[4]/a")).click();

		
		//Clear the amount field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).clear();
		//Enter the amount When it is applicable
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[1]/input")).sendKeys("1000000");

		//Click the add Free Item Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/label/span[2]/a/i")).click();
		
		//Click the Items Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/div/div/div[1]/a")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/div/div/div[1]/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the Items Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/div/div/div[2]/div/a")).click();
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/div/div/div[2]/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(5000);
		//Clear the Free Item Offer Price
		driver.findElement(By.name("freeItemSingleOffer")).clear();
		//Enter the Free Item Amount
		driver.findElement(By.name("freeItemSingleOffer")).sendKeys("1000000");
		
		Thread.sleep(1000);
		//Clear the Quantity field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/div/div/div[5]/input")).clear();
		//Enter the Quantity
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[4]/div/div/div/div/div/div[5]/input")).sendKeys("1");
		
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Days of Month");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Required Date form the Calendar
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/table/tbody/tr[2]/td[7]/button")).click();
		Thread.sleep(5000);
		
		//Enable or Disable the Restriction Months option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[3]/div/label/span")).click();
		
		Thread.sleep(5000);
		//Click the months field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div/ul")).click();
		//Enter the Required month
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div/ul/li/input")).sendKeys("MAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/label/span")).click();
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("Before Tax");

		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		Thread.sleep(5000);
		//Enable or Disable the Attach Customer option
		driver.findElement(By.name("isCustomerAttach")).click();
		
		//Click the Save button
		driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")));
		
//		Thread.sleep(7000);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String s1 = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText();Thread.sleep(3000);
		//System.out.println("Required String  is : "+s1);
		//Check whether the Product Item updated successfully or not
		if(s1.equalsIgnoreCase("Updated and published successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated and Published Successfully for Time Period as Days of Month");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated and Published Failed for Time Period as Days of Month");
		}

		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=70)
	public void Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodasDateRange(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"211");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(5000);
		
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"22");
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
/*		//Click the Add symbol
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		//Enter the amount When it is applicable
		driver.findElement(By.name("offers")).sendKeys("500.00");
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.name("offDis")).sendKeys("50.00");
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[1]/a")).click();
*/		
/*		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys("PIZZA");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys("COKE");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);*/
				
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Date Range");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name("fromDate")).clear();
		//Enter the required from Date
		driver.findElement(By.name("fromDate")).sendKeys("25-May-2040");
		//Clear the To Date field
		driver.findElement(By.name("endDate")).clear();
		//Enter the required To Date
		driver.findElement(By.name("endDate")).sendKeys("28-May-2040");

		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/label/span")).click();
		
		Thread.sleep(5000);
		//Click the Days of a Week option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/ul")).click();
		//Enter the Required Date
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/label/span")).click();
		Thread.sleep(5000);
	
		Thread.sleep(1000);
		//Check it is AM or PM i	n the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[7]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("Before Tax");

		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully for Time Period as Date Range");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed for Time Period as Date Range");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=71)
	public void Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsSpecificDate(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"221");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(5000);
		
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"23");
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
/*		//Click the Add symbol
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		//Enter the amount When it is applicable
		driver.findElement(By.name("offers")).sendKeys("500.00");
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.name("offDis")).sendKeys("50.00");
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[1]/a")).click();
*/		
/*		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys("PIZZA");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys("COKE");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);*/
		
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Specific date");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Clear the Date field
		driver.findElement(By.name("date")).clear();
		//Enter the required date
		driver.findElement(By.name("date")).sendKeys("15-May-2040");
		
		//Enable or Disable the Restriction Time Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[3]/div/label/span")).click();
		Thread.sleep(5000);
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("Before Tax");
		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully for Time Period as Specific Date");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed for Time Period as Specific Date");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=72)
	public void Check_Based_Discount_method_editDiscount_CheckBased_TimePeriodAsStartDateTimeAndEndDateTime(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"231");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		Thread.sleep(5000);
		//Click the Edit icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(5000);
		
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"24");
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
/*		//Click the Add symbol
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		//Enter the amount When it is applicable
		driver.findElement(By.name("offers")).sendKeys("500.00");
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.name("offDis")).sendKeys("50.00");
		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[1]/a")).click();
*/		
/*		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys("PIZZA");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys("COKE");
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);*/
		
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Start date time & end date time");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Clear the From date field
		driver.findElement(By.name("fromDate")).clear();
		//Enter the required from Date
		driver.findElement(By.name("fromDate")).sendKeys("25-May-2040");
		//Clear the To Date field
		driver.findElement(By.name("endDate")).clear();
		//Enter the required To Date
		driver.findElement(By.name("endDate")).sendKeys("28-May-2040");
		
		//Enable or Disable the Restriction Days Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/label/span")).click();
		Thread.sleep(5000);
		
		//Click the Days of a week option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/ul")).click();
		//Enter the required day
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("Before Tax");

		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
	
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Item is updated Successfully for Time Period as Start Date and End Date");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Item is updated Failed for Time Period as Start Date and End Date");
		}

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=73)
	public void Check_Based_Discount_method_verifyActive_InActiveButton(WebDriver driver) throws Exception
	{
		Thread.sleep(15000);
		//Click Active or In Active Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();

		Thread.sleep(5000);
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Deleted groups are here, we are in InActive Page");
			
			Thread.sleep(15000);
			//Click Active or In Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[1]/input")).click();
			
			Thread.sleep(5000);
			if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Deleted groups are not here, we are in Active Page");
			}

		}
		else if(driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger")).isDisplayed())
		{
			test.log(LogStatus.FAIL, "Deleted groups are not here, we are in Active Page");
			//Click Active or In Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();			
		}
		
	}
	
	@Test(enabled=false,priority=74)
	public void Check_Based_Discount_method_addSameName(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Click on the Add Menu Item option
		driver.findElement(By.id("newDiscount")).click();
		Thread.sleep(3000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//h3[.='Discounts']")).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "New Discounts Item form loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discounts Item form loaded Failed");
		}

		
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the Name
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_CheckBased_Amount")+"24");
		Thread.sleep(5000);
		
		//Enable or Disable the Coupon only check box button
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[2]/div/label/span")).click();
		Thread.sleep(5000);
		
		//Click the Auto discount option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
		//Click the Add symbol for adding offers
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[1]/a/i")).click();
		Thread.sleep(5000);
		

		//Click the button field for selecting the required field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/button")).click();
		//Click the amount option from the given list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[1]/div/div[3]/ng-form/div/div[3]/div/div[1]/ul/li[2]/a")).click();
		
		//Enter the amount When it is applicable
		driver.findElement(By.name("offers")).sendKeys("1000000");
		//Enter how much of amount is discount for the particular product
		driver.findElement(By.name("offDis")).sendKeys("500");
		
		//Click the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul")).click();
		//Enter the Exclude Category field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//Click the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul")).click();
		//Enter the Exclude Menu Item field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
		//Press the Enter key
		driver.findElement(By.xpath("//form[@name='discountForm']/div[1]/div[4]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		
		//Click the Applicable Time Period field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/a")).click();
		//Enter the required time option from the given item list
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys("Days of Week");
		//Press the Enter Key in the Applicable time period option field
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Days of Week Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/div/ul")).click();
		//Enter the required DAY for Searching
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter key after enter the keyword
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time Option
		driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[3]/div/label/span")).click();
		
		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(5000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='discountForm']/div[2]/div[4]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("Before Tax");

		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);

		//Check the New Display Group saved or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Validation Error(s)"))
		{
			test.log(LogStatus.PASS, "New Discount added Fail, User cannot be able to give the same group name");
			Thread.sleep(3000);
			//Find the validation message for the name field
			if(driver.findElement(By.xpath("//p[@class='help-block ng-binding']")).getText().equalsIgnoreCase("Name already exists"))
			{
				test.log(LogStatus.PASS, "Discount name is already exist");
			}
		}
		else
		{
			test.log(LogStatus.FAIL, "New Discount added success, User can be able to give the same group name");
		}

		Thread.sleep(5000);
		
	}

}
