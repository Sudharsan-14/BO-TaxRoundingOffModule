package epicList_Chrome_Xpath;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


public class Settings_Wait_List {


	public WebDriver driver;
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Settings_Wait_List");
	
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
			Wait_List_Settings_Method_open(driver);
			Wait_List_Settings_Method_refresh(driver);
			Wait_List_Settings_Method_pagination(driver);
			Wait_List_Settings_Method_add(driver);
			Wait_List_Settings_Method_delete(driver);
			Wait_List_Settings_Method_close_Button(driver);
			Wait_List_Reason_Settings_Method_pagination(driver);
			Wait_List_Reason_Settings_Method_add(driver);
			Wait_List_Reason_Settings_Method_delete(driver);
			Wait_List_Reason_Settings_Method_close_Button(driver);
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
	
	@Test(enabled=false,priority=32)
	public void Wait_List_Settings_Method_open(WebDriver driver) throws Exception
	{

			
			Thread.sleep(3000);
			//Enter the URL
			driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"waitList");
			
			Thread.sleep(3000);
			
			//Check weather the Wait List page is loaded or not
			if(driver.findElement(By.xpath("//a[.='Wait List']")).getText().equalsIgnoreCase("Wait List"))
			{
				test.log(LogStatus.PASS, "Wait List page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List page loaded fail");
			}

			Thread.sleep(3000);

		}	
		
	@Test(enabled=false,priority=33)
	public void Wait_List_Settings_Method_refresh(WebDriver driver) throws Exception
		{
			//Click the refresh button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/a/i")).click();
			Thread.sleep(3000);
			
			//Check weather the page is refreshed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[1]/div/h3")).getText().equalsIgnoreCase("Wait List"))
			{
				test.log(LogStatus.PASS, "Wait List Page refreshed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List Page refreshed successfully");
			}
			
		}
		
	@Test(enabled=false,priority=34)
	public void Wait_List_Settings_Method_pagination(WebDriver driver) throws InterruptedException
	{
	
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Wait List");
			//Create the web element for Wait List Table
			List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Wait List");
			//Create the web element for Wait List Table
			List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Wait List");
			//Create the web element for Wait List Table
			List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Wait List");
			//Create the web element for Wait List Table
			List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List");
			}
			Thread.sleep(3000);
		}
		
	@Test(enabled=false,priority=35)
	public void Wait_List_Settings_Method_add(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Add Wait List button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Wait List']")).getText().equalsIgnoreCase("New Wait List"))
			{
				test.log(LogStatus.PASS, "New Wait List form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Wait List form or page loaded fail");
			}
			
			
			Thread.sleep(2000);
	        List<WebElement> rows=driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[2]"));
	        rows.size();
	        int large = 0;
	        for(int i = 1; i<= rows.size(); i++)
	        {
	        	String max_Seat_Value = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[2]")).getText();
	        	int seat_Value = Integer.parseInt(max_Seat_Value);
	        	
	        	if(seat_Value > large)
	        	{
	        		large  = seat_Value;
	        	}
	        	
	        }
	        
	        System.out.println("Maximum Seat number is : "+large);
	        
	        int min_Value = large+1;
	        int max_Value = min_Value+2;
	        
	        Thread.sleep(2000);
	        //Clear the minimum seat value
	        driver.findElement(By.name("minSeat")).clear();
	        //Enter the minimum seat value
	        driver.findElement(By.name("minSeat")).sendKeys(""+min_Value);
	        
	        Thread.sleep(2000);
	        //Clear the maximum seat value
	        driver.findElement(By.name("maxSeat")).clear();
	        //Enter the maximum seat value
	        driver.findElement(By.name("maxSeat")).sendKeys(""+max_Value);
			
			Thread.sleep(2000);
			//Select the required hour
			Select hour = new Select(driver.findElement(By.xpath("//select[@ng-model='selectedHrs']")));
			hour.selectByVisibleText("00");
			
			Thread.sleep(2000);
			//Select the required min
			Select min = new Select(driver.findElement(By.xpath("//select[@ng-model='selectedMins']")));
			min.selectByVisibleText("15");
			
			Thread.sleep(5000);
			//Click the Save button
			driver.findElement(By.xpath("//button[@type='submit']")).click();


			Thread.sleep(2000);
			//Check weather the new denomination form saved or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Wait List Added Succesfully"))
			{
				test.log(LogStatus.PASS, "Wait List Added Succesfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List Added fail");
			}
		}
		
	@Test(enabled=false,priority=36)
	public void Wait_List_Settings_Method_delete(WebDriver driver) throws Exception
		{     
			Thread.sleep(2000);
	        List<WebElement> rows=driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table/tbody/tr/td[2]"));
	        rows.size();
	        int large = 0;
	        for(int i = 1; i<= rows.size()-1; i++)
	        {
	        	String max_Seat_Value = driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table/tbody/tr["+i+"]/td[2]")).getText();
	        	int seat_Value = Integer.parseInt(max_Seat_Value);
	        	
	        	if(seat_Value > large)
	        	{
	        		large  = seat_Value;
	        	}
	        	
	        }
	        
	        int min_Value = large+1;
	        int max_Value = min_Value+2;
	        
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//enter the keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys(""+max_Value);
			
			
			Thread.sleep(3000);
			//Click the Delete button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[1]/div/div/div[2]/div[2]/table/tbody/tr[1]/td[4]/span/a")).click();
		
		
			Thread.sleep(2500);
			//Check the denomination deleted or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Wait List Removed successfuly"))
			{
				test.log(LogStatus.PASS, "Wait List deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List deleted Failed");
			}
			Thread.sleep(3000);
			
			
		}
		
	@Test(enabled=false,priority=37)
	public void Wait_List_Settings_Method_close_Button(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			
			Thread.sleep(2000);
			//Click the Add Wait List button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Wait List']")).getText().equalsIgnoreCase("New Wait List"))
			{
				test.log(LogStatus.PASS, "New Wait List form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Wait List form or page loaded fail");
			}
			

			
			Thread.sleep(1000);
			//Click the Close button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			Thread.sleep(2000);
			
			//Check weather the new Wait List form closed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div/div/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Wait List form Closed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List form Closed fail");
			}
			if(Utility.getProperty("Product").equalsIgnoreCase("UPOS")){}else{Thread.sleep(5000);watchTutorial(driver);Thread.sleep(5000);}
		}
		
	@Test(enabled=false,priority=38)
	public void Wait_List_Reason_Settings_Method_pagination(WebDriver driver) throws InterruptedException
		{			
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchReasonText']")).clear();
			Thread.sleep(1000);
			
			//Click the Pagination option as 10
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Wait List Reason");
			//Create the web element for Wait List reason Table
			List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results){						
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List Reason");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 15
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Wait List Reason");
			//Create the web element for Wait List reason Table
			List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results1){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List Reason");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 20
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Wait List Reason");
			//Create the web element for Wait List reason Table
			List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results2){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List Reason");
			}
			Thread.sleep(3000);
			
			//Click the Pagination option as 5
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
			test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Wait List Reason");
			//Create the web element for Wait List REason Table
			List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/table"));
			for (WebElement result : results3){
				//Create the web element for delete button
			     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
			     //Create the variable for getting the size of the box
			     int numberOfBoxes = boxes.size();
			     System.out.println("There are totally "+numberOfBoxes+" elements available for Wait List Reason");
			}
			Thread.sleep(3000);
		}

	@Test(enabled=false,priority=39)
	public void Wait_List_Reason_Settings_Method_add(WebDriver driver) throws Exception
		{
			Thread.sleep(2000);
			//Click the Add Wait List Reason button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Wait List Reason']")).getText().equalsIgnoreCase("New Wait List Reason"))
			{
				test.log(LogStatus.PASS, "New Wait List Reason form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Wait List Reason form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the reason
			driver.findElement(By.name("waitListReason")).clear();
			Thread.sleep(3000);
			//Enter the reason
			driver.findElement(By.name("waitListReason")).sendKeys("Cleaning Process is going on");
			
			Thread.sleep(3000);
			//Click the Save button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div[2]/form/div[2]/div/button")).click();

			Thread.sleep(2500);
			//Check weather the new denomination form saved or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Wait List Reason Added Succesfully"))
			{
				test.log(LogStatus.PASS, "Wait List Reason Added Succesfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List Reason Added fail");
			}
			Thread.sleep(3000);
		}
		
	@Test(enabled=false,priority=40)
	public void Wait_List_Reason_Settings_Method_delete(WebDriver driver) throws Exception
		{     

	        
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchReasonText']")).clear();
			Thread.sleep(2000);
			//Enter the keyword
			driver.findElement(By.xpath("//input[@ng-model='searchReasonText']")).sendKeys("Cleaning Process is going on");
			
			Thread.sleep(3000);
		 	//Click the Delete button
		   driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/span/a")).click();;
			
			Thread.sleep(2000);
			//Check the denomination deleted or not
			if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Wait List Reason Removed successfuly"))
			{
				test.log(LogStatus.PASS, "Wait List Reason deleted Successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List Reason deleted Failed");
			}
			Thread.sleep(3000);
			
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchReasonText']")).clear();
			
		}
		
	@Test(enabled=false,priority=41)
	public void Wait_List_Reason_Settings_Method_close_Button(WebDriver driver) throws Exception
		{
			
			Thread.sleep(3000);
			//Clear the search field
			driver.findElement(By.xpath("//input[@ng-model='searchReasonText']")).clear();
			Thread.sleep(2000);
			//Click the Add Wait List Reason button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/button")).click();
			Thread.sleep(2000);
			
			//Check weather the new form loaded or not
			if(driver.findElement(By.xpath("//span[.='New Wait List Reason']")).getText().equalsIgnoreCase("New Wait List Reason"))
			{
				test.log(LogStatus.PASS, "New Wait List Reason form or page loaded successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "New Wait List Reason form or page loaded fail");
			}
			
			Thread.sleep(2000);
			//Clear the reason
			driver.findElement(By.name("waitListReason")).clear();
			//Enter the reason
			driver.findElement(By.name("waitListReason")).sendKeys("Cleaning Process is going on");

			Thread.sleep(3000);
			//Click the Close button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div[3]/div/div/div[2]/form/div[2]/div/a")).click();
			Thread.sleep(2000);
			
			//Check weather the new Wait List form closed or not
			if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div[2]/button")).isDisplayed())
			{
				test.log(LogStatus.PASS, "Wait List Reason form Closed successfully");
			}
			else
			{
				test.log(LogStatus.FAIL, "Wait List Reason form Closed fail");
			}
				
				Thread.sleep(3000);
		
		}
}
