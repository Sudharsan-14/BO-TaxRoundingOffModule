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

public class AddEditDelete_Tax {
	
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("AddEditDelete Tax");

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
			Tax_method_openTax_Page(driver);
			Tax_method_refreshTax_Page(driver);
			Tax_method_add_Tax(driver);
			Tax_method_edit_Tax_Item(driver);
			Tax_method_edit_Tax_Check(driver);
			Tax_method_edit_Tax_TaxOnItemTax(driver);
			Tax_method_edit_Tax_TaxOnCheckTax(driver);
			Tax_method_delete_Tax(driver);
			Tax_method_cancelTax(driver);
			Tax_method_inactiveAndActive_Button(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=61)
		public void Tax_method_openTax_Page(WebDriver driver) throws Exception
		{
	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//span[.='Taxes']"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Taxes Option		
			driver.findElement(By.xpath("//span[.='Taxes']")).click();*/
			//driver.get(Utility.getURLProperty("PAI_Taxes"));
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"tax");
	
			Thread.sleep(5000);
			//Check tax page opened or not
			if(driver.findElement(By.xpath("//a[.='Taxes']")).getText().equalsIgnoreCase("Taxes"))
			{
				test.log(LogStatus.PASS, "Taxes page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Taxes page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=62)
		public void Tax_method_refreshTax_Page(WebDriver driver) throws InterruptedException
		{
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//a[@class='btn btn-md btn-border btn-refresh']")).click();
			Thread.sleep(5000);
			
			//Check Taxes page opened or not
			if(driver.findElement(By.xpath("//h3[@class='ng-binding']")).getText().equalsIgnoreCase("Taxes"))
			{
				test.log(LogStatus.PASS, "Taxes page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Taxes page loaded Failed");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=64)
		public void Tax_method_add_Tax(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click the Add Tax button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).click();
			
			Thread.sleep(2000);
			//Check weather the new Tax form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Tax']")).getText().equalsIgnoreCase("New Tax"))
			{
				test.log(LogStatus.PASS, "New Tax form Loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax form Loaded Fail");
			}
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[1]/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[1]/input")).sendKeys(Utility.getProperty("Tax_Name"));
			
			Thread.sleep(5000);
			//Select the required Apply To option
			Select applyTo = new Select(driver.findElement(By.name("applyTo")));
			applyTo.selectByValue("Item");
			
	
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Enable or Disable the Default Tax
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[4]/label/span")).click();
			
			Thread.sleep(3000);
			//Enable or Disable the Inclusive Tax
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[6]/label/span")).click();
	
			
			//Clear the percentage field
			driver.findElement(By.name("percentage")).clear();
			//Enter the required percentage field
			driver.findElement(By.name("percentage")).sendKeys("1000");
			
			//Clear the Tax Code
			driver.findElement(By.xpath("//input[@name='taxCode']")).clear();
			//Enter the Tax Code
			driver.findElement(By.xpath("//input[@name='taxCode']")).sendKeys(Utility.getProperty("Tax_Code"));
			
			Thread.sleep(2000);
			//Click the save button
			driver.findElement(By.xpath("//button[@id='saveTax']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Tax is saved or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax saved successfully"))
			{
				test.log(LogStatus.PASS, "New Tax Saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax saved fail");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=65)
		public void Tax_method_edit_Tax_Item(WebDriver driver) throws Exception
		{
	
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Tax_Name")+"1");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(2000);
			//Clear the name field
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[1]/input")).clear();
			//Enter the required name
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[1]/input")).sendKeys(Utility.getProperty("Tax_Name")+"1");
	
			Thread.sleep(3000);
			//Enable or Disable the Default Tax
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[4]/label/span")).click();
						
			//Enable or Disable the Inclusive Tax
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[6]/label/span")).click();
	
			//Enable or Disable the Quantity Tax
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[5]/label/span")).click();
	
			//Click the Add Set Percentage option
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[6]/div[1]/div/a/i")).click();
			
			Thread.sleep(2000);
			//Clear the quantity field
			driver.findElement(By.name("quantity")).clear();
			//Enter the required Quantity
			driver.findElement(By.name("quantity")).sendKeys("3");
			
			//Clear the price field
			driver.findElement(By.name("price")).clear();
			//Enter the required price
			driver.findElement(By.name("price")).sendKeys("1450");
			
			Thread.sleep(3000);
			//Click the Add Set Percentage option
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[6]/div[1]/div/a/i")).click();
	
			Thread.sleep(3000);
			//Click the Close button of Last Set percentage option
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[6]/div[4]/ng-form/div/div[3]/a/i")).click();
			
			Thread.sleep(1000);
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveTax']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Tax is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax updated successfully"))
			{
				test.log(LogStatus.PASS, "New Tax updated successfully for Item");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax updated fail for Item");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=66)
		public void Tax_method_edit_Tax_Check(WebDriver driver) throws Exception
		{
	
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Tax_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
	
			Thread.sleep(3000);
			//Select the required Apply To option
			Select applyTo = new Select(driver.findElement(By.name("applyTo")));
			applyTo.selectByVisibleText("CHECK");
	
			Thread.sleep(2000);
			//Enable or Disable the Apply on Total
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[4]/label/span")).click();
						
			Thread.sleep(2000);
			//Clear the check amount field
			driver.findElement(By.name("amount")).clear();
			//Enter the required check amount
			driver.findElement(By.name("amount")).sendKeys("155");
			
			//Clear the percentage field
			driver.findElement(By.name("percentage")).clear();
			//Enter the required percentage
			driver.findElement(By.name("percentage")).sendKeys("1450");
			
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveTax']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Tax is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax updated successfully"))
			{
				test.log(LogStatus.PASS, "New Tax updated successfully for Check");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax updated fail for Check");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=67)
		public void Tax_method_edit_Tax_TaxOnItemTax(WebDriver driver) throws Exception
		{
	
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Tax_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
	
			Thread.sleep(3000);
			//Select the required Apply To option
			Select applyTo = new Select(driver.findElement(By.name("applyTo")));
			applyTo.selectByVisibleText("Tax On Item Tax");
	
			Thread.sleep(2000);
			//Clear the percentage field
			driver.findElement(By.name("percentage")).clear();
			//Enter the required percentage
			driver.findElement(By.name("percentage")).sendKeys("1450");
			
			Thread.sleep(2000);
			//Click the Apply For option
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[7]/div/ul")).click();
			//Enter the required apply for option
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[7]/div/ul/li/input")).sendKeys(Keys.ARROW_DOWN);
			//Press the enter button
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[7]/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(1000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveTax']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Tax is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax updated successfully"))
			{
				test.log(LogStatus.PASS, "New Tax updated successfully for Tax On Item Tax");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax updated fail Tax On Item Tax");
			}
			Thread.sleep(5000);
		}
	
		@Test(enabled=false,priority=68)
		public void Tax_method_edit_Tax_TaxOnCheckTax(WebDriver driver) throws Exception
		{
	
			Thread.sleep(3000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Tax_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(2000);
			//Click the Edit button
			Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
	
			Thread.sleep(3000);
			//Select the required Apply To option
			Select applyTo = new Select(driver.findElement(By.name("applyTo")));
			applyTo.selectByVisibleText("Tax On Check Tax");
	
			Thread.sleep(2000);
			//Clear the percentage field
			driver.findElement(By.name("percentage")).clear();
			//Enter the required percentage
			driver.findElement(By.name("percentage")).sendKeys("1450");
			
			Thread.sleep(2000);
			//Click the Apply For option
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[7]/div/ul")).click();
			
			//Enter the required apply for option
			//driver.findElement(By.xpath("//form[@name='taxCreation']/div[4]/div/ul/li/input")).sendKeys("Default");
			
			//Press the enter button
			driver.findElement(By.xpath("//form[@name='taxCreation']/div[7]/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the Update button
			driver.findElement(By.xpath("//button[@id='saveTax']")).click();
			
			Thread.sleep(2000);
			//Check weather the new Tax is updated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax updated successfully"))
			{
				test.log(LogStatus.PASS, "New Tax updated successfully Tax On Check Tax");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax updated fail Tax On Check Tax");
			}
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=69)
		public void Tax_method_delete_Tax(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Tax_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(3000);
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			
			Thread.sleep(3000);
			//Check the Tax deleted or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax Inactivated Successfully"))
			{
				test.log(LogStatus.PASS, "New Tax is deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Tax is deleted Failed");
			}
			
			Thread.sleep(3000);
			//Click the Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(3000);
			
			Thread.sleep(3000);
			//Click the success button
			driver.findElement(By.cssSelector("i.fa.fa-check")).click();
			
			
			Thread.sleep(3000);
			//Click the Yes button in the popup
			driver.findElement(By.linkText("Yes")).click();
			
			Thread.sleep(3000);
			//Check the course activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Tax Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Tax is activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tax is activated Failed");
			}
			
			Thread.sleep(5000);
			//Click the Active Button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
	
		}
		
		@Test(enabled=false,priority=70)
		public void Tax_method_cancelTax(WebDriver driver) throws Exception
		{
			Thread.sleep(5000);
			//Clear the Search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Tax_Name")+"11");
			Thread.sleep(2000);driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Keys.BACK_SPACE);
			
			Thread.sleep(3000);
			//Click the Edit button
			Thread.sleep(3000);driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			
			Thread.sleep(5000);
			//Click the Cancel button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			Thread.sleep(2000);
			//Check the Tax form is closed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[1]/div/div/div/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Tax page is Closed after click the close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "Tax page is displayed after click the close button");
			}
			
			Thread.sleep(5000);
		}
		
		@Test(enabled=false,priority=71)
		public void Tax_method_inactiveAndActive_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();Thread.sleep(2000);
			Thread.sleep(1000);
			
			//Click the Active button
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
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
			driver.findElement(By.xpath("//input[@ng-model='activeStatus']")).click();
			Thread.sleep(3000);
			
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
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}
		@Test(priority=3, enabled=false)
		public void watchTutorial(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Watch Tutorial Option
			driver.findElement(By.xpath("//span[contains(.,'Tutorial')]")).click();
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
