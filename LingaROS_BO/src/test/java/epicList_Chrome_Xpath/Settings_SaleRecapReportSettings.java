package epicList_Chrome_Xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class Settings_SaleRecapReportSettings {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_SaleRecapReportSettings");
	
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
			Sale_Recap_Settings_Method_openpage(driver);
			Sale_Recap_Settings_Method_edit(driver);
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

	@Test(enabled=false,priority=30)
	public void Sale_Recap_Settings_Method_openpage(WebDriver driver) throws Exception
		{
			
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"saleRecapReportSettings");
			
				
			Thread.sleep(3000);
		
			//Check whether the Sale recap Report Settings page is loaded or not
			if(driver.findElement(By.xpath("//a[.='Sale recap Report Settings']")).getText().equalsIgnoreCase("Sale recap Report Settings"))
			{
				test.log(LogStatus.PASS, " Sale recap Report Settings page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, " Sale recap Report Settings page loaded fail");
			}

			Thread.sleep(3000);

		}	
		
	@Test(enabled=false,priority=31)
	public void Sale_Recap_Settings_Method_edit(WebDriver driver) throws Exception
	{
			Thread.sleep(2000);
			Thread.sleep(1000);
			//Enable the Guests/Checks check box
			driver.findElement(By.name("guestOrCheck")).click();
			Thread.sleep(2000);
			//Check weather the Guests/Checks check box is enabled or not
			if(driver.findElement(By.name("guestOrCheck")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the Guests/Checks check box
				driver.findElement(By.name("guestOrCheck")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the grandSales check box is enabled or not
			if(driver.findElement(By.name("grandSales")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the grandSales check box
				driver.findElement(By.name("grandSales")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the grossReceipt check box is enabled or not
			if(driver.findElement(By.name("grossReceipt")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the grossReceipt check box
				driver.findElement(By.name("grossReceipt")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the grossVoid check box is enabled or not
			if(driver.findElement(By.name("grossVoid")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the grossVoid check box
				driver.findElement(By.name("grossVoid")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the netVoid check box is enabled or not
			if(driver.findElement(By.name("netVoid")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the netVoid check box
				driver.findElement(By.name("netVoid")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the creditCardSplitUps check box is enabled or not
			if(driver.findElement(By.name("creditCardSplitUp")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the creditCardSplitUp check box
				driver.findElement(By.name("creditCardSplitUp")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the otherPayments check box is enabled or not
			if(driver.findElement(By.name("otherPayments")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the otherPayments check box
				driver.findElement(By.name("otherPayments")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the taxes check box is enabled or not
			if(driver.findElement(By.name("taxes")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the taxes check box
				driver.findElement(By.name("taxes")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the taxExempt check box is enabled or not
			if(driver.findElement(By.name("taxExempt")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the taxExempt check box
				driver.findElement(By.name("taxExempt")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the departmentSummary check box is enabled or not
			if(driver.findElement(By.name("departmentSummary")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the departmentSummary check box
				driver.findElement(By.name("departmentSummary")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the categorySummary check box is enabled or not
			if(driver.findElement(By.name("categorySummary")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the categorySummary check box
				driver.findElement(By.name("categorySummary")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the hourlySummary check box is enabled or not
			if(driver.findElement(By.name("hourlySummary")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the hourlySummary check box
				driver.findElement(By.name("hourlySummary")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the openingBalance check box is enabled or not
			if(driver.findElement(By.name("openingBalance")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the openingBalance check box
				driver.findElement(By.name("openingBalance")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the paid check box is enabled or not
			if(driver.findElement(By.name("paid")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the paid check box
				driver.findElement(By.name("paid")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Cash drop check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='store.saleRecapReport.cashDrop']")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the Cash drop check box
				driver.findElement(By.xpath("//input[@ng-model='store.saleRecapReport.cashDrop']")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the cashExpected check box is enabled or not
			if(driver.findElement(By.name("cashExpected")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the cashExpected check box
				driver.findElement(By.name("cashExpected")).click();
				Thread.sleep(1000);
			}
			
			Thread.sleep(2000);
			//Check weather the Over Shortage check box is enabled or not
			if(driver.findElement(By.xpath("//input[@ng-model='store.saleRecapReport.overShortage']")).isSelected()){}
			else
			{
				Thread.sleep(1000);
				//Enable the Over Shortage check box
				driver.findElement(By.xpath("//input[@ng-model='store.saleRecapReport.overShortage']")).click();
				Thread.sleep(1000);
			}
			Thread.sleep(2000);
			//Check weather the Exclude Opening Balance Calculation is enabled or not
			if(driver.findElement(By.xpath("//input[@name= 'excludeOpeningBalance']")).isSelected()) {}
			else
			{
				Thread.sleep(3000);
				//Enable the Exclude Opening Balance Calculation
				driver.findElement(By.xpath("//input[@name= 'excludeOpeningBalance']")).click();
			}
			
			Thread.sleep(1000);
			//Check whether the User defined name is selected or not
			if(driver.findElement(By.xpath("//input[@ng-model='store.saleRecapReport.paymentMethodOrType']/../../../div[contains(.,'User')]/div/input")).isSelected()){}
			else
			{
				//Click the user defined name check box
				driver.findElement(By.xpath("//input[@ng-model='store.saleRecapReport.paymentMethodOrType']/../../../div[contains(.,'User')]/div/input")).click();
			}
			
			Thread.sleep(2500);
			//Click the update button
			driver.findElement(By.xpath("//span[.='Update']")).click();
			Thread.sleep(3000);
			
			//Check weather theSale recap Report Settings is updated or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("SaleRecap Report Settings updated successsfully"))
			{
				test.log(LogStatus.PASS, "Sale recap Report Settings updated successfully");
			}
			
			else{
				test.log(LogStatus.FAIL, "Sale recap Report Settings updated fail");
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}
}
