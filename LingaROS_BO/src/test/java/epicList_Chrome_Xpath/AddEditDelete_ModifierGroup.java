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


public class AddEditDelete_ModifierGroup {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Modifier Group");

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
			Modifiers_Group_method_openModifier_Groups(driver);
			Modifiers_Group_method_refreshModifier_Groups_Page(driver);
			Modifiers_Group_method_add_Modifier_Groups(driver);
			Modifiers_Group_method_edit_Modifier_Group(driver);
			Modifiers_Group_method_edit_Modifier_Group_Customer_Sort(driver);
			Modifiers_Group_method_delete_Modifier(driver);
			Modifiers_Group_method_cancelModifier_Group(driver);
			Modifiers_Group_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=141)
		public void Modifiers_Group_method_openModifier_Groups(WebDriver driver) throws Exception
		{
	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Modifier Groups']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Modifier groups Option		
			driver.findElement(By.xpath("//span[.='Modifier Groups']")).click();*/
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"modifierGroups");
			Thread.sleep(8000);
			//Check Modifier groups page opened or not
			if(driver.findElement(By.xpath("//a[.='Modifier Groups']")).getText().equalsIgnoreCase("Modifier Groups"))
			{
				test.log(LogStatus.PASS, "Modifier Groups page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Groups loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=142)
		public void Modifiers_Group_method_refreshModifier_Groups_Page(WebDriver driver) throws InterruptedException
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/a[1]/i")).click();
			Thread.sleep(5000);
			
			//Check Modifier Groups page opened or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Modifier Groups"))
			{
				test.log(LogStatus.PASS, "Modifier Groups page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Groups page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=144)
		public void Modifiers_Group_method_add_Modifier_Groups(WebDriver driver) throws Exception
		{
			for (int i = 0; i < 10; i++) {
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
				driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			}
			Thread.sleep(3000);
			//Click the Add Modifier Groups button
			driver.findElement(By.xpath("//a[@id='modifierGroup']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifiers Group form loaded or not
			if(driver.findElement(By.xpath("//span[.='NEW MODIFIER GROUP']")).getText().equalsIgnoreCase("NEW MODIFIER GROUP"))
			{
				test.log(LogStatus.PASS, "New Modifier Group form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier Group form Loaded Fail");
			}
			
			Thread.sleep(6000);
			//Clear the name field
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[1]/div/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Modifier_Group_Name"));
			
			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul")).click();
			//Enter the required modifier
			//driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul")).click();
			//Enter the required modifier
			//driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul")).click();
			//Enter the required modifier
			//driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("MUSHROOMS");
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul")).click();
			//Enter the required modifier
			//driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("OLIVES");
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul")).click();
			//Enter the required modifier
			//driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("PEPPERONI");
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

			Thread.sleep(2000);
			//Click the Modifiers option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul")).click();
			//Enter the required modifier
			//driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys("SAUSAGE");
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[2]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

			
			//Clear the priority field
			driver.findElement(By.name("priority")).clear();
			//Enter the priority
			driver.findElement(By.name("priority")).sendKeys("3");
			
			Thread.sleep(3000);
			//Enable or Disable the Pizza Topping Option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[4]/div/label/span")).click();
			
			Thread.sleep(3000);
			//Enable or Disable the Set Price Here Option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[5]/div/label/span")).click();
			
			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[7]/button")).click();
			
			Thread.sleep(3000);
			//Click the Serving Size Level Option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[6]/table/tbody/tr/td[1]/div/div/a")).click();
			//Enter the required Serving Size Level
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[6]/table/tbody/tr/td[1]/div/div/div/div/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the Enter Key
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[6]/table/tbody/tr/td[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(2000);
			//Clear the Price Option
			driver.findElement(By.name("price")).clear();
			//Enter the Price
			driver.findElement(By.name("price")).sendKeys("125");
			
			Thread.sleep(3000);
			//Click the Add Serving Size Level
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[7]/button")).click();

			//Click the Close button of Last Added SSL
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[6]/table/tbody/tr[2]/td[3]/span/a/span/i")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();

			Thread.sleep(5000);
			//Click the Sort Z-A button
			driver.findElement(By.xpath("//a[.='Sort Z-A']")).click();
			Thread.sleep(5000);
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[3]/div/div/button")).click();
			
			Thread.sleep(3500);
			//Check weather the new modifier group is saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier group saved successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier group Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier group saved fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=145)
		public void Modifiers_Group_method_edit_Modifier_Group(WebDriver driver) throws Exception
		{

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Enable or Disable the  Pizza Topping option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[4]/div/label/span")).click();
			
			Thread.sleep(2000);
			//Enable or Disable the Set Price Here option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[5]/div/label/span")).click();

			Thread.sleep(5000);
			//Click the sort Option
			driver.findElement(By.xpath("//a[.='Sort A-Z']")).click();

			Thread.sleep(10000);
			//Click the Update button
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[3]/div/div/button")).click();
			
			Thread.sleep(3500);
			//Check weather the new Modifier Group is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier group updated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Group updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier Group updated fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=146)
		public void Modifiers_Group_method_edit_Modifier_Group_Customer_Sort(WebDriver driver) throws Exception
		{

			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Enable or Disable the  Pizza Topping option
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[1]/div[4]/div/label/span")).click();
			
			Thread.sleep(2000);
			//Click the Taxes Option
			driver.findElement(By.xpath("//a[.='Custom sort']")).click();

			Thread.sleep(5000);
			// Element which needs to drag.
			WebElement From = driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[2]/div/div[2]/div/div/ol/li[3]/div"));
			// Element on which need to drop.
			WebElement To = driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[2]/div/div[2]/div/div/ol/li[1]/div"));
			Thread.sleep(2000);
			// Using Action class for drag and drop.
			Actions act = new Actions(driver);
			Thread.sleep(2000);
			// Dragged and dropped.
			act.clickAndHold(From).moveToElement(To).release(To).build().perform();
			
			
			Thread.sleep(10000);
			//Click the Update button
			driver.findElement(By.xpath("//form[@name='modifierGroupCreation']/div[3]/div/div/button")).click();
			
			Thread.sleep(2000);
			//Check weather the new Modifier Group is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier group updated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier Group updated successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier Group updated fail");
			}
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=147)
		public void Modifiers_Group_method_delete_Modifier(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check the modifier group deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier group inactivated successfully"))
			{
				test.log(LogStatus.PASS, "New Modifier group is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Modifier group is deleted Failed");
			}
			Thread.sleep(3000);
			
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
			
			//Click the success button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(1000);
			
			//Click the Yes button in the popup
			Thread.sleep(1500);driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			
			//Check the modifier activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Modifier group activated successfully"))
			{
				test.log(LogStatus.PASS, "Modifier Group is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Group is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=148)
		public void Modifiers_Group_method_cancelModifier_Group(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Modifier_Group_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(3000);
			//Click the Edit button
			Thread.sleep(2000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Cancel']")).click();
			
			Thread.sleep(2000);
			//Check the course form is closed or not
			if(driver.findElement(By.xpath("//a[.='Modifier Groups']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Modifier Groups page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Modifier Groups page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=149)
		public void Modifiers_Group_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();
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
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();
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
