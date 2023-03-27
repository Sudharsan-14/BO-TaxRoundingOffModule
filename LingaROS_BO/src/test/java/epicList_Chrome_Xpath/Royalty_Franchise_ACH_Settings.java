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

public class Royalty_Franchise_ACH_Settings {
	public WebDriver driver;
		
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Royalty_Franchise_ACH_Settings");

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
			Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_ettings(driver);
			Enterprice_Royalty_Franchise_Settings_method_Update_ACH_Settings(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=12)
	public void Enterprice_Royalty_Franchise_Settings_method_open_Enterprice_Royalty_Franchise_ACH_ettings(WebDriver driver) throws Exception
	{
		/*driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		//Click the My Stores option
		driver.findElement(By.xpath("//span[.='My Stores']")).click();

		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+("Royalty_Franchise_Settings"));
				
		Thread.sleep(3000);
        //Click the Royalty/Franchise Option		
		driver.findElement(By.xpath("//span[.='Royalty/Franchise']")).click();
		
		Thread.sleep(5000);
		//Check Royalty/Franchise page opened or not
		if(driver.findElement(By.xpath("//a[.='Royalty/Franchise']")).getText().equalsIgnoreCase("Royalty/Franchise"))
		{
			test.log(LogStatus.PASS, "Royalty/Franchise Settings page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Royalty/Franchise Settings page loaded Failed");
		}
		*/
		Thread.sleep(3000);
		//Enter the Coursing URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"royalty");Thread.sleep(8000);
		//Click the ACH Settings
		driver.findElement(By.xpath("//span[contains(.,'ACH Settings')]")).click();
		Thread.sleep(2500);
		
		//Check ACH Settings page opened or not
		if(driver.findElement(By.xpath("//a[.='ACH Settings']")).getText().equalsIgnoreCase("ACH Settings"))
		{
			test.log(LogStatus.PASS, "ACH Settings page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "ACH Settings page loaded Failed");
		}
		Thread.sleep(3000);
	}

	@Test(enabled=false,priority=13)
	public void Enterprice_Royalty_Franchise_Settings_method_Update_ACH_Settings(WebDriver driver) throws Exception
	{
		Thread.sleep(4000);
		//Clear the Destination Name
		driver.findElement(By.xpath("//input[@name='destinationName']")).clear();
		//Enter the Destination name
		driver.findElement(By.xpath("//input[@name='destinationName']")).sendKeys("Dest_Test");
		
		Thread.sleep(1500);
		//Clear the Destination Routing number
		driver.findElement(By.xpath("//input[@name='routingNumber']")).clear();
		//Enter the Destination Routing number
		driver.findElement(By.xpath("//input[@name='routingNumber']")).sendKeys("2224222");
		
		Thread.sleep(1500);
		//Clear the Immediate Origin name
		driver.findElement(By.xpath("//input[@name='originName']")).clear();
		//Enter the Immediate Origin Name
		driver.findElement(By.xpath("//input[@name='originName']")).sendKeys("Org_Test");
		
		Thread.sleep(1500);
		//Clear the Immediate Origin number
		driver.findElement(By.xpath("//input[@name='originNumber']")).clear();
		//Enter the Immediate Origin number
		driver.findElement(By.xpath("//input[@name='originNumber']")).sendKeys("4545");
		
		Thread.sleep(1500);
		//Clear the Company name
		driver.findElement(By.xpath("//input[@name='companyName']")).clear();
		//Enter the Company Name
		driver.findElement(By.xpath("//input[@name='companyName']")).sendKeys("Comp_Test");
		
		Thread.sleep(1500);
		//Clear the Company entry description
		driver.findElement(By.xpath("//input[@name='companyEntryDescription']")).clear();
		//Enter the Company entry description
		driver.findElement(By.xpath("//input[@name='companyEntryDescription']")).sendKeys("Desc_Test");
		
		Thread.sleep(1500);
		//Clear the Effective Entry Date
		driver.findElement(By.xpath("//input[@name='effectiveDate']")).clear();
		//Enter the Effective Entry Date
		driver.findElement(By.xpath("//input[@name='effectiveDate']")).sendKeys("02-Jan-2020");
		
		Thread.sleep(1500);
		//Clear the Company Discretionary Data
		driver.findElement(By.xpath("//input[@name='companyDiscretionData']")).clear();
		//Enter the Company Discretionary Data
		driver.findElement(By.xpath("//input[@name='companyDiscretionData']")).sendKeys("Disc_Data");
		
		Thread.sleep(1500);
		//Clear the Company Identification Number
		driver.findElement(By.xpath("//input[@name='companyIdentificationNumber']")).clear();
		//Enter the Company Identification Number
		driver.findElement(By.xpath("//input[@name='companyIdentificationNumber']")).sendKeys("2552");
		
		Thread.sleep(1500);
		//Clear the Originating DFI Identification
		driver.findElement(By.xpath("//input[@name='originatingDFIIdentification']")).clear();
		//Enter the Originating DFI Identification
		driver.findElement(By.xpath("//input[@name='originatingDFIIdentification']")).sendKeys("5555");

		Thread.sleep(3000);
		//Click the Update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		//Check whether the account user is saved or not
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Saved Successfully"))
		{
			test.log(LogStatus.PASS, "ACH Settings updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "ACH Settings updated fail");
		}
		
		Thread.sleep(3000);
	}

}
