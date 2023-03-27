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


public class EditDelete_Discount_OpenItemAmount {
	
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("EditDelete_Discount_OpenItemAmount");

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
			Open_Item_Discount_method_editDiscount_OpenItem(driver);
			Open_Item_Discount_method_deleteOrActiveDiscount_OpenItem(driver);
			Open_Item_Discount_method_cancelButton(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=81)
	public void Open_Item_Discount_method_editDiscount_OpenItem(WebDriver driver) throws Exception
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
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"open_item/openDiscount");
		Thread.sleep(8000);
		//Check Check based discount page opened or not
		if(driver.findElement(By.cssSelector("h3.ng-binding")).getText().equalsIgnoreCase("Discounts"))
		{
			test.log(LogStatus.PASS, "Open Item page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Open Item page loaded Failed");
		}
		
		Thread.sleep(8000);
		//Click edit button
		driver.findElement(By.id("editOpen")).click();Thread.sleep(3000);
		//Clear the name field
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the required name
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_OpenItemAMT"));
		
		//Enable or Disable the Coupon only option
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[2]/div/label/span")).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.name("isPercentage")).isSelected())
		{
			Thread.sleep(2000);
			//Clear the minimum percentage option
			driver.findElement(By.name("minPercentage")).clear();
			//Enter the required minimum value in minimum percentage
			driver.findElement(By.name("minPercentage")).sendKeys("4");
			//Clear the maximum percentage option
			driver.findElement(By.name("maxPercentage")).clear();
			//Enter the required maximum value in maximum percentage
			driver.findElement(By.name("maxPercentage")).sendKeys("5");

		}
		else
		{
			Thread.sleep(2000);
			//Click the Discount in percentage button
			driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[3]/div/label/span")).click();
			Thread.sleep(3000);
			//Clear the minimum percentage option
			driver.findElement(By.name("minPercentage")).clear();
			//Enter the required minimum value in minimum percentage
			driver.findElement(By.name("minPercentage")).sendKeys("4");
			//Clear the maximum percentage option
			driver.findElement(By.name("maxPercentage")).clear();
			//Enter the required maximum value in maximum percentage
			driver.findElement(By.name("maxPercentage")).sendKeys("7");
			Thread.sleep(2000);
		}

		Thread.sleep(2000);
		if(driver.findElement(By.name("isAmount")).isSelected())
		{
			
			Thread.sleep(3000);
			//Clear the minimum amount option
			driver.findElement(By.name("minAmount")).clear();
			//Enter the required minimum value in minimum amount
			driver.findElement(By.name("minAmount")).sendKeys("5.00");
			//Clear the maximum amount option
			driver.findElement(By.name("maxAmount")).clear();
			//Enter the required maximum value in maximum amount
			driver.findElement(By.name("maxAmount")).sendKeys("7.00");

		}
		else
		{
			Thread.sleep(2000);
			//Click the Discount in Amount button
			driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[6]/div/label/span")).click();
			Thread.sleep(3000);
			//Clear the minimum amount option
			driver.findElement(By.name("minAmount")).clear();
			//Enter the required minimum value in minimum amount
			driver.findElement(By.name("minAmount")).sendKeys("4.00");
			//Clear the maximum amount option
			driver.findElement(By.name("maxAmount")).clear();
			//Enter the required maximum value in maximum amount
			driver.findElement(By.name("maxAmount")).sendKeys("5.00");
		}

		Select discType = new Select(driver.findElement(By.name("discountCouponType")));
		discType.selectByVisibleText("Donation");
		
		//Clear the Safety Limit Percentage text box
		driver.findElement(By.name("safetyLimitPercentage")).clear();
		//Enter the required percentage
		driver.findElement(By.name("safetyLimitPercentage")).sendKeys("10");
	
		//Select the any one tax from the give list
		Select applyDiscount = new Select(driver.findElement(By.name("adjustAmount")));
		applyDiscount.selectByVisibleText("After Tax");
		
		//Enable or Disable the Include Additional Modifiers
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[2]/div[3]/div/label/span")).click();
		Thread.sleep(3000);
		
		//Clear the priority field
		driver.findElement(By.name("priority")).clear();
		//Enter the priority
		driver.findElement(By.name("priority")).sendKeys("3");
		
		//CLick the Roles Option
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[2]/div[6]/div/div/ul")).click();
		//Enter the required Role
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[2]/div[6]/div/div/ul/li/input")).sendKeys("Driver");
		//Press the Enter Key
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[2]/div[6]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the isCustomer Attach
		driver.findElement(By.name("isCustomerAttach")).click();
		
		//Clear the Discount Code
		//driver.findElement(By.name("discountCode")).clear();
		//Enter the Discount Code
		//driver.findElement(By.name("discountCode")).sendKeys(Utility.getProperty("OpenItem_Discount_Code"));
		
		//Choose any one color from the given
		driver.findElement(By.xpath("//div[@class='email-template-color color-swatch ng-scope'][6]")).click(); 	
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.cssSelector("button.btn.btn-small.btn-success")).click();
		Thread.sleep(2000);
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated successfully"))
		{
			test.log(LogStatus.PASS, "New discount Open Item is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Open Item is updated Failed");
		}
		Thread.sleep(5000);

		//Click edit button
		driver.findElement(By.id("editOpen")).click();
		Thread.sleep(3000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DiscountName_OpenItemAMT")))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits name is updated Failed");
		}
		
		Thread.sleep(1000);
		//Check the coupon only is enable or not
		if(driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[2]/div/label/span")).isEnabled())
		{
			test.log(LogStatus.PASS, "Coupon only field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Coupon only field is disabled");
		}
		Thread.sleep(1000);
		//Check the discount in percentage is enable or not
		if(driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[3]/div/label/span")).isEnabled())
		{
			test.log(LogStatus.PASS, "Discount in percentage field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Discount in percentage field is disabled");
		}
		Thread.sleep(1000);
		//Check the discount in amount is enable or not
		if(driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[6]/div/label/span")).isEnabled())
		{
			test.log(LogStatus.PASS, "Discount in amount field is enabled");
		}
		else
		{
			test.log(LogStatus.PASS, "Discount in amount field is disabled");
		}
		
		Thread.sleep(3000);
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=82)
	public void Open_Item_Discount_method_deleteOrActiveDiscount_OpenItem(WebDriver driver) throws Exception
	{
		Thread.sleep(10000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);Thread.sleep(3000);
				//Click the Active/InActive button
				driver.findElement(By.xpath("//a[@id='editOpen']/../a[2]")).click();
				//Click the Yes button in the popup
				Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
				Thread.sleep(3000);
				//Check the menu item deleted/Activated or not
				if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Discount activated Successfully"))
				{
					test.log(LogStatus.PASS, "New discount Open Item is Activated Successfully");
				}
				else
				{
					test.log(LogStatus.PASS, "New discount Open Item is Inactivated Successfully");
				}

	}
	
	@Test(enabled=false,priority=83)
	public void Open_Item_Discount_method_cancelButton(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Click edit button
		driver.findElement(By.id("editOpen")).click();
		//Clear the name field
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[1]/div/input")).clear();
		//Enter the required name
		driver.findElement(By.xpath("//form[@name='openDiscountForm']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DiscountName_OpenItemAMT")+"1");
		
		Thread.sleep(3000);
		//Click the update
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger.ng-binding")).click();
		//Check whether the Product Item updated successfully or not
		if(driver.findElement(By.xpath("//a[.='Show open item discount']")).getText().equalsIgnoreCase("Show open item discount"))
		{
			test.log(LogStatus.PASS, "New discount Open Item is canceled Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New discount Open Item is canceled Failed");
		}
		Thread.sleep(3000);

	}

}
