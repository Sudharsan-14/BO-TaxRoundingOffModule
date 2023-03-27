package epicList_Chrome_Xpath;

import java.util.List;
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


public class Settings_OT_Setings {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_OT_Setings");
	
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
			OT_Settings_Method_openpage(driver);
			OT_Settings_Method_add(driver);
			OT_Settings_Method_close_Button(driver);
			Thread.sleep(1500);
		}

	@Test(priority=3, enabled=false)
	public void watchTutorial(WebDriver driver) throws Exception
	{driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
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

	@Test(enabled=false,priority=25)
	public void OT_Settings_Method_openpage(WebDriver driver) throws Exception
		{
			
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"overtime");
			
			Thread.sleep(8000);
			
			//Check weather the OT Settings page is loaded or not
			if(driver.findElement(By.xpath("//a[.='Overtime Settings']")).getText().equalsIgnoreCase("Overtime Settings"))
			{
				test.log(LogStatus.PASS, "OT Settings page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "OT Settings page loaded fail");
			}

			Thread.sleep(3000);

		}	
		
	@Test(enabled=false,priority=26)
	public void OT_Settings_Method_add(WebDriver driver) throws Exception
		{
			
			Thread.sleep(1000);
			//Click the refresh button
			driver.findElement(By.xpath("//i[@ uib-tooltip='refresh']")).click();
			
			Thread.sleep(2000);
			//Click the Add OT settings button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New OT Settings']")).getText().equalsIgnoreCase("New OT Settings"))
			{
				test.log(LogStatus.PASS, "New OT Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Select the OT
			Select overTime = new Select(driver.findElement(By.xpath("//select[@ng-model='storeOT.type']")));
			overTime.selectByVisibleText("Daily");
			
			//Clear the OverTime1 hours field
			driver.findElement(By.name("overTimeHours1")).clear();
			//Enter the OverTime1 hours
			driver.findElement(By.name("overTimeHours1")).sendKeys("1");
			
			Thread.sleep(2000);
			//Clear the OverTime1 percentage field
			driver.findElement(By.name("overTime1")).clear();
			//Enter the OverTime1 percentage
			driver.findElement(By.name("overTime1")).sendKeys("101");
			
			//Clear the OverTime2 hours field
			driver.findElement(By.name("overTimeHours2")).clear();
			//Enter the OverTime2 hours
			driver.findElement(By.name("overTimeHours2")).sendKeys("2");
			
			Thread.sleep(2000);
			//Clear the OverTime2 percentage field
			driver.findElement(By.name("overTime2")).clear();
			//Enter the OverTime2 percentage
			driver.findElement(By.name("overTime2")).sendKeys("102");
			
			//Clear the OverTime3 hours field
			driver.findElement(By.name("overTimeHours3")).clear();
			//Enter the OverTime3 hours
			driver.findElement(By.name("overTimeHours3")).sendKeys("3");
			
			Thread.sleep(2000);
			//Clear the OverTime3 percentage field
			driver.findElement(By.name("overTime3")).clear();
			//Enter the OverTime3 percentage
			driver.findElement(By.name("overTime3")).sendKeys("103");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			//Clear the effectiveDate field
			driver.findElement(By.xpath("//form/div[6]/div/input")).clear();
			//Enter the effectiveDate
			driver.findElement(By.xpath("//form/div[6]/div/input")).sendKeys(Utility.getProperty("OT_Settings_Date"));
				
			Thread.sleep(1000);
			//Click the Save button
			driver.findElement(By.xpath("//form/div[7]/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new OT form saved or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("OT saved successfully"))
			{
				test.log(LogStatus.PASS, "New OT saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT saved fail");
			}
			Thread.sleep(4000);
			
			//get the number of rows
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr"));
			rows.size();
			
			for(int i = rows.size() ; i == rows.size(); i-- )
			{
				//Print the date
				System.out.println("The Effective Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText());
				test.log(LogStatus.INFO, "The Effective Date is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[1]")).getText());
				
				//Print the Type
				System.out.println("The OT Type is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText());
				test.log(LogStatus.INFO, "The OT Type is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[2]")).getText());
				
				//Print the Hour
				System.out.println("OT Hour is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText());
				test.log(LogStatus.INFO, "OT Hour is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[3]")).getText());
				
				//Print the Percentage
				System.out.println("The Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText());
				test.log(LogStatus.INFO, "The Percentage is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[2]/div/div[2]/div/table/tbody/tr["+i+"]/td[4]")).getText());
			
			}
		}
		
	@Test(enabled=false,priority=27)
	public void OT_Settings_Method_close_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Add OT settings button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New OT Settings']")).getText().equalsIgnoreCase("New OT Settings"))
			{
				test.log(LogStatus.PASS, "New OT Settings form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New OT Settings form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Select the OT
			Select overTime = new Select(driver.findElement(By.xpath("//select[@ng-model='storeOT.type']")));
			overTime.selectByVisibleText("Daily");
			
			//Clear the OverTime1 hours field
			driver.findElement(By.name("overTimeHours1")).clear();
			//Enter the OverTime1 hours
			driver.findElement(By.name("overTimeHours1")).sendKeys("1");
			
			Thread.sleep(2000);
			//Clear the OverTime1 percentage field
			driver.findElement(By.name("overTime1")).clear();
			//Enter the OverTime1 percentage
			driver.findElement(By.name("overTime1")).sendKeys("101");
			
			//Clear the OverTime2 hours field
			driver.findElement(By.name("overTimeHours2")).clear();
			//Enter the OverTime2 hours
			driver.findElement(By.name("overTimeHours2")).sendKeys("2");
			
			Thread.sleep(2000);
			//Clear the OverTime2 percentage field
			driver.findElement(By.name("overTime2")).clear();
			//Enter the OverTime2 percentage
			driver.findElement(By.name("overTime2")).sendKeys("102");
			
			//Clear the OverTime3 hours field
			driver.findElement(By.name("overTimeHours3")).clear();
			//Enter the OverTime3 hours
			driver.findElement(By.name("overTimeHours3")).sendKeys("3");
			
			Thread.sleep(2000);
			//Clear the OverTime3 percentage field
			driver.findElement(By.name("overTime3")).clear();
			//Enter the OverTime3 percentage
			driver.findElement(By.name("overTime3")).sendKeys("103");
			
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
			//Clear the effectiveDate field
			driver.findElement(By.xpath("//form/div[6]/div/input")).clear();
			//Enter the effectiveDate
			driver.findElement(By.xpath("//form/div[6]/div/input")).sendKeys(Utility.getProperty("OT_Settings_Date"));

			Thread.sleep(1000);
			//Click the Close button
			driver.findElement(By.xpath("//form/div[7]/div/a")).click();
			Thread.sleep(2000);
			
			//Check weather the new emv settings form closed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "OT Settings form Closed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "OT Settings form Closed fail");
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}

}
