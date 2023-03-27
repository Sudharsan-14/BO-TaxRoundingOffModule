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
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Printer_Reroute {
	public WebDriver driver;
	
	
	ExtentReports rep = ExtentManager.getInstance();
	ExtentTest test = rep.startTest("Printer Reroute");

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
			Printer_Reroute_Open_Printer_Reroute(driver);
			Printer_Reroute_refresh_Page(driver);
			Printer_Reroute_pagination(driver);
			Printer_Reroute_Add_Printer_Reroute_By_Node(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Node_Days_Of_Week(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Node_Days_Of_Month(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Node_Date_Range(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Node_Specific_Date(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Node_Start_Date_And_End_Date_Time(driver);
			Printer_Reroute_Same_From_And_TO_Printer(driver);
			Printer_Reroute_Delete_Printer_Reroute_By_Node(driver);
			Printer_Reroute_Add_Printer_Reroute_By_Role(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Role_Days_Of_Week(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Role_Days_Of_Month(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Role_Date_Range(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Role_Specific_Date(driver);
			Printer_Reroute_Edit_Printer_Reroute_By_Role_Start_Date_And_End_Date_Time(driver);
			Printer_Reroute_Delete_Printer_Reroute_By_Role(driver);
			Printer_Reroute_Verify_Kitchen_KDS_Available_Or_Not(driver);
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
	
	@Test(enabled=false,priority=2)
	public void Printer_Reroute_Open_Printer_Reroute(WebDriver driver) throws Exception
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
		driver.findElement(By.xpath("//span[.='Printer Configuration']")).click();
		Thread.sleep(5000);
		//Check Front End Receipt page opened or not
		if(driver.findElement(By.xpath("//a[.='Front End Receipt Template']")).getText().equalsIgnoreCase("Front End Receipt Template"))
		{
			test.log(LogStatus.PASS, "Front End Receipt Template page loaded Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Front End Receipt Template page loaded Failed");
		}
		
		Thread.sleep(4000);
		
		Thread.sleep(3000);
		//Click the Printer Reroute
		driver.findElement(By.xpath("//span[.='Printer Reroute']")).click();*/

		Thread.sleep(15000);
		driver.get(Utility.getProperty("baseURL")+Utility.getProperty("store_Id")+"printerReroute");
		Thread.sleep(8000);
		try
		{
		//Check Printer Reroute page opened or not
		if(driver.findElement(By.xpath("//a[.='Printer Reroute List']")).getText().equalsIgnoreCase("Printer Reroute List"))
		{
			test.log(LogStatus.PASS, "Printer Reroute List page loaded Successfully");
		
			String scnShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String s="data:image/png;base64,"+scnShot;
			test.log(LogStatus.INFO,test.addScreenCapture(s));
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute List page loaded Failed");
		
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
	public void Printer_Reroute_refresh_Page(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(5000);
		//Click the refresh button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/a/i")).click();
		
		Thread.sleep(5000);
		//Check Printer Reroute page opened or not
		if(driver.findElement(By.xpath("//a[.='Printer Reroute List']")).getText().equalsIgnoreCase("Printer Reroute List"))
		{
			test.log(LogStatus.PASS, "Printer Reroute page refreshed Successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute page refreshed Failed");
		}
	}
	
	@Test(enabled=false,priority=4)
	public void Printer_Reroute_pagination(WebDriver driver) throws InterruptedException
	{

		
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Clear the search field
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(1000);
		
		//Click the Pagination option as 10
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[2]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 10 for Printer Reroute");
		//Create the web element for Receipt Printer Table
		List<WebElement> results = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results){						
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Printer Reroute");
		}
		Thread.sleep(4000);
		
		//Click the Pagination option as 15
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[3]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 15 for Printer Reroute");
		//Create the web element for Receipt Printer Table
		List<WebElement> results1 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results1){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Printer Reroute");
		}
		Thread.sleep(4000);
		
		//Click the Pagination option as 20
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 20 for Printer Reroute");
		//Create the web element for Receipt Printer Table
		List<WebElement> results2 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results2){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Printer Reroute");
		}
		Thread.sleep(4000);
		
		//Click the Pagination option as 5
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a/span")).click();
		test.log(LogStatus.INFO, "Now we click the Pagination option as 5 for Printer Reroute");
		//Create the web element for Receipt Printer Table
		List<WebElement> results3 = driver.findElements(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[3]/div/div/div[2]/div[2]/table"));
		for (WebElement result : results3){
			//Create the web element for delete button
		     List<WebElement> boxes = result.findElements(By.cssSelector("i.fa.fa-trash-o"));
		     //Create the variable for getting the size of the box
		     int numberOfBoxes = boxes.size();
		     System.out.println("There are totally "+numberOfBoxes+" elements available for Printer Reroute");
		}
		Thread.sleep(4000);
	
	}
	
	@Test(enabled=false,priority=5)
	public void Printer_Reroute_Add_Printer_Reroute_By_Node(WebDriver driver) throws Exception
	{
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Select the Re-routing Method
		Select for_Node = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/select")));
		for_Node.selectByVisibleText("Reroute By Node");
		
		Thread.sleep(2000);
		//Click the Add by Node Printer Re-routing button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/button")).click();
		
		Thread.sleep(10000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//a[.='Create Printer Reroute']")).getText().equalsIgnoreCase("Create Printer Reroute"))
		{
			test.log(LogStatus.PASS, "Create New Printer Reroute for Node form loaded Successully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Create New Printer Reroute for Node form loaded Fail");
		}
		Thread.sleep(2000);
		
		//Click the Node Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
		//Enter the Required Node
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("A001");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		String node = driver.findElement(By.xpath("//form[@name='printerForm']/div/div[1]/div/div/a/span")).getText();
		node.length();
		
		//Click the From Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/a")).click();
		//Enter the Required From Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("TEST FROM REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the To Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the Required To Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("TEST TO REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("All Days");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Enable or Disable the Set Time option
		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		
		Thread.sleep(3500);
		//Click the Save button
		driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();
		
		
		Thread.sleep(3500);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Saved Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Saved fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=6)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Node_Days_Of_Week(WebDriver driver) throws Exception
	{
		//node = (String)extras.getString("node");
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("TEST");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Node Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
		//Enter the Required Node
		driver.findElement(By.tagName("html")).sendKeys(Keys.ARROW_DOWN);
		//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("A002");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click the To Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the Required To Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("No Print");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Days of Week");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Days Of Week Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/div/ul")).click();
		//Enter the Required Days Of Week
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

/*		Thread.sleep(1500);
		//Enable or Disable the Set Time option
		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
				
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=7)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Node_Days_Of_Month(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Test");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Node Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
		//Enter the Required Node
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("A001");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		//Click the To Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the Required To Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("TEST TO REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Days of Month");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Required date from the calendar
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/table/tbody/tr[2]/td[6]/button")).click();
		
		//Enable or Disable the Applicable Months
		driver.findElement(By.xpath("//span[contains(.,'Applicable months')]")).click();
		
		//Click the Months field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div/ul")).click();
		//Enter the Required month
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div/ul/li/input")).sendKeys("MAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
/*		//Enable or Disable the Set Time option
		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
				
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=8)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Node_Date_Range(WebDriver driver) throws Exception
	{
		Thread.sleep(8000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("TEST");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(5000);
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Clear the from date field
		driver.findElement(By.xpath("//input[@name='fromDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='fromDate']")).sendKeys("25-Aug-2020");
		
		Thread.sleep(1500);
		//Clear the from date field
		driver.findElement(By.xpath("//input[@name='endDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='endDate']")).sendKeys("27-Aug-2020");
			
		//Enable or Disable the Applicable Months
		driver.findElement(By.xpath("//span[contains(.,'Applicable Days')]")).click();
		
		//Click the Days field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul")).click();
		//Enter the Required Day
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Enable or Disable the Set Time option
/*		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
				
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[10]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[10]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[10]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=9)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Node_Specific_Date(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("TEST");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Specific date");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		//Clear the date field
		driver.findElement(By.xpath("//input[@name='date']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='date']")).sendKeys("25-Aug-2020");
			
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=10)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Node_Start_Date_And_End_Date_Time(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("TEST");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Start date time & end date time");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Clear the from date field
		driver.findElement(By.xpath("//input[@name='fromDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='fromDate']")).sendKeys("25-Aug-2020");
		
		//Clear the to date field
		driver.findElement(By.xpath("//input[@name='endDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='endDate']")).sendKeys("27-Aug-2020");
				
		//Enable or Disable the Applicable Days option
		driver.findElement(By.xpath("//span[contains(.,'Applicable Days')]")).click();
		
		//Click the Days Of Week Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul")).click();
		//Enter the Required Days Of Week
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		Thread.sleep(2000);
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=10)
	public void Printer_Reroute_Same_From_And_TO_Printer(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("TEST");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the From Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/a")).click();
		//Enter the required From Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("TEST FROM REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
	
		Thread.sleep(5000);
		//Click the From Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the required From Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("TEST FROM REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);

		Thread.sleep(8000);
		//Check whether the validation message is enabled or not
		if(driver.findElement(By.xpath("//p[contains(.,'From & To Printers should not be same')]")).getText().equalsIgnoreCase("From & To Printers should not be same"))
		{
			test.log(LogStatus.PASS, "Validation message shows successfully, when user give the same From and To printers");
		}
		else
		{
			test.log(LogStatus.FAIL, "Validation message shows fail, when user give the same From and To printers");
		}
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("All Days");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Enable or Disable the Set Time option
/*		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Check whether the Update button is enabled or not
		if(driver.findElement(By.xpath("//div[@class='form-actions']/button[@disabled='disabled']")).isDisplayed())
		{
			test.log(LogStatus.PASS, "Update button is disabled, when user choose the same prniter in from and to option");
		}
		else
		{
			test.log(LogStatus.FAIL, "Update button is enabled, when user choose the same prniter in from and to option");
		}
		
		Thread.sleep(3000);
		//Click the Close button
		driver.findElement(By.xpath("//a[.='Cancel']")).click();
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=11)
	public void Printer_Reroute_Delete_Printer_Reroute_By_Node(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("TEST");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		
		Thread.sleep(1500);
		//Click the Yes button
		driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
		
		Thread.sleep(2500);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Deleted Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Deleted successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Deleted fail");
		}
		Thread.sleep(3000);
	}

	@Test(enabled=false,priority=12)
	public void Printer_Reroute_Add_Printer_Reroute_By_Role(WebDriver driver) throws Exception
	{
		for(int i = 0; i <= 10; i++)
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.HOME);
		}
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		Thread.sleep(5000);
		//Select the Re-routing Method
		Select for_Node = new Select(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[1]/select")));
		for_Node.selectByVisibleText("Reroute By Role");
		
		Thread.sleep(2000);
		//Click the Add by Role Printer Re-routing button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/button")).click();
		
		Thread.sleep(5000);
		//Check whether the new form loaded or not
		if(driver.findElement(By.xpath("//a[.='Create Printer Reroute']")).getText().equalsIgnoreCase("Create Printer Reroute") )
		{
			test.log(LogStatus.PASS, "Create New Printer Reroute for Role form loaded Successully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Create New Printer Reroute for Role form loaded Fail");
		}
		Thread.sleep(2000);
		
		//Click the Role Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
		//Enter the Required Node
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("Admin");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the From Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/a")).click();
		//Enter the Required From Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("TEST FROM REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the To Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the Required To Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("TEST TO REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("All Days");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Enable or Disable the Set Time option
		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		Thread.sleep(2500);
		//Click the Save button
		driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();
		
		Thread.sleep(3500);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Saved Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Saved successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Saved fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=13)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Role_Days_Of_Week(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Admin");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(8000);
		
		Thread.sleep(2000);
		
		//Click the Role Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
		//Enter the Required Node
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("Cook");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);

		//Click the To Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the Required To Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("No Print");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Days of Week");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Days Of Week Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/div/ul")).click();
		//Enter the Required Days Of Week
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(1500);
		//Enable or Disable the Set Time option
/*		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=14)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Role_Days_Of_Month(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Cook");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(8000);
		Thread.sleep(2000);
		
		//Click the Role Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
		//Enter the Required Node
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("Admin");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);

		
		//Click the To Printer Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
		//Enter the Required To Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("TEST TO REROUTE");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Days of Month");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Click the Required date from the calendar
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[5]/div/table/tbody/tr[2]/td[6]/button")).click();
		
		//Enable or Disable the Applicable Months
		driver.findElement(By.xpath("//span[contains(.,'Applicable months')]")).click();
		
		//Click the Months field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div/ul")).click();
		//Enter the Required month
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div/ul/li/input")).sendKeys("MAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[7]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Enable or Disable the Set Time option
/*		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=15)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Role_Date_Range(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Admin");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Date Range");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Clear the from date field
		driver.findElement(By.xpath("//input[@name='fromDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='fromDate']")).sendKeys("25-Aug-2020");
		
		Thread.sleep(1500);
		//Clear the from date field
		driver.findElement(By.xpath("//input[@name='endDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='endDate']")).sendKeys("27-Aug-2020");
			
		//Enable or Disable the Applicable Months
		driver.findElement(By.xpath("//span[contains(.,'Applicable Days')]")).click();
		
		//Click the Days field
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul")).click();
		//Enter the Required Day
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(1500);
		//Enable or Disable the Set Time option
/*		driver.findElement(By.xpath("//span[contains(.,'Set time')]")).click();
		
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		
		Thread.sleep(2500);
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[10]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[10]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[10]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}*/
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=16)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Role_Specific_Date(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Admin");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Specific date");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		//Clear the date field
		driver.findElement(By.xpath("//input[@name='date']")).clear();
		Thread.sleep(1000);
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='date']")).sendKeys("25-Aug-2020");
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=17)
	public void Printer_Reroute_Edit_Printer_Reroute_By_Role_Start_Date_And_End_Date_Time(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Admin");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
		Thread.sleep(10000);
		//Click the Applicable Time Period Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
		//Enter the Required Applicable Time Period
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("Start date time & end date time");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2500);
		//Clear the from date field
		driver.findElement(By.xpath("//input[@name='fromDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='fromDate']")).sendKeys("25-Aug-2020");
		
		//Clear the to date field
		driver.findElement(By.xpath("//input[@name='endDate']")).clear();
		//Enter the required date
		driver.findElement(By.xpath("//input[@name='endDate']")).sendKeys("27-Aug-2020");
				
		//Enable or Disable the Applicable Days option
		driver.findElement(By.xpath("//span[contains(.,'Applicable Days')]")).click();
		
		//Click the Days Of Week Option
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul")).click();
		//Enter the Required Days Of Week
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys("WEDNESDAY");
		//Press the Enter button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[8]/div/div/ul/li/input")).sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		//Click the down arrow mark
		//driver.findElement(By.xpath("//span[@class='fa fa-chevron-down']")).click();
		Thread.sleep(2000);
		
		//Check it is AM or PM in the Start Time Option
		if(driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).getText().equalsIgnoreCase("AM"))
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the End Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[2]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		else
		{
			Thread.sleep(2000);
			//Click the AM or PM option in the Start Time
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[9]/div/div[1]/div/table/tbody/tr[2]/td[6]/button")).click();
		}
		
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);

		Thread.sleep(3500);
		//Click the update button
		driver.findElement(By.xpath("//span[.='Update']")).click();
		
		Thread.sleep(3000);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Updated Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Updated successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Updated fail");
		}
		Thread.sleep(3000);
	}
	
	@Test(enabled=false,priority=18)
	public void Printer_Reroute_Delete_Printer_Reroute_By_Role(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Clear the Search button
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("Admin");
		
		Thread.sleep(3000);
		//Click the Delete Icon
		driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		
		Thread.sleep(1500);
		//Click the Yes button
		driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
		
		Thread.sleep(2500);
		if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Deleted Successfully"))
		{
			test.log(LogStatus.PASS, "Printer Reroute Deleted successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Printer Reroute Deleted fail");
		}
		Thread.sleep(3000);
	}

	@Test(enabled=false,priority=19)//19
	public void Printer_Reroute_Verify_Kitchen_KDS_Available_Or_Not(WebDriver driver) throws Exception
	{
		Thread.sleep(5000);
		//Click the Kitchen Printer Option
		driver.findElement(By.xpath("//span[.='Kitchen Printers']")).click();
		Thread.sleep(5000);
		
		//Clear the search text box
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("KitchenPrinters");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		
/*		Thread.sleep(3000);
    	Select fontSize = new Select(driver.findElement(By.xpath("//div/select[@ng-model='kitchenPrinter.type']")));
		fontSize.selectByVisibleText("KDS");
		
		//Click the Update option
		driver.findElement(By.xpath("//span[.='Update']")).click();*/
		
		Thread.sleep(5000);
		//Click the Type Option of Kitchen Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/a")).click();
		//Enter the Required Type
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).sendKeys("Printer");
		//Press the Enter Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);

		
		Thread.sleep(5000);
		//Click the Type Option of Kitchen Printer
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/a")).click();
		//Enter the Required Type
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).sendKeys("KDS");
		//Press the Enter Button
		driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		
		//Page down
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(3000);
		//Click the update button
		driver.findElement(By.id("saveKitchen")).click();
		Thread.sleep(5000);
		
		//Clear the search text box
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
		//Enter the required Keyword
		driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("KitchenPrinters");
		
		Thread.sleep(2000);
		//Click the Edit Icon
		driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();

		Thread.sleep(5000);
		if(driver.findElement(By.xpath("//span[.='KDS']")).isDisplayed())
		{
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			driver.findElement(By.tagName("html")).sendKeys(Keys.END);
			
			Thread.sleep(3000);
			//Click the Close button
			driver.findElement(By.xpath("//a[.='Close']")).click();
			
			//Click the Printer Reroute option
			driver.findElement(By.xpath("//span[.='Printer Reroute']")).click();
			
			Thread.sleep(3000);
			Thread.sleep(2000);
			//Click the Add by Role Printer Re-routing button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/button")).click();

			
			Thread.sleep(5000);
			//Click the From Printer Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/a")).click();
			Thread.sleep(3000);
			try
			{
				//Check Whether the Printer is available or not
				if(driver.findElement(By.xpath("//li[.='KitchenPrinters']")).isDisplayed())
				{
					test.log(LogStatus.FAIL, "Kitchen Printer(KDS) Reflected but it is KDS");
				}
			}
			catch (Exception e1)
			{
				test.log(LogStatus.PASS, "Kitchen Printer(KDS) Not Reflected Successfully");
			}
			
			//Click the Close button
			driver.findElement(By.xpath("//a[.='Cancel']")).click();
			Thread.sleep(5000);
			
			Thread.sleep(3000);
			//Click the Kitchen Printer Option
			driver.findElement(By.xpath("//span[.='Kitchen Printers']")).click();
			Thread.sleep(5000);
			
			//Clear the search text box
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
			//Enter the required Keyword
			driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("KitchenPrinters");
			
			Thread.sleep(2000);
			//Click the Edit Icon
			driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
			Thread.sleep(5000);
			
			//Click the Type Option of Kitchen Printer
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/a")).click();
			//Enter the Required Type
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).sendKeys("Printer");
			//Press the Enter Button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/form/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
			
			//Page down
			driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
			driver.findElement(By.tagName("html")).sendKeys(Keys.PAGE_DOWN);
			
			Thread.sleep(3000);
			//Click the update button
			driver.findElement(By.id("saveKitchen")).click();
			
			Thread.sleep(20000);
			//Click the Printer Reroute option
			driver.findElement(By.xpath("//span[.='Printer Reroute']")).click();
			
			Thread.sleep(3000);
			Thread.sleep(2000);
			//Click the Add by Role Printer Re-routing button
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[2]/div/div[2]/button")).click();

			
			Thread.sleep(5000);
			//Click the From Printer Option
			driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/a")).click();
			Thread.sleep(3000);
			
			try
			{
				//Check Whether the Printer is available or not
				if(driver.findElement(By.xpath("//li[.='KitchenPrinters']")).isDisplayed())
				{
					test.log(LogStatus.PASS, "Kitchen Printer Reflected Successfully");
					
					//Click the Node Option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/a")).click();
					//Enter the Required Node
					//driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys("A001");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[1]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					
					//Click the From Printer Option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/a")).click();
					//Enter the Required From Printer
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys("KitchenPrinters");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[2]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					
					//Click the To Printer Option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/a")).click();
					//Enter the Required To Printer
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys("TEST TO REROUTE");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[3]/div/div/div/div/input")).sendKeys(Keys.ENTER);
					
					//Click the Applicable Time Period Option
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/a")).click();
					//Enter the Required Applicable Time Period
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys("All Days");
					//Press the Enter button
					driver.findElement(By.xpath("//div[@id='main-container']/div[2]/div/div/div[2]/div[4]/div/div/div/div[2]/div/form/div[1]/div[4]/div/div/div/div/input")).sendKeys(Keys.ENTER);
											
					Thread.sleep(2500);
					//Click the Save button
					driver.findElement(By.xpath("//button[@class='btn btn-small btn-success']")).click();
					
					Thread.sleep(3500);
					if(driver.findElement(By.cssSelector("span > span.ng-scope")).getText().equalsIgnoreCase("Saved Successfully"))
					{
						test.log(LogStatus.PASS, "Printer Reroute Saved successfully after Change the KDS TO Printer in Kitchen Printer");
						
						Thread.sleep(5000);
						//Clear the Search button
						driver.findElement(By.xpath("//input[@ng-model='searchText']")).clear();
						//Enter the required Keyword
						driver.findElement(By.xpath("//input[@ng-model='searchText']")).sendKeys("KitchenPrinters");
						
						Thread.sleep(2000);
						//Click the Edit Icon
						driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
						
						Thread.sleep(1500);
						//Click the Yes button
						driver.findElement(By.xpath("//a[@class='btn btn-small btn-success']")).click();
						
						Thread.sleep(2500);
					}
					else
					{
						test.log(LogStatus.FAIL, "Printer Reroute Saved fail after Change the KDS TO Printer in Kitchen Printer");
					}
					Thread.sleep(3000);

				}
			}
			catch (Exception e1)
			{
				test.log(LogStatus.FAIL, "Kitchen Printer not reflected");
			}
			
			//Click the Close button
			//driver.findElement(By.xpath("//a[.='Cancel']")).click();
			Thread.sleep(5000);
		}
	}

}
