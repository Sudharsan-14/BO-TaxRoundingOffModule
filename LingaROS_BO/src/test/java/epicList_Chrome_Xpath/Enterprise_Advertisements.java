package epicList_Chrome_Xpath;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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


public class Enterprise_Advertisements {
	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Enterprise_Advertisement");
	
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
			Advertisement_page(driver);
			Advertisement_Refreshpage(driver);
			Advertisement_Setting_Method_pagination(driver);
			Advertisement_Newcreationpage(driver);
			Advertisement_New_Always(driver);
			Advertisement_Close(driver);
			Advertisement_Edit_Daysofweek(driver);
			Advertisement_delete(driver);
			Advertisement_deActive(driver);
			Advertisement_AS_DaysOfMonth(driver);
			Advertisement_AS_DateRange(driver);
			Advertisement_AS_Specific_date(driver);
			Advertisement_AS_Startdatetime_enddatetime(driver);
			Thread.sleep(1500);
		}

		@Test(enabled=false,priority=28)
		public void Advertisement_page(WebDriver driver) throws Exception
		{
			/*//Click the My Stores option
			driver.findElement(By.xpath("//span[.='My Stores']")).click();
			
			
			JavascriptExecutor je1 = (JavascriptExecutor) driver;
			//Identify the WebElement which will appear after scrolling down
			WebElement element1 = driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[12]/a/span"));
			//Scroll the page till the Reason option present
			je1.executeScript("arguments[0].scrollIntoView(true);",element1); 
			//Wait for 30 seconds
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(3000);
		    //Click the Advertisement Option		
			driver.findElement(By.xpath("//div[@id='side-bar']/div/div[1]/div[2]/div/ul/li[12]/a/span")).click();
			
			*/
			//Enter the URl
			Thread.sleep(3000);
			driver.get(Utility.getProperty("Enterprise_Base_URL")+"advertisements");
			
			Thread.sleep(5000);
			//Check Advertisement page opened or not
			if(driver.findElement(By.xpath("//a[.='Advertisement']")).getText().equalsIgnoreCase("Advertisement"))

			{
				test.log(LogStatus.PASS, "Advertisement page loaded Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement page loaded Failed");
			}
			
			Thread.sleep(3000);
			
		}
		
		@Test(enabled=false,priority=29)
		public void Advertisement_Refreshpage(WebDriver driver) throws InterruptedException
		{
		
		
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[1]/div/div/div/a/i")).click();
			Thread.sleep(3000);
			
			//Check weather the page is refreshed or not
			if(driver.findElement(By.xpath("//a[.='Advertisement']")).getText().equalsIgnoreCase("Advertisement"))

			{
				test.log(LogStatus.PASS, "Advertisement Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement Page refreshed successfully");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=30)
		public void Advertisement_Setting_Method_pagination(WebDriver driver) throws InterruptedException
		{
			
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
					
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(3000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Advertisement");
			//Create the web element for Advertisement Table
			List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available");
			}
			Thread.sleep(3000);
		}
				
		@Test(enabled=false,priority=31)
		public void Advertisement_Newcreationpage(WebDriver driver) throws InterruptedException
		{
			//Scroll the web page
		    for(int i=1; i <= 15; i++)
		    {
		    	driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_UP);
		    	Thread.sleep(1000);
		    }
		    
					
			//Click Advertisement creation button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[1]/div/div/div/button")).click();
		
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[1]/h3")).getText().equalsIgnoreCase("Advertisement"))
			{
				test.log(LogStatus.PASS, "New Advertisement Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Advertisement Page refreshed successfully");
			}
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=32)
		public void Advertisement_New_Always(WebDriver driver) throws Exception
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
			//Clear name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
			Thread.sleep(2000);
			//Enter name field
	     	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name"));
			
	     	//Click the Applicable Time Period option
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
	 		//Enter the required Time Period
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Always");
	 		//Press the Enter button
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	 		
	 		//Click the Level  option
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/a")).click();
	 		//Enter the required Level
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("Stores");
	 		//Press the Enter button
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	 		
	 		//Select the Store
	 		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul")).click();
	 		Thread.sleep(3000);
	 		//Click remove store
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul/li[1]/a")).click();	
	 		
	 		Thread.sleep(2000);
	 		//Select the Store
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul")).click();
	 		//Enter the required stores
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul/li/input")).sendKeys(Utility.getProperty("Advertisement_Store"));
	 		//Enter the required stores
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
	 	
	 		
	 		//Click the select image button
	 		//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
			//driver.findElement(By.xpath("//span[@id='denomination']")).click();
			
	 		Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.xpath("//input[@id='advertisementImage']")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath("//a[.='Remove']")).click();
			
			Thread.sleep(2000);
			//Choose the required image
			driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			Thread.sleep(5000);
			     //Click the Save button
					driver.findElement(By.xpath("//button[@type='submit']")).click();
					
					Thread.sleep(5000);
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement saved successfully"))
					{
						test.log(LogStatus.PASS, "New Advertisement saved successfully");
					}
					else
					{
						test.log(LogStatus.FAIL, "New Advertisement saved failed");
					}
					Thread.sleep(3000);
		}

		@Test(enabled=false,priority=33)
		public void Advertisement_Close(WebDriver driver) throws Exception
		{
			Thread.sleep(3000);
			//Click Advertisement creation button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[1]/div/div/div/button")).click();
			
			Thread.sleep(2000);
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[1]/h3")).getText().equalsIgnoreCase("Advertisement"))
			{
				test.log(LogStatus.PASS, "New Advertisement Page refreshed successfully for close button");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Advertisement Page refreshed successfully for close button");
			}
		
			//Clear name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
			Thread.sleep(2000);
			//Enter name field
	     	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name"));
			
	     	//Click the Applicable Time Period option
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
	 		//Enter the required Time Period
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Always");
	 		//Press the Enter button
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	 		
	 		//Click the Level  option
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/a")).click();
	 		//Enter the required Level
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("Stores");
	 		//Press the Enter button
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	 		
	 		//Select the Store
	 		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul")).click();
	 		Thread.sleep(3000);
	 		//Click remove store
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul/li[1]/a")).click();	
	 		
	 		Thread.sleep(2000);
	 		//Select the Store
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul")).click();
	 		//Enter the required stores
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul/li/input")).sendKeys(Utility.getProperty("Advertisement_Store"));
	 		//Enter the required stores
	 		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
	 	
	 		
	 		//Click the select image button
	 		//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
			//driver.findElement(By.xpath("//span[@id='denomination']")).click();
			
	 		Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath("//a[.='Remove']")).click();
			
			Thread.sleep(2000);
			//Choose the required image
			driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			Thread.sleep(3000);
			     //Click the Close  button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			Thread.sleep(5000);
			//Check Advertisement page opened or not
			if(driver.findElement(By.xpath("//a[.='Advertisement']")).getText().equalsIgnoreCase("Advertisement"))

			{
				test.log(LogStatus.PASS, "Advertisement page loaded Successfully for close button action");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement page loaded Failed for close button action");
			}
			
			Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=34)
		public void Advertisement_Edit_Daysofweek(WebDriver driver) throws Exception
		{
			//Clear the search field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Advertisement_name"));
			//Click the Edit icon
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			Thread.sleep(3000);
			
			
			//Clear name field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
			Thread.sleep(2000);
			//Enter name field
		 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name")+"1");
			
			//Click the Applicable Time Period Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
			//Enter the required Time Period
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Days of Week");
			//Press Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			Thread.sleep(5000);
			//Click the Days of a week Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/ul")).click();
			//Enter the required Day
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
			//Press Enter Key
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
			
			//Enable or Disable the Restriction Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/label/span")).click();

			Thread.sleep(1000);
			//Check it is AM or PM in the Start Time Option
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the End Time
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
			}
			else
			{
				Thread.sleep(2000);
				//Click the AM or PM option in the Start Time
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
			}
			
			Thread.sleep(3000);
			//Click the Level  option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/a")).click();
			//Enter the required Level
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/div/div/input")).sendKeys("Stores");
			//Press the Enter button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Select the Store
			//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/div/ul")).click();
			Thread.sleep(3000);
			//Click remove store
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div/ul/li[1]/a")).click();	
			
			Thread.sleep(2000);
			//Select the Store
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div/ul")).click();
			//Enter the required stores
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div/ul/li/input")).sendKeys(Utility.getProperty("Advertisement_Store"));
			//Enter the required stores
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
			
			//Click the select image button
			//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
			//driver.findElement(By.xpath("//span[@id='denomination']")).click();
			
			Thread.sleep(3000);
			//Choose the required image
			driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			
			Thread.sleep(2000);
			//Click the Remove image
			driver.findElement(By.xpath("//a[.='Remove']")).click();
			
			Thread.sleep(2000);
			//Choose the required image
			driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
			Thread.sleep(4000);
			     //Click the Save button
					driver.findElement(By.xpath("//button[@type='submit']")).click();
					
					Thread.sleep(5000);
					if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
					{
						test.log(LogStatus.PASS, "Advertisement Updated Successfully for Days of week");
					}
					else
					{
						test.log(LogStatus.FAIL, "Advertisement Updated failed for Days of week");
					}
					Thread.sleep(3000);
		}
		
		@Test(enabled=false,priority=35)
		public void Advertisement_delete(WebDriver driver) throws Exception
		{
			
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
			//Enter the required keyword
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Advertisement_name")+"1");
			//Click the Delete button
			driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			Thread.sleep(3000);
			//Click the yes button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			Thread.sleep(3000);
			//Check the menu item deleted or not 
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Inactivated Successfully"))
			{
				test.log(LogStatus.PASS, "Advertisement deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement deleted Successfully Failed");
			}
			Thread.sleep(5000);
		}
			
		@Test(enabled=false,priority=36)
		public void Advertisement_deActive(WebDriver driver) throws Exception
		{
			
			//Click the DeActive button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[1]/input")).click();
			
			Thread.sleep(3000);
			//Click the success button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[3]/div[2]/a/i")).click();
			
			
			Thread.sleep(3000);
			//Click the yes button
			driver.findElement(By.cssSelector("a.btn.btn-small.btn-success")).click();
			
			Thread.sleep(3000);
			//Check the menu item activated or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Activated Successfully"))
			{
				test.log(LogStatus.PASS, "Advertisement Activated Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Advertisement Activated Successfully Failed");
			}
			//Clear the search field
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
			
			Thread.sleep(5000);
			
		}
			
		@Test(enabled=false,priority=37)
		public void Advertisement_AS_DaysOfMonth(WebDriver driver) throws Exception
		{
				//Clear the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Advertisement_name")+"1");
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name")+"2");

				//Click the Applicable Time Period Option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Days of Month");
				//Press Enter Key
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Click the Required Date from the Days of a Month
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[3]/div/table/tbody/tr[2]/td[3]/button")).click();

				//Enable or Disable the Restriction Time
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/label/span")).click();
				//Click the Months OIption
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div/ul")).click();
				//Enter the Required Month
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div/ul/li/input")).sendKeys("MAY");
				//Press the Enter Key
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
				
				//Enable or Disable the Restriction Time
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/label/span")).click();

				Thread.sleep(1000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath("//a[.='Remove']")).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for Days of MONTH");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for Days of MONTH");
						}
						Thread.sleep(3000);
			}
			
		@Test(enabled=false,priority=38)
		public void Advertisement_AS_DateRange(WebDriver driver) throws Exception
		{
				//Clear the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Advertisement_name")+"2");
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name")+"3");
				
			 	//Click the Applicable Time Period Option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Date Range");
				//Press Enter Key
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Clear the From Date Field
				driver.findElement(By.name("fromDate")).clear();
				//Enter the From Date
				driver.findElement(By.name("fromDate")).sendKeys("16-May-2020");
				//Clear the To Date Field
				driver.findElement(By.name("endDate")).clear();
				//Enter the To Date
				driver.findElement(By.name("endDate")).sendKeys("20-May-2020");
			
			
				Thread.sleep(3000);
				//Enable or Disable Restriction days
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/label/span")).click();
				//Click the DAys of a week
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/ul")).click();
				//Enter the Required Day
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/ul/li/input")).sendKeys("FRIDAY");
				//Press the Enter Key
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
				
				//Enable or Enable the Restriction Time
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/label/span")).click();

				Thread.sleep(1000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[8]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[8]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[8]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				
				
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath("//a[.='Remove']")).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for DateRange");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for DateRange");
						}
						Thread.sleep(3000);
			}
		
		@Test(enabled=false,priority=39)
		public void Advertisement_AS_Specific_date(WebDriver driver) throws Exception
		{
				//Clear the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Advertisement_name")+"3");
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name")+"4");
				
				//Click the Applicable Time Period Option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Specific date");
				//Press Enter Key
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Clear the Date Field
				driver.findElement(By.name("date")).clear();
				//Enter the Date
				driver.findElement(By.name("date")).sendKeys("16-May-2020");

				//Enable or Disable the Restriction Time
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[4]/div/label/span")).click();

				Thread.sleep(1000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath("//a[.='Remove']")).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for Specific date");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for Specific date");
						}
						Thread.sleep(3000);
			}
			
		@Test(enabled=false,priority=40)
		public void Advertisement_AS_Startdatetime_enddatetime(WebDriver driver) throws Exception
		{
				//Clear the search field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).clear();
				//Enter the required keyword
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div[2]/div/div/div/div[2]/div[1]/div[2]/label/input")).sendKeys(Utility.getProperty("Advertisement_name")+"4");
				//Click the Edit icon
				driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
				Thread.sleep(3000);
				
				
				//Clear name field
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).clear();
				Thread.sleep(2000);
				//Enter name field
			 	driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys(Utility.getProperty("Advertisement_name")+"5");
				
				//Click the Applicable Time Period Option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/a")).click();
				//Enter the required Time Period
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("Start date time & end date time");
				//Press Enter Key
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
				
				Thread.sleep(5000);
				//Clear the From Date Field
				driver.findElement(By.name("fromDate")).clear();
				//Enter the From Date
				driver.findElement(By.name("fromDate")).sendKeys("16-May-2020");
				//Clear the To Date Field
				driver.findElement(By.name("endDate")).clear();
				//Enter the To Date
				driver.findElement(By.name("endDate")).sendKeys("20-May-2020");
				
				//Enable or Disable the Restriction days option
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[5]/div/label/span")).click();
				
				//Click the Days of a Week Function
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/ul")).click();
				//Enter the Required day
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/ul/li/input")).sendKeys("FRIDAY");
				//Press the Enter button
				driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[6]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

				Thread.sleep(2000);
				//Check it is AM or PM in the Start Time Option
				if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the End Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				else
				{
					Thread.sleep(2000);
					//Click the AM or PM option in the Start Time
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div/div/div/div/div[2]/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
				}
				
				
				//Click the select image button
				//driver.findElement(By.xpath("//id[.='advertisementImage']")).click();
				//driver.findElement(By.xpath("//span[@id='denomination']")).click();
				
				Thread.sleep(3000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				
				Thread.sleep(2000);
				//Click the Remove image
				driver.findElement(By.xpath("//a[.='Remove']")).click();
				
				Thread.sleep(2000);
				//Choose the required image
				driver.findElement(By.id("advertisementImage")).sendKeys(Utility.getProperty("Settings_advertisementImage_Store_Image_Path"));
				Thread.sleep(4000);
				     //Click the Save button
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						
						Thread.sleep(5000);
						if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[1]/div/span/span")).getText().equalsIgnoreCase("Advertisement Updated Successfully"))
						{
							test.log(LogStatus.PASS, "Advertisement Updated Successfully for Start date time & end date time");
						}
						else
						{
							test.log(LogStatus.FAIL, "Advertisement Updated failed for Start date time & end date time");
						}
						Thread.sleep(3000);
			}

}
