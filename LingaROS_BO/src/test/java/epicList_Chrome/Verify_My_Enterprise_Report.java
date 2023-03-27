package epicList_Chrome;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import epicList_Chrome.Utility;

public class Verify_My_Enterprise_Report {


	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Verify_My_Enterprise_Report");

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
		{		Browser a = new Browser();
		a.Logout(driver, test);}
	
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
			My_Enterprise_Report_open_(driver);
			My_Enterprise_Report_Stores(driver);
			My_Enterprise_Report_Group(driver);
			My_Enterprise_Report_City(driver);
			My_Enterprise_Report_State(driver);
			My_Enterprise_Report_ZipCode(driver);
			Thread.sleep(1500);
		}
	
	@Test(enabled=false,priority=2)
	public void My_Enterprise_Report_open_(WebDriver driver) throws Exception
	{
		Thread.sleep(15000);
		
		//Enter the URL
		driver.get(Utility.getProperty("Enterprise_Base_URL")+"myEnterprise");
		
		Thread.sleep(5000);
		try
		{
		//Check My Enterprise Report page opened or not
		if(driver.findElement(By.xpath("//a[.='My Enterprise']")).getText().equalsIgnoreCase("My Enterprise"))
		{
			test.log(LogStatus.PASS, "My Enterprise report page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "My Enterprise report page loaded Failed");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		}
		catch(Exception e)
		{
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.FAIL,test.addScreenCapture(s));
		}
		
		Thread.sleep(3000);
		
	}
	
	@Test(enabled=false,priority=3)
	public void My_Enterprise_Report_Stores(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		//Click the my Enterprise Report
		driver.findElement(By.id("myEnterprise")).click();
		
		Thread.sleep(3000);
		//Click the Specific date option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[1]/ul/li[11]/a")).click();

		Thread.sleep(1000);
		//Clear the input option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[1]/ul/li[11]/div/div/div/div/input[1]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[1]/ul/li[11]/div/div/div/div/input[1]")).sendKeys(Utility.getReportProperty("Date_Range_From"));

		Thread.sleep(1000);
		//Clear the input option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[1]/ul/li[11]/div/div/div/div/input[2]")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[1]/ul/li[11]/div/div/div/div/input[2]")).sendKeys(Utility.getReportProperty("Date_Range_To"));
		
		Thread.sleep(3000);
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul")).click();
		//Enter the required option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Utility.getReportProperty("Store"));
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		//Click the Run or Refresh Button
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']/i")).click();
		
		
		Thread.sleep(2000);
		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		Thread.sleep(3000);
		System.out.println("********************* View The Store Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("Stores");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul")).click();
		//Enter the required option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Utility.getReportProperty("Store"));
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1000);
		//Click the Load Data button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[3]/button")).click();
		
		try
		{
			//Check whether the Net sale is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Sale report is avilable for the selected Time Period");
				
				System.out.println("Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
			}
			
			
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Sale is not available");
		}
		
		try
		{
			//Check whether the Tax is available or not
			if(driver.findElement(By.xpath("//div[.='Tax']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Tax report is avilable for the selected Time Period");
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Tax is not available");
		}
		
		try
		{
			//Check whether the Discount is available or not
			if(driver.findElement(By.xpath("//div[.=' Discount']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Discount report is avilable for the selected Time Period");
				
				System.out.println("Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Discount is not available");
		}
		
		try
		{
			//Check whether the Net Void is available or not
			if(driver.findElement(By.xpath("//div[.='Net Void']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Void report is avilable for the selected Time Period");
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Void is not available");
		}
		
		try
		{
			//Check whether the AVG CHECK is available or not
			if(driver.findElement(By.xpath("//div[.='AVG CHECK']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "AVG CHECK report is avilable for the selected Time Period");
				
				System.out.println("AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "AVG CHECK is not available");
		}
		
		try
		{
			//Check whether the Customer Count is available or not
			if(driver.findElement(By.xpath("//div[.='Customer Count']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Customer Count report is avilable for the selected Time Period");
				
				System.out.println("Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Customer Count is not available");
		}
		
		try
		{
			//Check whether the Refunds is available or not
			if(driver.findElement(By.xpath("//div[.='Refunds']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Refunds report is avilable for the selected Time Period");
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Refunds is not available");
		}
		
		try
		{
			//Check whether the COGS is available or not
			if(driver.findElement(By.xpath("//div[.='COGS']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "COGS report is avilable for the selected Time Period");
				
				System.out.println("COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "COGS is not available");
		}
		
		try
		{
			//Check whether the Labor is available or not
			if(driver.findElement(By.xpath("//div[.='Labor']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Labor report is avilable for the selected Time Period");
				
				System.out.println("Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Labor is not available");
		}
		
		try
		{
			//Check whether the SPLH is available or not
			if(driver.findElement(By.xpath("//div[.='SPLH']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SPLH report is avilable for the selected Time Period");
				
				System.out.println("SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SPLH is not available");
		}

		try
		{
			//Check whether the NET SALES BY HOUR is available or not
			if(driver.findElement(By.xpath("//div[.='NET SALES BY HOUR']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "NET SALES BY HOUR report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "NET SALES BY HOUR is not available");
		}
		
		try
		{
			//Check whether the SALES BY STORE is available or not
			if(driver.findElement(By.xpath("//div[.='SALES BY STORE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALES BY STORE report is avilable for the selected Time Period");
				
				Thread.sleep(1000);
			
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr"));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[2]/td"));
				columns.size();
				
				for(int i = 2; i <= rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[1]/th["+j+"]")).getText()+" is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td["+j+"]")).getText());
					}
				}
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALES BY STORE is not available");
		}
		
		try
		{
			//Check whether the TOP 5 STORES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 STORES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 STORES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 STORES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 CATEGORIES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 CATEGORIES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 CATEGORIES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 CATEGORIES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 MENU ITEMS is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 MENU ITEMS report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 MENU ITEMS is not available");
		}
		
		try
		{
			//Check whether the SALE PER ORDER TYPE is available or not
			if(driver.findElement(By.xpath("//div[.='SALE PER ORDER TYPE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALE PER ORDER TYPE report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALE PER ORDER TYPE is not available");
		}
		
		Thread.sleep(3000);
		System.out.println("********************* End of Store Report *************************");
		test.log(LogStatus.INFO, "********************* End of Store Report *************************");

		
	}
	
	@Test(enabled=false,priority=4)
	public void My_Enterprise_Report_Group(WebDriver driver) throws Exception
	{

		Thread.sleep(3000);
		System.out.println("********************* View The Group Report *************************");
		test.log(LogStatus.INFO, "********************* View The Store Report *************************");
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("Group");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul")).click();
		//Enter the required option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Utility.getReportProperty("Group"));
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1000);
		//Click the Run or Refresh Button
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']/i")).click();
		
		try
		{
			//Check whether the Net sale is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Sale report is avilable for the selected Time Period");
				
				System.out.println("Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Sale is not available");
		}
		
		try
		{
			//Check whether the Tax is available or not
			if(driver.findElement(By.xpath("//div[.='Tax']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Tax report is avilable for the selected Time Period");
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Tax is not available");
		}
		
		try
		{
			//Check whether the Discount is available or not
			if(driver.findElement(By.xpath("//div[.=' Discount']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Discount report is avilable for the selected Time Period");
				
				System.out.println("Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Discount is not available");
		}
		
		try
		{
			//Check whether the Net Void is available or not
			if(driver.findElement(By.xpath("//div[.='Net Void']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Void report is avilable for the selected Time Period");
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Void is not available");
		}
		
		try
		{
			//Check whether the AVG CHECK is available or not
			if(driver.findElement(By.xpath("//div[.='AVG CHECK']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "AVG CHECK report is avilable for the selected Time Period");
				
				System.out.println("AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "AVG CHECK is not available");
		}
		
		try
		{
			//Check whether the Customer Count is available or not
			if(driver.findElement(By.xpath("//div[.='Customer Count']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Customer Count report is avilable for the selected Time Period");
				
				System.out.println("Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Customer Count is not available");
		}
		
		try
		{
			//Check whether the Refunds is available or not
			if(driver.findElement(By.xpath("//div[.='Refunds']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Refunds report is avilable for the selected Time Period");
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Refunds is not available");
		}
		
		try
		{
			//Check whether the COGS is available or not
			if(driver.findElement(By.xpath("//div[.='COGS']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "COGS report is avilable for the selected Time Period");
				
				System.out.println("COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "COGS is not available");
		}
		
		try
		{
			//Check whether the Labor is available or not
			if(driver.findElement(By.xpath("//div[.='Labor']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Labor report is avilable for the selected Time Period");
				
				System.out.println("Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Labor is not available");
		}
		
		try
		{
			//Check whether the SPLH is available or not
			if(driver.findElement(By.xpath("//div[.='SPLH']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SPLH report is avilable for the selected Time Period");
				
				System.out.println("SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SPLH is not available");
		}

		try
		{
			//Check whether the NET SALES BY HOUR is available or not
			if(driver.findElement(By.xpath("//div[.='NET SALES BY HOUR']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "NET SALES BY HOUR report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "NET SALES BY HOUR is not available");
		}
		
		try
		{
			//Check whether the SALES BY STORE is available or not
			if(driver.findElement(By.xpath("//div[.='SALES BY STORE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALES BY STORE report is avilable for the selected Time Period");
				
				Thread.sleep(1000);
			
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr"));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[2]/td"));
				columns.size();
				
				for(int i = 2; i <= rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[1]/th["+j+"]")).getText()+" is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td["+j+"]")).getText());
					}
				}
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALES BY STORE is not available");
		}
		
		try
		{
			//Check whether the TOP 5 STORES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 STORES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 STORES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 STORES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 CATEGORIES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 CATEGORIES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 CATEGORIES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 CATEGORIES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 MENU ITEMS is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 MENU ITEMS report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 MENU ITEMS is not available");
		}
		
		try
		{
			//Check whether the SALE PER ORDER TYPE is available or not
			if(driver.findElement(By.xpath("//div[.='SALE PER ORDER TYPE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALE PER ORDER TYPE report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALE PER ORDER TYPE is not available");
		}
		
		Thread.sleep(3000);
		System.out.println("********************* End of Group Report *************************");
		test.log(LogStatus.INFO, "********************* End of Group Report *************************");
	}
	
	@Test(enabled=false,priority=5)
	public void My_Enterprise_Report_City(WebDriver driver) throws Exception
	{

		Thread.sleep(3000);
		System.out.println("********************* View The City Report *************************");
		test.log(LogStatus.INFO, "********************* View The City Report *************************");
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("City");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul")).click();
		//Enter the required option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Utility.getReportProperty("City"));
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1000);
		//Click the Run or Refresh Button
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']/i")).click();
		
		try
		{
			//Check whether the Net sale is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Sale report is avilable for the selected Time Period");
				
				System.out.println("Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Sale is not available");
		}
		
		try
		{
			//Check whether the Tax is available or not
			if(driver.findElement(By.xpath("//div[.='Tax']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Tax report is avilable for the selected Time Period");
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Tax is not available");
		}
		
		try
		{
			//Check whether the Discount is available or not
			if(driver.findElement(By.xpath("//div[.=' Discount']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Discount report is avilable for the selected Time Period");
				
				System.out.println("Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Discount is not available");
		}
		
		try
		{
			//Check whether the Net Void is available or not
			if(driver.findElement(By.xpath("//div[.='Net Void']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Void report is avilable for the selected Time Period");
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Void is not available");
		}
		
		try
		{
			//Check whether the AVG CHECK is available or not
			if(driver.findElement(By.xpath("//div[.='AVG CHECK']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "AVG CHECK report is avilable for the selected Time Period");
				
				System.out.println("AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "AVG CHECK is not available");
		}
		
		try
		{
			//Check whether the Customer Count is available or not
			if(driver.findElement(By.xpath("//div[.='Customer Count']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Customer Count report is avilable for the selected Time Period");
				
				System.out.println("Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Customer Count is not available");
		}
		
		try
		{
			//Check whether the Refunds is available or not
			if(driver.findElement(By.xpath("//div[.='Refunds']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Refunds report is avilable for the selected Time Period");
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Refunds is not available");
		}
		
		try
		{
			//Check whether the COGS is available or not
			if(driver.findElement(By.xpath("//div[.='COGS']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "COGS report is avilable for the selected Time Period");
				
				System.out.println("COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "COGS is not available");
		}
		
		try
		{
			//Check whether the Labor is available or not
			if(driver.findElement(By.xpath("//div[.='Labor']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Labor report is avilable for the selected Time Period");
				
				System.out.println("Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Labor is not available");
		}
		
		try
		{
			//Check whether the SPLH is available or not
			if(driver.findElement(By.xpath("//div[.='SPLH']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SPLH report is avilable for the selected Time Period");
				
				System.out.println("SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SPLH is not available");
		}

		try
		{
			//Check whether the NET SALES BY HOUR is available or not
			if(driver.findElement(By.xpath("//div[.='NET SALES BY HOUR']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "NET SALES BY HOUR report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "NET SALES BY HOUR is not available");
		}
		
		try
		{
			//Check whether the SALES BY STORE is available or not
			if(driver.findElement(By.xpath("//div[.='SALES BY STORE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALES BY STORE report is avilable for the selected Time Period");
				
				Thread.sleep(1000);
			
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr"));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[2]/td"));
				columns.size();
				
				for(int i = 2; i <= rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[1]/th["+j+"]")).getText()+" is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td["+j+"]")).getText());
					}
				}
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALES BY STORE is not available");
		}
		
		try
		{
			//Check whether the TOP 5 STORES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 STORES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 STORES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 STORES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 CATEGORIES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 CATEGORIES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 CATEGORIES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 CATEGORIES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 MENU ITEMS is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 MENU ITEMS report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 MENU ITEMS is not available");
		}
		
		try
		{
			//Check whether the SALE PER ORDER TYPE is available or not
			if(driver.findElement(By.xpath("//div[.='SALE PER ORDER TYPE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALE PER ORDER TYPE report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALE PER ORDER TYPE is not available");
		}
		
		Thread.sleep(3000);
		System.out.println("********************* End of City Report *************************");
		test.log(LogStatus.INFO, "********************* End of City Report *************************");
	}
	
	@Test(enabled=false,priority=6)
	public void My_Enterprise_Report_State(WebDriver driver) throws Exception
	{

		Thread.sleep(3000);
		System.out.println("********************* View The State Report *************************");
		test.log(LogStatus.INFO, "********************* View The State Report *************************");
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("State");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul")).click();
		//Enter the required option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Utility.getReportProperty("State"));
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1000);
		//Click the Run or Refresh Button
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']/i")).click();
		
		try
		{
			//Check whether the Net sale is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Sale report is avilable for the selected Time Period");
				
				System.out.println("Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Sale is not available");
		}
		
		try
		{
			//Check whether the Tax is available or not
			if(driver.findElement(By.xpath("//div[.='Tax']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Tax report is avilable for the selected Time Period");
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Tax is not available");
		}
		
		try
		{
			//Check whether the Discount is available or not
			if(driver.findElement(By.xpath("//div[.=' Discount']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Discount report is avilable for the selected Time Period");
				
				System.out.println("Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Discount is not available");
		}
		
		try
		{
			//Check whether the Net Void is available or not
			if(driver.findElement(By.xpath("//div[.='Net Void']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Void report is avilable for the selected Time Period");
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Void is not available");
		}
		
		try
		{
			//Check whether the AVG CHECK is available or not
			if(driver.findElement(By.xpath("//div[.='AVG CHECK']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "AVG CHECK report is avilable for the selected Time Period");
				
				System.out.println("AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "AVG CHECK is not available");
		}
		
		try
		{
			//Check whether the Customer Count is available or not
			if(driver.findElement(By.xpath("//div[.='Customer Count']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Customer Count report is avilable for the selected Time Period");
				
				System.out.println("Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Customer Count is not available");
		}
		
		try
		{
			//Check whether the Refunds is available or not
			if(driver.findElement(By.xpath("//div[.='Refunds']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Refunds report is avilable for the selected Time Period");
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Refunds is not available");
		}
		
		try
		{
			//Check whether the COGS is available or not
			if(driver.findElement(By.xpath("//div[.='COGS']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "COGS report is avilable for the selected Time Period");
				
				System.out.println("COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "COGS is not available");
		}
		
		try
		{
			//Check whether the Labor is available or not
			if(driver.findElement(By.xpath("//div[.='Labor']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Labor report is avilable for the selected Time Period");
				
				System.out.println("Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Labor is not available");
		}
		
		try
		{
			//Check whether the SPLH is available or not
			if(driver.findElement(By.xpath("//div[.='SPLH']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SPLH report is avilable for the selected Time Period");
				
				System.out.println("SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SPLH is not available");
		}

		try
		{
			//Check whether the NET SALES BY HOUR is available or not
			if(driver.findElement(By.xpath("//div[.='NET SALES BY HOUR']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "NET SALES BY HOUR report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "NET SALES BY HOUR is not available");
		}
		
		try
		{
			//Check whether the SALES BY STORE is available or not
			if(driver.findElement(By.xpath("//div[.='SALES BY STORE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALES BY STORE report is avilable for the selected Time Period");
				
				Thread.sleep(1000);
			
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr"));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[2]/td"));
				columns.size();
				
				for(int i = 2; i <= rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[1]/th["+j+"]")).getText()+" is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td["+j+"]")).getText());
					}
				}
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALES BY STORE is not available");
		}
		
		try
		{
			//Check whether the TOP 5 STORES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 STORES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 STORES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 STORES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 CATEGORIES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 CATEGORIES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 CATEGORIES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 CATEGORIES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 MENU ITEMS is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 MENU ITEMS report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 MENU ITEMS is not available");
		}
		
		try
		{
			//Check whether the SALE PER ORDER TYPE is available or not
			if(driver.findElement(By.xpath("//div[.='SALE PER ORDER TYPE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALE PER ORDER TYPE report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALE PER ORDER TYPE is not available");
		}
		
		Thread.sleep(3000);
		System.out.println("********************* End of State Report *************************");
		test.log(LogStatus.INFO, "********************* End of State Report *************************");
	}
	
	@Test(enabled=false,priority=7)
	public void My_Enterprise_Report_ZipCode(WebDriver driver) throws Exception
	{

		Thread.sleep(3000);
		System.out.println("********************* View The Zip Code Report *************************");
		test.log(LogStatus.INFO, "********************* View The Zip Code Report *************************");
		//Click the Select option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/a")).click();
		//Enter the required filter
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("Zip Code");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(1500);
		//Click the Select Some Options
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul")).click();
		//Enter the required option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Utility.getReportProperty("Zipcode"));
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/form/div/div/div[2]/div[2]/div/ul/li/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(1000);
		//Click the Run or Refresh Button
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']/i")).click();
		
		try
		{
			//Check whether the Net sale is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Sale report is avilable for the selected Time Period");
				
				System.out.println("Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Sale is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Sale is not available");
		}
		
		try
		{
			//Check whether the Tax is available or not
			if(driver.findElement(By.xpath("//div[.='Tax']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Tax report is avilable for the selected Time Period");
				
				System.out.println("Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Tax is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Tax is not available");
		}
		
		try
		{
			//Check whether the Discount is available or not
			if(driver.findElement(By.xpath("//div[.=' Discount']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Discount report is avilable for the selected Time Period");
				
				System.out.println("Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Discount is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Discount is not available");
		}
		
		try
		{
			//Check whether the Net Void is available or not
			if(driver.findElement(By.xpath("//div[.='Net Void']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Net Void report is avilable for the selected Time Period");
				
				System.out.println("Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Net Void is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Net Void is not available");
		}
		
		try
		{
			//Check whether the AVG CHECK is available or not
			if(driver.findElement(By.xpath("//div[.='AVG CHECK']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "AVG CHECK report is avilable for the selected Time Period");
				
				System.out.println("AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "AVG CHECK is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "AVG CHECK is not available");
		}
		
		try
		{
			//Check whether the Customer Count is available or not
			if(driver.findElement(By.xpath("//div[.='Customer Count']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Customer Count report is avilable for the selected Time Period");
				
				System.out.println("Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Customer Count is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Customer Count is not available");
		}
		
		try
		{
			//Check whether the Refunds is available or not
			if(driver.findElement(By.xpath("//div[.='Refunds']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Refunds report is avilable for the selected Time Period");
				
				System.out.println("Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Refunds is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Refunds is not available");
		}
		
		try
		{
			//Check whether the COGS is available or not
			if(driver.findElement(By.xpath("//div[.='COGS']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "COGS report is avilable for the selected Time Period");
				
				System.out.println("COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "COGS is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "COGS is not available");
		}
		
		try
		{
			//Check whether the Labor is available or not
			if(driver.findElement(By.xpath("//div[.='Labor']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Labor report is avilable for the selected Time Period");
				
				System.out.println("Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "Labor is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[4]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Labor is not available");
		}
		
		try
		{
			//Check whether the SPLH is available or not
			if(driver.findElement(By.xpath("//div[.='SPLH']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SPLH report is avilable for the selected Time Period");
				
				System.out.println("SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
				
				test.log(LogStatus.INFO, "SPLH is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[5]/div[2]/span[1]")).getText());
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SPLH is not available");
		}

		try
		{
			//Check whether the NET SALES BY HOUR is available or not
			if(driver.findElement(By.xpath("//div[.='NET SALES BY HOUR']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "NET SALES BY HOUR report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "NET SALES BY HOUR is not available");
		}
		
		try
		{
			//Check whether the SALES BY STORE is available or not
			if(driver.findElement(By.xpath("//div[.='SALES BY STORE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALES BY STORE report is avilable for the selected Time Period");
				
				Thread.sleep(1000);
			
				//Get the rows count
				List<WebElement> rows = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr"));
				rows.size();
				
				//Get the columns count
				List<WebElement> columns = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[2]/td"));
				columns.size();
				
				for(int i = 2; i <= rows.size() ; i++)
				{
					for(int j = 2; j <= columns.size() ; j++)
					{
						test.log(LogStatus.INFO, driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td[1]")).getText()+" contain the "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr[1]/th["+j+"]")).getText()+" is : "+driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div[3]/div/div/div/div[2]/table/tbody/tr["+i+"]/td["+j+"]")).getText());
					}
				}
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALES BY STORE is not available");
		}
		
		try
		{
			//Check whether the TOP 5 STORES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 STORES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 STORES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 STORES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 CATEGORIES is available or not
			if(driver.findElement(By.xpath("//div[.='TOP 5 CATEGORIES']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 CATEGORIES report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 CATEGORIES is not available");
		}
		
		try
		{
			//Check whether the TOP 5 MENU ITEMS is available or not
			if(driver.findElement(By.xpath("//div[.='Net sale']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "TOP 5 MENU ITEMS report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "TOP 5 MENU ITEMS is not available");
		}
		
		try
		{
			//Check whether the SALE PER ORDER TYPE is available or not
			if(driver.findElement(By.xpath("//div[.='SALE PER ORDER TYPE']")).isDisplayed())
			{
				test.log(LogStatus.PASS, "SALE PER ORDER TYPE report is avilable for the selected Time Period");
				
			}
		}
		
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "SALE PER ORDER TYPE is not available");
		}
		
		Thread.sleep(3000);
		System.out.println("********************* End of Zip Code Report *************************");
		test.log(LogStatus.INFO, "********************* End of Zip Code Report *************************");

		if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);//watchTutorial(driver);
		Thread.sleep(5000);
		}
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
