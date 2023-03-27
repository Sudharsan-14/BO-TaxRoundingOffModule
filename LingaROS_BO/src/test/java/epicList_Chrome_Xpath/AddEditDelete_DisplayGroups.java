package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AddEditDelete_DisplayGroups {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete_DisplayGroups");
	
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
			Display_Groups_method_openDisplayGroups(driver);
			Display_Groups_method_addDisplayGroup_TimePeriod_AS_Always(driver);
			Display_Groups_method_editDisplayGroup(driver);
			Display_Groups_method_deleteDisplayGroup(driver);
			Display_Groups_method_editDisplayGroup_TimePeriod_AS_DaysOfWeek(driver);
			Display_Groups_method_editDisplayGroup_TimePeriod_AS_DaysOfMonth(driver);
			Display_Groups_method_editDisplayGroup_TimePeriod_AS_DateRange(driver);
			Display_Groups_method_editDisplayGroup_TimePeriod_AS_SpecificDate(driver);
			Display_Groups_method_editDisplayGroup_TimePeriod_AS_StartDateAndEndDateTime(driver);
			Display_Groups_method_verifyCancelButton(driver);
			Display_Groups_method_addSameName(driver);
			Thread.sleep(1500);
		}

	@Test(enabled=false,priority=21)
	public void Display_Groups_method_openDisplayGroups(WebDriver driver) throws Exception
	{
/*		//Click the Products/Items option
		driver.findElement(By.xpath("//span[.='Products/Items']")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Display Groups']"));
		//Scroll the page till the Reason option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Display Groups Option		
		driver.findElement(By.xpath("//span[.='Display Groups']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"displayGroups");
		Thread.sleep(5000);
		//Check Menu items page opened or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Display Group"))
		{
			test.log(LogStatus.PASS, "Display Group page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Display Group page loaded Failed");
		}

	}
	
	@Test(enabled=false,priority=23)
	public void Display_Groups_method_addDisplayGroup_TimePeriod_AS_Always(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Click Add Display Group Icon or button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]")).click();
		Thread.sleep(3000);
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW DISPLAY GROUP']")).getText().equalsIgnoreCase("NEW DISPLAY GROUP"))
		{
			test.log(LogStatus.PASS, "New Display Group form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group form opened Failed");
		}

		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName"));
		
		Thread.sleep(15000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		if(driver.findElement(By.xpath("//select[@name='priceChoosen']")).isEnabled())
		{
			Thread.sleep(20000);
			//Click the Price Level option
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[3]/div/div/a")).click();
			//Click the input field
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[3]/div/div/div/div/input")).click();
			//Press the Enter button
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		}
		
		Thread.sleep(1000);
		//Click Any One of the Color for Display button
		driver.findElement(By.xpath("//div[@class='email-template-color color-swatch ng-scope'][5]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("displayGroupImage")).sendKeys(Utility.getProperty("DisplayGroupImage1"));
		
		Thread.sleep(3000);
		//Click the Sort Z-A button
		driver.findElement(By.xpath("//a[.='Sort Z-A']")).click();
		//Create the Log
		test.log(LogStatus.PASS, "Menu items are arranged in descending(Sort Z to A) order");
		
		Thread.sleep(2000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(3000);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Saved Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group added Successfully for Applicable time period as Always");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group added Failed for Applicable time period as Always");
		}

		

	}
	
	@Test(enabled=false,priority=24)
	public void Display_Groups_method_editDisplayGroup(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"1");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(3000);
		//Clear the Name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"1");
		
		Thread.sleep(2000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
	
		
		Thread.sleep(2000);
		//Click the Close button of required menu item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li[1]/a")).click();
					
		Thread.sleep(2000);
		//Change the Display group icon
		driver.findElement(By.id("displayGroupImage")).sendKeys(Utility.getProperty("DisplayGroupImage"));
		
		Thread.sleep(1000);
		//Click the Sort A-Z button
		driver.findElement(By.xpath("//a[.='Sort A-Z']")).click();
		
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(3000);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group updated Failed");
		}
		Thread.sleep(5000);
		
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"1");
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(3000);
		
		//Check weather name changes are updated or not
		if(driver.getPageSource().contains(Utility.getProperty("DisplayGroupName")+"1"))
		{
			test.log(LogStatus.PASS, "Edited name is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits name is updated Failed");
		}
		
		Thread.sleep(2000);
		//Check weather the sorting order is correct or not
		if(driver.findElement(By.xpath("//a[@class='btn btn-small btn-inverse active']")).getText().equalsIgnoreCase("Sort A-Z"))
		{
			test.log(LogStatus.PASS, "Edited sorting is updated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Edits sorting is updated Failed");
		}
		Thread.sleep(5000);
		//Click the display image remove button
		driver.findElement(By.xpath("//i[@class='fa fa-remove']")).click();
		Thread.sleep(5000);
		
		//Click the Cancel button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=25)
	public void Display_Groups_method_deleteDisplayGroup(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"11");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Delete button
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		//Switch the control to the alert pouup
		Alert x = driver.switchTo().alert();
		//Capture the alert message									
		String alertMessage = driver.switchTo().alert().getText();
		//Print the alert message
		System.out.println(alertMessage);
		//Accept the alert
		x.accept();
		Thread.sleep(3000);
		//Check the menu item deleted or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup inactivated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group deleted Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group deleted Failed");
		}
		Thread.sleep(5000);
		
		
		//Click the Active button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();
		
		Thread.sleep(3000);
		//Click the success button
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
		Thread.sleep(1000);
		
		//Switch the control to the alert pouup
		Alert x1 = driver.switchTo().alert();
		//Capture the alert message									
		String alertMessage1= driver.switchTo().alert().getText();
		//Print the alert message
		System.out.println(alertMessage1);
		//Accept the alert
		x1.accept();
		Thread.sleep(4500);
		
		//Check the menu item activated or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Activated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group activated Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group activated Failed");
		}
		Thread.sleep(5000);
		
		
		//Click the In-Active button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();

		Thread.sleep(5000);
	}
	
	@Test(enabled=false,priority=26)
	public void Display_Groups_method_editDisplayGroup_TimePeriod_AS_DaysOfWeek(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"11");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(3000);


		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"10");
		Thread.sleep(15000);

		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/a")).click();
		//Enter the required Time Period
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys("Days of Week");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click the Days of a week Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[5]/div/div/ul")).click();
		//Enter the required Day
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[5]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[6]/div/label/span")).click();

		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Click Any One of the Color
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/div[2]/div[1]/div[7]")).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//Click the Sort Z-A button
		//driver.findElement(By.xpath("//a[.='Sort Z-A']")).click();
		//Create the Log
		//test.log(LogStatus.PASS, "Menu items are arranged in descending(Sort Z to A) order");
		
		Thread.sleep(3000);
		//Click the Sort A-Z button
		//driver.findElement(By.xpath("//a[.='Sort A-Z']")).click();
		//Create the Log
		//test.log(LogStatus.PASS, "Menu items are arranged in ascending(Sort A to Z) order");
		
		//Thread.sleep(3000);
		//Click the Custom Sort
		//driver.findElement(By.xpath("//a[.='Custom sort']")).click();
		// Element which needs to drag.
		//WebElement From = driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[2]/div/div[2]/div/div/ol/li[3]/div"));
		// Element on which need to drop.
		//WebElement To = driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[2]/div/div[2]/div/div/ol/li[1]/div"));
		// Using Action class for drag and drop.
		//Actions act = new Actions(driver);
		// Dragged and dropped.
		//act.dragAndDrop(From, To).build().perform();
		//Create the Log
		//test.log(LogStatus.PASS, "Menu items are arranged by Custom Sort");

		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group updated Successfully for Applicable time period as Days of Week");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group updated Failed for Applicable time period as Days of Week");
		}

		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=27)
	public void Display_Groups_method_editDisplayGroup_TimePeriod_AS_DaysOfMonth(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"101");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(3000);


		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"11");
		Thread.sleep(3000);
/*		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("COKE");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
*/
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/a")).click();
		//Enter the required Time Period
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys("Days of Month");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click the Required Date from the Days of a Month
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[5]/div/table/tbody/tr[2]/td[3]/button")).click();

		Thread.sleep(3000);
		//Enable or Disable Restriction Methods
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[6]/div/label/span")).click();
		//Click the Months OIption
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div/ul")).click();
		//Enter the Required Month
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div/ul/li/input")).sendKeys("MAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/label/span")).click();

		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}

		
		//Click Any One of the Color
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[10]/div/div/div[2]/div[1]/div[7]")).click();
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group updated Successfully for Applicable time period as Days of Month");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group updated Failed for Applicable time period as Days of Month");
		}

		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=28)
	public void Display_Groups_method_editDisplayGroup_TimePeriod_AS_DateRange(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"111");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(3000);


		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"12");
		Thread.sleep(3000);
/*		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("COKE");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
*/
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/a")).click();
		//Enter the required Time Period
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys("Date Range");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Clear the From Date Field
		driver.findElement(By.name("fromDate")).clear();
		//Enter the From Date
		driver.findElement(By.name("fromDate")).sendKeys("16-May-2020");
		//Clear the To Date Field
		driver.findElement(By.name("endDate")).clear();
		//Enter the To Date
		driver.findElement(By.name("endDate")).sendKeys("20-May-2020");
	
	
		Thread.sleep(3000);
		//Enable or Disable Restriction days
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/label/span")).click();
		//Click the DAys of a week
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/ul")).click();
		//Enter the Required Day
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Enable or Disable the Restriction Time
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/label/span")).click();

		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[10]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[10]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[10]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Click Any One of the Color
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[11]/div/div/div[2]/div[1]/div[7]")).click();
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group updated Successfully for Applicable time period as Date Range");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group updated Failed for Applicable time period as Date Range");
		}

		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=29)
	public void Display_Groups_method_editDisplayGroup_TimePeriod_AS_SpecificDate(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"121");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(3000);


		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"13");
		Thread.sleep(3000);
/*		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("COKE");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
*/
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/a")).click();
		//Enter the required Time Period
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys("Specific date");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Clear the Date Field
		driver.findElement(By.name("date")).clear();
		//Enter the Date
		driver.findElement(By.name("date")).sendKeys("16-May-2020");

		//Enable or Disable the Restriction Time
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[6]/div/label/span")).click();

		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Click Any One of the Color
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/div[2]/div[1]/div[7]")).click();
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group updated Successfully for Applicable time period as Specific Date");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group updated Failed for Applicable time period as Specific Date");
		}

		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=30)
	public void Display_Groups_method_editDisplayGroup_TimePeriod_AS_StartDateAndEndDateTime(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("DisplayGroupName")+"131");
		Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
		
		//Click the Edit icon
		Thread.sleep(6000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		Thread.sleep(2000);


		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"14");
		Thread.sleep(2000);
/*		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("COKE");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
*/
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/a")).click();
		//Enter the required Time Period
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys("Start date time & end date time");
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Clear the From Date Field
		driver.findElement(By.name("fromDate")).clear();
		//Enter the From Date
		driver.findElement(By.name("fromDate")).sendKeys("16-May-2040");
		//Clear the To Date Field
		driver.findElement(By.name("endDate")).clear();
		//Enter the To Date
		driver.findElement(By.name("endDate")).sendKeys("20-May-2040");
		
		//Enable or Disable the Restriction days option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[7]/div/label/span")).click();
		
		//Click the Days of a Week Function
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/ul")).click();
		//Enter the Required day
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/ul/li/input")).sendKeys("FRIDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[8]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		//Click Any One of the Color
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[10]/div/div/div[2]/div[1]/div[7]")).click();
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3500);
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("DisplayGroup Updated Successfully"))
		{
			test.log(LogStatus.PASS, "New Display Group updated Successfully for Applicable time period as Start date time & end date time");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group updated Failed for Applicable time period as Start date time & end date time");
		}

		Thread.sleep(5000);
		
	}
	
	@Test(enabled=false,priority=31)
	public void Display_Groups_method_verifyCancelButton(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Click Add Display Group Icon or button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]")).click();
		

		Thread.sleep(5000);
		//Check the New Display Group form is loaded or not
		if(driver.findElement(By.xpath("//span[.='NEW DISPLAY GROUP']")).getText().equalsIgnoreCase("NEW DISPLAY GROUP"))
		{
			test.log(LogStatus.PASS, "New Display Group form opened Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group form opened Failed");
		}
		
		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"14");
		
		Thread.sleep(3000);
		//Click the Cancel button
		driver.findElement(By.cssSelector("a.btn.btn-small.btn-danger.ng-binding")).click();
		
		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//a[.='Display Groups']")).getText().equalsIgnoreCase("Display Groups"))
		{
			test.log(LogStatus.PASS, "Cancel button working fine");
		}
		else
		{
			test.log(LogStatus.FAIL, "Cancel button not worked");
		}

		Thread.sleep(5000);
		
	}

	@Test(enabled=false,priority=33)
	public void Display_Groups_method_addSameName(WebDriver driver) throws Exception
	{
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		Thread.sleep(3000);
		//Click Add Display Group Icon or button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]")).click();
		Thread.sleep(3000);


		//Clear the name field
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).clear();
		//Enter the name
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("DisplayGroupName")+"14");
		Thread.sleep(15000);
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		//Click the MenuItems Option
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul")).click();
		//Enter the required Menu Item
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).click();
		//Press Enter Key
		driver.findElement(By.xpath("//form[@name='displayGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		//Click the Save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);

		//Check the New Display Group saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Error in validation"))
		{
			test.log(LogStatus.PASS, "New Display Group added Fail, User cannot be able to give the same group name");
			Thread.sleep(3000);
			//Find the validation message for the name field
			if(driver.findElement(By.xpath("//p[@class='help-block ng-binding']")).getText().equalsIgnoreCase("Name already exists"))
			{
				test.log(LogStatus.PASS, "Group name is already exist");
			}
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);Thread.sleep(3000);
			driver.findElement(By.xpath("//a[.='Cancel']")).click();
			
		}
		else
		{
			test.log(LogStatus.FAIL, "New Display Group added success, User can be able to give the same group name");
		}

		Thread.sleep(5000);
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
