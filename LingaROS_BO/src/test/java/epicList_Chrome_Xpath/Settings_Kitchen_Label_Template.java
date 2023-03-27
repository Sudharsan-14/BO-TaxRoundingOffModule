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

public class Settings_Kitchen_Label_Template {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Kitchen_Label_Template");
	
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
			Kitchen_Label_Template_method_open_Kitchen_Label_Template(driver);
			Kitchen_Label_Template_method_update_Kitchen_Label_Template(driver);
			Verify_Kitchen_Label_Template_method_Kitchen_Label_Template(driver);
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
	
	@Test(priority=32,enabled = false)
	public void Kitchen_Label_Template_method_open_Kitchen_Label_Template(WebDriver driver) throws Exception
	{
/*		//Click the Settings option
		driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[5]/a/span[1]")).click();
		// Create instance of Java script executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//span[.='Printer Configuration']"));
		//Scroll the page till the Printer Configuration option present
		je.executeScript("arguments[0].scrollIntoView(true);",element); 
        //Click the Store Option		
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();*/
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"kitchenLabelTemplate");
		Thread.sleep(5000);
		//Check Kitchen Label Template page opened or not
		if(driver.findElement(By.xpath("//a[.='Label Template']")).getText().equalsIgnoreCase("Label Template"))
		{
			test.log(LogStatus.PASS, "Kitchen Label Template page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Label Template page loaded Failed");
		}
		Thread.sleep(3000);
	}	
		
	@Test(priority=33,enabled = false)
	public void Kitchen_Label_Template_method_update_Kitchen_Label_Template(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Width Field
		driver.findElement(By.xpath("//input[@ng-model='settings.width']")).clear();
		//Enter the required Width Field
		driver.findElement(By.xpath("//input[@ng-model='settings.width']")).sendKeys("1");
		
		Thread.sleep(2000);
		//Clear the Height field
		driver.findElement(By.xpath("//input[@ng-model='settings.height']")).clear();
		//Enter the required Height field
		driver.findElement(By.xpath("//input[@ng-model='settings.height']")).sendKeys("2");
		
		Thread.sleep(2000);
		//Select Header Font Size
		Select fontSize = new Select(driver.findElement(By.xpath("//select[@ng-model='settings.fontSize_H']")));
		fontSize.selectByVisibleText("2 width");
		
		 Thread.sleep(2000);
		//Check whether the Show Date & Time enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDateTime']")).isSelected())
        {}
        else
        {
        	//Enable the Show Date & Time
        driver.findElement(By.xpath("//input[@ng-model='settings.showDateTime']/../span")).click();
        }
       
       //Check whether the Order Type is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showOrderType']")).isSelected())
		{}
		else
		{
			//Enable the Order Type option
			driver.findElement(By.xpath("//input[@ng-model='settings.showOrderType']/../span")).click();	
		}
				
		//Check whether the Sale number is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTicketNo']")).isSelected())
		{}
		else
		{
			//Enable the SAle Number option
			driver.findElement(By.xpath("//input[@ng-model='settings.showTicketNo']/../span")).click();
		}		
		
		//Check whether the Order number is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showOrderNo']")).isSelected())
		{}
		else
		{
			//Enable the Order Number option
			driver.findElement(By.xpath("//input[@ng-model='settings.showOrderNo']/../span")).click();
		}
		
		//Check whether the Customer Name is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected())
		{}
		else
		{
			//Enable the Customer Name option
			driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']/../span")).click();
		}
		
		Select fontSize1 = new Select(driver.findElement(By.xpath("//select[@ng-model='settings.fontSize_T']")));
		fontSize1.selectByVisibleText("2 width");
		
		
		Select fontSize2 = new Select(driver.findElement(By.xpath("//select[@ng-model='settings.fontSize_B']")));
		fontSize2.selectByVisibleText("2 width");
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}
		
		Thread.sleep(2000);
		//Check whether the Autocut is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.enableAutoCut']")).isSelected())
		{}
		else
		{
			//Enable the Autocut option
			driver.findElement(By.xpath("//input[@ng-model='settings.enableAutoCut']/../span")).click();
		}
		
		//Check whether the Show line seperator in Header is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.lineSeparatorForHeader']")).isSelected())
		{}
		else
		{
			//Enable the Show line seperator in Header option
			driver.findElement(By.xpath("//input[@ng-model='settings.lineSeparatorForHeader']/../span")).click();
		}
		
		//Check whether the Show line seperator in Footer is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.lineSeparatorForFooter']")).isSelected())
		{}
		else
		{
			//Enable the Show line seperator in Footer option
			driver.findElement(By.xpath("//input[@ng-model='settings.lineSeparatorForFooter']/../span")).click();
		}
		Thread.sleep(5000);
		
		//Click the update button
		driver.findElement(By.xpath("//button[@id='UpdateLabelT']")).click();
		Thread.sleep(4000);
		
		//Check weather the new Label Template updated or not
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Label template saved successfully"))
		{
			test.log(LogStatus.PASS, "Kitchen Label template Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Kitchen Label template Updated fail");
		}
		Thread.sleep(3000);
	}

	@Test(priority=34,enabled = false)
	public void Verify_Kitchen_Label_Template_method_Kitchen_Label_Template(WebDriver driver) throws Exception
	{
		
		 Thread.sleep(2000);
		//Check whether the Show Date & Time enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showDateTime']")).isSelected())
        {
			test.log(LogStatus.PASS, "Show Date & Time enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Show Date & Time disabled in Kitchen Label Template");
        }
       
       //Check whether the Order Type is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showOrderType']")).isSelected())
		{
			test.log(LogStatus.PASS, "Order Type enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Order Type disabled in Kitchen Label Template");
        }
				
		//Check whether the Sale number is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showTicketNo']")).isSelected())
		{
			test.log(LogStatus.PASS, "Sale number enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Sale number disabled in Kitchen Label Template");
        }

		Thread.sleep(3000);
		//Check whether the Order number is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showOrderNo']")).isSelected())
		{
			test.log(LogStatus.PASS, "Order number enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Order number disabled in Kitchen Label Template");
        }
		
		//Check whether the Customer Name is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.showCustomerName']")).isSelected())
		{
			test.log(LogStatus.PASS, "Customer Name enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Customer Name disabled in Kitchen Label Template");
        }
		
		for(int i = 1; i <= 8; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		}Thread.sleep(3000);
		
		//Check whether the Autocut is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.enableAutoCut']")).isSelected())
		{
			test.log(LogStatus.PASS, "Autocut enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Autocut disabled in Kitchen Label Template");
        }
		
		//Check whether the Show line seperator in Header is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.lineSeparatorForHeader']")).isSelected())
		{
			test.log(LogStatus.PASS, "Show line seperator in Header enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Show line seperator in Header disabled in Kitchen Label Template");
        }
		
		//Check whether the Show line seperator in Footer is enabled or not
		if(driver.findElement(By.xpath("//input[@ng-model='settings.lineSeparatorForFooter']")).isSelected())
		{
			test.log(LogStatus.PASS, "Show line seperator in Footer enabled in Kitchen Label Template");
        }
        else
        {
        	test.log(LogStatus.FAIL, "Show line seperator in Footer disabled in Kitchen Label Template");
        }
		Thread.sleep(5000);

	}

}
