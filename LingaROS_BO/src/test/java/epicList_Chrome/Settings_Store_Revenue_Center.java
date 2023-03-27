package epicList_Chrome;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Settings_Store_Revenue_Center {
public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settins_Store_Revenue_Center");

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
				Browser_Account_Level_User a = new Browser_Account_Level_User();
				a.UPOS_login(driver, test);
			}
			else 			
			{
				Browser_Account_Level_User a = new Browser_Account_Level_User();
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
			//Close the Browser_Account_Level_User
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
			if(driver.findElement(By.xpath("//div[@id='content']/div/div/h2")).getText().equalsIgnoreCase("Sign in"))
			{
		    	test.log(LogStatus.PASS, "User Logged out Successfully LingaPOs");
			}
			else
			{
				test.log(LogStatus.FAIL, "User Logged out Failed LingaPos");
			}
			Thread.sleep(3000);
			//Close the Browser_Account_Level_User
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
			Revenue_Center_Open_Revenue_Center(driver);
			Revenue_Center_Refresh_Page(driver);
			Revenue_Center_Add(driver);
			Scale_Barcode_Pagination(driver);
		    Revenue_Center_Edit(driver);
		    Revenue_Center_Delete(driver);
		    Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=151)
		public void Revenue_Center_Open_Revenue_Center(WebDriver driver) throws Exception
		{	
			//ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newRevenueCenter");
			Thread.sleep(5000);
			try
			{
			//Check POS Licenses page is opened or not
			if(driver.findElement(By.xpath("//a[.='Revenue Center Configuration']")).getText().equalsIgnoreCase("Revenue Center Configuration"))
			{
				test.log(LogStatus.PASS, "Revenue Center page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "Revenue Center page loaded Failed");
			
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
		
		@Test(enabled=false,priority=152)
		public void Revenue_Center_Refresh_Page(WebDriver driver) throws Exception
		{
			//ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//a[@class='btn btn-md btn-reload']")).click();
			
			Thread.sleep(4000);
			if(driver.findElement(By.xpath("//h4[.='Revenue Center']")).getText().equalsIgnoreCase("Revenue Center"))
			{
				test.log(LogStatus.PASS, "Revenue Center page Refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Revenue Center page Refreshed Failed");
			}
			Thread.sleep(3000);
		}
		@Test(priority=153,enabled=false)
		public void Scale_Barcode_Pagination(WebDriver driver) throws InterruptedException
		{			
			
			try {
				if(driver.findElement(By.xpath("//tr//td[.='No records found']")).isDisplayed()) {
	            	test.log(LogStatus.INFO, "There is no Revenue center configured for this store as of now, so no pagination available");
				}
				}
				catch(Exception e)
				{
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for Service Charge Table
			List<WebElement> results = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[3]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(1000);
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
			//Create the web element for Service Charge Table
			List<WebElement> results3 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[3]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(1000);
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
			//Create the web element for Service Charge Table
			List<WebElement> results1 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[3]/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(1000);
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
			//Create the web element for Service Charge Table
			List<WebElement> results2 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div[3]/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(1000);	
			}
		}
		@Test(enabled=false,priority=154)
		public void Revenue_Center_Add(WebDriver driver) throws Exception
		{	
			driver.findElement(By.xpath("//button[@class='btn btn-small btn-color ng-binding']")).click();
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//span[normalize-space()='New Revenue Center']")).getText().equalsIgnoreCase("New Revenue Center"))
			{
				test.log(LogStatus.PASS, "New Revenue Center popup opened successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Revenue Center popup opened fail");
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Utility.getProperty("Settings_Store_Revenue_Center_Name1"));
			driver.findElement(By.xpath("//li[@class='search-field']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='chosen-results']"));
			driver.findElement(By.xpath("//ul[@class='chosen-results']/li[1]")).click();
			Thread.sleep(2000);
	        driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
	        Thread.sleep(2000);
			if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Revenue Center Saved Successfully"))
			{
				test.log(LogStatus.PASS, "Revenue Center saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Revenue Center saved fail");
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@class='btn btn-small btn-color ng-binding']")).click();
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Utility.getProperty("Settings_Store_Revenue_Center_Name2"));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[@class='search-field']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//ul[@class='chosen-results']"));
			driver.findElement(By.xpath("//ul[@class='chosen-results']/li[1]")).click();
			Thread.sleep(2000);
	        driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
	        Thread.sleep(2000);
			if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Revenue Center Saved Successfully"))
			{
				test.log(LogStatus.PASS, "Another Revenue Center saved successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Another Revenue Center saved fail");
			}
			Thread.sleep(2000);
		}
		@Test(enabled=false,priority=155)
		public void Revenue_Center_Edit(WebDriver driver) throws Exception
		{	
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Revenue_Center_Name1"));
			 Thread.sleep(2000); 
			 driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();
			 Thread.sleep(2000); 
			 driver.findElement(By.xpath("//li[@class='search-field']")).click();
			 Thread.sleep(2000);
			 try {
			 driver.findElement(By.xpath("//a[@class='search-choice-close']")).click();}catch(Exception k) {}
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//ul[@class='chosen-results']"));
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//ul[@class='chosen-results']/li[1]")).click();
			 Thread.sleep(2000);
		        driver.findElement(By.xpath("//span[normalize-space()='Update']")).click();
		        Thread.sleep(2000);
				if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Revenue Center Updated Successfully"))
				{
					test.log(LogStatus.PASS, "Revenue Center Updated successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Revenue Center Updated fail");
				}
				Thread.sleep(2000);
				 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Revenue_Center_Name2"));
				 Thread.sleep(2000); 
				 driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();
				 Thread.sleep(2000); 
				 driver.findElement(By.xpath("//li[@class='search-field']")).click();
				 Thread.sleep(2000); 
				 driver.findElement(By.xpath("//a[@class='search-choice-close']")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.xpath("//ul[@class='chosen-results']"));
				 driver.findElement(By.xpath("//ul[@class='chosen-results']/li[1]")).click();
				 Thread.sleep(2000);
			        driver.findElement(By.xpath("//span[normalize-space()='Update']")).click();
			       // Thread.sleep(2000);
			        WebElement ele=driver.findElement(By.xpath("//div[@role='alert']/span/span"));
			        WebDriverWait wait=new WebDriverWait(driver,50);
			        
					if(wait.until(ExpectedConditions.visibilityOf(ele)).getText().equalsIgnoreCase("Revenue Center Updated Successfully"))
					{
						test.log(LogStatus.PASS, "Another Revenue Center Updated successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Another Revenue Center Updated fail");
					}
					Thread.sleep(2000);
	
		}
		@Test(enabled=false,priority=155)
		public void Revenue_Center_Delete(WebDriver driver) throws Exception
		{	
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Revenue_Center_Name1"));
			 Thread.sleep(2000); 
			 driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
			 driver.findElement(By.linkText("Yes")).click();
			 Thread.sleep(2000); 
			 if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Revenue Center Removed Successfully"))
				{
					test.log(LogStatus.PASS, "Revenue Center Removed  successfully");
				}
				else
				{
					test.log(LogStatus.FAIL, "Revenue Center Removed fail");
				}
				Thread.sleep(2000);
				 driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
				 driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(Utility.getProperty("Settings_Store_Revenue_Center_Name2"));
				 Thread.sleep(2000); 
				 driver.findElement(By.xpath("//i[@class='fa fa-trash-o']")).click();
				 driver.findElement(By.linkText("Yes")).click();
				 Thread.sleep(2000); 
				 if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("Revenue Center Removed Successfully"))
					{
						test.log(LogStatus.PASS, "Another Revenue Center Removed  successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "Another Revenue Center Removed fail");
					}
					Thread.sleep(5000);
		}
}
