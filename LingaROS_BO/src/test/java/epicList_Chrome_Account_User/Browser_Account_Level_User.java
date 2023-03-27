package epicList_Chrome_Account_User;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import newReadExcelFile_New.ExcelDataConfig;

public class Browser_Account_Level_User {
	public WebDriver driver;
	
	//ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test;
	
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
			//rep.endTest(test);
			//rep.flush();
		}

		@Test(priority=1)
		public void UPOS_login(WebDriver driver,ExtentTest test) throws Exception
		{
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			try
			{
				if(driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).isDisplayed())
				{
					driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).click();
				}
			}
			catch(Exception e)
			{}
			Thread.sleep(800);
			try
			{
				if(driver.findElement(By.xpath("//a[.='Accept']")).isDisplayed())
				{
					driver.findElement(By.xpath("//a[.='Accept']")).click();
				}
			}
			catch(Exception e)
			{}
			//Clear the text from the user name text box
			driver.findElement(By.name("emailId")).clear();
			//Enter the user name
			driver.findElement(By.name("emailId")).sendKeys(Utility.getProperty("userName_Account_User"));
			Thread.sleep(1000);
			//Clear the password from the password text box
			driver.findElement(By.name("password")).clear();
			//Enter the password
			driver.findElement(By.name("password")).sendKeys(Utility.getProperty("password"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
			//Click the login button
			driver.findElement(By.id("round1")).click();
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//div[@id='navigation']/ul[1]/li/a")).getText().equalsIgnoreCase("My Stores"))
			{
				test.log(LogStatus.PASS, "User Logged in Successfully for Dealer Enterprise ");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged in Failed for Dealer Enterprise ");
			}
			Thread.sleep(2000);
			//Click the refresh button 
			driver.findElement(By.cssSelector("button.btn.btn-sm.btn-primary")).click();
			
			Thread.sleep(2000);
			//Clear the search field 
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the search field 
		     driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Store1"));
			
			//Click the Entered store Dashboard page
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[4]/div/div/div[2]/div/div[1]/a/h5")).click();
			
			//Check if we logged in or not
			try
			{
				if(driver.findElement(By.xpath("//span[.='Dashboard']")).getText().equalsIgnoreCase("Dashboard"))
				{
					test.log(LogStatus.PASS, "Dealer Store level Dashboard page loaded sucessfully ");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Dealer Store level Dashboard page loaded failed ");
			}
			Thread.sleep(5000);
	}

		@Test(priority=1)
		public void Linga_login(WebDriver driver,ExtentTest test) throws Exception
		{			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			try
			{
				if(driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).isDisplayed())
				{Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).click();
				}
			}
			catch(Exception e)
			{}
			try
			{
				if(driver.findElement(By.xpath("//a[.='Accept']")).isDisplayed())
				{
					driver.findElement(By.xpath("//a[.='Accept']")).click();
				}
			}
			catch(Exception e)
			{}
			//Clear the text from the user name text box
			driver.findElement(By.name("txtusername")).clear();
			//Enter the user name   txtusername  emailId
			driver.findElement(By.name("txtusername")).sendKeys(Utility.getProperty("userName_Account_User"));
			//Clear the password from the password text box
			driver.findElement(By.name("txtpassword")).clear();
			//Enter the password    txtpassword  password
			driver.findElement(By.name("txtpassword")).sendKeys(Utility.getProperty("password"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);Thread.sleep(2000);
			
			//Click the login button
			driver.findElement(By.name("submitlogin")).click();
			//By.xpath("//button[@id='round1']") -- staging
			//By.name("submitlogin") -- Prod
			Thread.sleep(30000);   
			//Check if we logged in or not
			if(driver.findElement(By.xpath("//div[@id='navigation']/ul[1]/li/a")).getText().equalsIgnoreCase("My Stores"))
			{
				test.log(LogStatus.PASS, "User Logged in Successfully for Linga Enterprise ");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged in Failed for Linga Enterprise ");
			}
			Thread.sleep(10000);
			//Click the refresh button 
			driver.findElement(By.cssSelector("button.btn.btn-sm.btn-primary")).click();
			
			Thread.sleep(5000);
			//Clear the search field 
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the search field 
		     driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Store1"));
			
			//Click the Entered store Dashboard page
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div[4]/div/div/div[2]/div/div[1]/a/h5")).click();
			
			//Check if we logged in or not
			try
			{
				if(driver.findElement(By.xpath("//span[.='Dashboard']")).getText().equalsIgnoreCase("Dashboard"))
				{
					test.log(LogStatus.PASS, "Linga Store level Dashboard page loaded sucessfully ");
				}
			}
			catch(Exception e)
			{
				test.log(LogStatus.FAIL, "Linga Store level Dashboard page loaded failed ");
			}
			Thread.sleep(8000);
			
			
			//Thread.sleep(15000);
			try
			{
				if(driver.findElement(By.xpath("//span[.='Do You Want to Take a look.. ']")).isDisplayed())
				{
					Thread.sleep(3000);
					//Click Not now
					driver.findElement(By.xpath("//button[.=' Not now']")).click();
				}
			}
			catch(Exception e) {}
			
			
	}

		@Test(priority=1)
		public void Logout(WebDriver driver,ExtentTest test) throws Exception
		{			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
/*			try
			{
				if(driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).isDisplayed())
				{Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@id='popmake-26044']/button")).click();
				}
			}
			catch(Exception e)
			{}
			try
			{
				if(driver.findElement(By.xpath("//a[.='Accept']")).isDisplayed())
				{
					driver.findElement(By.xpath("//a[.='Accept']")).click();
				}
			}
			catch(Exception e)
			{}*/
			
			ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS"))
			{
				JavascriptExecutor je = (JavascriptExecutor) driver;
				//Identify the WebElement which will appear after scrolling down
				WebElement element = driver.findElement(By.xpath(excel.getData(0, 9, 1)));
				//Scroll the page till the Reason option present
				je.executeScript("arguments[0].scrollIntoView(true);",element); 
				//Wait for 30 seconds
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Click on Logout button
				driver.findElement(By.xpath(excel.getData(0, 9, 1))).click();
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
			WebElement element = driver.findElement(By.xpath(excel.getData(0, 9, 1)));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Click on Logout button
			driver.findElement(By.xpath(excel.getData(0, 9, 1))).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			//Check whether user get logged out or not
	/*		if(driver.findElement(By.xpath(excel.getData(0, 10, 1))).isDisplayed())
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}*/
			Thread.sleep(3000);
			//Close the Browser
			driver.close();
		}
			Thread.sleep(5000);
	}

}
