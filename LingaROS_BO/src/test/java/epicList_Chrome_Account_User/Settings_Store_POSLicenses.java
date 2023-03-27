package epicList_Chrome_Account_User;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Settings_Store_POSLicenses {

	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settins_Store_POS_Licenses");

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
			POS_License_Open_POS_License_Page(driver);
			POS_License_Refresh_Page(driver);
			POS_Licenses_Pagination(driver);
			POS_Licenses_Revert(driver);
			Thread.sleep(1500);
		}
	
		@Test(enabled=false,priority=151)
		public void POS_License_Open_POS_License_Page(WebDriver driver) throws Exception
		{
	/*		//Click the Products/Items option
			driver.findElement(By.xpath("//span[.='Products/Items']")).click();
			// Create instance of Java script executor
			JavascriptExecutor je = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[3]/ul/li[10]/a/span"));
			//Scroll the page till the Reason option present
			je.executeScript("arguments[0].scrollIntoView(true);",element); 
	        //Click the Products/Items Option		
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[3]/ul/li[10]/a/span")).click();
	*/		
			//ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id1")+"newLicense");
			Thread.sleep(5000);
			try
			{
			//Check POS Licenses page is opened or not
			if(driver.findElement(By.xpath("//a[.='POS Licenses']")).getText().equalsIgnoreCase("POS Licenses"))
			{
				test.log(LogStatus.PASS, "POS Licenses page loaded Successfully");
			
				String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
				String s="data:image/png;base64,"+scnShot;
				test.log(LogStatus.INFO,test.addScreenCapture(s));
			}
			else
			{
				test.log(LogStatus.FAIL, "POS Licenses page loaded Failed");
			
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
		}
		
		@Test(enabled=false,priority=152)
		public void POS_License_Refresh_Page(WebDriver driver) throws Exception
		{
			//ExcelDataConfig excel = new ExcelDataConfig(Utility.getProperty("Excel_Sheet_Path_For_Xpath"));
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			Thread.sleep(5000);
			//Click the refresh button
			driver.findElement(By.xpath("//a[@ng-click='ipadList()']")).click();
			if(driver.findElement(By.xpath("//a[.='POS Licenses']")).getText().equalsIgnoreCase("POS Licenses"))
			{
				test.log(LogStatus.PASS, "POS Licenses page Refreshed Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "POS Licenses page Refreshed Failed");
			}
			//Thread.sleep(3000);
		}
		@Test(priority=153,enabled=false)
		public void POS_Licenses_Pagination(WebDriver driver) throws InterruptedException
		{			
			
			try {
				if(driver.findElement(By.xpath("//tr//td[.='No records found']")).isDisplayed()) {
	            	test.log(LogStatus.INFO, "There is no POS Licenses for this store as of now, so no pagination available");
				}
				}
				catch(Exception e)
				{
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10");
			//Create the web element for Service Charge Table
			List<WebElement> results = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15");
			//Create the web element for Service Charge Table
			List<WebElement> results1 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20");
			//Create the web element for Service Charge Table
			List<WebElement> results2 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(8000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5");
			//Create the web element for Service Charge Table
			List<WebElement> results3 = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.xpath("//a[@class='btn btn-small btn-danger']"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(2000);
		}
		}
		@Test(priority=154,enabled=false)
		public void POS_Licenses_Revert(WebDriver driver) throws InterruptedException
		{			
			Thread.sleep(3000);                                          
			try {
			if(driver.findElement(By.xpath("//tr//td[.='No records found']")).isDisplayed()) {
            	test.log(LogStatus.INFO, "There is no POS Licenses for this store as of now");
			}
			}
			catch(Exception e)
			{
			Thread.sleep(2000);                                          
			driver.findElement(By.xpath("//div[@ng-table-pagination='params']//li[4]/a/span")).click();
			List<WebElement> elements = driver.findElements(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table/tbody/tr"));
		    int f = elements.size(); 
		    System.out.println(f);
		//	for (int i = 1; i <=f; i++) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table/tbody/tr["+f+"]/td[7]"));
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/div/table/tbody/tr["+f+"]/td[7]/span/a")).click();
				Thread.sleep(2000);
				driver.findElement(By.linkText("Yes")).click();
			    
			    Thread.sleep(2000);
				  if(driver.findElement(By.xpath("//div[@role='alert']/span/span")).getText().equalsIgnoreCase("License Unlinked from the device Successfully"))
					{
						test.log(LogStatus.PASS, "Licenses Unlinked Successfully");
					}
					else
					{
						test.log(LogStatus.INFO, "License unlinked Failed");
					} 
	       
		//	}
            Thread.sleep(5000);
			      }}}

		

	
	


	
	

