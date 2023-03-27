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

public class AddEditDelete_Modifiers {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Modifiers");

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
			Modifiers_method_openModifiers(driver);
			Modifiers_method_refreshModifiers_Page(driver);
			Modifiers_method_add_Modifiers(driver);
			Modifiers_method_edit_Modifiers(driver);
			Modifiers_method_delete_Modifier(driver);
			Modifiers_method_cancelModifier_BasicDetails(driver);
			Modifiers_method_cancelModifier_Prefixes(driver);
			//Modifiers_method_add_Modifiers_Save_BasicDetails(driver);
			//Modifiers_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=121)
		public void Modifiers_method_openModifiers(WebDriver driver) throws Exception
		{
	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Modifiers']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Modifiers Option		
			driver.findElement(By.xpath("//span[.='Modifiers']")).click();*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"modifiers");
			Thread.sleep(8000);
			//Check Modifiers page opened or not
			if(driver.findElement(By.xpath("//a[.='Modifiers']")).getText().equalsIgnoreCase("Modifiers"))
			{
				test.log(LogStatus.PASS, "Modifiers page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=122)
		public void Modifiers_method_refreshModifiers_Page(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]/i")).click();
			Thread.sleep(5000);
			
			//Check Modifiers page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Modifiers"))
			{
				test.log(LogStatus.PASS, "Modifiers page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=124)
		public void Modifiers_method_add_Modifiers(WebDriver driver) throws Exception
		{
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			//Click the Add Modifiers button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[2]")).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifiers form loaded or not
			if(driver.findElement(By.xpath("//span[.='NEW MODIFIER']")).getText().equalsIgnoreCase("NEW MODIFIER"))
			{
				test.log(LogStatus.PASS, "New Modifiers form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifiers form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[1]/div/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Modifier_Name"));
			
			Thread.sleep(2000);
			//Enable or Disable the Include Default button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[2]/div/label/span")).click();
			
			Thread.sleep(2000);
			//Click the Add Tax button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[2]/a/i")).click();
			
			Thread.sleep(2000);
			//Check weather the new tax form is loaded or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New tax"))
			{
				test.log(LogStatus.PASS, "New Tax form is loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax form loaded fail");
			}
			
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[1]/div/input")).clear();
			//Enter the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[1]/div/input")).sendKeys(Utility.getProperty("Modifier_Tax_Name"));
			
			//Enable or disable the Default tax option
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[2]/div[1]/div/label/span")).click();
			
			//Enable or disable the Inclusive Tax
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[2]/div[2]/div/label/span")).click();

			Thread.sleep(2000);
			//Clear the percentage field
			driver.findElement(By.name("percentage")).clear();
			//Enter the required percentage field
			driver.findElement(By.name("percentage")).sendKeys("1230");
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/div/button")).click();
			
			Thread.sleep(2000);
			//Add the Kitchen printer button   
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[2]/a/i")).click();
			
			Thread.sleep(2000);
			//Check weather the new kitchen printer form is loaded or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[1]/h3/span")).getText().equalsIgnoreCase("New kitchenPrinter"))
			{
				test.log(LogStatus.PASS, "New kitchen printer form is loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New kitchen printer form loaded fail");
			}
			
			Thread.sleep(3000);
			//Click the Type field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[1]/div/div/a")).click();
			//Enter the required input
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[1]/div/div/div/div/input")).sendKeys("KDS");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[2]/div/input")).clear();
			//Enter the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[2]/div/input")).sendKeys(Utility.getProperty("Modifier_Kitchen_Printer_Name"));

			//Clear the IP field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/input")).clear();
			//Enter the IP field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[3]/div/input")).sendKeys(Utility.getProperty("Modifier_Kitchen_Printer_IP"));
			
			//Enable or disable the Enable Service Type Restriction option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[4]/div/label/span")).click();
			
			Thread.sleep(2000);
			//Click the Service Type Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[5]/div/div/ul")).click();
			//Enter the required service type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[5]/div/div/ul/li/input")).sendKeys("BarTab");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			//Enable or disable the Apply to all categories
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[6]/div/label/span")).click();
			
			//Enable or disable to apply all menu item
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[7]/div/label/span")).click();		
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div[8]/div/div/button")).click();

			Thread.sleep(2000);
			//CLeare the maximum number of time
			driver.findElement(By.name("maxNoOfTimes")).clear();
			//Enter the maximum number of time
			driver.findElement(By.name("maxNoOfTimes")).sendKeys("3");
			
			//Click the Modify With Option	
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[2]/div/div/a")).click();
			//Enter the required option	//
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[2]/div/div/div/div/input")).sendKeys("TOPPINGS");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(3000);
			//Enable or disable the Set Price Here Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[3]/div/input")).click();
			
			Thread.sleep(3000);
			//Enable or disable the Show modifier
			driver.findElement(By.xpath("//label[.='Show Modifier']/../div/input")).click();

			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
			
			Thread.sleep(5000);
			//Click the Add Prefixes button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[2]/button/span/i")).click();
			
			Thread.sleep(2000);
			//CLear the prefix name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[1]/div/div/ng-form/div/div[1]/input")).clear();
			//Enter the required prefix name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[1]/div/div/ng-form/div/div[1]/input")).sendKeys("Add");
			
			//Clear the price field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[1]/div/div/ng-form/div/div[2]/input")).clear();
			//Enter the Price
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[1]/div/div/ng-form/div/div[2]/input")).sendKeys("125");
			
			//Enable or disable the Override modifier price
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[1]/div/div/ng-form/div/div[3]/input")).click();
			
			Thread.sleep(5000);
			//Click the Add Prefixes button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[2]/button/span/i")).click();

			//Click the Close button of Last Added Prefix
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[2]/div/div/div[1]/div[2]/div/div/a/span/i")).click();
			
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[4]/div[1]/button")).click();
			
			Thread.sleep(3000);
			//Click the Serving Size Level Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[1]/ng-form/div/div/a")).click();
			//Enter the required Serving Size Level
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[1]/ng-form/div/div/div/div/input")).sendKeys("EACH");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[1]/ng-form/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Price Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[2]/ng-form/div/div/input")).clear();
			//Enter the Price
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[2]/ng-form/div/div/input")).sendKeys("125");
			  
			Thread.sleep(2000);
			//Clear the Prefix Price Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[3]/div/input")).clear();
			//Enter the Prefix Price
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr/td[3]/div/input")).sendKeys("125");

			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[4]/div[1]/button")).click();
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);Thread.sleep(1500);
			//Click the Close button of Last Added SSL
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/ng-form/div[3]/div/table/tbody/tr[2]/td[4]/span/a/span/i")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();

			Thread.sleep(3000);
			//Click the previous button
			driver.findElement(By.cssSelector("i.fa.fa-arrow-left")).click();
			
			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div/div[2]/div/button")).click();
			
			Thread.sleep(2000);
			//Check weather the new modifier is saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier saved successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier saved fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=125)
		public void Modifiers_method_edit_Modifiers(WebDriver driver) throws Exception
		{

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Include default taxes option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[2]/div/label/span")).click();
			
			//Click the Taxes Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[1]/div/ul")).click();
			//Enter the required Tax
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[1]/div/ul/li/input")).sendKeys("GST");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
		
			//Click the Kitchen Printer Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[1]/div/ul")).click();
			Thread.sleep(1000);
			//Enter the Kitchen Printer
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[1]/div/ul/li/input")).sendKeys("KitchenPrinter_1");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);

			
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[3]/div[2]/div/button")).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifier is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier updated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier updated fail");
			}
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=126)
		public void Modifiers_method_delete_Modifier(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check the modifier deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier is deleted Failed");
			}
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check the modifier activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier activated successfully"))
			{
				test.log(LogStatus.PASS, "Modifier is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=127)
		public void Modifiers_method_cancelModifier_BasicDetails(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Cancel']")).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath("//a[.='Modifiers']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifiers page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=128)
		public void Modifiers_method_cancelModifier_Prefixes(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);

			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(3000);
			//Click the Next button
			driver.findElement(By.cssSelector("i.fa.fa-arrow-right")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Cancel']")).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath("//a[.='Modifiers']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifiers page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifiers page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=129)
		public void Modifiers_method_add_Modifiers_Save_BasicDetails(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click the Add Modifiers button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[2]")).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifiers form loaded or not
			if(driver.findElement(By.xpath("//span[.='NEW MODIFIER']")).getText().equalsIgnoreCase("NEW MODIFIER"))
			{
				test.log(LogStatus.PASS, "New Modifiers form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifiers form Loaded Fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[1]/div/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Modifier_Name")+1);
			
			Thread.sleep(3000);
			//Enable or Disable the Include Default button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[2]/div/label/span")).click();
			
			//Click the Taxes Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[1]/div/ul")).click();
			//Enter the required Tax
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[1]/div/ul/li/input")).sendKeys("GST");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[3]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
		
			//Click the Kitchen Printer Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[1]/div/ul")).click();
			//Enter the Kitchen Printer
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[1]/div/ul/li/input")).sendKeys("KitchenPrinter_1");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[1]/div[4]/div[1]/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//CLeare the maximum number of time
			driver.findElement(By.name("maxNoOfTimes")).clear();
			//Enter the maximum number of time
			driver.findElement(By.name("maxNoOfTimes")).sendKeys("3");
			
			//Click the Modify With Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[2]/div/div/a")).click();
			//Enter the required option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[2]/div/div/div/div/input")).sendKeys("TOPPINGS");
			//Press the Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div[2]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Enable or disable the Set Price Here Option
			driver.findElement(By.xpath("//label[.='Set Price Here']/../div/input")).click();
											
			//Enable or disable the Show modifier
			driver.findElement(By.xpath("//label[.='Show Modifier']/../div/input")).click();
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/form/div/ng-form/div/div[2]/div/button")).click();
			
			Thread.sleep(2000);
			//Check weather the new modifier is saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier saved successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier saved fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=130)
		public void Modifiers_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector("i.fa.fa-check")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Inactive page is displayed after click the Active button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Inactive page is not displayed after click the Active button");
			}
			
			Thread.sleep(3000);
			//Click the InActive button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(1000);
			
			//Check the page
			if(driver.findElement(By.cssSelector("i.fa.fa-trash-o")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Active page is displayed after click the Inactive button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Active page is not displayed after click the Inactive button");
			}
			Thread.sleep(5000);
		}

}
